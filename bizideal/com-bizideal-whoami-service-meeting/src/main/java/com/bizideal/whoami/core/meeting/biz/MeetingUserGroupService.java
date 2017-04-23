package com.bizideal.whoami.core.meeting.biz;

import java.util.List;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.facade.meeting.dto.MeetingGroupDto;
import com.bizideal.whoami.facade.meeting.dto.MeetingUsersDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;
import com.bizideal.whoami.facade.meeting.entity.MeetingUserGroup;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.pojo.DubboxResult;

/**
 * 用户聊天群
 * 
 * @author 作者 sy:
 * @data 创建时间：2017年2月17日11:25:16
 * @version 1.0
 */
public interface MeetingUserGroupService extends BaseBiz<MeetingUserGroup> {

	/**
	 * 批量加入群成员
	 * 
	 * @param userGroup
	 * @return DubboxResult
	 */
	boolean insertAddUsers(MeetingUserGroup meetingUserGroup)
			throws CustomException;

	/**
	 * 删除群成员
	 * 
	 * @param userGroup
	 * @return DubboxResult
	 */
	boolean deleteUsers(MeetingUserGroup meetingUserGroup)
			throws CustomException;

	/**
	 * 查询群成员列表
	 * 
	 * @param groupId
	 * @return
	 */
	List<MeetingUsersDto> selectGroupMembers(String groupId)
			throws CustomException;

	/**
	 * 解散群
	 * 
	 * @param groupId
	 * @return
	 */
	boolean deleteAllGroupMembers(String groupId)
			throws CustomException;

	/**
	 * 修改群基本信息
	 * 
	 * @param chatGroup
	 * @return
	 */
	boolean updateMeetingGroups(MeetingGroups meetingGroups)
			throws CustomException;

}
