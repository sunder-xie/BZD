package com.bizideal.whoami.user.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName UserInterceptor
 * @Description TODO(用户登录连接器)
 * @Author Zj.Qu
 * @Date 2016-12-13 16:22:38
 */
public class UserInterceptor implements HandlerInterceptor {

	private final String USER = "user";
	private Object sessionOBJ = null;
	
	// 拦截前处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+ "/";  
		sessionOBJ = request.getSession().getAttribute(USER);
		if (sessionOBJ != null) {
			return true;
		}
		response.sendRedirect(url+"/index.html");
		return false;
	}

	// 拦截后处理
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {

	}

	// 全部完成后处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
			throws Exception {

	}

}
