package com.bizideal.whoami.core.meeting.biz;



import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.facade.meeting.entity.MeetingLinkUser;
import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName MeetingLinkUserBiz
 * @Description TODO(用户关注的会议)
 * @Author Zj.Qu
 * @Date 2017-01-10 16:37:32
 */
public interface MeetingLinkUserBiz extends BaseBiz<MeetingLinkUser> {

	/**
	 * 根据会议ID查询会议粉丝数
	 * @param meeId
	 * @return
	 */
	Integer countMeetingFollowByMeeId(Integer meeId)
			throws CustomException;
	
}