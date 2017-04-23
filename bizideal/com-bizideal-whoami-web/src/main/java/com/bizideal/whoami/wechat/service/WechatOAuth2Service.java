package com.bizideal.whoami.wechat.service;

import javax.servlet.http.HttpServletRequest;

import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.wechat.config.WebAppConfig;
import com.bizideal.whoami.wechat.pojo.WeixinOauth2Token;


public interface WechatOAuth2Service {
    /**
     * 获取微信公众号网页授权的用户信息
     * @param code
     * @param request
     * @return
     */
    String getOauth2AccessTokenFlag(String code, HttpServletRequest request);
	 /**
	  * 获取微信公众号网页授权的token
	  * @param code
	  * @param request
	  * @return
	  */
     WeixinOauth2Token getOauth2AccessToken(String code, HttpServletRequest request);
     /**
      * 获取微信用户信息
      * @param accessToken
      * @param openId
      * @return
      */
     UserWeixinInfo getSNSUserInfo(String accessToken, String openId);
     /**
      * 根据网站应用配置获取
      * 获取微信开放平台网站应用token
      * @param code
      * @param webAppConfig
      * @param request
      * @return
      */
     WeixinOauth2Token getWechatWebAccessToken(String code, WebAppConfig webAppConfig, HttpServletRequest request);
     /**
      * 根据网站应用配置获取
      * 获取微信开放平台网站应用token
      * @param code
      * @param webAppConfig
      * @param request
      * @return
      */
     String getWechatWebAccessTokenString(String code, WebAppConfig webAppConfig, HttpServletRequest request);

}
