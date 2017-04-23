package com.bizideal.whoami.user.facade.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.user.dto.UserInfoDto;
import com.bizideal.whoami.user.entity.UserInfo;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserInfoFacade;
import com.bizideal.whoami.user.service.UserInfoService;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月5日 上午9:52:40
 * @version 1.0
 */
@Component("userInfoFacade")
public class UserInfoFacadeImpl implements UserInfoFacade {

	@Autowired
	private UserInfoService userInfoService;

	@Override
	public JSONObject insertRegister(UserInfoDto userInfoDto) {
		return userInfoService.insertRegister(userInfoDto);
	}

	@Override
	public JSONObject loginByPassword(UserInfo userInfo) {
		return userInfoService.loginByPassword(userInfo);
	}

	@Override
	public JSONObject updateBindPhone(UserWeixinInfo userWeixinInfo) {
		return userInfoService.updateBindPhone(userWeixinInfo);
	}

	@Override
	public JSONObject updatePassword(UserWeixinInfo userWeixinInfo) {
		return userInfoService.updatePassword(userWeixinInfo);
	}

	@Override
	public int updateImgAndName(UserWeixinInfo userWeixinInfo) {
		return userInfoService.updateImgAndName(userWeixinInfo);
	}

	@Override
	public int updateHeadimgurl(String userId, String headimgurl) {
		return userInfoService.updateHeadimgurl(userId, headimgurl);
	}

}
