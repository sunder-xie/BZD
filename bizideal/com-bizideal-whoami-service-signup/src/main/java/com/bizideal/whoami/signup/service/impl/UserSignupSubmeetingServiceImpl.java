package com.bizideal.whoami.signup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.signup.entity.UserSignupSubmeeting;
import com.bizideal.whoami.signup.mapper.UserSignupSubmeetingMapper;
import com.bizideal.whoami.signup.service.UserSignupSubmeetingService;

@Component("userSignupSubmeetingService")
public class UserSignupSubmeetingServiceImpl implements UserSignupSubmeetingService{

	@Autowired
	UserSignupSubmeetingMapper userSignupSubmeetingMapper;
	
	@Override
	public int insertUserSignupSubmeeting(
			UserSignupSubmeeting userSignupSubmeeting) {
		return userSignupSubmeetingMapper.insertSelective(userSignupSubmeeting);
	}

}
