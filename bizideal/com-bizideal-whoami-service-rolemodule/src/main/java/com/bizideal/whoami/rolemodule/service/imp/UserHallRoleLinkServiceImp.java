package com.bizideal.whoami.rolemodule.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.rolemodule.entity.UserHallRoleLink;
import com.bizideal.whoami.rolemodule.entity.UserRoleLink;
import com.bizideal.whoami.rolemodule.mapper.UserHallRoleLinkMapper;
import com.bizideal.whoami.rolemodule.service.UserHallRoleLinkService;

/**
 * @author zhu_shangjin
 * @version 2017年2月27日 上午9:09:35
 */
@Service
public class UserHallRoleLinkServiceImp implements UserHallRoleLinkService {
	@Autowired
	UserHallRoleLinkMapper userHallRoleLinkMapper;

	@Override
	public int batchsaveUserHallRoleLink(List<UserHallRoleLink> userRoles)
			throws Exception {

		return userHallRoleLinkMapper.batchsaveUserHallRoleLink(userRoles);
	}

	@Override
	public long countUseridHallId(String userid, Integer hallid) {

		return userHallRoleLinkMapper.countUseridHallId(userid, hallid);
	}

	@Override
	public List<Integer> getHallIdByUserId(String userid) {

		return userHallRoleLinkMapper.getHallIdByUserId(userid);
	}

	@Override
	public int insertUserHallRole(UserHallRoleLink userhallrolelink)
			throws Exception {

		return userHallRoleLinkMapper.insert(userhallrolelink);
	}

	@Override
	public int deleteUserHallRole(UserHallRoleLink userhallrolelink)
			throws Exception {
		Example example = new Example(UserHallRoleLink.class);
		example.createCriteria()
				.andEqualTo("userId", userhallrolelink.getUserId())
				.andEqualTo("meetHallId", userhallrolelink.getMeetHallId());
		return userHallRoleLinkMapper.deleteByExample(example);
	}

	@Override
	public int updateUserHallRole(UserHallRoleLink userhallrolelink)
			throws Exception {
		Example example = new Example(UserHallRoleLink.class);
		example.createCriteria()
				.andEqualTo("userId", userhallrolelink.getUserId())
				.andEqualTo("meetHallId", userhallrolelink.getMeetHallId());
		return userHallRoleLinkMapper
				.updateByExample(userhallrolelink, example);
	}

}
