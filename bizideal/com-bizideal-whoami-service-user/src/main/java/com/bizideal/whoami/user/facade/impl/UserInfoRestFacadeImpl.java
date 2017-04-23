package com.bizideal.whoami.user.facade.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.dto.UserInfoDto;
import com.bizideal.whoami.user.entity.UserInfo;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.enums.UserEnum;
import com.bizideal.whoami.user.facade.UserInfoRestFacade;
import com.bizideal.whoami.user.service.UserInfoService;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月5日 上午9:53:57
 * @version 1.0
 */
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
@Component("userInfoRestFacade")
public class UserInfoRestFacadeImpl implements UserInfoRestFacade {

	@Autowired
	private UserInfoService userInfoService;
	@Value("${fastdfsurl}")
	private String fastdfsurl;

	@POST
	@Path("/register")
	@Override
	public JSONObject insertRegister(UserInfoDto userInfoDto) {
		return userInfoService.insertRegister(userInfoDto);
	}

	@POST
	@Path("/loginByPwd")
	@Override
	public JSONObject loginByPassword(UserInfo userInfo) {
		return userInfoService.loginByPassword(userInfo);
	}

	@Override
	@POST
	@Path("/bindPhone")
	public JSONObject updateBindPhone(UserWeixinInfo userWeixinInfo) {
		JSONObject json = userInfoService.updateBindPhone(userWeixinInfo);
		if ("0".equals(json.get("errcode").toString())) {
			// 绑定手机号成功,hall表示会议厅,meeting表示会议界面
			JSONObject user = json.getJSONObject("user");
			UserWeixinInfo weixinInfo = (UserWeixinInfo) JSONObject.toBean(
					user, UserWeixinInfo.class);
			if (StringUtils.isNotBlank(weixinInfo.getHeadimgurl())
					&& !StringUtils.startsWith(weixinInfo.getHeadimgurl(),
							"http://wx")) {
				weixinInfo.setHeadimgurl(fastdfsurl
						+ weixinInfo.getHeadimgurl());
			}
			json.put("user", weixinInfo);
			// 会议url
			json.put("meetingUrl", "user/returnPage?loginType=app&userId="
					+ weixinInfo.getUserId() + "&type=meeting");
			// 会议厅url
			json.put("meetingHallUrl", "user/returnPage?loginType=app&userId="
					+ weixinInfo.getUserId() + "&type=hall");
		} else {
			json.put("user", null);
			json.put("meetingUrl",
					"user/returnPage?loginType=weixin&userId=9527");
			json.put("meetingHallUrl",
					"user/returnPage?loginType=weixin&userId=9527");
		}
		return json;
	}

	@POST
	@Path("/updatePwd")
	@Override
	public JSONObject updatePassword(UserWeixinInfo userWeixinInfo) {
		return userInfoService.updatePassword(userWeixinInfo);
	}

	@POST
	@Path("/updateRealName")
	@Override
	public DubboxResult updateRealname(UserFriendDto userFriendDto) {
		int result = userInfoService.updateRealname(userFriendDto.getUserId(),
				userFriendDto.getRealName());
		if (result == 0) {
			return DubboxResult.build("1", "更新失败", null);
		}
		return DubboxResult.build("0", "ok", null);
	}

	@POST
	@Path("/updateNickname")
	@Override
	public DubboxResult updateNickname(UserFriendDto userFriendDto) {
		int result = userInfoService.updateNickname(userFriendDto.getUserId(),
				userFriendDto.getNickname());
		if (result == 0) {
			return DubboxResult.build("1", "更新失败", null);
		}
		return DubboxResult.build("0", "ok", null);
	}

	@POST
	@Path("/loginByMsgCode")
	@Override
	public JSONObject loginByMsgCode(UserWeixinInfo userWeixinInfo) {
		JSONObject json = new JSONObject();
		if (null == userWeixinInfo
				|| StringUtils.isBlank(userWeixinInfo.getPhone())
				|| StringUtils.isBlank(userWeixinInfo.getMsgCode())) {
			json.put("errcode", UserEnum.USER_PARAMETER_EMTPY.getErrcode());
			json.put("errmsg", UserEnum.USER_PARAMETER_EMTPY.getErrmsg());
			return json;
		}
		return userInfoService.loginByMsgCode(userWeixinInfo);
	}

	@POST
	@Path("/forgetPwd")
	@Override
	public JSONObject updateForgetPassword(UserWeixinInfo userWeixinInfo) {
		return userInfoService.updateForgetPassword(userWeixinInfo);
	}

}
