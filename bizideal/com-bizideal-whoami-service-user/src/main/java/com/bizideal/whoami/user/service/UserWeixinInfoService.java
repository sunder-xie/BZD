package com.bizideal.whoami.user.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.bizideal.whoami.dto.SignUpInfoEditDto;
import com.bizideal.whoami.user.dto.SignUpMeetingDto;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午3:25:52
 * @version 1.0 类说明
 */
public interface UserWeixinInfoService {
	/**
	 * app微信登陆
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	UserWeixinInfo insertWeixinLogin(UserWeixinInfo userWeixinInfo);

	/**
	 * 只给没有注册过账号的人发送验证码
	 * 
	 * @param phone
	 *            接收验证码的手机号
	 * @return
	 */
	JSONObject sendMsgCode(String phone);

	/**
	 * 会议厅创建第二步接口，更新姓名和weixin号
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	JSONObject updateCreateHallStep2(UserWeixinInfo userWeixinInfo);

	/**
	 * 不管存不存在，发送验证码
	 * 
	 * @param phone
	 *            接收验证码的手机号
	 * @return
	 */
	JSONObject sendMsgCodeToAnyOne(String phone);

	/**
	 * 只给已经注册过账号的人发送验证码
	 * 
	 * @param phone
	 *            接收验证码的手机号
	 * @return
	 */
	JSONObject sendMsgCodeToHasRegistered(String phone);

	/**
	 * 用户报名时信息更新
	 * 
	 * @param weixinInfo
	 * @return
	 */
	int updateSignUpInfo(UserWeixinInfo weixinInfo);

	/**
	 * 通过userId查找用户
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	UserWeixinInfo selectByUserId(String userId);

	/**
	 * 根据用户id批量查询
	 * 
	 * @param userIds
	 *            需要查询的userid列表
	 * @return
	 */
	List<UserWeixinInfo> selectByUserIds(List<String> userIds);

	/**
	 * 根据会议id查询报名信息
	 * 
	 * @param signUpMeetingDto
	 * @return
	 */
	PageInfo<UserWeixinInfo> selectBySignUp(SignUpMeetingDto signUpMeetingDto);

	/**
	 * 用户修改报名
	 * 
	 * @param signUpInfoEditDto
	 * @return
	 */
	int updateSignup(SignUpInfoEditDto signUpInfoEditDto);
}
