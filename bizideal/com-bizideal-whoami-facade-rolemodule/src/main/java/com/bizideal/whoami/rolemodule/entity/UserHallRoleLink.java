package com.bizideal.whoami.rolemodule.entity;

import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 用户和会议厅角色的关联
 * 
 * @author zhu_shangjin
 * @version 2016年12月23日 上午9:10:32
 */
@Table(name = "user_hallrole_link")
public class UserHallRoleLink extends BaseEntity {
	// 用户id
	private String userId;
	// 会议厅id
	private Integer meetHallId;
	// 会议厅角色id
	private Integer hallRoleId;

	public UserHallRoleLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserHallRoleLink(String userId, Integer meetHallId, Integer hallRoleId) {
		super();
		this.userId = userId;
		this.meetHallId = meetHallId;
		this.hallRoleId = hallRoleId;
	}

	public String getUserId() {
		return userId;
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

	public Integer getHallRoleId() {
		return hallRoleId;
	}

	public void setHallRoleId(Integer hallRoleId) {
		this.hallRoleId = hallRoleId;
	}

	@Override
	public String toString() {
		return "UserHallRoleLink [userId=" + userId + ", meetHallId=" + meetHallId + ", hallRoleId=" + hallRoleId + "]";
	}

}
