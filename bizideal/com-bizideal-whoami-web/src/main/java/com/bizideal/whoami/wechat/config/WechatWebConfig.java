package com.bizideal.whoami.wechat.config;

/**
 * 
 * @author zhu_shangjin
 * @version 2016年12月20日 上午10:28:29 公众号网页授权配置和公众号配置
 */
public class WechatWebConfig {
	//  公众号网页授权配置和公众号配置  appId
	private String appId;
	// 公众号网页授权配置和公众号配置   appSecret
	private String appSecret;
	//  公众号网页授权配置和公众号配置 partnerId
	

	private String partnerId;
	//  公众号网页授权配置和公众号配置 partnerKey
	private String partnerKey;
	 //  公众号网页授权配置和公众号配置  token
	private String token;
	//  公众号网页授权配置和公众号配置 aesKey 
	private String aesKey;
	//  公众号网页授权配置和公众号配置   profile
	private String profile;

	public WechatWebConfig() {
		super();
	}

	public WechatWebConfig(String appId, String appSecret, String partnerId,
			String partnerKey, String token, String aesKey) {
		super();
		this.appId = appId;
		this.appSecret = appSecret;
		this.partnerId = partnerId;
		this.partnerKey = partnerKey;
		this.token = token;
		this.aesKey = aesKey;
	}

	public WechatWebConfig(String appId, String appSecret, String partnerId,
			String partnerKey, String token, String aesKey, String profile) {
		super();
		this.appId = appId;
		this.appSecret = appSecret;
		this.partnerId = partnerId;
		this.partnerKey = partnerKey;
		this.token = token;
		this.aesKey = aesKey;
		this.profile = profile;
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

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
