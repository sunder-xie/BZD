package com.bizideal.whoami.core.meeting.biz;

import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;
import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName MeetingGroupBiz
 * @Description TODO(会议群)
 * @Author sy
 * @Date 2017-2-15 11:25:59
 */
public interface MeetingGroupBiz {

	/**
	 * 根据会议id删除会议群
	 * 
	 * @param meeId
	 * @return
	 */
	boolean deleteById(Integer meeId) throws CustomException;

	/**
	 * 根据会议id查找会议群
	 * 
	 * @param meeId
	 * @return
	 */
	MeetingGroups selectByMeeId(Integer meeId)
			throws CustomException;

}
