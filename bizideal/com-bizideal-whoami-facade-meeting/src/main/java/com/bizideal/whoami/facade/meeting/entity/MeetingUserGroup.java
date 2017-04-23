package com.bizideal.whoami.facade.meeting.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 用户聊天群
 * 
 * @author 作者 sy:
 * @data 创建时间：2017年2月17日11:23:05
 * @version 1.0
 */
@Table(name = "meeting_user_group")
public class MeetingUserGroup extends BaseEntity {
	private static final long serialVersionUID = 1144103907533203850L;

	/**
	 * 用户id
	 */
	@Id
	private String userId;

	/**
	 * 聊天群id
	 */
	@Id
	private String groupId;

	/**
	 * 用户们
	 */
	@Transient
	private String userIds;

	public MeetingUserGroup() {
		super();
	}

	public MeetingUserGroup(String userId, String groupId) {
		super();
		this.userId = userId;
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	@Override
	public String toString() {
		return "MeetingUserGroup [userId=" + userId + ", groupId=" + groupId
				+ "]";
	}
}
