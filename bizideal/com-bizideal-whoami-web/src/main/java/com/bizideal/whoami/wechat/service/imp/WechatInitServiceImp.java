package com.bizideal.whoami.wechat.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.utils.EmojiFilterUtil;
import com.bizideal.whoami.wechat.config.WechatWebConfig;
import com.bizideal.whoami.wechat.service.CoreService;
import com.bizideal.whoami.wechat.service.WechatInitService;
import com.bizideal.whoami.wechat.utils.PropertiesUtils;
import com.bizideal.whoami.wechat.utils.WeiXinInterfaceUtil;
import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.bizideal.whoami.weixin.entity.WechatConfig;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;
import com.bizideal.whoami.weixin.facade.WechatUserReadFacade;
import com.bizideal.whoami.weixin.facade.WechatUserWriteFacade;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author zhu_shangjin
 * @version 2017年1月3日 下午3:15:30
 */
@Service
public class WechatInitServiceImp implements WechatInitService {
	@Autowired
	CoreService coreService;
	@Autowired
	WechatUserReadFacade wechatUserReadFacade;
	@Autowired(required=false)
	WechatWebConfig wechatConfig;
	@Autowired
	WechatUserWriteFacade wechatUserWriteFacade;

	@Override
	public void initWechatTotalUser() throws Exception {
		Gson googleJson = new Gson();
		String requestUrl = PropertiesUtils.getInstance().getValue(
				"user_get_url");
		WechatAccessToken wechatAccessToken = coreService.getWechatAccessToken(
				wechatConfig.getAppId(), wechatConfig.getAppSecret());

		requestUrl = requestUrl.replace("ACCESS_TOKEN",
				wechatAccessToken.getToken());
		JsonObject jsonObject = WeiXinInterfaceUtil.getHttpRequest(requestUrl);
		// 从微信拉去的 openid 的集合
		List<String> getFromWechat = new ArrayList<String>();

		int countFromWechat = 0;
		// 从微信拉去用户列表不为null
		if (jsonObject != null && !jsonObject.has("errcode")) {
			// total 关注人数 关注该公众账号的总用户数
			int total = jsonObject.get("total").getAsInt();
			// 拉取的OPENID个数，最大值为10000
			int count = jsonObject.get("count").getAsInt();
			if (total > 10000) {
				// 关注总人数 大于 10000
				boolean totalflag = true;
				while (totalflag) {
					String nextopenid = jsonObject.get("next_openid")
							.getAsString();
					if (jsonObject != null && !jsonObject.has("errcode")
							&& jsonObject.has("data")) {
						JsonObject openids = jsonObject.get("data")
								.getAsJsonObject();
						JsonArray openidsArray = openids.get("openid")
								.getAsJsonArray();
						getFromWechat.addAll(googleJson.fromJson(openidsArray,
								List.class));
					}
					countFromWechat = count + countFromWechat;
					if (countFromWechat < total) {
						requestUrl = PropertiesUtils.getInstance().getValue(
								"user_get_urlNext");
						requestUrl = requestUrl.replace("ACCESS_TOKEN",
								wechatAccessToken.getToken()).replace(
								"NEXT_OPENID", nextopenid);
						jsonObject = WeiXinInterfaceUtil
								.getHttpRequest(requestUrl);
					} else {
						totalflag = false;
					}

				}

			} else {
				// 关注总人数 小于 10000
				if (jsonObject.has("data")) {
					JsonObject openids = jsonObject.get("data")
							.getAsJsonObject();
					JsonArray openidsArray = openids.get("openid")
							.getAsJsonArray();
					getFromWechat = googleJson.fromJson(openidsArray,
							List.class);
				}
			}
		}

		// 找出已关注但在 wechat_user_info 表中不存在 的用户openids
		List<String> getUserFromDB = wechatUserReadFacade
				.getUserFromDBByOpenids(getFromWechat);
		List<String> all = new ArrayList<String>();
		all.addAll(getFromWechat);
		all.removeAll(getUserFromDB);
		// wechat_total_user 数据库中存在的记录
		List<String> getFromDB = wechatUserReadFacade
				.getFromDBByOpenids(getFromWechat);

		getFromWechat.removeAll(getFromDB);
		// 需要新增的
		List<String> insertList = getFromWechat;
		if (insertList.size() > 0) {
			wechatUserWriteFacade.batchInsertTotalUser(getFromWechat);
		}
		initWechatUserInfo(all,wechatAccessToken.getToken());
	}

	@Override
	public void initWechatUserInfo(List<String> openids,String token) throws Exception {
		JsonObject jsonObjOpenid = null;
		JsonArray jsonArOpenids = new JsonArray();
		for(String openid : openids){
			jsonObjOpenid	= new JsonObject();
			jsonObjOpenid.addProperty("openid", openid);
			jsonObjOpenid.addProperty("lang", "zh-CN");
			jsonArOpenids.add(jsonObjOpenid);
		}
		JsonObject jsonObjUserList = new JsonObject();
		jsonObjUserList.add("user_list", jsonArOpenids);
		// 将菜单对象转换成json字符串
		String jsonString = jsonObjUserList.toString();
		
		String	requestUrl = PropertiesUtils.getInstance().getValue("user_info_batchget_url");
		requestUrl = requestUrl.replace("ACCESS_TOKEN", token); 
		JsonObject jsonObject = null;
			 jsonObject = WeiXinInterfaceUtil.postHttpRequest(requestUrl, jsonString);
			if (null != jsonObject) {
				// 判断是否有这个字段
				if (jsonObject.has("errcode")) {
					
				} else {
					JsonArray jsonArUserInfoList = jsonObject.get("user_info_list").getAsJsonArray();
					JsonObject jsonObjUserInfo = null;
					WechatUserInfo userInfo = null;
					List<WechatUserInfo> users = new ArrayList<WechatUserInfo>();
					Gson gson = new Gson();
					for(int i = 0; i< jsonArUserInfoList.size() ; i++){
						jsonObjUserInfo = jsonArUserInfoList.get(i).getAsJsonObject();
						String name =EmojiFilterUtil.filterEmoji(jsonObjUserInfo.get("nickname").getAsString());
						jsonObjUserInfo.addProperty("nickname", name);
						 userInfo = gson.fromJson(jsonObjUserInfo, WechatUserInfo.class);
						 users.add(userInfo);
						
					}
					wechatUserWriteFacade.batchSaveUser(users);
					
				}
			}
	}

}
