package com.bizideal.whoami.user.facade;

import net.sf.json.JSONObject;

import com.bizideal.whoami.user.dto.UserInfoDto;
import com.bizideal.whoami.user.entity.UserInfo;
import com.bizideal.whoami.user.entity.UserWeixinInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午2:37:55
 * @version 1.0
 */
public interface UserInfoFacade {

	/**
	 * 手机注册
	 * 
	 * @param userInfoDto
	 * @return
	 */
	JSONObject insertRegister(UserInfoDto userInfoDto);

	/**
	 * 密码登陆
	 * 
	 * @param userInfo
	 * @return
	 */
	JSONObject loginByPassword(UserInfo userInfo);

	/**
	 * 微信登陆绑定手机号
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	JSONObject updateBindPhone(UserWeixinInfo userWeixinInfo);

	/**
	 * 更新密码
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	JSONObject updatePassword(UserWeixinInfo userWeixinInfo);

	/**
	 * 更新头像+昵称+真实姓名
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	int updateImgAndName(UserWeixinInfo userWeixinInfo);

	/**
	 * app用更新头像
	 * 
	 * @param userId
	 * @param headimgurl
	 * @return
	 */
	int updateHeadimgurl(String userId, String headimgurl);

}
