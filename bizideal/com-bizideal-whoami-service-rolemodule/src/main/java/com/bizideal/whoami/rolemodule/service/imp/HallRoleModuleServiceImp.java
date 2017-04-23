package com.bizideal.whoami.rolemodule.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.RedisClientTemplate;
import com.bizideal.whoami.rolemodule.entity.HallFunction;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.UserHallRoleLink;
import com.bizideal.whoami.rolemodule.mapper.HallFunctionMapper;
import com.bizideal.whoami.rolemodule.mapper.HallRoleModuleMapper;
import com.bizideal.whoami.rolemodule.mapper.UserHallRoleLinkMapper;
import com.bizideal.whoami.rolemodule.service.HallRoleModuleService;
import com.bizideal.whoami.rolemodule.utils.roleUtils;

/**
 * @author zhu_shangjin
 * @version 2017年2月27日 上午9:07:20
 */
@Service
public class HallRoleModuleServiceImp implements HallRoleModuleService {
	@Autowired
	HallRoleModuleMapper hallRoleModuleMapper;
	@Autowired
	HallFunctionMapper hallFunctionMapper;
	@Autowired
	UserHallRoleLinkMapper userHallRoleLinkMapper;
	@Autowired
	RedisClientTemplate redisClientTemplate;

	// @Override
	// public int saveRoleModules(Map<String, Integer> roleModule)
	// throws Exception {
	//
	// return hallRoleModuleMapper.saveRoleModules(roleModule);
	// }

	/*
	 * @Override public int findRoleModule(Map<String, Integer> roleModule) {
	 * 
	 * return hallRoleModuleMapper.findRoleModule(roleModule); }
	 */

	// @Override
	// public int batchsaveRoleModules(List<Map<String, Integer>> list) throws Exception {
	//
	// return hallRoleModuleMapper.batchsaveRoleModules(list);
	// }

	@Override
	public int deleteRoleModules(int roleid) throws Exception {

		return hallRoleModuleMapper.deleteRoleModules(roleid);
	}

	@Override
	public List<HallRole> getRoleByCondition(String roleType, Integer meet_hall_id) {

		return hallRoleModuleMapper.getRoleByCondition(roleType, meet_hall_id);
	}

	// 查询会议厅角色和功能
	@Override
	public HallRole findRoleByUserIdHallId(String userid, Integer hallid) {
		HallRole hallrole = hallRoleModuleMapper.findRoleByUserIdHallId(userid, hallid);
		if (hallrole != null) {
			String functionsString = hallrole.getFunctionsString();
			List<Integer> functions = roleUtils.stringToList(functionsString);
			List<HallFunction> selectfunction = hallFunctionMapper.selectfunction(functions);
			hallrole.setFunctions(selectfunction);
		}
		return hallrole;
	}

	@Override
	public List<String> getManagerUserIdByhallid(int hallid) {

		return hallRoleModuleMapper.getManagerUserIdByhallid(hallid);
	}

	@Override
	public List<HallFunction> getAllHallFunction() {

		return hallFunctionMapper.selectAll();
	}

	/**
	 * 设置邀请管理员的连接存在时间，默认为1小时
	 */
	@Override
	public String setHallInviteUrl(Integer meetHallId, Integer roleId) {

		return redisClientTemplate.setex("inviteHallUrl_" + meetHallId + "_" + roleId, 3600, meetHallId + "_" + roleId);
	}

	/**
	 * 设置邀请管理员的连接存在时间，时间单位为秒
	 */
	@Override
	public String setHallInviteUrl(Integer meetHallId, Integer roleId, Integer timeout) {
		if (timeout < 1) {
			timeout = 3600;
		}
		String value = redisClientTemplate.get("inviteHallUrl_" + meetHallId + "_" + roleId);
		if (StringUtils.isNotBlank(value)) {
			return "ok";
		} else {
			return redisClientTemplate.setex("inviteHallUrl_" + meetHallId + "_" + roleId, timeout, meetHallId + "_" + roleId);
		}

	}

	@Override
	public String deleteHallInviteUrl(Integer meetHallId, Integer roleId) {

		return redisClientTemplate.deteteByKey("inviteHallUrl_" + meetHallId + "_" + roleId);
	}

	@Override
	public String inviteHallManager(Integer meetHallId, Integer roleId, String userId) {
		String value = redisClientTemplate.get("inviteHallUrl_" + meetHallId + "_" + roleId);
		if (StringUtils.isBlank(value)) {
			// 连接超时
			return "timeout";
		}
		long count = userHallRoleLinkMapper.countUseridHallId(userId, meetHallId);
		if (count > 0) {
			// 该用户已经是该会议厅的管理员了
			return "already";
		}
		UserHallRoleLink userhallrolelink = new UserHallRoleLink(userId, meetHallId, roleId);
		int i = userHallRoleLinkMapper.insert(userhallrolelink);
		if (i > 0) {
			redisClientTemplate.deteteByKey("inviteHallUrl_" + meetHallId + "_" + roleId);
			return "ok";
		} else {
			return "error";
		}

	}

