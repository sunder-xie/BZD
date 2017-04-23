package com.bizideal.whoami.wechat.service.imp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.EmojiFilterUtil;
import com.bizideal.whoami.utils.HttpClientUtils;
import com.bizideal.whoami.wechat.config.WebAppConfig;
import com.bizideal.whoami.wechat.config.WechatWebConfig;
import com.bizideal.whoami.wechat.pojo.WeixinOauth2Token;
import com.bizideal.whoami.wechat.service.WechatOAuth2Service;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class WechatOAuth2ServiceImpl implements WechatOAuth2Service {
	@Autowired(required = false)
	WechatWebConfig wechatConfig;
	@Autowired
	UserWeixinInfoFacade userWeixinInfoFacade;
	
	@Value("${fastdfsurl}")
	private String fastdfsurl;

	public WechatOAuth2ServiceImpl() {
		super();
	}

	public WechatOAuth2ServiceImpl(WechatWebConfig wechatConfig) {
		super();
		this.wechatConfig = wechatConfig;
	}

	/**
	 * 获取微信网页授权的token
	 */
	@Override
	public String getOauth2AccessTokenFlag(String code,
			HttpServletRequest request) {
		String info = "false";
		WeixinOauth2Token weixinOauth2Token = (WeixinOauth2Token) request
				.getSession().getAttribute("access_token");
		if (weixinOauth2Token != null) {
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			UserWeixinInfo wcu = getSNSUserInfo(accessToken, openId);// 设置要传递的参数
			request.getSession().setAttribute("user", wcu);
			request.getSession()
					.setAttribute("access_token", weixinOauth2Token);
			info = "true";
		} else {
			if (!"authdeny".equals(code)) {

				weixinOauth2Token = getOauth2AccessToken(code, request);
				if (null == weixinOauth2Token) {
					info = "false";
				} else {
					// 网页授权接口访问凭证
					String accessToken = weixinOauth2Token.getAccessToken();
					// 用户标识
					String openId = weixinOauth2Token.getOpenId();
					// 获取用户信息
					UserWeixinInfo wcu = getSNSUserInfo(accessToken, openId);// 设置要传递的参数
					request.getSession().setAttribute("user", wcu);
					request.getSession().setAttribute("access_token",
							weixinOauth2Token);
					info = "true";
				}

			}
		}

		return info;
	}

	/**
	 * 获取微信网页授权的accesstoken
	 */
	@Override
	public WeixinOauth2Token getOauth2AccessToken(String code,
			HttpServletRequest request) {
		/**
		 * 欢迎大家关注我的博客，如有疑问，请加qq群：454796847、135430763 共同进步！
		 * 也可以浏览我的博客，左侧有支付宝和微信的捐款二维码！
		 */
		String appId = wechatConfig.getAppId();
		String appSecret = wechatConfig.getAppSecret();
		WeixinOauth2Token wat = (WeixinOauth2Token) request.getSession()
				.getAttribute("access_token");
		if (wat != null) {
			return wat;
		} else {
			// 拼接请求地址
			String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
			requestUrl = requestUrl.replace("APPID", appId);
			requestUrl = requestUrl.replace("SECRET", appSecret);
			requestUrl = requestUrl.replace("CODE", code);
			// 获取网页授权凭证
			String result = HttpClientUtils.httpsRequest(requestUrl, "GET",
					null);
			JsonObject asJsonObject = (JsonObject) new JsonParser()
					.parse(result);

			if (null != asJsonObject && asJsonObject.has("access_token")) {
				try {
					wat = new WeixinOauth2Token();
					wat.setAccessToken(asJsonObject.get("access_token")
							.getAsString());
					wat.setExpiresIn(asJsonObject.get("expires_in").getAsInt());
					wat.setRefreshToken(asJsonObject.get("refresh_token")
							.getAsString());
					wat.setOpenId(asJsonObject.get("openid").getAsString());
					wat.setScope(asJsonObject.get("scope").getAsString());
					if (asJsonObject.has("unionid")) {
						wat.setUnionid(asJsonObject.get("unionid")
								.getAsString());
					}
					request.getSession().setAttribute("access_token", wat);
				} catch (Exception e) {
					e.printStackTrace();
					wat = null;
					int errorCode = asJsonObject.get("errcode").getAsInt();
					String errorMsg = asJsonObject.get("errmsg").getAsString();
				}
			}
			return wat;
		}

	}

	/**
	 * 网页授权和开放平台网站应用获取用户信息
	 */
	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public UserWeixinInfo getSNSUserInfo(String accessToken, String openId) {
		UserWeixinInfo wcu = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 通过网页授权获取用户信息
		String result = HttpClientUtils.httpsRequest(requestUrl, "GET", null);
		JsonObject asJsonObject = (JsonObject) new JsonParser().parse(result);
		if (null != asJsonObject) {
			try {
				wcu = new UserWeixinInfo();
				// 用户的标识
				wcu.setOpenid(asJsonObject.get("openid").getAsString());
				// 昵称
				wcu.setNickname(EmojiFilterUtil.filterEmoji(asJsonObject.get(
						"nickname").getAsString()));
				// 性别（1是男性，2是女性，0是未知）
				wcu.setSex(asJsonObject.get("sex").getAsInt() + "");
				// 用户所在国家
				wcu.setCountry(asJsonObject.get("country").getAsString());
				// 用户所在省份
				wcu.setProvince(asJsonObject.get("province").getAsString());
				// 用户所在城市
				wcu.setCity(asJsonObject.get("city").getAsString());
				// 用户头像
				wcu.setHeadimgurl(asJsonObject.get("headimgurl").getAsString());
				wcu.setLanguage(asJsonObject.get("language").getAsString());
				// 用户特权信息
				Gson googleJson = new Gson();
				List<String> privilegeList = googleJson.fromJson(asJsonObject
						.get("privilege").getAsJsonArray(), List.class);
				// wcu.setPrivilegeList(privilegeList);
				if (asJsonObject.has("unionid")) {
					wcu.setUnionid(asJsonObject.get("unionid").getAsString());
				} else if (!asJsonObject.has("unionid")) {
					wcu.setUnionid(asJsonObject.get("openid").getAsString());
				}
				wcu = userWeixinInfoFacade.weixinLogin(wcu);

			} catch (Exception e) {
				e.printStackTrace();
				int errorCode = asJsonObject.get("errcode").getAsInt();
				String errorMsg = asJsonObject.get("errmsg").getAsString();
			}
		}
		return wcu;
	}

	public WechatWebConfig getWechatConfig() {
		return wechatConfig;
	}

	public void setWechatConfig(WechatWebConfig wechatConfig) {
		this.wechatConfig = wechatConfig;
	}

	/**
	 * 获取微信开放平台 网站应用accesstoken
	 * 
	 */
	@Override
	public WeixinOauth2Token getWechatWebAccessToken(String code,
			WebAppConfig webAppConfig, HttpServletRequest request) {
		String appId = webAppConfig.getAppid();
		String appSecret = webAppConfig.getAppsecret();
		WeixinOauth2Token wat = (WeixinOauth2Token) request.getSession()
				.getAttribute("wechatweb_access_token");
		if (wat != null) {
			return wat;
		} else {
			// 拼接请求地址

			String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
			requestUrl = requestUrl.replace("APPID", appId);
			requestUrl = requestUrl.replace("SECRET", appSecret);
			requestUrl = requestUrl.replace("CODE", code);
			// 获取网页授权凭证
			String result = HttpClientUtils.httpsRequest(requestUrl, "GET",
					null);
			JsonObject asJsonObject = (JsonObject) new JsonParser()
					.parse(result);

			if (null != asJsonObject) {
				try {
					wat = new WeixinOauth2Token();
					wat.setAccessToken(asJsonObject.get("access_token")
							.getAsString());
					wat.setExpiresIn(asJsonObject.get("expires_in").getAsInt());
					wat.setRefreshToken(asJsonObject.get("refresh_token")
							.getAsString());
					wat.setOpenId(asJsonObject.get("openid").getAsString());
					wat.setScope(asJsonObject.get("scope").getAsString());
					if (asJsonObject.has("unionid")) {
						wat.setUnionid(asJsonObject.get("unionid")
								.getAsString());
					}
				} catch (Exception e) {
					e.printStackTrace();
					wat = null;
					int errorCode = asJsonObject.get("errcode").getAsInt();
					String errorMsg = asJsonObject.get("errmsg").getAsString();
				}
			}
			return wat;
		}
	}

	/**
	 * 获取微信开放平台网站应用token
	 */
	@Override
	public String getWechatWebAccessTokenString(String code,
			WebAppConfig webAppConfig, HttpServletRequest request) {
		String info = "false";
		WeixinOauth2Token weixinOauth2Token = (WeixinOauth2Token) request
				.getSession().getAttribute("wechatweb_access_token");
		if (weixinOauth2Token != null) {
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			// 通过微信公众号获取用户信息
			UserWeixinInfo wcu = getSNSUserInfo(accessToken, openId);// 设置要传递的参数
			request.getSession().setAttribute("user", wcu);
			request.getSession().setAttribute("wechatweb_access_token",
					weixinOauth2Token);
			info = "true";
		} else {
			if (!"authdeny".equals(code)) {

				weixinOauth2Token = getWechatWebAccessToken(code, webAppConfig,
						request);
				if (null == weixinOauth2Token) {
					info = "false";
				} else {
					// 网页授权接口访问凭证
					String accessToken = weixinOauth2Token.getAccessToken();
					// 用户标识
					String openId = weixinOauth2Token.getOpenId();
					// 获取用户信息
					UserWeixinInfo wcu = getSNSUserInfo(accessToken, openId);// 设置要传递的参数
					request.getSession().setAttribute("user", wcu);
					request.getSession().setAttribute("wechatweb_access_token",
							weixinOauth2Token);
					info = "true";
				}

			}
		}

		return info;
	}

}
