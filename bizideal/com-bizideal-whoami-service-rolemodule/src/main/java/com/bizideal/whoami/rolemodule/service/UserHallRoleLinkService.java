package com.bizideal.whoami.rolemodule.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.UserHallRoleLink;
import com.bizideal.whoami.rolemodule.entity.UserRoleLink;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2016年12月23日 下午2:17:56
 */
public interface UserHallRoleLinkService{
	/**
	 * 批量新增用户和会议厅角色关联
	 */
	int batchsaveUserHallRoleLink(List<UserHallRoleLink> userRoles) throws Exception;
	/**
	 * 根据用户id和会议厅id查询是否有角色
	 * @param userid  用户id
	 * @param hallid  会议厅id
	 * @return
	 */
	long countUseridHallId(String userid,
						   Integer hallid);
	/**
	 * 根据用户id查询用户管理的会议厅id
	 * @param userid  用户id
	 * @return
	 */
	List<Integer> getHallIdByUserId(String userid);
	


	/**
	 * 新增用户和会议厅角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	int insertUserHallRole(UserHallRoleLink userhallrolelink)
			throws Exception;

	/**
	 * 删除用户和会议厅角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	int deleteUserHallRole(UserHallRoleLink userhallrolelink)
			throws Exception;

	/**
	 * 更新用户和会议厅角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	int updateUserHallRole(UserHallRoleLink userhallrolelink)
			throws Exception;

}
