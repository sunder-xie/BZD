package com.bizideal.whoami.rolemodule.service;

import java.util.List;

import com.bizideal.whoami.rolemodule.entity.UserRoleLink;

/**
 * @author zhu_shangjin
 * @version 2016年12月23日 下午2:39:37
 */
public interface UserRoleLinkService {
	/**
	 * 新增用户和主会议角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	int insertUserRole(UserRoleLink userRoleLink) throws Exception;

	/**
	 * 删除用户和主会议角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	int deleteUserRole(UserRoleLink userRoleLink) throws Exception;

	/**
	 * 更新用户和主会议角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	int updateUserRole(UserRoleLink userRoleLink) throws Exception;

	/**
	 * 获取用户管理的主会议id
	 * 
	 * @param userid
	 * @return
	 */
	List<Integer> getHallIdByUserId(String userid);

}
