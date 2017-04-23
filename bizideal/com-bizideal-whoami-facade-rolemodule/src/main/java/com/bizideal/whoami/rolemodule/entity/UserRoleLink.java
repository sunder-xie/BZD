package com.bizideal.whoami.rolemodule.entity;

import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 用户和主会议的角色关联
 * 
 * @author zhu_shangjin
 * @version 2016年12月23日 上午9:10:32
 */
@Table(name = "user_role_link")
public class UserRoleLink extends BaseEntity {
	// 用户id
	private String userId;
	// 会议厅id
	private Integer meetHallId;
	// 角色id
	private Integer roleId;
	// 主会议id
	private Integer meetingId;

	public UserRoleLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRoleLink(String userId, Integer meetHallId, Integer roleId) {
		super();
		this.userId = userId;
		this.meetHallId = meetHallId;
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public UserRoleLink(String userId, Integer meetHallId, Integer roleId, Integer meetingId) {
		super();
		this.userId = userId;
		this.meetHallId = meetHallId;
		this.roleId = roleId;
		this.meetingId = meetingId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getMeetHallId() {
		return meetHallId;
	}

	public void setMeetHallId(Integer meetHallId) {
		this.meetHallId = meetHallId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	@Override
	public String toString() {
		return "UserRoleLink [userId=" + userId + ", meetHallId=" + meetHallId + ", roleId=" + roleId + ", meetingId=" + meetingId + "]";
	}

}
