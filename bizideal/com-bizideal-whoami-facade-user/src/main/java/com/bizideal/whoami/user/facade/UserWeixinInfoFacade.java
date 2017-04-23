package com.bizideal.whoami.user.facade;

import java.util.List;

import net.sf.json.JSONObject;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.dto.SignUpInfoEditDto;
import com.bizideal.whoami.user.dto.SignUpMeetingDto;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午3:10:21
 * @version 1.0
 */
public interface UserWeixinInfoFacade {

	/**
	 * 微信登陆
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	UserWeixinInfo weixinLogin(UserWeixinInfo userWeixinInfo);

	/**
	 * 给未注册的人发送验证码
	 * 
	 * @param phone
	 *            手机号
	 * @return
	 */
	JSONObject sendMsgCode(String phone);

	/**
	 * 发送验证码，无限制条件
	 * 
	 * @param phone
	 *            手机号
	 * @return
	 */
	JSONObject sendMsgCodeToAnyOne(String phone);

	/**
	 * 给已注册用户发送验证码
	 * 
	 * @param phone
	 *            手机号
	 * @return
	 */
	JSONObject sendMsgCodeToHasRegistered(String phone);

	/**
	 * 创建会议厅第二步认证
	 * 
	 * @param userWeixinInfo
	 * @return
	 */
	JSONObject updateCreateHallStep2(UserWeixinInfo userWeixinInfo);

	/**
	 * 通过userid查询查找用户
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
	 * 用户报名接口
	 * 
	 * @param signUpInfoDto
	 * @return 返回int，大于0表示成功
	 */
	boolean userSignUp(SignUpInfoDto signUpInfoDto);

	/**
	 * 用户修改报名
	 * 
	 * @param signUpInfoEditDto userId,realName,sex,phone
	 * @return
	 */
	boolean updateSignup(SignUpInfoEditDto signUpInfoEditDto);
}
