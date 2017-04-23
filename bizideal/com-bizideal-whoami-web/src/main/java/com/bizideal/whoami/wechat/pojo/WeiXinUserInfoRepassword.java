package com.bizideal.whoami.wechat.pojo;

import com.bizideal.whoami.user.entity.UserWeixinInfo;

public class WeiXinUserInfoRepassword extends UserWeixinInfo{
	
	String repassword ;//重复密码

	
	public WeiXinUserInfoRepassword() {
		super();
	}

	public WeiXinUserInfoRepassword(String repassword) {
		super();
		this.repassword = repassword;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	@Override
	public String toString() {
		return "weiXinUserInfo [repassword=" + repassword + "]";
	}

}
