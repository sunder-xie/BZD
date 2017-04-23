package com.bizideal.whoami.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 作者 sy
 * @data 创建时间：2017-3-21 09:29:23
 * @version 1.0
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	/**
	 * 跳转会员管理页面
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String memberFunc() {

		return "member/member-func(new)";
	}
}
