package com.bizideal.whoami.facade.meeting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.meeting.biz.MeetingLinkUserBiz;
import com.bizideal.whoami.facade.meeting.entity.MeetingLinkUser;
import com.bizideal.whoami.facade.meeting.service.MeetingLinkUserFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName MeetingLinkUserFacadeImpl
 * @Description TODO(用户关注的会议)
 * @Author Zj.Qu
 * @Date 2017-01-11 09:58:19
 */
@Component("meetingLinkUserFacade")
public class MeetingLinkUserFacadeImpl implements MeetingLinkUserFacade{

	@Autowired
	private MeetingLinkUserBiz meetingLinkUserBiz;

	@Override
	public Integer followMeeting(Integer meeId, String userId) 
			throws CustomException{
		MeetingLinkUser meetingLinkUser = new MeetingLinkUser();
		meetingLinkUser.setMeeId(meeId);
		meetingLinkUser.setUserId(userId);
		return meetingLinkUserBiz.saveSelective(meetingLinkUser);
	}
	
	@Override
	public Integer unfollowMeeting(Integer meeId, String userId) 
			throws CustomException{
		MeetingLinkUser meetingLinkUser = new MeetingLinkUser();
		meetingLinkUser.setMeeId(meeId);
		meetingLinkUser.setUserId(userId);
		return meetingLinkUserBiz.deleteByWhere(meetingLinkUser);
	}

	@Override
	public Integer countMeetingFollowByMeeId(Integer meeId) 
			throws CustomException{
		return meetingLinkUserBiz.countMeetingFollowByMeeId(meeId);
	}
	
}