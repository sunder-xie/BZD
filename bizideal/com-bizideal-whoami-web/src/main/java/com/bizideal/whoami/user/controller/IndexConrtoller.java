package com.bizideal.whoami.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author zhu_shangjin
 * @version 2016年12月21日 下午12:53:59 跳转首页
 */
@Controller
@RequestMapping
public class IndexConrtoller {
	@RequestMapping
	public String index(HttpServletRequest request,HttpServletResponse response) {

		return "login";
	}

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	@RequestMapping("signIn")
	public String signIn() {
		return "signIn";
	}
	
	
	@RequestMapping("wechaterror")
	public String wechaterror() {
		return "wechaterror";
	}
	
	@RequestMapping("appNouser")
	public String appNouser() {
		return "appNouser";
	}
		

	
	
	
}
