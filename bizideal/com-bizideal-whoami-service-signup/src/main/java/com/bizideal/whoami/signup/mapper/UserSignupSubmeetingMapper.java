package com.bizideal.whoami.signup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.signup.entity.UserSignupSubmeeting;

public interface UserSignupSubmeetingMapper extends Mapper<UserSignupSubmeeting>{

	/**
	 * 根据报名的主会议记录的ID查询子会议报名信息
	 * @param id
	 * @return
	 */
	List<UserSignupSubmeeting> selectSignUpSubmeets(@Param("id") Integer id);
}
