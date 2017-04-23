package com.bizideal.whoami.facade.meeting.service;

import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName MeetingLinkUserFacade
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2017-01-11 09:52:17
 */
public interface MeetingLinkUserFacade {

	/**
	 * 关注某个会议
	 * @param meeId 会议id
	 * @param userId 用户id
	 * @return
	 */
	Integer followMeeting(Integer meeId, String userId)
			throws CustomException;
	
	/**
	 * 取消关注某个会议
	 * @param meeId 会议id
	 * @param userId 用户id
	 * @return
	 */
	Integer unfollowMeeting(Integer meeId, String userId)
			throws CustomException;
	
	/**
	 * 根据会议ID查询会议粉丝数
	 * @param meeId
	 * @return
	 */
	Integer countMeetingFollowByMeeId(Integer meeId)
			throws CustomException;
}
