package com.bizideal.whoami.weixin.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 微信公众号配置实体类
 * @author zhu_shangjin
 * @version 2016年12月16日 下午2:17:20
 */
@Table(name="wechat_config")
public class WechatConfig extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
	//主键
	private  Integer id;
	// 公众号token
	private String token;
	//公众号加解密密钥
	private String encodingAesKey;
	// 公众号appid
	private String appId;
	//公众号 appSecret
	private String appSecret;
	//公众号环境配置  测试或者正式
	private String wechatProfile;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEncodingAesKey() {
		return encodingAesKey;
	}
	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getWechatProfile() {
		return wechatProfile;
	}
	public void setWechatProfile(String wechatProfile) {
		this.wechatProfile = wechatProfile;
	}
	
	

}
