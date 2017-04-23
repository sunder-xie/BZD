package com.bizideal.whoami.core.meeting.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.core.meeting.biz.MeetingInfoBiz;
import com.bizideal.whoami.core.meeting.mapper.MeetingGroupMapper;
import com.bizideal.whoami.core.meeting.mapper.MeetingHallMapper;
import com.bizideal.whoami.core.meeting.mapper.MeetingInfoMapper;
import com.bizideal.whoami.core.meeting.mapper.MeetingUserGroupMapper;
import com.bizideal.whoami.core.meeting.mapper.SubMeetingInfoMapper;
import com.bizideal.whoami.croe.service.impl.BaseOpBizImpl;
import com.bizideal.whoami.enums.DelFlagEnum;
import com.bizideal.whoami.facade.meeting.dto.MeetingInfoDto;
import com.bizideal.whoami.facade.meeting.dto.MeetingInfoSignUpDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.MeetingUserGroup;
import com.bizideal.whoami.facade.meeting.enums.MeetingInfoEnum;
import com.bizideal.whoami.facade.meeting.enums.MeettingExceptionEnums;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.im.facade.ChatGroupsFacade;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingInfoBizImpl
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-04 16:25:39
 */
@Service("meetingInfoBiz")
public class MeetingInfoBizImpl extends BaseOpBizImpl<MeetingInfo> implements MeetingInfoBiz{

	private static final Logger LOGGER = LoggerFactory.getLogger(MeetingInfoBizImpl.class);
	//会议信息Mapper
	@Autowired
	private MeetingInfoMapper meetingInfoMapper;
	//子会议信息Mapper
	@Autowired
	private SubMeetingInfoMapper subMeetingInfoMapper;
	//会厅信息Mapper
	@Autowired
	private MeetingHallMapper meetingHallMapper;
	//IM群组管理接口
	@Autowired
	private ChatGroupsFacade chatGroupsFacade;
	//会议群Mapper
	@Autowired
	private MeetingGroupMapper meetingGroupMapper;
	//会议群和用户关联Mapper
	@Autowired
	private MeetingUserGroupMapper meetingUserGroupMapper;
	
