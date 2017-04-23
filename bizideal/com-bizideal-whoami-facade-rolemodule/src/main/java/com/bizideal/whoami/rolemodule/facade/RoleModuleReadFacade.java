package com.bizideal.whoami.rolemodule.facade;

import java.util.List;

import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.entity.HallFunction;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.Module;
import com.bizideal.whoami.rolemodule.entity.QrcodeScan;
import com.bizideal.whoami.rolemodule.entity.Role;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午1:23:52 读的RPC接口
 */
public interface RoleModuleReadFacade {
	/**
	 * 查询主会议显示的模块菜单 ==
	 * 
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	List<Module> selectHallModules(Integer meetid);

	/**
	 * 根据主会议roleid查询模块和角色
	 * 
	 * @param roleid
	 *            角色id
	 * @return
	 */
	Role getRoleModuleByRoleId(int roleid);

	/**
	 * 根据角色Id查询角色包括角色费用
	 * 
	 * @param roleid
	 * @return
	 */
	RoleMemberDto getRoleModuleBymember_type_id(int roleid);

	/**
	 * 根据主会议member_type_id,和标记字段去查询模块和角色
	 * 
	 * @param roleid
	 *            角色id
	 * @return
	 */
	RoleMemberDto getRoleModuleBymember_type_id(Integer meeId, int member_type_id, int type);

	/**
	 * 根据主会议id和角色类型查询角色
	 * 
	 * @param roleType
	 *            角色类型
	 * @param meetId
	 *            主会议id
	 * @return
	 */
	List<Role> getRoleByCondition(String roleType, Integer meetId);

	/**
	 * 根据用户id和主会议id查询用户的主会议角色
	 * 
	 * @param userid
	 *            用户id
	 * @param meetId
	 *            主会议id
	 * @return
	 */
	Role findRoleByUserIdHallId(String userid, Integer meetId);

	/**
	 * 根据主会议id查询管理员用户id集合
	 * 
	 * @param meetId
	 *            主会议id
	 * @return
	 */
	List<String> getManagerUserIdByhallid(int meetId);

	/**
	 * 获取所有模块
	 * 
	 * @return
	 */
	List<Module> getAllModules();

	/**
	 * 设置邀请链接
	 * 
	 * @param meetId
	 *            主会议id
	 * @param roleId
	 *            角色id
	 * @param timeout
	 *            连接超时时间
	 * @param hallid
	 *            会议厅id
	 * @return
	 * @throws Exception
	 */
	String setInviteUrl(Integer meetId, Integer roleId, Integer hallid, Integer timeout) throws Exception;

	/**
	 * 邀请别人成为管理员
	 * 
	 * @param meetid
	 *            主会议id
	 * @param roleId
	 *            角色id
	 * @param userId
	 *            用户id
	 * @param hallid
	 *            会议厅id
	 * 
	 * @return
	 * @throws Exception
	 */
	String inviteManager(Integer meetid, Integer roleId, Integer hallid, String userId) throws Exception;

	/**
	 * 根据用户id查询该用户管理的主会议的id集合
	 * 
	 * @param userid
	 * @return
	 */
	List<Integer> getHallIdByUserId(String userid);

	/**
	 * 根据主键获取二维码
	 * 
	 * @param codeid
	 *            二维码id
	 * @return
	 */
	QrcodeScan getQrcodeScanById(Integer codeid);

	/**
	 * 根据用户id 主会议id 获取用户角色
	 * 
	 * @param userid
	 *            用户id
	 * 
	 * @param meetid
	 *            主会议id
	 * 
	 * @return
	 */
	// public Role findRoleByUserIdHallIdMeetId(String userid, Integer meetid);

	/*
	 * 
	 */

	/**
	 * 根据会议厅id和角色类型查询角色
	 * 
	 * @param roleType
	 *            角色类型
	 * @param hallid
	 *            会议厅id
	 * @return
	 */
	List<HallRole> getHallRoleByCondition(String roleType, Integer hallid);

	/**
	 * 根据用户id和会议厅id查询用户的会议厅角色
	 * 
	 * @param userid
	 *            用户id
	 * @param hallid
	 *            会议厅id
	 * @return
	 */
	HallRole findHallRoleByUserIdHallId(String userid, Integer hallid);

	/**
	 * 根据会议厅id查询管理员用户id集合
	 * 
	 * @param hallid
	 *            会议厅id
	 * @return
	 */
	List<String> getHallManagerUserIdByhallid(int hallid);

	/**
	 * 获取所有会议厅功能
	 * 
	 * @return
	 */
	List<HallFunction> getAllHallFunction();

	/**
	 * 会议厅设置邀请链接
	 * 
	 * @param hallid
	 *            会议厅id
	 * @param roleId
	 *            会议厅角色id
	 * @param timeout
	 *            连接超时时间
	 * @return
	 * @throws Exception
	 */
	String setHallInviteUrl(Integer hallid, Integer roleId, Integer timeout) throws Exception;

	/**
	 * 邀请别人成为会议厅管理员
	 * 
	 * @param hallid
	 *            会议厅id
	 * @param roleId
	 *            角色id
	 * @param userId
	 *            用户id
	 * @return
	 * @throws Exception
	 */
	String inviteHallManager(Integer hallid, Integer roleId, String userId) throws Exception;

	/**
	 * 根据用户id查询该用户管理的会议厅的id集合
	 * 
	 * @param userid
	 * @return
	 */
	List<Integer> getMeetHallIdByUserId(String userid);

	/**
	 * 判断角色名称是否重复普通会员
	 * 
	 * @param roleName
	 *            角色名
	 * @param meetid
	 *            主会议id
	 * @return false 名称可用
	 */
	boolean existRoleName(String roleName, Integer meetid, Integer type);

	/**
	 * 判断角色名称是否重复普通会员
	 * 
	 * @param roleName
	 *            角色名
	 * @param meetid
	 *            主会议id
	 * @return false 名称可用
	 */
	boolean existRoleNameManage(String roleName, Integer meetid);

	/**
	 * 判断会议厅角色名称是否重复
	 * 
	 * @param roleName
	 *            角色名
	 * @param hallid
	 *            会议厅id
	 * @return false 名称可用
	 */
	boolean existHallRoleName(String roleName, Integer hallid);

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

	/**
	 * 根据会议厅角色id查询会议厅角色
	 * 
	 * @param roleid
	 *            角色id
	 * @return
	 */
	HallRole getHallRoleByRoleid(Integer roleid);

}
