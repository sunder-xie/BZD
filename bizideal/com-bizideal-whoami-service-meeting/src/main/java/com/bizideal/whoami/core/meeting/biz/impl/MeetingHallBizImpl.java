package com.bizideal.whoami.core.meeting.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.meeting.biz.MeetingHallBiz;
import com.bizideal.whoami.core.meeting.mapper.MeetingHallMapper;
import com.bizideal.whoami.croe.service.impl.BaseOpBizImpl;
import com.bizideal.whoami.enums.DelFlagEnum;
import com.bizideal.whoami.facade.meeting.dto.MeetingHallDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.enums.MeettingExceptionEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName MeetingHallBizImpl
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-04 16:25:33
 */
@Service("meetingHallBiz")
public class MeetingHallBizImpl extends BaseOpBizImpl<MeetingHall> implements MeetingHallBiz {

	@Autowired
	private MeetingHallMapper meetingHallMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(MeetingHallBizImpl.class);

	@Override
	public Integer saveSelective(MeetingHall meetingHall) 
			throws CustomException{
		Integer integer = null;		
		try {
			MeetingHall temp = new MeetingHall();
			temp.setHallName(meetingHall.getHallName());
			List<MeetingHall> list=meetingHallMapper.select(temp);
			if(null!=list && list.size()>0){
				LOGGER.debug("你所添加的会议厅名称重复:" + meetingHall.getHallName());
				return 0;
			}
			meetingHall.setCreateTime(System.currentTimeMillis());
			int effectedRows = meetingHallMapper.insertSelective(meetingHall);
			if (effectedRows == 1) {
				LOGGER.debug("你所添加的自增主键hallId为:" + meetingHall.getHallId());
				integer = meetingHall.getHallId();
			}
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingHallBizImpl_saveSelective_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingHallBizImpl_saveSelective_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != meetingHall){
				params = meetingHall.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return integer;
	}

	@Override
	public PageInfo<MeetingHall> queryPageListByUserId(String userId, Integer page, Integer rows) 
			throws CustomException{
		PageInfo<MeetingHall> pageInfos;
		try {
			
			pageInfos = null;
			PageHelper.startPage(page, rows);
			Example example = new Example(MeetingHall.class);
			example.createCriteria().andEqualTo("userId", userId)
									.andEqualTo("delFlag", DelFlagEnum.DEL_FLAG_NORMAL.getValue());
			example.orderBy("hallLeaveTime").desc();
			List<MeetingHall> list = meetingHallMapper.selectByExample(example);
			pageInfos = new PageInfo<MeetingHall>(list);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingHallBizImpl_queryPageListByUserId_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingHallBizImpl_queryPageListByUserId_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != page){
				params += "page:"+page.toString()+";";
			}
			if(null != rows){
				params += "rows:"+rows.toString()+";";
			}
			if(null != userId){
				params += "userId:"+userId.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfos;
	}

	@Override
	public MeetingHall queryByUserIdMeetingHall(String userId) {
		MeetingHall meetingHall = null;
		try {
			
			meetingHall = meetingHallMapper.queryByUserIdMeetingHall(userId);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingHallBizImpl_queryByUserIdMeetingHall_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingHallBizImpl_queryByUserIdMeetingHall_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;			
			if(null != userId){
				params += "userId:"+userId.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return meetingHall;
	}

	@Override
	public Integer delLogicMeetingHall(Integer hallId) 
			throws CustomException{
		Integer integer =  null;
		try {
			
			MeetingHall meetingHall = new MeetingHall();
			meetingHall.setHallId(hallId);
			meetingHall.setDelFlag(DelFlagEnum.DEL_FLAG_DELETE.getValue());
			integer = meetingHallMapper.updateByPrimaryKeySelective(meetingHall);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingHallBizImpl_delLogicMeetingHall_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingHallBizImpl_delLogicMeetingHall_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;			
			if(null != hallId){
				params += "hallId:"+hallId.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return integer;
	}

	@Override
	public List<MeetingHall> queryByUserIdsMeeHall(List<Integer> hall_ids) 
			throws CustomException{
		List<MeetingHall> lists = null;
		try {
			if (hall_ids.isEmpty()) {
				LOGGER.error("hall_ids为空!");
				return null;
			}
			lists = meetingHallMapper.queryByUserIdsMeeHall(hall_ids);
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingHallBizImpl_queryByUserIdsMeeHall_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingHallBizImpl_queryByUserIdsMeeHall_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;			
			if(null != hall_ids){
				params += "hall_ids:"+hall_ids.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return lists;
	}
	
	@Override
	public PageInfo<MeetingHallDto> listUserFollowHotHall(Integer page,Integer rows, String userId) 
			throws CustomException{
		PageInfo<MeetingHallDto> pageInfos = null;
		// 设置分页参数
		try {
			
			PageHelper.startPage(page, rows);
			List<MeetingHallDto> list = meetingHallMapper.listUserFollowHotHall(userId);
			pageInfos = new PageInfo<MeetingHallDto>(list);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingHallBizImpl_listUserFollowHotHall_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingHallBizImpl_listUserFollowHotHall_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;			
			if(null != page){
				params += "page:"+page.toString()+";";
			}
			if(null != rows){
				params += "rows:"+rows.toString()+";";
			}
			if(null != userId){
				params += "userId:"+userId.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfos;
	}

	@Override
	public List<MeetingHall> queryByHallName(String hallName) 
			throws CustomException{
		List<MeetingHall> lists = null;
		try {
			MeetingHall temp=new MeetingHall();
			temp.setHallName(hallName);
			lists = meetingHallMapper.select(temp);
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingHallBizImpl_queryByHallName_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingHallBizImpl_queryByHallName_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;			
			if(null != hallName){
				params += "hallName:"+hallName.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return lists;
	}

	@Override
	public Integer updateMeetingHall(MeetingHall meetingHall)
			throws CustomException {
		Integer integer = null;
		try {
			MeetingHall temp = new MeetingHall();
			temp.setHallName(meetingHall.getHallName());
			List<MeetingHall> list=meetingHallMapper.select(temp);
			if(null!=list && list.size()>0){
				boolean flag=true;
				for(MeetingHall mh:list){
					if(mh.getHallId().equals(meetingHall.getHallId())){
						flag=false;
						break;
					}
					flag=true;
				}
				if(flag){
					LOGGER.debug("你所修改的会议厅名称重复:" + meetingHall.getHallName());
					return 0;
				}
			}
			meetingHall.setCreateTime(System.currentTimeMillis());
			int effectedRows = meetingHallMapper.updateByPrimaryKeySelective(meetingHall);
			if (effectedRows == 1) {
				LOGGER.debug("你所修改的会议厅主键hallId为:" + meetingHall.getHallId());
				integer = meetingHall.getHallId();
			}
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingHallBizImpl_updateMeetingHall_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingHallBizImpl_updateMeetingHall_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != meetingHall){
				params = meetingHall.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return integer;
	}
}