	@Override
	public Integer insertMeetingInfo(MeetingInfo meetingInfo) throws CustomException{
		try{
			//判断会议厅是否合法
			if(null!=meetingInfo.getHallId()){
				MeetingHall hall=meetingHallMapper.selectByPrimaryKey(meetingInfo.getHallId());
				if(null==hall || hall.getHallName()==null){
					throw new Exception("{'code':'"+
							 MeettingExceptionEnums.MeetingInfoBizImpl_insertMeetingInfo_01_ERROR.getCode()+"','msg':'"+
							 MeettingExceptionEnums.MeetingInfoBizImpl_insertMeetingInfo_01_ERROR.getMsg()+"'}");
				}
			}
			//查找是否有重名的会议(只对主会议做校验)
			meetingInfo.setMeeParentId(0);
			MeetingInfo temp=new MeetingInfo();
			temp.setMeeName(meetingInfo.getMeeName());
			temp.setMeeParentId(0);
			List<MeetingInfo> list=meetingInfoMapper.select(temp);
			if(null!=list && list.size()>0){
				throw new Exception("{'code':'"+
						 MeettingExceptionEnums.MeetingInfoBizImpl_insertMeetingInfo_02_ERROR.getCode()+"','msg':'"+
						 MeettingExceptionEnums.MeetingInfoBizImpl_insertMeetingInfo_02_ERROR.getMsg()+"'}");
			}
			meetingInfo.setCreateTime(System.currentTimeMillis());
			
			if(meetingInfoMapper.insertSelective(meetingInfo)==1){
				
				/* 创建聊天群 */
				ChatGroup chatGroup = new ChatGroup();
				chatGroup.setGroupname(meetingInfo.getMeeName() + " 的聊天群");
				chatGroup.setDescription(meetingInfo.getMeeName() + " 的聊天群\n" + meetingInfo.getMeeIntro());
				chatGroup.setIsPublic(false);
				chatGroup.setMaxusers(99999);
				chatGroup.setOwner(meetingInfo.getCreateUser());
				chatGroup.setApproval(false);
				
				ObjectMapper mapper = new ObjectMapper();
				String createString = chatGroupsFacade.creatChatGroups(chatGroup);
				if (StringUtils.isBlank(createString) || !"200".equals(mapper.readTree(createString).get("statusCode").asText())) {
					throw new Exception("{'code':'"+
							 MeettingExceptionEnums.MeetingInfoBizImpl_insertMeetingInfo_03_ERROR.getCode()+"','msg':'"+
							 MeettingExceptionEnums.MeetingInfoBizImpl_insertMeetingInfo_03_ERROR.getMsg()+"'}");
				}

				// 插入chat_group
				MeetingGroups meetingGroups = new MeetingGroups();
				String groupId = mapper.readTree(createString).get("data").get("groupid").asText();
				meetingGroups.setGroupId(groupId);
				meetingGroups.setMeeId(meetingInfo.getMeeId());
				meetingGroups.setGroupDescription(chatGroup.getDescription());
				meetingGroups.setGroupIsPublic(chatGroup.getIsPublic());
				meetingGroups.setApproval(chatGroup.getApproval());
				meetingGroups.setGroupMaxusers(chatGroup.getMaxusers().intValue());
				meetingGroups.setGroupName(chatGroup.getGroupname());
				meetingGroups.setGroupOwner(chatGroup.getOwner());
				int insert = meetingGroupMapper.insert(meetingGroups);
				if(insert > 0){
					// 批量插入群成员
					String owner = chatGroup.getOwner();
					String member = chatGroup.getMember();
					List<MeetingUserGroup> meetingUserGroups = new ArrayList<MeetingUserGroup>();
					MeetingUserGroup meetingUserGroup = null;
					meetingUserGroup = new MeetingUserGroup(owner, groupId);
					meetingUserGroups.add(meetingUserGroup);
					if (!StringUtils.isBlank(member)) {
						String[] split = StringUtils.split(member, ",");
						for (String userId : split) {
							// 群成员
							meetingUserGroup = new MeetingUserGroup(userId, groupId);
							meetingUserGroups.add(meetingUserGroup);
						}
					}
					meetingUserGroupMapper.insertList(meetingUserGroups);
				}
				
				return meetingInfo.getMeeId();
			}
		}catch(Exception e){	
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_insertMeetingInfo_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_insertMeetingInfo_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = meetingInfo.toString();
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return 0;
	}

	@Override
	public Map<String,Object> updateMeetingInfo(MeetingInfo meetingInfo) 
			throws CustomException{
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			//更新主会议信息需要判断会议名是否重复
			MeetingInfo temp=new MeetingInfo();
			temp.setMeeName(meetingInfo.getMeeName());
			temp.setMeeParentId(0);
			List<MeetingInfo> list=meetingInfoMapper.select(temp);
			if(null!=list && list.size()>0 && !list.get(0).getMeeId().equals(meetingInfo.getMeeId())){
				return makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SELECT_REPEAT);
			}
			if(meetingInfoMapper.updateByPrimaryKeySelective(meetingInfo)==1){
				map.put("meetingInfo", meetingInfo);
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_UPDATE_SUCCESS);
			}else{
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_UPDATE_EXCEPTION);
				map.put("meetingInfo", meetingInfo);
			}
		}catch(Exception e){
			LOGGER.error(e+"");
			e.printStackTrace();
			map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SYSTEM_EXCEPTION);
			return map;
		}
		return map;
	}

	@Override
	public Map<String,Object> selectMeetingInfo(MeetingInfo meetingInfo) 
			throws CustomException{
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			List<MeetingInfo> list=null;
			meetingInfo.setMeeParentId(0);
			list=meetingInfoMapper.selectByConditions(meetingInfo);
			map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SELECT_SUCCESS);
			map.put("list", list);
		}catch(Exception e){
			LOGGER.error(e+"");
			e.printStackTrace();
			map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SYSTEM_EXCEPTION);
			return map;
		}
		return map;
	
	}

	@Override
	public Map<String,Object> selectMeetingInfoByName(MeetingInfo meetingInfo) 
			throws CustomException{
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			MeetingInfo temp = new MeetingInfo();
			temp.setMeeParentId(0);
			temp.setMeeName(meetingInfo.getMeeName());
			List<MeetingInfo> list=meetingInfoMapper.select(temp);
			if(null!=list && list.size()>0){
				//如果会议ID不为空，而且会议ID和查询所得相同则判断为不重复
				if(null!=meetingInfo.getMeeId() && meetingInfo.getMeeId().equals(list.get(0).getMeeId())){
					map.put("meetingInfo", list.get(0));
					return makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SELECT_NOREPEAT);
				}
				return makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SELECT_REPEAT);
			}else if(null==list || list.size()==0){
				map.put("meetingInfo", null);
				return makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SELECT_NOREPEAT);
			}
		}catch(Exception e){
			LOGGER.error(e+"");
			e.printStackTrace();
			map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SYSTEM_EXCEPTION);
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteMeetingInfoByIds(List<String> ids) 
			throws CustomException{
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			if(meetingInfoMapper.deleteMeetingInfoByIds(ids)>=1 
					&& subMeetingInfoMapper.deleteSubMeetingInfoByParentIds(ids)>0){
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_DELETE_SUCCESS);
			}else{
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_DELETESUB_EXCEPTION);
			}
		}catch(Exception e){
			LOGGER.error(e+"");
			e.printStackTrace();
			map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SYSTEM_EXCEPTION);
			return map;
		}
		return map;
	}
	
	private Map<String,Object> makeMapMsg(Map<String,Object> map,MeetingInfoEnum enums) 
			throws CustomException{
		map.put("code", enums.getCode());
		map.put("msg", enums.getMsg());
		return map;
	}

	@Override
	public Map<String, Object> selectPopMeetingInfo(MeetingInfoDto meetingInfo) 
			throws CustomException{
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			if(null==meetingInfo.getPageNum()){
				meetingInfo.setPageNum(1);
				meetingInfo.setPageSize(10);
			}
			if(null==meetingInfo.getUserId()){
				return makeMapMsg(map, MeetingInfoEnum.MEETINGINFO_SELECT_USERISNULL);
			}
			PageHelper.startPage(meetingInfo.getPageNum(), meetingInfo.getPageSize());
			Example example = new Example(MeetingInfo.class);
			if(null!=meetingInfo.getMeeName()){
				example.createCriteria().andCondition("mee_name=", meetingInfo.getMeeName())
					.andCondition("del_flag=", DelFlagEnum.DEL_FLAG_NORMAL.getValue()).andCondition("mee_parent_id=", "0");
			}else{
				example.createCriteria().andCondition("del_flag=", DelFlagEnum.DEL_FLAG_NORMAL.getValue()).andCondition("mee_parent_id=", "0");
			}
			example.setOrderByClause(" create_time ");
			List<MeetingInfoDto> list=meetingInfoMapper.selectPopMeetingInfoDto(meetingInfo);
			PageInfo<MeetingInfoDto> pi=new PageInfo<MeetingInfoDto>(list);
			map.put("page", pi);
			map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SELECT_SUCCESS);
		}catch(Exception e){
			LOGGER.error(e+"");
			e.printStackTrace();
			map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_SYSTEM_EXCEPTION);
			return map;
		}
		
		return map;
	}

	@Override
	public MeetingInfo selectMeetingInfoById(MeetingInfo meetingInfo) 
			throws CustomException{
		MeetingInfo meet = null;
		try {
			
			meet = meetingInfoMapper.selectByPrimaryKey(meetingInfo.getMeeId());
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectMeetingInfoById_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectMeetingInfoById_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != meetingInfo){
				params = meetingInfo.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return meet;
	}
//
//	@Override
//	public List<MeetingInfoSignUpDto> selectSignUpMeetingInfo(List<Integer> list) {
//		return meetingInfoMapper.selectJoinMeetingInfoByUserIdFuture(list,System.currentTimeMillis());
//	}

	@Override
	public PageInfo<MeetingInfo> selectFocusMeetingInfoByUserIdFuture(String userId,int pageNum,int pageSize) 
			throws CustomException{
		PageInfo<MeetingInfo> pageInfo = null;
		try {
			
			PageHelper.startPage(pageNum, pageSize);
			List<MeetingInfo> list=meetingInfoMapper.selectFocusMeetingInfoByUserIdFuture(userId,System.currentTimeMillis());
			pageInfo = new PageInfo<MeetingInfo>(list);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectFocusMeetingInfoByUserIdFuture_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectFocusMeetingInfoByUserIdFuture_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != userId){
				params += "userId:"+userId.toString()+";";
			}
			params += "pageNum:"+pageNum+";";
			params += "pageSize:"+pageSize+";";
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfo;
	}

	@Override
	public PageInfo<MeetingInfo> selectFocusMeetingInfoByUserIdOver(String userId,int pageNum,int pageSize) 
			throws CustomException{
		PageInfo<MeetingInfo> pageInfo = null;
		try {
			
			PageHelper.startPage(pageNum, pageSize);
			List<MeetingInfo> list=meetingInfoMapper.selectFocusMeetingInfoByUserIdOver(userId,System.currentTimeMillis());
			pageInfo = new PageInfo<MeetingInfo>(list);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectFocusMeetingInfoByUserIdOver_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectFocusMeetingInfoByUserIdOver_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != userId){
				params += "userId:"+userId.toString()+";";
			}
			params += "pageNum:"+pageNum+";";
			params += "pageSize:"+pageSize+";";
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return  pageInfo;
	}

	@Override
	public List<MeetingInfo> selectFollowMeetingInfo(String userId) 
			throws CustomException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdFuture(List<Integer> list,List<Integer> subList,int pageNum,int pageSize) 
			throws CustomException{
		PageInfo<MeetingInfoSignUpDto> pageInfo = null;
		try {
			
			PageHelper.startPage(pageNum, pageSize);
			List<MeetingInfoSignUpDto> li=meetingInfoMapper.selectJoinMeetingInfoByUserIdFuture(list,subList,System.currentTimeMillis());
			pageInfo = new PageInfo<MeetingInfoSignUpDto>(li);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectJoinMeetingInfoByUserIdFuture_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectJoinMeetingInfoByUserIdFuture_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != list){
				params += "list:"+list.toString()+";";
			}
			if(null != subList){
				params += "subList:"+subList.toString()+";";
			}
			params += "pageNum:"+pageNum+";";
			params += "pageSize:"+pageSize+";";
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfo;
	}

	@Override
	public PageInfo<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdOver(List<Integer> list,List<Integer> subList,int pageNum,int pageSize) 
			throws CustomException{
		PageInfo<MeetingInfoSignUpDto> pageInfo = null;
		try {
			
			PageHelper.startPage(pageNum, pageSize);
			List<MeetingInfoSignUpDto> li=meetingInfoMapper.selectJoinMeetingInfoByUserIdOver(list,subList,System.currentTimeMillis());
			pageInfo = new PageInfo<MeetingInfoSignUpDto>(li);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectJoinMeetingInfoByUserIdOver_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectJoinMeetingInfoByUserIdOver_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != list){
				params += "list:"+list.toString()+";";
			}
			if(null != subList){
				params += "subList:"+subList.toString()+";";
			}
			params += "pageNum:"+pageNum+";";
			params += "pageSize:"+pageSize+";";
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfo;
	}
	
	@Override
	public PageInfo<MeetingInfo> selectMeetingInfoByListOver(List<Integer> list,int pageNum,int pageSize) 
			throws CustomException{
		PageInfo<MeetingInfo> pageInfo = null;		
		try {
			
			List<MeetingInfo> li=new ArrayList<MeetingInfo>();
			PageHelper.startPage(pageNum, pageSize);
			if(null!=list && list.size()>0){
				Example example = new Example(MeetingInfo.class);
				example.createCriteria().andIn("meeId", list).andEqualTo("meeParentId", 0).andLessThan("meeEndTime", System.currentTimeMillis());
				li=meetingInfoMapper.selectByExample(example);
			}
			pageInfo = new PageInfo<MeetingInfo>(li);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectMeetingInfoByListOver_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectMeetingInfoByListOver_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != list){
				params += "list:"+list.toString()+";";
			}
			params += "pageNum:"+pageNum+";";
			params += "pageSize:"+pageSize+";";
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfo;
	}

	@Override
	public PageInfo<MeetingInfo> selectMeetingInfoByListFuture(
			List<Integer> list, int pageNum, int pageSize) throws CustomException{
		PageInfo<MeetingInfo> pageInfo = null;
		try {
			
			List<MeetingInfo> li=new ArrayList<MeetingInfo>();
			PageHelper.startPage(pageNum, pageSize);
			if(null!=list && list.size()>0){
				Example example = new Example(MeetingInfo.class);
				example.createCriteria().andIn("meeId", list).andEqualTo("meeParentId", 0).andGreaterThan("meeEndTime", System.currentTimeMillis());
				li=meetingInfoMapper.selectByExample(example);
			}
			pageInfo = new PageInfo<MeetingInfo>(li);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectMeetingInfoByListFuture_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectMeetingInfoByListFuture_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != list){
				params += "list:"+list.toString()+";";
			}
			params += "pageNum:"+pageNum+";";
			params += "pageSize:"+pageSize+";";
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfo;
	}

	@Override
	public PageInfo<MeetingInfo> selectMeetingInfoByHallId(
			MeetingInfo meetingInfo) throws CustomException{
		PageInfo<MeetingInfo> pageInfo = null;
		try {
			PageHelper.startPage(meetingInfo.getPageNum(), meetingInfo.getPageSize());
			MeetingInfo temp=new MeetingInfo();
			temp.setHallId(meetingInfo.getHallId());
			temp.setDelFlag("0");
			List<MeetingInfo> list=meetingInfoMapper.select(temp);
			pageInfo = new PageInfo<MeetingInfo>(list);
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectMeetingInfoByHallId_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_selectMeetingInfoByHallId_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != meetingInfo){
				params += meetingInfo.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfo;
	}

	@Override
	public Integer deleteMeetingInfoById(Integer meeId) throws CustomException{
		Integer integer = null;
		try {
			integer = meetingInfoMapper.deleteByPrimaryKey(meeId);
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingInfoBizImpl_deleteMeetingInfoById_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingInfoBizImpl_deleteMeetingInfoById_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != meeId){
				params += "meeId:"+meeId.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return integer;
	}
	
}
