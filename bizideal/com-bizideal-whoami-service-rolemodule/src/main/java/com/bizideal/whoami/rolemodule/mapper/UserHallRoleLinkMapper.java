package com.bizideal.whoami.rolemodule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bizideal.whoami.rolemodule.entity.UserHallRoleLink;
import com.bizideal.whoami.rolemodule.entity.UserRoleLink;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2016年12月23日 下午2:17:56
 */
public interface UserHallRoleLinkMapper extends Mapper<UserHallRoleLink>{
	/**
	 * 批量新增用户会议厅角色关联
	 * @param userRoles
	 * @return
	 * @throws Exception
	 */
	int batchsaveUserHallRoleLink(List<UserHallRoleLink> userRoles) throws Exception;
	/**
	 * 查询该用户是否在该会议厅下有角色
	 * @param userid
	 * @param hallid
	 * @return
	 */
	long countUseridHallId(@Param("userid") String userid,
						   @Param("hallid") Integer hallid);
	/**
	 * 查询该用户管理的会议厅
	 * @param userid
	 * @return
	 */
	List<Integer> getHallIdByUserId(String userid);

}
