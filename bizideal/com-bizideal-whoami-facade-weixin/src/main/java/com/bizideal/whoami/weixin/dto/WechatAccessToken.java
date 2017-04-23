package com.bizideal.whoami.weixin.dto;

import java.io.Serializable;

/**
 * 调用微信公众号接口的accesstoken
 * @author zhu_shangjin
 * @version 2016年12月19日 下午3:28:56
 */
public class WechatAccessToken implements Serializable{
	
	 // 获取到的凭证  
    private String token;  
    // 凭证有效时间，单位：秒  
    private int expiresIn; 
    // 生成时间
    private Long generateTime;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Long getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(Long generateTime) {
		this.generateTime = generateTime;
	}

}
