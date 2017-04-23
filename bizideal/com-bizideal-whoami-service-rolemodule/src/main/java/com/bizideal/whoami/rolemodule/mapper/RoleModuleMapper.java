package com.bizideal.whoami.rolemodule.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.entity.Role;

/**
 * @author 作者 zhushangjin:
 * @date 创建时间：2016年11月29日 下午2:29:53
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface RoleModuleMapper extends Mapper<Role> {
	// 根据角色id查询所有模块
	// public List<Module> selectModules(int roleId);

	// 根据id查询角色信息
	// public Role findById(int roleid);

	// 新增中间表记录
	int saveRoleModules(Map<String, Integer> roleModule) throws Exception;

	// 查询 roleid和moduleid中间标的记录数
	int findRoleModule(Map<String, Integer> roleModule);

	// 批量新增rolemodule 中间表记录
	int batchsaveRoleModules(Map<String, Object> rolemoduleid) throws Exception;

	// 修改rolemodule中间表记录
	int batchupdateRoleModules(Map<String, Object> rolemoduleid);

	// 根据roleid 删除中间表记录
	int deleteRoleModules(int roleid) throws Exception;

	// 根据角色Id查询角色
	Role findByRoleModuleId(int roleid);

	// 根据主会议id和角色类型查询角色
	List<Role> getRoleByCondition(@Param("roleType") String roleType, @Param("meetid") Integer meetid);

	// 根据角色id查询权限模块和个人报名的费用
	// public Role findByRoleModulePersonal(int roleid);

	// 根据角色id查询权限模块和单位的报名限制
	// public Role findByRoleModuleUnit(int roleid);

	// 根据用户id和会议id查询用户的角色
	Role findRoleByUserIdHallId(@Param("userid") String userid, @Param("meetid") Integer meetid);

	// 根据用户id和会议厅id查询用户的角色和个人报名限制信息
	// public Role findRoleModulePersonalByUseridHallId(@Param("userid") String userid, @Param("meetid") Integer meetid);

	// 根据用户id和会议厅id查询用户的角色和单位报名限制信息
	// public Role findRoleModuleUnitByUseridHallId(@Param("userid") String userid, @Param("meetid") Integer meetid);

	// 根据主会议Id
	List<String> getManagerUserIdByhallid(int meetid);

	// public Role getDefaultJoiner(Integer hallid);

	// public Role findRoleByUserIdHallIdMeetId(@Param("userid") String userid, @Param("meetid") Integer meetid);

	Role existRoleName(@Param("roleName") String roleName, @Param("meetId") Integer meetId, @Param("type") Integer type);

	Role existRoleNameManage(@Param("roleName") String roleName, @Param("meetId") Integer meetId);

	// 根据主会议id获取未报名角色权限
	Role getDefaultNoSignByMeetId(Integer meetid);

	// 根据主会议id获取已报名角色权限
	Role getDefaultSignByMeetId(Integer meetid);

	// 根据主会议id获取默认管理员角色权限
	Role getDefaultManagerByMeetId(Integer meetid);

	// 根据查询个人
	RoleMemberDto getRoleModuleBymember_type_idpersonal(@Param("meeId") Integer meeId, @Param("member_type_id") int member_type_id, @Param("type") int type);

	// 根据查询组织
	RoleMemberDto getRoleModuleBymember_type_idUnit(@Param("meeId") Integer meeId, @Param("member_type_id") int member_type_id, @Param("type") int type);

}
