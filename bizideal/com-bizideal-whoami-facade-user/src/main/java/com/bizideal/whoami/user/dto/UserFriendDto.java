package com.bizideal.whoami.user.dto;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月9日 上午10:06:20
 * @version 1.0 app好友传输类
 */
public class UserFriendDto extends BaseEntity {

	private static final long serialVersionUID = -4352106835057921472L;
	private String userId; // 用户id
	private String realName;// 真实姓名
	private String nickname;// 昵称
	private String sex;// 性别
	private String headimgurl;// 头像地址
	private String phone;// 手机号
	private String isFriend;// 是否是好友

	public UserFriendDto() {
		super();
	}

	public UserFriendDto(String userId, String realName, String nickname,
			String sex, String headimgurl, String phone, String isFriend) {
		super();
		this.userId = userId;
		this.realName = realName;
		this.nickname = nickname;
		this.sex = sex;
		this.headimgurl = headimgurl;
		this.phone = phone;
		this.isFriend = isFriend;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(String isFriend) {
		this.isFriend = isFriend;
	}

	@Override
	public String toString() {
		return "UserFriendDto [userId=" + userId + ", realName=" + realName
				+ ", nickname=" + nickname + ", sex=" + sex + ", headimgurl="
				+ headimgurl + ", phone=" + phone + ", isFriend=" + isFriend
				+ "]";
	}

}
