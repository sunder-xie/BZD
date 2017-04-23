package com.bizideal.whoami.user.facade;

import net.sf.json.JSONObject;

import com.bizideal.whoami.user.entity.UserWeixinInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午4:13:26
 * @version 1.0 类说明
 */
public interface UserWeixinInfoRestFacade {

	/**
	 * app微信登陆
	 * 
	 * @param userWeixinInfo
	 * @return JSONObject
	 */
	JSONObject weixinLogin(UserWeixinInfo userWeixinInfo);

	/**
	 * 给未注册的人发送验证码
	 * 
	 * @param phone
	 *            手机号
	 * @return
	 */
	JSONObject sendMsgCode(String phone);

	/**
	 * 通过userId查找用户
	 * 
	 * @param userId
	 * @return UserWeixinInfo
	 */
	UserWeixinInfo selectByUserId(String userId);

	/**
	 * 不管存不存在，发送验证码
	 * 
	 * @param phone
	 *            接收验证码的手机号
	 * @return JSONObject
	 */
	JSONObject sendMsgCodeToAnyOne(String phone);

	/**
	 * 只给已经注册过账号的人发送验证码
	 * 
	 * @param phone
	 *            接收验证码的手机号
	 * @return JSONObject
	 */
	JSONObject sendMsgCodeToHasRegistered(String phone);
}
