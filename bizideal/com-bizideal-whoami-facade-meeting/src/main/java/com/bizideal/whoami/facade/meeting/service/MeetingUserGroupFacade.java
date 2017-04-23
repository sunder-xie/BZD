package com.bizideal.whoami.facade.meeting.service;

import java.util.List;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.facade.meeting.dto.MeetingUsersDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;
import com.bizideal.whoami.facade.meeting.entity.MeetingUserGroup;
import com.bizideal.whoami.pojo.CustomException;
/**
 * 用户聊天群
 * 
 * @author 作者 sy:
 * @data 创建时间：2017年2月17日11:23:31
 * @version 1.0
 */
public interface MeetingUserGroupFacade {

	/**
	 * 批量加入群成员
	 * 
	 * @param userGroup
	 * @return DubboxResult
	 */
	boolean insertAddUsers(MeetingUserGroup meetingUserGroup)
			throws CustomException;
	
	
	/**
	 * 报名加入群成员
	 * 
	 * @param userGroup
	 * @return DubboxResult
	 */
	boolean meetingSingUpAddMeetingGroup(SignUpInfoDto singUpInfoDto) throws CustomException;
	

	/**
	 * 删除群成员
	 * 
	 * @param userGroup
	 * @return DubboxResult
	 */
	boolean deleteUsers(MeetingUserGroup meetingUserGroup)
			throws CustomException;
	
	/**
	 * 根据会议id查找会议群
	 * 
	 * @param meeId
	 * @return
	 */
	MeetingGroups selectByMeeId(Integer meeId) throws CustomException;

	/**
	 * 根据会议id删除会议群
	 * 
	 * @param meeId
	 * @return
	 */
	boolean delete(Integer meeId) throws CustomException;
	
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
	boolean deleteGroup(String groupId) throws CustomException;
		
	/**
	 * 修改群基本信息
	 * 
	 * @param chatGroup
	 * @return
	 */
	boolean updateMeetingGroups(MeetingGroups meetingGroups)
			throws CustomException;
	
}
