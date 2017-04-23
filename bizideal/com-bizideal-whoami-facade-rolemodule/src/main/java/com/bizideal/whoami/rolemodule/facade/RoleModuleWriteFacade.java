package com.bizideal.whoami.rolemodule.facade;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.entity.HallModule;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.QrcodeScan;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.entity.UserHallRoleLink;
import com.bizideal.whoami.rolemodule.entity.UserRoleLink;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午1:23:16 写的RPC接口
 */
public interface RoleModuleWriteFacade {
	/**
	 * 批量新增主会议应该显示的模块
	 * 
	 * @param hallModule
	 *            Map中的Key 和Value key value meet_hall_id 会议厅id meet_id 主会议id module_id 模块id
	 * @return
	 */
	DubboxResult batchsaveHallModules(HallModule hallmodule);

	/**
	 * 批量修改主会议应该显示的模块
	 * 
	 * @param hallModule
	 *            Map中的Key 和Value key value meet_hall_id 会议厅id meet_id 主会议id module_id 模块id
	 * @return
	 */
	DubboxResult updateHallModules(HallModule hallModule);

	/**
	 * 新增主会议角色和权限模块(管理员)
	 * 
	 * @param role
	 * @return
	 */
	DubboxResult insertRoleModule(Role role);

	/**
	 * 新增主会议角色和权限模块,个人和单位报名费用
	 * 
	 * @param roleMemberDto
	 * @return
	 * @throws Exception
	 */
	DubboxResult insertRoleModuleJoiner(RoleMemberDto roleMemberDto) throws Exception;

	/**
	 * 删除主会议角色和权限模块
	 * 
	 * @param role
	 * @return
	 */
	DubboxResult deleteRoleModule(Role role);

	/**
	 * 更新主会议角色和权限模块
	 * 
	 * @param role
	 * @return
	 */
	DubboxResult updateRoleModule(Role role);

	/**
	 * 更新主会议角色和权限模块
	 * 
	 * @param role
	 * @return
	 */
	DubboxResult updateRoleModuleMember(RoleMemberDto roleMemberDto);

	/**
	 * 根据主会议角色id删除角色和权限模块
	 * 
	 * @param roleid
	 *            主会议角色id
	 * @return
	 */
	DubboxResult deleteRoleModule(int roleid);

	/**
	 * 新增用户和主会议角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	DubboxResult insertUserRole(UserRoleLink userRoleLink) throws Exception;

	/**
	 * 删除用户和主会议角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	DubboxResult deleteUserRole(UserRoleLink userRoleLink) throws Exception;

	/**
	 * 更新用户和主会议角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	DubboxResult updateUserRole(UserRoleLink userRoleLink) throws Exception;

	/**
	 * 新增默认角色
	 * 
	 * @param meetHallId
	 * @return
	 * @throws Exception
	 */
	DubboxResult insertDefaultRole(Integer meetid, Integer hallid) throws Exception;

	/**
	 * 设置二维码
	 * 
	 * @param qrcodeScan
	 * @return
	 * @throws Exception
	 */
	DubboxResult setQRCodeScan(QrcodeScan qrcodeScan) throws Exception;

	/**
	 * 更新二维码
	 * 
	 * @param userid
	 *            用户id
	 * @param redirect
	 *            二维码中的参数
	 * @return
	 * @throws Exception
	 */
	DubboxResult updateQRCodeScan(String userid, String redirect) throws Exception;

	/*
	 * 
	 * 
	 * 
	 */

	/**
	 * 新增会议厅角色和功能模块
	 * 
	 * @param hallrole
	 *            会议厅角色
	 * @return
	 */
	DubboxResult insertHallRoleFunctions(HallRole hallrole) throws Exception;

	/**
	 * 删除会议厅角色和功能模块
	 * 
	 * @param hallrole
	 * @return
	 */
	DubboxResult deleteHallRoleFunction(HallRole hallrole) throws Exception;

	/**
	 * 更新主会议厅角色和功能模块
	 * 
	 * @param hallrole
	 * @return
	 */
	DubboxResult updateHallRoleFunction(HallRole hallrole) throws Exception;

	/**
	 * 根据会议厅角色id删除角色和功能
	 * 
	 * @param roleid
	 *            会议厅角色id
	 * @return
	 */
	DubboxResult deleteHallRoleFunction(int roleid) throws Exception;

	/**
	 * 新增用户和会议厅角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	DubboxResult insertUserHallRole(UserHallRoleLink userhallrolelink) throws Exception;

	/**
	 * 删除用户和会议厅角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	DubboxResult deleteUserHallRole(UserHallRoleLink userhallrolelink) throws Exception;

	/**
	 * 更新用户和会议厅角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	DubboxResult updateUserHallRole(UserHallRoleLink userhallrolelink) throws Exception;

	/**
	 * 新增会议厅默认角色
	 * 
	 * @param hallid
	 *            会议厅id
	 * @return
	 * @throws Exception
	 */
	DubboxResult insertDefaultHallRole(Integer hallid) throws Exception;

}
