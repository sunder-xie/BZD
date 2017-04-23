package com.bizideal.whoami.rolemodule.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.RedisClientTemplate;
import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.entity.Module;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.entity.RolePersonal;
import com.bizideal.whoami.rolemodule.entity.RoleUnit;
import com.bizideal.whoami.rolemodule.entity.UserRoleLink;
import com.bizideal.whoami.rolemodule.mapper.ModuleMapper;
import com.bizideal.whoami.rolemodule.mapper.RoleModuleMapper;
import com.bizideal.whoami.rolemodule.mapper.RolePersonalMapper;
import com.bizideal.whoami.rolemodule.mapper.RoleUnitMapper;
import com.bizideal.whoami.rolemodule.mapper.UserRoleLinkMapper;
import com.bizideal.whoami.rolemodule.service.RoleModuleService;
import com.bizideal.whoami.rolemodule.utils.roleUtils;

/**
 * @author zhu_shangjin
 * @version 2016年12月6日 下午3:17:01
 */
@Service
public class RoleModuleServiceImp implements RoleModuleService {
	@Autowired
	RoleModuleMapper roleModuleMapper;

	@Autowired
	RolePersonalMapper rolePersonalMapper;

	@Autowired
	RoleUnitMapper roleUnitMapper;

	@Autowired
	UserRoleLinkMapper userRoleLinkMapper;

	@Autowired
	ModuleMapper moduleMapper;

	@Autowired
	RedisClientTemplate redisClientTemplate;

	/**
	 * 新增角色和角色对应的权限
	 * 
	 */
	@Override
	public int insertRoleModule(Role role) throws Exception {

		int i = roleModuleMapper.insert(role);
		Integer roleid = role.getRoleId();

		List<Integer> moduleIds = role.getModuleIds();
		String moduleIdsString = roleUtils.listToString(moduleIds);
		Map<String, Object> rolemoduleid = new HashMap<String, Object>();
		rolemoduleid.put("role_id", roleid);
		rolemoduleid.put("module_id", moduleIdsString);
		i = roleModuleMapper.batchsaveRoleModules(rolemoduleid);
		return i;
	}

	/**
	 * 删除角色和相应的权限
	 */
	@Override
	public int deleteRoleModule(Role role) throws Exception {
		int i = roleModuleMapper.deleteRoleModules(role.getRoleId());
		i = roleModuleMapper.deleteByPrimaryKey(role.getRoleId());
		return i;
	}

	/**
	 * 修改角色和权限模块
	 */
	@Override
	public int updateRoleModule(Role role) throws Exception {
		List<Integer> moduleIds = role.getModuleIds();
		String moduleIdsString = roleUtils.listToString(moduleIds);
		Map<String, Object> rolemoduleid = new HashMap<String, Object>();
		rolemoduleid.put("role_id", role.getRoleId());
		rolemoduleid.put("module_id", moduleIdsString);

		int i = roleModuleMapper.batchupdateRoleModules(rolemoduleid);
		// 更新角色信息
		// i = roleModuleMapper.updateByPrimaryKeySelective(role);
		return i;
	}

	@Override
	public int updateRoleModuleMember(RoleMemberDto roleMemberDto) throws Exception {
		Role role = new Role();
		role.setModuleIds(roleMemberDto.getModuleIds());
		role.setRoleId(roleMemberDto.getRoleId());
		int i = this.updateRoleModule(role);
		if (roleMemberDto.getType() == 0) {
			RoleUnit roleUnit = new RoleUnit();
			roleUnit.setCost(roleMemberDto.getCost());
			roleUnit.setPeopleNumber(roleMemberDto.getPeopleNumber());
			roleUnit.setRoleId(roleMemberDto.getRoleId());
			// 更新单位
			i = roleUnitMapper.updateByRoleId(roleUnit);
		} else {
			// 更新个人会议
			RolePersonal rolePersonal = new RolePersonal();
			rolePersonal.setCost(roleMemberDto.getCost());
			rolePersonal.setRoleId(roleMemberDto.getRoleId());
			i = rolePersonalMapper.updateByRoleId(rolePersonal);
		}
		return i;
	}

	// 根据角色Id查询角色和角色下的权限
	@Override
	public Role getRoleModuleByRoleId(int roleid) {
		Role role = roleModuleMapper.findByRoleModuleId(roleid);
		String modulesListString = role.getModulesListString();
		List<Module> modules = moduleMapper.selectmeetingModules(roleUtils.stringToList(modulesListString));
		role.setModules(modules);
		return role;
	}

	@Override
	public int deleteRoleModule(int roleid) throws Exception {
		Role role = roleModuleMapper.findByRoleModuleId(roleid);
		return deleteRoleModule(role);
	}