	// 插入中间表记录和角色
	@Override
	public int insertHallRoleFunctions(HallRole hallrole) throws Exception {
		// 插入角色表
		int i = hallRoleModuleMapper.insert(hallrole);

		List<Integer> moduleids = hallrole.getFunctionIds();
		String moduleidsString = roleUtils.listToString(moduleids);

		hallrole.setFunctionsString(moduleidsString);
		Map<String, Object> rolemoduleid = new HashMap<String, Object>();
		rolemoduleid.put("role_id", hallrole.getRoleId());
		rolemoduleid.put("function_id", moduleidsString);
		// 插入中间表记录
		i = hallRoleModuleMapper.batchsaveRoleModules(rolemoduleid);
		return i;
	}

	@Override
	public int deleteHallRoleFunction(HallRole hallrole) throws Exception {
		int i = hallRoleModuleMapper.deleteRoleModules(hallrole.getRoleId());

		i = hallRoleModuleMapper.deleteByPrimaryKey(hallrole.getRoleId());

		return i;
	}

	@Override
	public int updateHallRoleFunction(HallRole hallrole) throws Exception {
		// // 先删除权限模块
		// int i = hallRoleModuleMapper.deleteRoleModules(hallrole.getRoleId());
		// // 批量新增角色权限
		// List<Integer> moduleids = hallrole.getFunctionIds();
		// int roleid = hallrole.getRoleId();
		// List<Map<String, Integer>> roleModule = new ArrayList<Map<String, Integer>>();
		// Map<String, Integer> rolemoduleid = null;
		// for (Integer moduleid : moduleids) {
		// rolemoduleid = new HashMap<String, Integer>();
		// rolemoduleid.put("role_id", roleid);
		// rolemoduleid.put("function_id", moduleid);
		// roleModule.add(rolemoduleid);
		// }
		// i = hallRoleModuleMapper.batchsaveRoleModules(roleModule);
		//
		// // 更新角色信息
		// i = hallRoleModuleMapper.updateByPrimaryKeySelective(hallrole);
		// Example example = new Example(HallRole.class);
		// example.createCriteria().andEqualTo("userId", HallRole.getUserId())
		// hallRoleModuleMapper.updateByExample(hallrole, example);
		List<Integer> moduleids = hallrole.getFunctionIds();
		String listToString = roleUtils.listToString(moduleids);
		int i = hallRoleModuleMapper.updateByPrimaryKey(hallrole);
		i = hallRoleModuleMapper.updateHallRoleFunction(listToString, hallrole.getRoleId());
		return i;
	}

	@Override
	public int deleteHallRoleFunction(int roleid) throws Exception {
		// HallRole role = hallRoleModuleMapper.findByHallRoleModuleId(roleid);
		// List<Integer> moduleids = new ArrayList<Integer>();
		// for (HallFunction module : role.getFunctions()) {
		// moduleids.add(module.getFunctionId());
		// }
		// role.setFunctionIds(moduleids);
		int i = hallRoleModuleMapper.deleteRoleModules(roleid);

		i = hallRoleModuleMapper.deleteByPrimaryKey(roleid);
		return i;
	}

	@Override
	public HallRole existHallRoleName(String roleName, Integer hallid) {
		HallRole role = hallRoleModuleMapper.existHallRoleName(roleName, hallid);
		return role;
	}

	@Override
	public int insertDefaultHallRole(Integer meetHallId) throws Exception {
		List<Integer> functionids = hallFunctionMapper.getAllFunctionIds();
		HallRole hallRole = new HallRole();
		hallRole.setFunctionIds(functionids);
		hallRole.setMeetHallId(meetHallId);
		hallRole.setRoleName("管理员");
		hallRole.setRoleStatus("default");
		hallRole.setRoleType("manager");
		hallRole.setRoleRemark("默认，系统创建的");
		int i = hallRoleModuleMapper.insertSelective(hallRole);
		int hallroleid = hallRole.getRoleId();
		String listToString = roleUtils.listToString(functionids);
		Map<String, Object> rolemoduleid = new HashMap<String, Object>();
		rolemoduleid.put("role_id", hallroleid);
		rolemoduleid.put("function_id", listToString);
		i = hallRoleModuleMapper.batchsaveRoleModules(rolemoduleid);
		return i;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see 根据角色Id查询会议厅角色
	 */
	@Override
	public HallRole getHallRoleByRoleid(Integer roleid) {
		HallRole hallrole = hallRoleModuleMapper.findByHallRoleModuleId(roleid);
		String functionsString = hallrole.getFunctionsString();
		List<Integer> stringToList = roleUtils.stringToList(functionsString);
		List<HallFunction> selectfunction = hallFunctionMapper.selectfunction(stringToList);
		hallrole.setFunctions(selectfunction);
		return hallrole;
	}

}
