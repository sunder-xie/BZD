package com.bizideal.whoami.user.entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月8日 下午2:25:22
 * @version 1.0
 */
@Table(name = "user_friend")
public class UserFriend extends BaseEntity {

	private static final long serialVersionUID = -9184385617469139579L;
	private String userId; // 用户id
	private String userIdFriend; // 好友id
	@Transient
	private String info;

	public UserFriend() {
		super();
	}

	public UserFriend(String userId, String userIdFriend) {
		super();
		this.userId = userId;
		this.userIdFriend = userIdFriend;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIdFriend() {
		return userIdFriend;
	}

	public void setUserIdFriend(String userIdFriend) {
		this.userIdFriend = userIdFriend;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "UserFriend [userId=" + userId + ", userIdFriend="
				+ userIdFriend + "]";
	}

}