	/**
	 * 根据主会议id和角色类型查询主会议角色
	 * 
	 * @param roleType
	 *            角色类型
	 * @param meetId
	 *            主会议id
	 * @return
	 */
	@Override
	public List<Role> getRoleByCondition(String roleType, Integer meetHallId) {

		return roleModuleMapper.getRoleByCondition(roleType, meetHallId);
	}

	// @Override
	// public Role findByRoleModulePersonal(int roleid) {
	//
	// return roleModuleMapper.findByRoleModulePersonal(roleid);
	// }

	// @Override
	// public Role findByRoleModuleUnit(int roleid) {
	//
	// return roleModuleMapper.findByRoleModuleUnit(roleid);
	// }

	// 根据用户id和会议id查询用户的角色
	@Override
	public Role findRoleByUserIdHallId(String userid, Integer meethallid) {
		Role role = roleModuleMapper.findRoleByUserIdHallId(userid, meethallid);
		if (role != null) {
			String modulesListString = role.getModulesListString();
			List<Module> modules = moduleMapper.selectmeetingModules(roleUtils.stringToList(modulesListString));
			role.setModules(modules);
		}
		return role;
	}

	// @Override
	// public Role findRoleModulePersonalByUseridHallId(String userid, Integer meethallid) {
	//
	// return roleModuleMapper.findRoleModulePersonalByUseridHallId(userid, meethallid);
	// }

	// @Override
	// public Role findRoleModuleUnitByUseridHallId(String userid, Integer meethallid) {
	//
	// return roleModuleMapper.findRoleModuleUnitByUseridHallId(userid, meethallid);
	// }

	/**
	 * 根据主会议id获取管理员userid集合
	 * 
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	@Override
	public List<String> getManagerUserIdByhallid(int meetid) {

		return roleModuleMapper.getManagerUserIdByhallid(meetid);
	}

	/**
	 * 设置邀请管理员的连接存在时间，默认为1小时
	 */
	@Override
	public String setInviteUrl(Integer meetid, Integer roleId, Integer hallid) {

		return redisClientTemplate.setex("inviteUrl_" + meetid + "_" + roleId + "_" + hallid, 3600, meetid + "_" + roleId + "_" + hallid);
	}

	/**
	 * 设置邀请管理员的连接存在时间，时间单位为秒
	 */
	@Override
	public String setInviteUrl(Integer meetid, Integer roleId, Integer hallid, Integer timeout) {
		if (timeout < 1) {
			timeout = 3600;
		}
		String value = redisClientTemplate.get("inviteUrl_" + meetid + "_" + roleId + "_" + hallid);
		if (StringUtils.isNotBlank(value)) {
			return "ok";
		} else {
			return redisClientTemplate.setex("inviteUrl_" + meetid + "_" + roleId + "_" + hallid, timeout, meetid + "_" + roleId + "_" + hallid);
		}

	}

	@Override
	public String deleteInviteUrl(Integer meetHallId, Integer roleId, Integer hallid) {

		return redisClientTemplate.deteteByKey("inviteUrl_" + meetHallId + "_" + roleId + "_" + hallid);
	}

	@Override
	public String inviteManager(Integer meetid, Integer roleId, Integer hallid, String userId) {
		String value = redisClientTemplate.get("inviteUrl_" + meetid + "_" + roleId + "_" + hallid);
		if (StringUtils.isBlank(value)) {
			// 连接超时
			return "timeout";
		}
		long count = userRoleLinkMapper.countUseridHallId(userId, meetid);
		if (count > 0) {
			// 该用户已经是该会议厅的管理员了
			return "already";
		}
		UserRoleLink userRoleLink = new UserRoleLink(userId, hallid, roleId, meetid);
		int i = userRoleLinkMapper.insert(userRoleLink);
		if (i > 0) {
			redisClientTemplate.deteteByKey("inviteUrl_" + meetid + "_" + roleId + "_" + hallid);
			return "ok";
		} else {
			return "error";
		}

	}

	@Override
	public int insertDefault(Integer meetid, Integer meetHallId) throws Exception {
		// 管理员的模块
		List<Integer> moduleids = moduleMapper.getAllModuleIds();
		// 已报名角色模块
		List<Integer> nosign = moduleMapper.getNoSignupModuleIds();
		// 未报名角色模块
		List<Integer> sign = moduleMapper.getSignupModuleIds();

		Role role = new Role();
		role.setMeetHallId(meetHallId);
		role.setModuleIds(moduleids);
		role.setRoleName("管理员");
		role.setRoleRemark("默认的，系统自动生成的");
		role.setRoleStatus("default");
		role.setRoleType("manager");
		role.setMeetId(meetid);
		role.setMember_type_id(0);
		int i = this.insertRoleModule(role);
		role = new Role();
		role.setMeetHallId(meetHallId);
		role.setModuleIds(sign);
		role.setRoleName("已报名");
		role.setRoleRemark("默认的，系统自动生成的");
		role.setRoleStatus("default");
		role.setRoleType("joiner");
		role.setMeetId(meetid);
		role.setMember_type_id(0);
		i = this.insertRoleModule(role);
		role = new Role();
		role.setMeetHallId(meetHallId);
		role.setModuleIds(nosign);
		role.setRoleName("未报名");
		role.setRoleRemark("默认的，系统自动生成的");
		role.setRoleStatus("default");
		role.setRoleType("joiner");
		role.setMeetId(meetid);
		role.setMember_type_id(0);
		i = this.insertRoleModule(role);
		return i;
	}

