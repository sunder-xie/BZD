package com.bizideal.whoami.wechat.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.utils.CookieUtils;
import com.bizideal.whoami.utils.HttpClientUtils;

/**
 * 会议厅链接 和 邀请管理员链接的拦截器 用户没登陆 则定向到登录页面
 * 
 * 
 * @author pc
 *
 */
public class WechatWebAppInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 获取访问的url
		String allurl = request.getRequestURL().toString();

		// url /whoami/meet/byhall/1
		String url = request.getRequestURI();
		// 从session中获取用户
		UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request.getSession()
				.getAttribute("user");
		if (null != userWeixinInfo
				&& StringUtils.isNotBlank(userWeixinInfo.getUserId())) {
			//用户存在且用户id不为null 放行 
			return true;
		}
		// /whoami
		String contenpath = request.getContextPath();
		//重定向的url
		url = url.replace(contenpath, "");
         //定向到二维码登录页面    WeixinOauthController中qrcodeLogin方法
		response.sendRedirect(contenpath+"/weixinoauth/qrcodeLogin?redirect=" + url);

		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
