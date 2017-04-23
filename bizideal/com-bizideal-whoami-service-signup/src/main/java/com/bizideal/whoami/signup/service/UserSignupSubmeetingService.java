package com.bizideal.whoami.signup.service;

import com.bizideal.whoami.signup.entity.UserSignupSubmeeting;

/**
 * 报名中用户与子会议的信息
 * @ClassName UserSignupSubmeetingService
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月10日
 */
public interface UserSignupSubmeetingService {

	/**
	 * 插入一条用户与子会议的报名关联信息
	 * @param userSignupSubmeeting
	 * @return
	 */
	int insertUserSignupSubmeeting(UserSignupSubmeeting userSignupSubmeeting);
}
