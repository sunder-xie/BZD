package com.bizideal.whoami.user.service;

import net.sf.json.JSONObject;

import com.bizideal.whoami.user.dto.UserInfoDto;
import com.bizideal.whoami.user.entity.UserInfo;
import com.bizideal.whoami.user.entity.UserWeixinInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月5日 上午9:27:23
 * @version 1.0
 */
public interface UserInfoService {

	/**
	 * 注册用
	 * 
	 * @param userInfoDto
	 * @return
	 */
	JSONObject insertRegister(UserInfoDto userInfoDto);

	/**
	 * 微信登陆绑定手机号
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	JSONObject updateBindPhone(UserWeixinInfo userWeixinInfo);

	/**
	 * 通过手机号和密码登陆
	 * 
	 * @param userInfo
	 * @return
	 */
	JSONObject loginByPassword(UserInfo userInfo);

	/**
	 * 修改密码
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	JSONObject updatePassword(UserWeixinInfo userWeixinInfo);
	
	/**
	 * 忘记密码
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	JSONObject updateForgetPassword(UserWeixinInfo userWeixinInfo);

	/**
	 * 更新头像和昵称
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	int updateImgAndName(UserWeixinInfo userWeixinInfo);

	/**
	 * 修改真实姓名
	 * 
	 * @param userFriendDto
	 * @return
	 */
	int updateRealname(String userId, String realName);

	/**
	 * 修改昵称
	 * 
	 * @param userId
	 *            用户id
	 * @param nickname
	 *            昵称
	 * @return
	 */
	int updateNickname(String userId, String nickname);

	/**
	 * 修改头像地址
	 * 
	 * @param userId
	 *            用户id
	 * @param headimgurl
	 *            新头像地址
	 * @return
	 */
	int updateHeadimgurl(String userId, String headimgurl);

	/**
	 * app手机验证码登陆
	 * 
	 * @param userWeixinInfo
	 * @return JSONObject
	 */
	JSONObject loginByMsgCode(UserWeixinInfo userWeixinInfo);
}
