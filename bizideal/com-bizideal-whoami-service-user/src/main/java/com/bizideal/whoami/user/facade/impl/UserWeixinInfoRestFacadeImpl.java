package com.bizideal.whoami.user.facade.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.enums.UserEnum;
import com.bizideal.whoami.user.facade.UserWeixinInfoRestFacade;
import com.bizideal.whoami.user.service.UserWeixinInfoService;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午3:38:13
 * @version 1.0
 */
@Path("/weixin")
@Component("userWeixinInfoRestFacade")
public class UserWeixinInfoRestFacadeImpl implements UserWeixinInfoRestFacade {

	@Autowired
	private UserWeixinInfoService userWeixinInfoService;
	@Value("${fastdfsurl}")
	private String fastdfsurl;

	/* app登陆微信 */
	@POST
	@Path("/weixinLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public JSONObject weixinLogin(UserWeixinInfo userWeixinInfo) {
		JSONObject json = new JSONObject();
		UserWeixinInfo weixinInfo = userWeixinInfoService
				.insertWeixinLogin(userWeixinInfo);
		if (null == weixinInfo) {
			json.put("errcode", UserEnum.USER_LOGIN_FAILED.getErrcode());
			json.put("errmsg", UserEnum.USER_LOGIN_FAILED.getErrmsg());
			json.put("user", null);
			json.put("meetingUrl",
					"user/returnPage?loginType=weixin&userId=9527");
			json.put("meetingHallUrl",
					"user/returnPage?loginType=weixin&userId=9527");
			return json;
		}
		if (null != weixinInfo
				&& StringUtils.isNotBlank(weixinInfo.getHeadimgurl())
				|| !StringUtils.startsWith(weixinInfo.getHeadimgurl(),
						"http://wx")) {
			weixinInfo.setHeadimgurl(fastdfsurl + weixinInfo.getHeadimgurl());
		}
		if (StringUtils.isBlank(weixinInfo.getUserId())) {
			// 没有绑定过手机号
			json.put("meetingUrl",
					"user/returnPage?loginType=weixin&userId=9527");
			json.put("meetingHallUrl",
					"user/returnPage?loginType=weixin&userId=9527");
		} else {
			// 已经绑定过手机号
			// 会议url
			json.put("meetingUrl", "user/returnPage?loginType=app&userId="
					+ weixinInfo.getUserId() + "&type=meeting");
			// 会议厅url
			json.put("meetingHallUrl", "user/returnPage?loginType=app&userId="
					+ weixinInfo.getUserId() + "&type=hall");
		}
		json.put("errcode", UserEnum.USER_OK.getErrcode());
		json.put("errmsg", UserEnum.USER_OK.getErrmsg());
		json.put("user", weixinInfo);
		return json;
	}

	@Override
	@POST
	@Path("/sendMsg/{phone}")
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	public JSONObject sendMsgCode(@PathParam("phone") String phone) {
		return userWeixinInfoService.sendMsgCode(phone);
	}

	@POST
	@Path("/getById/{userId}")
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public UserWeixinInfo selectByUserId(@PathParam("userId") String userId) {
		return userWeixinInfoService.selectByUserId(userId);
	}

	@POST
	@Path("/sendMsgToAnyOne/{phone}")
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public JSONObject sendMsgCodeToAnyOne(@PathParam("phone") String phone) {
		return userWeixinInfoService.sendMsgCodeToAnyOne(phone);
	}

	@POST
	@Path("/sendMsgToHasReg/{phone}")
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public JSONObject sendMsgCodeToHasRegistered(
			@PathParam("phone") String phone) {
		return userWeixinInfoService.sendMsgCodeToHasRegistered(phone);
	}

}
