package com.bizideal.whoami.user.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午4:09:26
 * @version 1.0
 */
@Controller
@RequestMapping("/weixin")
public class UserWeixinController {

	private Logger logger = LoggerFactory.getLogger(UserWeixinController.class);

	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	
	@Value("${fastdfsurl}")
	private String fastdfsurl ;

	@RequestMapping("/sendMsg/{phone}")
	@ResponseBody
	public JSONObject sendMsgCode(@PathVariable("phone") String phone) {
		return userWeixinInfoFacade.sendMsgCode(phone);
	}

	@RequestMapping("/sendMsgToAny/{phone}")
	@ResponseBody
	public JSONObject sendMsgCodeToAnyOne(@PathVariable("phone") String phone) {
		return userWeixinInfoFacade.sendMsgCodeToAnyOne(phone);
	}

	@RequestMapping("/sendMsgToHasReg/{phone}")
	@ResponseBody
	public JSONObject sendMsgCodeToHasRegistered(
			@PathVariable("phone") String phone) {
		return userWeixinInfoFacade.sendMsgCodeToHasRegistered(phone);
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JSONObject exceptionHandler(Exception e) {
		JSONObject json = new JSONObject();
		json.put("errcode", "8001");
		json.put("errmsg", e.getMessage());
		e.printStackTrace();
		logger.error(e.getMessage());
		return json;
	}
}
