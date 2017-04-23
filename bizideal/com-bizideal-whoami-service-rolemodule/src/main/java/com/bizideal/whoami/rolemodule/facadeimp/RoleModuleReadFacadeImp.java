package com.bizideal.whoami.rolemodule.facadeimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.entity.HallFunction;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.Module;
import com.bizideal.whoami.rolemodule.entity.QrcodeScan;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.rolemodule.service.HallRoleModuleService;
import com.bizideal.whoami.rolemodule.service.ModuleService;
import com.bizideal.whoami.rolemodule.service.QrcodeScanService;
import com.bizideal.whoami.rolemodule.service.RoleModuleService;
import com.bizideal.whoami.rolemodule.service.UserHallRoleLinkService;
import com.bizideal.whoami.rolemodule.service.UserRoleLinkService;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午1:43:37
 */
@Component("roleModuleReadFacade")
public class RoleModuleReadFacadeImp implements RoleModuleReadFacade {

	@Autowired
	ModuleService moduleService;
	@Autowired
	RoleModuleService roleModuleService;
	@Autowired
	UserRoleLinkService userRoleLinkService;
	@Autowired
	QrcodeScanService qrcodeScanService;
	@Autowired
	UserHallRoleLinkService userHallRoleLinkService;
	@Autowired
	HallRoleModuleService hallRoleModuleService;

	@Override
	public List<Module> selectHallModules(Integer meetid) {

		return moduleService.selectHallModules(meetid);
	}

	@Override
	public Role getRoleModuleByRoleId(int roleid) {

		return roleModuleService.getRoleModuleByRoleId(roleid);
	}

	@Override
	public List<Role> getRoleByCondition(String roleType, Integer meetHallId) {

		return roleModuleService.getRoleByCondition(roleType, meetHallId);
	}

	@Override
	public Role findRoleByUserIdHallId(String userid, Integer meethallid) {

		return roleModuleService.findRoleByUserIdHallId(userid, meethallid);
	}

	@Override
	public List<String> getManagerUserIdByhallid(int meetid) {

		return roleModuleService.getManagerUserIdByhallid(meetid);
	}

	@Override
	public List<Module> getAllModules() {

		return moduleService.getAllModules();
	}

	@Override
	public String setInviteUrl(Integer meetid, Integer roleId, Integer hallid, Integer timeout) throws Exception {

		return roleModuleService.setInviteUrl(meetid, roleId, hallid, timeout);
	}

	@Override
	public String inviteManager(Integer meetid, Integer roleId, Integer hallid, String userId) throws Exception {

		return roleModuleService.inviteManager(meetid, roleId, hallid, userId);
	}

	@Override
	public List<Integer> getHallIdByUserId(String userid) {

		return userRoleLinkService.getHallIdByUserId(userid);
	}

	@Override
	public QrcodeScan getQrcodeScanById(Integer codeid) {

		return qrcodeScanService.selectByPK(codeid);
	}

	// @Override
	// public Role findRoleByUserIdHallIdMeetId(String userid,
	// Integer meetid) {
	//
	// return roleModuleService.findRoleByUserIdHallIdMeetId(userid, meetid);
	// }
	/*
     * 
     * 
     */

	@Override
	public List<HallRole> getHallRoleByCondition(String roleType, Integer hallid) {

		return hallRoleModuleService.getRoleByCondition(roleType, hallid);
	}

	@Override
	public HallRole findHallRoleByUserIdHallId(String userid, Integer hallid) {

		return hallRoleModuleService.findRoleByUserIdHallId(userid, hallid);
	}

	@Override
	public List<String> getHallManagerUserIdByhallid(int hallid) {

		return hallRoleModuleService.getManagerUserIdByhallid(hallid);
	}

	@Override
	public List<HallFunction> getAllHallFunction() {

		return hallRoleModuleService.getAllHallFunction();
	}

	@Override
	public String setHallInviteUrl(Integer hallid, Integer roleId, Integer timeout) throws Exception {

		return hallRoleModuleService.setHallInviteUrl(hallid, roleId, timeout);
	}

	@Override
	public String inviteHallManager(Integer hallid, Integer roleId, String userId) throws Exception {

		return hallRoleModuleService.inviteHallManager(hallid, roleId, userId);
	}

	@Override
	public List<Integer> getMeetHallIdByUserId(String userid) {

		return userHallRoleLinkService.getHallIdByUserId(userid);
	}

	// 根据普通角色名查询在该会议下是否重复
	@Override
	public boolean existRoleName(String roleName, Integer meetId, Integer type) {
		Role role = roleModuleService.existRoleName(roleName, meetId, type);
		return role != null;

	}

	// 查询管理员角色名
	@Override
	public boolean existRoleNameManage(String roleName, Integer meetid) {
		Role role = roleModuleService.existRoleNameManage(roleName, meetid);
		return role != null;
	}

	@Override
	public boolean existHallRoleName(String roleName, Integer hallid) {
		HallRole hallrole = hallRoleModuleService.existHallRoleName(roleName, hallid);
		return hallrole != null;
	}

	@Override
	public Role getDefaultNoSignByMeetId(Integer meetid) {

		return roleModuleService.getDefaultNoSignByMeetId(meetid);
	}

	@Override
	public Role getDefaultSignByMeetId(Integer meetid) {

		return roleModuleService.getDefaultSignByMeetId(meetid);
	}

	@Override
	public Role getDefaultManagerByMeetId(Integer meetid) {

		return roleModuleService.getDefaultManagerByMeetId(meetid);
	}

	@Override
	public HallRole getHallRoleByRoleid(Integer roleid) {

		return hallRoleModuleService.getHallRoleByRoleid(roleid);
	}

	@Override
	public RoleMemberDto getRoleModuleBymember_type_id(Integer meeId, int member_type_id, int type) {

		return roleModuleService.getRoleModuleBymember_type_id(meeId, member_type_id, type);
	}

	@Override
	public RoleMemberDto getRoleModuleBymember_type_id(int roleid) {

		return roleModuleService.getRoleModuleBymember_type_id(roleid);
	}

}