	// @Override
	// public Role findRoleByUserIdHallIdMeetId(String userid, Integer meetid) {
	// Role role = roleModuleMapper.findRoleByUserIdHallIdMeetId(userid, meetid);
	//
	// return role;
	// }

	@Override
	public Role existRoleName(String roleName, Integer meetId, Integer type) {
		Role role = roleModuleMapper.existRoleName(roleName, meetId, type);
		return role;
	}

	@Override
	public Role existRoleNameManage(String roleName, Integer meetId) {
		Role role = roleModuleMapper.existRoleNameManage(roleName, meetId);
		return role;
	}

	@Override
	public Role getDefaultNoSignByMeetId(Integer meetid) {
		Role role = roleModuleMapper.getDefaultNoSignByMeetId(meetid);
		String modulesListString = role.getModulesListString();
		List<Module> modules = moduleMapper.selectmeetingModules(roleUtils.stringToList(modulesListString));
		role.setModules(modules);
		return role;
	}

	@Override
	public Role getDefaultSignByMeetId(Integer meetid) {
		Role role = roleModuleMapper.getDefaultSignByMeetId(meetid);
		String modulesListString = role.getModulesListString();
		List<Module> modules = moduleMapper.selectmeetingModules(roleUtils.stringToList(modulesListString));
		role.setModules(modules);
		return role;
	}

	@Override
	public Role getDefaultManagerByMeetId(Integer meetid) {
		Role role = roleModuleMapper.getDefaultManagerByMeetId(meetid);
		String modulesListString = role.getModulesListString();
		List<Module> modules = moduleMapper.selectmeetingModules(roleUtils.stringToList(modulesListString));
		role.setModules(modules);
		return role;
	}

	@Override
	public int insertRoleModuleJoiner(RoleMemberDto roleMemberDto) throws Exception {
		int i;

		Role role = new Role();
		role.setMeetHallId(roleMemberDto.getMeetHallId());
		role.setModuleIds(roleMemberDto.getModuleIds());
		role.setRoleName(roleMemberDto.getRoleName());

		role.setRoleType(roleMemberDto.getRoleType());
		role.setMeetId(roleMemberDto.getMeetId());
		role.setMember_type_id(roleMemberDto.getMember_type_id());
		role.setType(roleMemberDto.getType());
		i = this.insertRoleModule(role);

		if (roleMemberDto.getType() == 0) { // 插入组织会员
			RoleUnit roleUnit = new RoleUnit();
			roleUnit.setRoleId(role.getRoleId());
			roleUnit.setCost(roleMemberDto.getCost());
			roleUnit.setPeopleNumber(roleMemberDto.getPeopleNumber());
			i = roleUnitMapper.insert(roleUnit);
		} else { // 插入个人会员
			RolePersonal rolePersonal = new RolePersonal();
			rolePersonal.setRoleId(role.getRoleId());
			rolePersonal.setCost(roleMemberDto.getCost());
			i = rolePersonalMapper.insert(rolePersonal);
		}

		return i;
	}

	@Override
	public RoleMemberDto getRoleModuleBymember_type_id(Integer meeId, int member_type_id, int type) {
		RoleMemberDto roleMemberDto;
		if (type == 0) {
			roleMemberDto = roleModuleMapper.getRoleModuleBymember_type_idUnit(meeId, member_type_id, type);
		} else {
			roleMemberDto = roleModuleMapper.getRoleModuleBymember_type_idpersonal(meeId, member_type_id, type);
		}
		List<Module> modules = moduleMapper.selectmeetingModules(roleUtils.stringToList(roleMemberDto.getModulesListString()));
		roleMemberDto.setModules(modules);
		return roleMemberDto;
	}

	@Override
	public RoleMemberDto getRoleModuleBymember_type_id(int roleId) {
		Role role = roleModuleMapper.findByRoleModuleId(roleId);
		RoleMemberDto roleDto = this.getRoleModuleBymember_type_id(role.getMeetId(), role.getMember_type_id(), role.getType());
		return roleDto;
	}

}
