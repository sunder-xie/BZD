package com.bizideal.whoami.user.controller;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.signup.entity.DietInfo;
import com.bizideal.whoami.signup.facade.DietInfoFacade;


/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月4日 下午1:37:50
 * @version 1.0
 */
@Controller
@RequestMapping("/diet")
public class DietInfoController {

	private Logger logger = LoggerFactory.getLogger(DietInfoController.class);

	@Autowired
	private DietInfoFacade dietInfoFacade;

	@RequestMapping("/selectByPageSelf")
	@ResponseBody
	public JSONObject selectByPageSelf(@RequestBody DietInfo dietInfo) {
		return dietInfoFacade.selectByPageSelf(dietInfo);
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
