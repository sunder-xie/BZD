package com.bizideal.whoami.user.facade.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.dto.SignUpInfoEditDto;
import com.bizideal.whoami.user.dto.SignUpMeetingDto;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.user.service.UserWeixinInfoService;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午3:35:25
 * @version 1.0
 */
@Component("userWeixinInfoFacade")
public class UserWeixinInfoFacadeImpl implements UserWeixinInfoFacade {

	@Autowired
	private UserWeixinInfoService userWeixinInfoService;

	@Override
	public UserWeixinInfo weixinLogin(UserWeixinInfo userWeixinInfo) {
		return userWeixinInfoService.insertWeixinLogin(userWeixinInfo);
	}

	@Override
	public JSONObject sendMsgCode(String phone) {
		return userWeixinInfoService.sendMsgCode(phone);
	}

	@Override
	public JSONObject sendMsgCodeToAnyOne(String phone) {
		return userWeixinInfoService.sendMsgCodeToAnyOne(phone);
	}

	@Override
	public JSONObject sendMsgCodeToHasRegistered(String phone) {
		return userWeixinInfoService.sendMsgCodeToHasRegistered(phone);
	}

	@Override
	public JSONObject updateCreateHallStep2(UserWeixinInfo userWeixinInfo) {
		return userWeixinInfoService.updateCreateHallStep2(userWeixinInfo);
	}

	@Override
	public UserWeixinInfo selectByUserId(String userId) {
		return userWeixinInfoService.selectByUserId(userId);
	}

	@Override
	public List<UserWeixinInfo> selectByUserIds(List<String> userIds) {
		return userWeixinInfoService.selectByUserIds(userIds);
	}

	@Override
	public PageInfo<UserWeixinInfo> selectBySignUp(SignUpMeetingDto signUpMeetingDto) {
		return userWeixinInfoService.selectBySignUp(signUpMeetingDto);
	}

	@Override
	public boolean userSignUp(SignUpInfoDto signUpInfoDto) {
		UserWeixinInfo weixinInfo = new UserWeixinInfo();
		weixinInfo.setUserId(signUpInfoDto.getUserId());
		// 首先判断数据库中此用户有没有默认邮箱
		UserWeixinInfo user = userWeixinInfoService.selectByUserId(signUpInfoDto.getUserId());
		if (StringUtils.isBlank(user.getEmail())) {
			// 没有设置过默认邮箱
			weixinInfo.setEmail(signUpInfoDto.getEmail());
		}
		if (StringUtils.isNotBlank(signUpInfoDto.getSex())) {
			weixinInfo.setSex(signUpInfoDto.getSex());
		} else {
			weixinInfo.setSex(user.getSex());
		}
		if (StringUtils.isNotBlank(signUpInfoDto.getRealName())) {
			weixinInfo.setRealName(signUpInfoDto.getRealName());
		} else {
			weixinInfo.setRealName(user.getRealName());
		}
		// 插入数据到用户微信表中(姓名/性别/邮箱)
		int flag = userWeixinInfoService.updateSignUpInfo(weixinInfo);
		return flag > 0;
	}

	@Override
	public boolean updateSignup(SignUpInfoEditDto signUpInfoEditDto) {
		int updateSignup = userWeixinInfoService.updateSignup(signUpInfoEditDto);
		return updateSignup != 0;
	}

}
