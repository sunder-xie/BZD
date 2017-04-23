package com.bizideal.whoami.facade.meeting.service.impl;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.meeting.biz.MeetingGroupBiz;
import com.bizideal.whoami.core.meeting.biz.MeetingUserGroupService;
import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.facade.meeting.dto.MeetingGroupDto;
import com.bizideal.whoami.facade.meeting.dto.MeetingUsersDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;
import com.bizideal.whoami.facade.meeting.entity.MeetingUserGroup;
import com.bizideal.whoami.facade.meeting.service.MeetingUserGroupFacade;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.pojo.DubboxResult;

/**
 * @author 作者 sy:
 * @data 创建时间：2017年2月17日11:24:23
 * @version 1.0
 */
@Component("meetingUserGroupFacade")
public class MeetingUserGroupFacadeImpl implements MeetingUserGroupFacade {

	@Autowired
	private MeetingUserGroupService meetingUserGroupService;
	
	@Autowired
	private MeetingGroupBiz meetingGroupBiz;

	@Override
	public boolean insertAddUsers(MeetingUserGroup meetingUserGroup) throws CustomException{
		return meetingUserGroupService.insertAddUsers(meetingUserGroup);
	}
	
	@Override
	public boolean meetingSingUpAddMeetingGroup(SignUpInfoDto singUpInfoDto) throws CustomException {
		// TODO Auto-generated method stub
		MeetingGroups meetingGroups = meetingGroupBiz.selectByMeeId(singUpInfoDto.getMeetingId());
		if(meetingGroups != null){
			MeetingUserGroup meetingUserGroup = new MeetingUserGroup();
			meetingUserGroup.setUserIds(singUpInfoDto.getUserId());
			meetingUserGroup.setGroupId(meetingGroups.getGroupId());
			// 插入用户聊天群信息
			return meetingUserGroupService.insertAddUsers(meetingUserGroup);
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteUsers(MeetingUserGroup meetingUserGroup) throws CustomException{
		return meetingUserGroupService.deleteUsers(meetingUserGroup);
	}

	@Override
	public List<MeetingUsersDto> selectGroupMembers(String groupId) throws CustomException{
		return meetingUserGroupService.selectGroupMembers(groupId);
	}

	@Override
	public boolean deleteGroup(String groupId) throws CustomException{
		return meetingUserGroupService.deleteAllGroupMembers(groupId);
	}

	@Override
	public boolean updateMeetingGroups(MeetingGroups meetingGroups) throws CustomException{
		return meetingUserGroupService.updateMeetingGroups(meetingGroups);
	}

	public boolean delete(Integer meeId) throws CustomException{
		return meetingGroupBiz.deleteById(meeId);
	}

	public MeetingGroups selectByMeeId(Integer meeId) throws CustomException{
		return meetingGroupBiz.selectByMeeId(meeId);
	}
}
