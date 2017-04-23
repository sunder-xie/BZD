package com.bizideal.whoami.wechat.service;


import javax.servlet.http.HttpServletRequest;

import com.bizideal.whoami.weixin.dto.WechatAccessToken;

public interface CoreService {
	/**
	 * 微信公众号处理请求
	 * @param request
	 * @return
	 */
	String processRequest(HttpServletRequest request);
	/**
	 *  微信公众号配置校验
	 * @param request
	 * @return
	 */
	String checkSignature(HttpServletRequest request);
	/**
	 * 获取微信接口调用凭证 
	 * @param appid  微信公众号   appid
	 * @param appsecret   微信公众号   appsecret
	 * @return
	 */
	WechatAccessToken getWechatAccessToken(String appid, String appsecret);
	

}
