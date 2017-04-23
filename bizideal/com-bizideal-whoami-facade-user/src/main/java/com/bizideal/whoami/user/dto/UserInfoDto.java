package com.bizideal.whoami.user.dto;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月5日 上午9:29:52
 * @version 1.0
 */
public class UserInfoDto extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String userId; // 用户id
	private String phone;// 手机号
	private String password;// 登陆密码
	private String msgCode;// 验证码

	public UserInfoDto() {
		super();
	}

	public UserInfoDto(String userId, String phone, String password,
			String msgCode) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.password = password;
		this.msgCode = msgCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserInfoDto [userId=" + userId + ", phone=" + phone
				+ ", password=" + password + ", msgCode=" + msgCode + "]";
	}

}
