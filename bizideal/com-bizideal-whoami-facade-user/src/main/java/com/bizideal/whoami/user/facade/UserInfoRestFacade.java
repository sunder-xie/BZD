package com.bizideal.whoami.user.facade;

import net.sf.json.JSONObject;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.dto.UserInfoDto;
import com.bizideal.whoami.user.entity.UserInfo;
import com.bizideal.whoami.user.entity.UserWeixinInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月5日 上午9:51:27
 * @version 1.0
 */
public interface UserInfoRestFacade {

	/**
	 * app注册
	 * 
	 * @param userInfoDto
	 * @return JSONObject
	 */
	JSONObject insertRegister(UserInfoDto userInfoDto);

	/**
	 * 手机密码登陆
	 * 
	 * @param userInfo
	 * @return JSONObject
	 */
	JSONObject loginByPassword(UserInfo userInfo);

	/**
	 * 微信登陆绑定手机号
	 * 
	 * @param userWeixinInfo
	 * @return JSONObject
	 */
	JSONObject updateBindPhone(UserWeixinInfo userWeixinInfo);

	/**
	 * 更新密码
	 * 
	 * @param userWeixinInfo
	 * @return JSONObject
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
	 * 修改真实姓名
	 * 
	 * @param userFriendDto
	 * @return
	 */
	DubboxResult updateRealname(UserFriendDto userFriendDto);

	/**
	 * 修改昵称
	 * 
	 * @param userFriendDto
	 * @return
	 */
	DubboxResult updateNickname(UserFriendDto userFriendDto);

	/**
	 * app手机验证码登陆
	 * 
	 * @param userWeixinInfo
	 * @return JSONObject
	 */
	JSONObject loginByMsgCode(UserWeixinInfo userWeixinInfo);
}
