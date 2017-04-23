package com.bizideal.whoami.rolemodule.service;

import java.util.List;

import com.bizideal.whoami.rolemodule.entity.HallFunction;
import com.bizideal.whoami.rolemodule.entity.HallRole;

/**
 * @author 作者 zhushangjin:
 * @date 创建时间：2016年11月29日 下午2:29:53
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface HallRoleModuleService {

	/**
	 * 新增用户和会议厅角色关联中间表记录
	 * 
	 * @param roleModule
	 * @return
	 * @throws Exception
	 */
	// public int saveRoleModules(Map<String, Integer> roleModule)
	// throws Exception;

	/**
	 * 查询 会议厅roleid和moduleid中间标的记录数
	 * 
	 * @param roleModule
	 * @return
	 */
	// public int findRoleModule(Map<String, Integer> roleModule);

	/**
	 * 批量新增rolemodule 用户和会议厅角色关联中间表记录
	 */
	// public int batchsaveRoleModules(List<Map<String, Integer>> list) throws Exception;

	/**
	 * 根据会议厅roleid 删除用户和会议厅角色关联中间表记录
	 * 
	 * @param roleid
	 *            角色id
	 * @return
	 * @throws Exception
	 */
	int deleteRoleModules(int roleid) throws Exception;

	/**
	 * 根据会议厅id和角色类型查询角色 1
	 * 
	 * @param roleType
	 *            角色类型
	 * @param meet_hall_id
	 *            会议厅id
	 * @return
	 */
	List<HallRole> getRoleByCondition(String roleType, Integer meet_hall_id);

	/**
	 * 根据用户id和会议厅id查询用户的角色 1
	 * 
	 * @param userid
	 *            用户id
	 * @param hallid
	 *            会议厅id
	 * 
	 * @return
	 */
	HallRole findRoleByUserIdHallId(String userid, Integer hallid);

	/**
	 * 根据会议厅id查询管理员用户id集合
	 * 
	 * @param hallid
	 *            会议厅id
	 * @return
	 */
	List<String> getManagerUserIdByhallid(int hallid);

	/**
	 * 获取所有功能模块
	 * 
	 * @return
	 */
	List<HallFunction> getAllHallFunction();

	/**
	 * 设置邀请管理员的连接存在时间，默认为1小时
	 * 
	 * @param meetHallId
	 *            会议厅id
	 * @param roleId
	 *            角色id
	 * @return
	 */
	String setHallInviteUrl(Integer meetHallId, Integer roleId);

	/**
	 * 设置邀请管理员的连接存在时间，时间单位为秒
	 * 
	 * @param meetHallId
	 *            会议厅id
	 * @param roleId
	 *            角色id
	 * @param timeout
	 *            超时时间
	 * @return
	 */
	String setHallInviteUrl(Integer meetHallId, Integer roleId, Integer timeout);

	/**
	 * 根据会议厅id和角色id删除邀请链接
	 * 
	 * @param meetHallId
	 *            会议厅id
	 * @param roleId
	 *            角色id
	 * @return
	 */
	String deleteHallInviteUrl(Integer meetHallId, Integer roleId);

	/**
	 * 邀请管理员
	 * 
	 * @param meetHallId
	 *            会议厅id
	 * @param roleId
	 *            角色id
	 * @param userId
	 *            用户id
	 * 
	 * @return
	 */
	String inviteHallManager(Integer meetHallId, Integer roleId, String userId);

	/**
	 * 新增会议厅角色和功能模块
	 * 
	 * @param hallrole
	 *            会议厅角色
	 * @return
	 */
	int insertHallRoleFunctions(HallRole hallrole) throws Exception;

	/**
	 * 删除会议厅角色和功能模块
	 * 
	 * @param hallrole
	 * @return
	 */
	int deleteHallRoleFunction(HallRole hallrole) throws Exception;

	/**
	 * 更新主会议厅角色和功能模块
	 * 
	 * @param hallrole
	 * @return
	 */
	int updateHallRoleFunction(HallRole hallrole) throws Exception;

	/**
	 * 根据会议厅角色id删除角色和功能
	 * 
	 * @param roleid
	 *            会议厅角色id
	 * @return
	 */
	int deleteHallRoleFunction(int roleid) throws Exception;

	/**
	 * 查询会议厅角色名是否重复
	 * 
	 * @param roleName
	 *            角色名
	 * @param hallid
	 *            会议厅id
	 * @return
	 */
	HallRole existHallRoleName(String roleName, Integer hallid);

	/**
	 * 创建会议厅默认角色
	 * 
	 * @param meetHallId
	 *            会议厅id
	 * @return
	 * @throws Exception
	 */
	int insertDefaultHallRole(Integer meetHallId) throws Exception;

	/**
	 * 根据角色id查询会议厅角色
	 * 
	 * @param roleid
	 * @return
	 */
	HallRole getHallRoleByRoleid(Integer roleid);

}
