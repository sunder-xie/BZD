package com.bizideal.whoami.user.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月10日 下午1:45:22
 * @version 1.0
 */
@Table(name = "user_group")
public class UserGroup extends BaseEntity {
	private static final long serialVersionUID = 1144103907533203850L;

	@Id
	private String userId;// 用户id
	@Id
	private String groupId;// 好友id列表
	@Transient
	private String userIds;// 用户id列表

	public UserGroup() {
		super();
	}

	public UserGroup(String userId, String groupId) {
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
		return "UserGroup [userId=" + userId + ", groupId=" + groupId + "]";
	}

}
