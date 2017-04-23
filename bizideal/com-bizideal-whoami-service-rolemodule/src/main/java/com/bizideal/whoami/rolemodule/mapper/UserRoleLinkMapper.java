package com.bizideal.whoami.rolemodule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bizideal.whoami.rolemodule.entity.UserRoleLink;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2016年12月23日 下午2:17:56
 */
public interface UserRoleLinkMapper extends Mapper<UserRoleLink>{
	/**
	 * 批量新增用户主会议角色关联
	 * @param userRoles
	 * @return
	 * @throws Exception
	 */
	int batchsaveUserRoleLink(List<UserRoleLink> userRoles) throws Exception;
	/**
	 * 查询该用户是否在该主会议下有角色
	 * @param userid
	 * @param hallid
	 * @return
	 */
	long countUseridHallId(@Param("userid") String userid,
						   @Param("meetid") Integer meetid);
	/**
	 * 查询该用户管理的主会议
	 * @param userid
	 * @return
	 */
	List<Integer> getHallIdByUserId(String userid);

}
