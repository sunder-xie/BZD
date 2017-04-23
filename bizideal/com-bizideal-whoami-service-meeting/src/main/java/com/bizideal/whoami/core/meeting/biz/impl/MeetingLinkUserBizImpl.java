package com.bizideal.whoami.core.meeting.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.meeting.biz.MeetingLinkUserBiz;
import com.bizideal.whoami.core.meeting.mapper.MeetingLinkUserMapper;
import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.facade.meeting.entity.MeetingLinkUser;
import com.bizideal.whoami.facade.meeting.enums.MeettingExceptionEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;

/**
 * @ClassName MeetingLinkUserBiz
 * @Description TODO(用户关注的会议)
 * @Author Zj.Qu
 * @Date 2017-01-10 16:39:32
 */
@Service("meetingLinkUserBiz")
public class MeetingLinkUserBizImpl extends BaseBizImpl<MeetingLinkUser> implements  MeetingLinkUserBiz{

	@Autowired
	private MeetingLinkUserMapper meetingLinkUserMapper;

	@Override
	public Integer save(MeetingLinkUser t) 
			throws CustomException{
		Integer integer = null;
		try {
			
			t.setCreateTime(System.currentTimeMillis());
			integer = meetingLinkUserMapper.insert(t);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingLinkUserBizImpl_save_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingLinkUserBizImpl_save_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != t){
				params = t.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return integer;
	}

	@Override
	public Integer saveSelective(MeetingLinkUser t) 
			throws CustomException{
		Integer integer = null;
		try {
			
			t.setCreateTime(System.currentTimeMillis());
			integer = meetingLinkUserMapper.insertSelective(t);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingLinkUserBizImpl_saveSelective_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingLinkUserBizImpl_saveSelective_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != t){
				params = t.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return integer;
	}

	@Override
	public Integer countMeetingFollowByMeeId(Integer meeId) 
			throws CustomException{
		Integer integer = null;
		try {
			MeetingLinkUser temp=new MeetingLinkUser();
			temp.setMeeId(meeId);
			integer = meetingLinkUserMapper.selectCount(temp);
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingLinkUserBizImpl_countMeetingFollowByMeeId_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingLinkUserBizImpl_countMeetingFollowByMeeId_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != meeId){
				params = "meeId" + meeId.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return integer;
	}


}