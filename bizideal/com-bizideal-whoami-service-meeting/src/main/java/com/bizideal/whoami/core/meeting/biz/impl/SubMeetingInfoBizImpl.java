package com.bizideal.whoami.core.meeting.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.core.meeting.biz.SubMeetingInfoBiz;
import com.bizideal.whoami.core.meeting.mapper.MeetingHallMapper;
import com.bizideal.whoami.core.meeting.mapper.MeetingInfoMapper;
import com.bizideal.whoami.core.meeting.mapper.SubMeetingInfoMapper;
import com.bizideal.whoami.croe.service.impl.BaseOpBizImpl;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.facade.meeting.enums.MeetingInfoEnum;
import com.bizideal.whoami.facade.meeting.enums.MeettingExceptionEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("subMeetingInfoBiz")
public class SubMeetingInfoBizImpl extends BaseOpBizImpl<SubMeetingInfo> implements SubMeetingInfoBiz{

	private static final Logger LOGGER = LoggerFactory.getLogger(SubMeetingInfoBizImpl.class);

	@Autowired
	private MeetingHallMapper meetingHallMapper;

	@Autowired
	private SubMeetingInfoMapper subMeetingInfoMapper;
	@Autowired
	private MeetingInfoMapper meetingInfoMapper;
	@Override
	public Map<String,Object> deleteSubMeetingInfoById(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			subMeetingInfo.setDelFlag("1");
			if(subMeetingInfoMapper.deleteSubMeetingInfo(subMeetingInfo)==1){
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_DELETESUB_SUCCESS);
				map.put("subMeetingInfo", subMeetingInfo);
			}else{
				map.put("subMeetingInfo", subMeetingInfo);
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_DELETESUB_EXCEPTION);
				return map;
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
	public int deleteSubMeetingInfoByIdReal(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		try {
			return subMeetingInfoMapper.deleteByPrimaryKey(subMeetingInfo.getMeeId());
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_deleteSubMeetingInfoByIdReal_00_ERROR.getCode(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_deleteSubMeetingInfoByIdReal_00_ERROR.getMsg());
			String params = null;
			if(null != subMeetingInfo){
				params = subMeetingInfo.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
	
	@Override
	public Map<String,Object> deleteSubMeetingInfoByIds(List<String> ids) 
			throws CustomException{
		Map<String,Object> map=new HashMap<String,Object>();
		try{
//			Example example=new Example(MeetingInfo.class);
//			example.createCriteria().andIn("meeId", ids);
//			if(meetingInfoMapper.deleteByExample(example)>=1){
			if(subMeetingInfoMapper.deleteSubMeetingInfoByIds(ids)>=1){
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_DELETESUB_SUCCESS);
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
	

	@Override
	public Map<String, Object> insertSubMeetingInfo(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		Map<String,Object> map=new HashMap<String,Object>();
		try{
//			//判断会议厅是否合法
//			if(null!=subMeetingInfo.getHallId()){
//				MeetingHall hall=meetingHallMapper.selectByPrimaryKey(subMeetingInfo.getHallId());
//				if(null==hall || hall.getHallName()==null){
//					return makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_INSERTSUB_NOHALL);
//				}
//			}
			if(null==subMeetingInfo.getMeeParentId() || subMeetingInfo.getMeeParentId().equals(new Integer(0))){
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_INSERTSUB_NOPARENTID);
				return map;
			}
			subMeetingInfo.setCreateTime(System.currentTimeMillis());
			//查询主会议是否存在
			MeetingInfo mi=meetingInfoMapper.selectByPrimaryKey(subMeetingInfo.getMeeParentId());
			if(null==mi){
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_INSERTSUB_NOPARENTID);
				return map;
			}
			//判断会议厅是否合法
			if(!subMeetingInfo.getHallId().equals(mi.getHallId())){
				return makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_INSERTSUB_NOHALL);
			}
			
			if(subMeetingInfoMapper.insertSelective(subMeetingInfo)==1){
				map.put("subMeetingInfo", subMeetingInfo);
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_INSERTSUB_SUCCESS);
			}else{
				map.put("subMeetingInfo", subMeetingInfo);
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_INSERTSUB_EXCEPTION);
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
	public Map<String, Object> selectSubMeetingInfoByParentId(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			//判断主会议ID是否正确
			if(null==subMeetingInfo.getMeeParentId() || subMeetingInfo.getMeeParentId().equals(new Integer(0))){
				map.put("list", null);
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_INSERTSUB_NOPARENTID);
				return map;
			}
			List<SubMeetingInfo> list=null;
			SubMeetingInfo temp=new SubMeetingInfo();
			temp.setDelFlag("0");
			temp.setMeeParentId(subMeetingInfo.getMeeParentId());
			//TODO 处理有关MeetingInfoMapper.selectByConditions(temp)
			
			
//			list=subMeetingInfoMapper.selectByConditions(temp);
			list=subMeetingInfoMapper.select(temp);
			map.put("list", list);
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
	public List<SubMeetingInfo> selectSubMeetingInfoByList(List<Integer> list) 
			throws CustomException{
		try {
			List<SubMeetingInfo> li=new ArrayList<SubMeetingInfo>();
			if(null!=list && list.size()>0){
				Example example = new Example(MeetingInfo.class);
				example.createCriteria().andIn("meeId", list).andNotEqualTo("meeParentId", 0);
				li=subMeetingInfoMapper.selectByExample(example);
			}
			return li;
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_deleteSubMeetingInfoByIdReal_00_ERROR.getCode(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_deleteSubMeetingInfoByIdReal_00_ERROR.getMsg());
			String params = null;
			if(null != list){
				params = list.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
	
	@Override
	public Map<String, Object> updateSubMeetingInfo (
			SubMeetingInfo subMeetingInfo) throws CustomException{
//		subMeetingInfoMapper.updateByPrimaryKeySelective(subMeetingInfo)

		Map<String,Object> map=new HashMap<String,Object>();
		try{
			if(subMeetingInfoMapper.updateByPrimaryKeySelective(subMeetingInfo)==1){
				map.put("subMeetingInfo", subMeetingInfo);
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_UPDATE_SUCCESS);
			}else{
				map=makeMapMsg(map,MeetingInfoEnum.MEETINGINFO_UPDATE_EXCEPTION);
				map.put("subMeetingInfo", subMeetingInfo);
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
	public PageInfo<SubMeetingInfo> selectPageSubMeetingInfoByList(
			List<Integer> list, int pageNum, int pageSize) throws CustomException{
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<SubMeetingInfo> li=new ArrayList<SubMeetingInfo>();
			if(null!=list && list.size()>0){
				Example example = new Example(MeetingInfo.class);
				example.createCriteria().andIn("meeId", list).andNotEqualTo("meeParentId", 0);
				li=subMeetingInfoMapper.selectByExample(example);
			}
			return new PageInfo<SubMeetingInfo>(li);
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_selectPageSubMeetingInfoByList_00_ERROR.getCode(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_selectPageSubMeetingInfoByList_00_ERROR.getMsg());
			String params = null;
			if(null != list){
				params = list.toString();
			}
			params += "pageNum:" + pageNum;
			params += "pageSize:" + pageSize;
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
	
	@Override
	public SubMeetingInfo selectSubMeetingInfoById(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		try {
			return subMeetingInfoMapper.selectByPrimaryKey(subMeetingInfo.getMeeId());
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_selectPageSubMeetingInfoByList_00_ERROR.getCode(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_selectPageSubMeetingInfoByList_00_ERROR.getMsg());
			String params = null;
			if(null != subMeetingInfo){
				params = subMeetingInfo.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	

	private Map<String,Object> makeMapMsg(Map<String,Object> map,MeetingInfoEnum enums) 
			throws CustomException{
		map.put("code", enums.getCode());
		map.put("msg", enums.getMsg());
		return map;
	}

	@Override
	public boolean isMeetingStartTimeOut(long startTime, Integer meeId)
			throws CustomException {
		try {
			Example ex=new Example(SubMeetingInfo.class);
			ex.createCriteria().andLessThan("meeStartTime", startTime).andEqualTo("meeParentId", meeId);
			List<SubMeetingInfo> list=subMeetingInfoMapper.selectByExample(ex);
			return null==list || list.size()==0;
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_isMeetingStartTimeOut_00_ERROR.getCode(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_isMeetingStartTimeOut_00_ERROR.getMsg());
			String params = null;
			params="startTime:"+startTime+",meeId:"+meeId;
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public boolean isMeetingEndTimeOut(long endTime, Integer meeId)
			throws CustomException {
		try {
			Example ex=new Example(SubMeetingInfo.class);
			ex.createCriteria().andGreaterThan("meeEndTime", endTime).andEqualTo("meeParentId", meeId);
			List<SubMeetingInfo> list=subMeetingInfoMapper.selectByExample(ex);
			return null==list || list.size()==0;
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_isMeetingEndTimeOut_00_ERROR.getCode(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_isMeetingEndTimeOut_00_ERROR.getMsg());
			String params = null;
			params="endTime:"+endTime+",meeId:"+meeId;
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public Long getFristStartTimeByParentMeeId(Integer meeId)
			throws CustomException {
		try {
			Example ex=new Example(SubMeetingInfo.class);
			ex.createCriteria().andEqualTo("meeParentId", meeId);
			ex.setOrderByClause("mee_start_time");
			List<SubMeetingInfo> list=subMeetingInfoMapper.selectByExample(ex);
			return null==list || list.size()==0?null:list.get(0).getMeeStartTime();
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_getFristStartTimeByParentMeeId_00_ERROR.getCode(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_getFristStartTimeByParentMeeId_00_ERROR.getMsg());
			String params = null;
			params="meeId:"+meeId;
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public Long getLastEndTimeByParentMeeId(Integer meeId)
			throws CustomException {
		try {
			Example ex=new Example(SubMeetingInfo.class);
			ex.createCriteria().andEqualTo("meeParentId", meeId);
			ex.setOrderByClause("mee_end_time DESC");
			List<SubMeetingInfo> list=subMeetingInfoMapper.selectByExample(ex);
			return null==list || list.size()==0?null:list.get(0).getMeeEndTime();
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_getLastEndTimeByParentMeeId_00_ERROR.getCode(),
					MeettingExceptionEnums.SubMeetingInfoBizImpl_getLastEndTimeByParentMeeId_00_ERROR.getMsg());
			String params = null;
			params="meeId:"+meeId;
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

}
