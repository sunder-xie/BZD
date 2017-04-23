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
 * 微信获取用户信息的拦截器
 * 
 * 
 * @author pc
 *
 */
public class WeiXinInterceptor implements HandlerInterceptor {
	protected String appId;

	public WeiXinInterceptor() {
		super();
	}

	public WeiXinInterceptor(String appId) {
		super();
		this.appId = appId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request.getSession()
				.getAttribute("user");
		// session 中有user 且已绑定手机号 放行
		if (null != userWeixinInfo
				&& StringUtils.isNotBlank(userWeixinInfo.getUserId())) {
			return true;
		}
		// rest风格连参数一起获取 http://localhost:8080/deptmeeting/meet/byhall/1
		// 非rest风格只获取url ?号后面的获取不到
		String allurl = request.getRequestURL().toString();
		// url /deptmeeting/meet/byhall/1 
		String url = request.getRequestURI();
		// 判断app 或pc端 打开的页面
		String ismobile = HttpClientUtils.isApp(request);
		String loginType = "";
		if (Objects.equals("pc", ismobile)) {
			loginType = "pc";
		} else {
			boolean isweixin = HttpClientUtils.isWeixin(request);
			if (isweixin) {
				//在微信中打开的
				loginType = "weixin";
			} else {
				//  手机端其他浏览器
				loginType = "app";
			}
		}
		
		if (Objects.equals("weixin", loginType)) {
			// 判断是否是域名
			String host = "";
			if (allurl.startsWith("https://")) {
				host = allurl.substring(8);
			} else {
				host = allurl.substring(7);
			}

			host = host.substring(0, host.indexOf("/"));
			if (host.contains(":")) {
				host = host.substring(0, host.indexOf(":"));
			}
			if (HttpClientUtils.isDomain(host)) {
               
			} else {
				 //不是域名 放行
				return true;
			}
		}

		
		// /deptmeeting  项目跟路径
		String contenpath = request.getContextPath();

		// System.out.println(contenpath);
		//  请求来源
		url = url.replace(contenpath, "");
		String appid = "wx10aa64b285851297"; // 测试
		appid = "wx1c0a7347e440e6c4";// 正式
		appid = appId;
		String redirect_uri = "";
		//请求来源
		String state = url;
		
		redirect_uri = allurl.replace(url, "");
		String authURL = "/weixinoauth/oauth";
		// http://qx.bizideal.cn/whoami/weixinoauth/oauth
		redirect_uri = redirect_uri + authURL;
		// System.out.println(url);
		// https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${meePlatPath}/wechatOAuth_oauth.shtml&response_type=code&scope=snsapi_userinfo&state=login#wechat_redirect
		// response.sendRedirect("http://blog.csdn.net/kepoon/article/details/53024841?locationNum=9&fps=1");

		if (Objects.equals("app", loginType)) {
			// app登录用户（不是在微信中打开） 获取cookie中的userId 自动登录
			String userId = CookieUtils.getCookieValue(request, "userId");
			redirect_uri = allurl.replace(url, "");
           
			if (StringUtils.isBlank(userId)) {
				
				redirect_uri = redirect_uri + "/weixinoauth/qrcodeLogin?redirect="+state;
			} else {
				// 用户id 存在  
				redirect_uri = redirect_uri + "/weixinoauth/applogin/" + userId
						+ "?redirect=" + state;
			}
			response.sendRedirect(redirect_uri);
		} else if(Objects.equals("weixin", loginType)){
			// 微信网页授权
			String redirectURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ appid
					+ "&redirect_uri="
					+ redirect_uri
					+ "&response_type=code&scope=snsapi_userinfo&state="
					+ state + "#wechat_redirect";
			response.sendRedirect(redirectURL);
		}else if(Objects.equals("pc", loginType)){
			String userId = CookieUtils.getCookieValue(request, "userId");
			redirect_uri = allurl.replace(url, "");

			if (StringUtils.isBlank(userId)) {
				redirect_uri = redirect_uri + "/weixinoauth/qrcodeLogin?redirect="+state;
			} else {
				redirect_uri = redirect_uri + "/weixinoauth/applogin/" + userId
						+ "?redirect=" + state;
			}
			response.sendRedirect(redirect_uri);
		}

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
