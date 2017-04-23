package com.bizideal.whoami.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.wechat.service.WechatNewsAndImageService;

/**
 * 测试使用
 * @author zhu_shangjin
 * @version 2017年1月17日 下午5:12:10
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
	@Autowired
	WechatNewsAndImageService wechatNewsAndImageService;

	@ResponseBody()
	@RequestMapping(value = "/getnews", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String getNews() {
		return wechatNewsAndImageService.getNews();
		

	}
    @ResponseBody
	@RequestMapping(value = "/getimage", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String getimage() {
		return wechatNewsAndImageService.getImage();
		

	}

	
}
