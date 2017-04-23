package com.bizideal.whoami.core.meeting.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.core.meeting.biz.MeetingGroupBiz;
import com.bizideal.whoami.core.meeting.mapper.MeetingGroupMapper;
import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;
import com.bizideal.whoami.facade.meeting.enums.MeettingExceptionEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;

@Service("meetingGroupBiz")
public class MeetingGroupBizImpl implements MeetingGroupBiz {

	@Autowired
	private MeetingGroupMapper meetingGroupMapper;

	@Override
	public boolean deleteById(Integer meeId) throws CustomException{
		try {
			
			Integer integer = meetingGroupMapper.deleteByPrimaryKey(meeId);
			if(integer > 0){
				return true;
			}
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingGroupBizImpl_deleteById_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingGroupBizImpl_deleteById_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != meeId){
				params = "meeId:"+meeId.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return false;
	}

	@Override
	public MeetingGroups selectByMeeId(Integer meeId) 
			throws CustomException{
		MeetingGroups meetingGroup = new MeetingGroups();
		try {
			Example example=new Example(MeetingGroups.class);
			example.createCriteria().andEqualTo("meeId", meeId);
			List<MeetingGroups> list = meetingGroupMapper.selectByExample(example);
			if(null==list || list.size()==0){
				meetingGroup = null;
			}else{
				meetingGroup = list.get(0);
			}
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingGroupBizImpl_selectByMeeId_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingGroupBizImpl_selectByMeeId_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != meeId){
				params = "meeId:"+meeId.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return meetingGroup ;
	}

}
