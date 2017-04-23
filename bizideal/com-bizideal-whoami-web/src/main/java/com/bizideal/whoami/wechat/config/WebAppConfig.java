package com.bizideal.whoami.wechat.config;

/**
 * 微信开放平台 网站应用信息
 * 
 * @author zhu_shangjin
 * @version 2017年1月4日 下午1:28:53
 */
public class WebAppConfig {
   //开放平台网站应用  appid
	private String appid;
	
	 //开放平台网站应用  scope
	private String scope;
	
	//开放平台网站应用  state
	private String state;
	//开放平台网站应用  redirectUri
	private String redirectUri;
	//开放平台网站应用  appsecret
	private String appsecret;

	public WebAppConfig() {
		super();
	}

	public WebAppConfig(String appid, String scope, String state) {
		super();
		this.appid = appid;
		this.scope = scope;
		this.state = state;
	}

	public WebAppConfig(String appid, String scope, String state,
			String redirectUri) {
		super();
		this.appid = appid;
		this.scope = scope;
		this.state = state;
		this.redirectUri = redirectUri;
	}

	public WebAppConfig(String appid, String scope, String state,
			String redirectUri, String appsecret) {
		super();
		this.appid = appid;
		this.scope = scope;
		this.state = state;
		this.redirectUri = redirectUri;
		this.appsecret = appsecret;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

}
