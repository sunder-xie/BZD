package com.bizideal.whoami.rolemodule.service;

import java.util.List;

import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.entity.Role;

/**
 * @author zhu_shangjin
 * @version 2016年12月6日 下午3:16:34
 */
public interface RoleModuleService {
	/**
	 * 新增主会议角色权限(管理员)
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	int insertRoleModule(Role role) throws Exception;

	/**
	 * 新增主会议角色权限(模块费用)
	 * 
	 * @param roleMemberDto
	 * @return
	 * @throws Exception
	 */
	int insertRoleModuleJoiner(RoleMemberDto roleMemberDto) throws Exception;

	/**
	 * 删除主会议角色权限
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	int deleteRoleModule(Role role) throws Exception;

	/**
	 * 更新主会议角色权限
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	int updateRoleModule(Role role) throws Exception;

	/**
	 * 更新主会议角色权限包括单位和个人的费用
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	int updateRoleModuleMember(RoleMemberDto roleMemberDto) throws Exception;

	/**
	 * 根据角色id获取对应角色的权限
	 * 
	 * @param roleid
	 *            角色id
	 * @return
	 */
	Role getRoleModuleByRoleId(int roleid);

	/**
	 * 根据角色id删除对应角色和权限
	 * 
	 * @param roleid
	 *            角色id
	 * @return
	 * @throws Exception
	 */
	int deleteRoleModule(int roleid) throws Exception;

	/**
	 * 根据主会议id和角色类型查询主会议角色
	 * 
	 * @param roleType
	 *            角色类型
	 * @param meetId
	 *            主会议id
	 * @return
	 */
	List<Role> getRoleByCondition(String roleType, Integer meetId);

	/**
	 * 根据查询角色包括权限和报名的费用
	 * 
	 * @param member_type_id
	 * @param type
	 * @return
	 */
	RoleMemberDto getRoleModuleBymember_type_id(Integer meeId, int member_type_id, int type);

	/**
	 * 根据查询角色包括权限和报名的费用
	 * 
	 * @param member_type_id
	 * @param type
	 * @return
	 */
	RoleMemberDto getRoleModuleBymember_type_id(int roleId);

	/**
	 * 根据角色id查询权限模块和个人报名的费用
	 * 
	 * @param roleid
	 *            角色id
	 * @return
	 */
	// public Role findByRoleModulePersonal(int roleid);

	/**
	 * 根据角色id查询权限模块和单位的报名限制
	 * 
	 * @param roleid
	 *            角色id
	 * @return
	 */
	// public Role findByRoleModuleUnit(int roleid);

	/**
	 * 根据用户id和主会议id查询用户的角色,包括权限模块
	 * 
	 * @param userid
	 *            用户id
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	Role findRoleByUserIdHallId(String userid, Integer meetid);

	/**
	 * 根据用户id和主会议id查询用户的角色和个人报名限制信息
	 * 
	 * @param userid
	 *            用户id
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	// public Role findRoleModulePersonalByUseridHallId(String userid, Integer meetid);

	/**
	 * 根据用户id和主会议id查询用户的角色和单位报名限制信息
	 * 
	 * @param userid
	 *            用户id
	 * 
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	// public Role findRoleModuleUnitByUseridHallId(String userid, Integer meetid);

	/**
	 * 根据主会议id获取管理员userid集合
	 * 
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	List<String> getManagerUserIdByhallid(int meetid);

	/**
	 * 设置邀请链接
	 * 
	 * @param meetid
	 *            主会议id
	 * @param roleId
	 *            角色id
	 * @param hallid
	 *            会议厅id
	 * @return
	 */
	String setInviteUrl(Integer meetid, Integer roleId, Integer hallid);

	/**
	 * 设置邀请链接
	 * 
	 * @param meetid
	 *            主会议id
	 * @param roleId
	 *            角色id
	 * @param timeout
	 *            连接超时时间 单位秒
	 * @return
	 */
	String setInviteUrl(Integer meetid, Integer roleId, Integer hallid, Integer timeout);

	/**
	 * 删除邀请链接
	 * 
	 * @param meetid
	 *            主会议id
	 * @param roleId
	 *            角色id
	 * @return
	 */
	String deleteInviteUrl(Integer meetid, Integer roleId, Integer hallid);

	/**
	 * 邀请别人成为管理员
	 * 
	 * @param meetid
	 *            主会议id
	 * @param roleId
	 *            角色id
	 * @param userId
	 *            用户id
	 * @return
	 * @throws Exception
	 */
	String inviteManager(Integer meetid, Integer roleId, Integer hallid, String userId) throws Exception;

	/**
	 * 主会议新增默认角色
	 * 
	 * @param meetid
	 *            主会议id
	 * @param hallid
	 *            会议厅id
	 * @return
	 * @throws Exception
	 */
	int insertDefault(Integer meetid, Integer hallid) throws Exception;

	/**
	 * 根据用户id和主会议id查询用户角色
	 * 
	 * @param userid
	 *            用户id
	 * @param meetid
	 *            主会议id
	 * @return
	 */

	// public Role findRoleByUserIdHallIdMeetId(String userid, Integer meetid);

	/**
	 * 查询角色名称是否重复
	 * 
	 * @param roleName
	 *            角色名
	 * @param meetId
	 *            主会议id
	 * @return
	 */
	Role existRoleName(String roleName, Integer meetId, Integer type);

	/**
	 * 查询角色名称是否重复管理员
	 * 
	 * @param roleName
	 *            角色名
	 * @param meetId
	 *            主会议id
	 * @return
	 */
	Role existRoleNameManage(String roleName, Integer meetId);

	/**
	 * 根据主会议id获取未报名角色权限
	 * 
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	Role getDefaultNoSignByMeetId(Integer meetid);

	/**
	 * 根据主会议id获取已报名角色权限
	 * 
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	Role getDefaultSignByMeetId(Integer meetid);

	/**
	 * 根据主会议id获取默认管理员角色权限
	 * 
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	Role getDefaultManagerByMeetId(Integer meetid);

}
