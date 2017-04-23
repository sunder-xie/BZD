package com.bizideal.whoami.wechat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.wechat.config.WechatWebConfig;
import com.bizideal.whoami.wechat.service.CoreService;
import com.bizideal.whoami.wechat.service.WechatNewsAndImageService;
import com.bizideal.whoami.wechat.utils.WeiXinInterfaceUtil;
import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.google.gson.JsonObject;

/**
 * @author zhu_shangjin
 * @version 2017年1月17日 下午5:17:11
 */
@Service
public class WechatNewsAndImageServicesImp implements
		WechatNewsAndImageService {
	@Autowired
	WechatWebConfig wechatConfig;
	@Autowired
	CoreService coreService;

	@Override
	public String getNews() {
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
		WechatAccessToken wechatAccessToken = coreService.getWechatAccessToken(wechatConfig.getAppId(), wechatConfig.getAppSecret());
		url = url.replace("ACCESS_TOKEN", wechatAccessToken.getToken());
		String jsonString = "{\"type\":\"news\",\"offset\":0,\"count\":2}";
		JsonObject jsonObject = null;
		try {
			 jsonObject = WeiXinInterfaceUtil.postHttpRequest(url, jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	public String getImage() {
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
		WechatAccessToken wechatAccessToken = coreService.getWechatAccessToken(wechatConfig.getAppId(), wechatConfig.getAppSecret());
		url = url.replace("ACCESS_TOKEN", wechatAccessToken.getToken());
		String jsonString = "{\"type\":\"image\",\"offset\":0,\"count\":2}";
		JsonObject jsonObject = null;
		try {
			 jsonObject = WeiXinInterfaceUtil.postHttpRequest(url, jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
