package com.bizideal.whoami.im.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName User
 * @Description TODO(IM用户结构)
 * @Author Zj.Qu
 * @Date 2017-01-05 10:02:03
 */
@XmlRootElement
public class IMUser  implements Serializable{

	private static final long serialVersionUID = 2169056607734856557L;
	
	/** 用户名 **/
	public String username;
	/** 用户密码 **/
	public String password;
	/** 用户昵称 **/
	public String nickname;

	public IMUser() {
		super();
	}

	public IMUser(String userName, String password, String nickname) {
		super();
		this.username = userName;
		this.password = password;
		this.nickname = nickname;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "IMUser [username=" + username + ", password=" + password
				+ ", nickname=" + nickname + "]";
	}

}
