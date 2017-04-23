package com.bizideal.whoami.rolemodule.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.rolemodule.entity.UserRoleLink;
import com.bizideal.whoami.rolemodule.mapper.UserRoleLinkMapper;
import com.bizideal.whoami.rolemodule.service.UserRoleLinkService;

/**
 * @author zhu_shangjin
 * @version 2016年12月23日 下午2:42:30
 */
@Service
public class UserRoleLinkServiceImp implements UserRoleLinkService {
	@Autowired
	private UserRoleLinkMapper userRoleLinkMapper;

	@Override
	public int insertUserRole(UserRoleLink userRoleLink) throws Exception {

		return userRoleLinkMapper.insert(userRoleLink);
	}

	@Override
	public int deleteUserRole(UserRoleLink userRoleLink) throws Exception {
		Example example = new Example(UserRoleLink.class);
		example.createCriteria().andEqualTo("userId", userRoleLink.getUserId()).andEqualTo("meetHallId", userRoleLink.getMeetHallId());
		return userRoleLinkMapper.deleteByExample(example);

	}

	/*
	 * 根据会议厅和用户Id更新用户角色中间
	 */
	@Override
	public int updateUserRole(UserRoleLink userRoleLink) throws Exception {
		Example example = new Example(UserRoleLink.class);
		example.createCriteria().andEqualTo("userId", userRoleLink.getUserId()).andEqualTo("meetHallId", userRoleLink.getMeetHallId()).andEqualTo("meetingId", userRoleLink.getMeetingId());
		return userRoleLinkMapper.updateByExample(userRoleLink, example);
	}

	@Override
	public List<Integer> getHallIdByUserId(String userid) {

		return userRoleLinkMapper.getHallIdByUserId(userid);
	}

}
