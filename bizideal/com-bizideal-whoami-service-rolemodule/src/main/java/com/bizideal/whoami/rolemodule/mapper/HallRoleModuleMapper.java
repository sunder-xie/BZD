package com.bizideal.whoami.rolemodule.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.rolemodule.entity.HallRole;

/**
 * @author 作者 zhushangjin:
 * @date 创建时间：2016年11月29日 下午2:29:53
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface HallRoleModuleMapper extends Mapper<HallRole> {

	/**
	 * 新增中间表记录 角色和功能的关联
	 * 
	 * @param roleModule
	 * @return
	 * @throws Exception
	 */
	// public int saveRoleModules(Map<String, Integer> roleModule)
	// throws Exception;

	/**
	 * 查询 roleid和function_id中间标的记录数
	 * 
	 * @param roleModule
	 * @return
	 */
	// public int findRoleModule(Map<String, Integer> roleModule);

	/**
	 * 新增rolemodule 和中间表记录
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	int batchsaveRoleModules(Map<String, Object> rolemoduleid) throws Exception;

	/**
	 * 根据roleid 删除中间表记录 1
	 * 
	 * @param roleid
	 * @return
	 * @throws Exception
	 */
	int deleteRoleModules(int roleid) throws Exception;

	/**
	 * 根据会议厅id和角色类型查询角色 1
	 * 
	 * @param roleType
	 * @param meet_hall_id
	 * @return
	 */
	List<HallRole> getRoleByCondition(@Param("roleType") String roleType, @Param("meet_hall_id") Integer meet_hall_id);

	/**
	 * 根据用户id和会议厅id查询用户的角色 包括其功能
	 * 
	 * @param userid
	 * @param meetid
	 * @return
	 */
	HallRole findRoleByUserIdHallId(@Param("userid") String userid, @Param("meethallid") Integer meetid);

	/**
	 * 根据会议厅id获取管理员用户id
	 * 
	 * @param hallid
	 * @return
	 */
	List<String> getManagerUserIdByhallid(int hallid);

	/**
	 * 根据角色id查询会议厅角色
	 * 
	 * @param roleid
	 * @return
	 */
	HallRole findByHallRoleModuleId(int roleid);

	/**
	 * 更新会议厅角色
	 * 
	 * @param functionid
	 * @param hallid
	 * @return
	 */
	int updateHallRoleFunction(@Param("functionid") String functionid, @Param("hallid") int hallid);

	/**
	 * 根据角色名和hallid获取角色
	 * 
	 * @param roleName
	 * @param hallid
	 * @return
	 */
	HallRole existHallRoleName(@Param("roleName") String roleName, @Param("meetHallId") Integer hallid);

}
