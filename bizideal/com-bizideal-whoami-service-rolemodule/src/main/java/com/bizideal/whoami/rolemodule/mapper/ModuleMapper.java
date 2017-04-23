package com.bizideal.whoami.rolemodule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.rolemodule.entity.Module;

/**
 * @author 作者 zhushangjin:
 * @date 创建时间：2016年11月29日 下午2:29:53
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface ModuleMapper extends Mapper<Module> {
	/**
	 * 根据主会议id获取主会议应显示的模块IdString
	 * 
	 * @param meetid
	 * @return
	 */
	String selectMeetingModulesId(Integer meetid);

	/**
	 * 根据模块id查询出模块的集合
	 * 
	 * @param modules
	 * @return
	 */
	List<Module> selectmeetingModules(List<Integer> modules);

	/**
	 * 批量保存主会议和模块的中间表记录
	 * 
	 * @param hallModule
	 * @return
	 * @throws Exception
	 */
	// public int batchsaveHallModules(List<Map<String, Integer>> hallModule) throws Exception;

	/**
	 * 根据主会议id删除主会议对应的模块
	 * 
	 * @param meetid
	 * @return
	 * @throws Exception
	 */
	int deleteHallModules(int meetid) throws Exception;

	/**
	 * 查询用户是否在该主会议下有角色
	 * 
	 * @param userid
	 * @param meeting_id
	 * @return
	 */
	long countUseridHallId(@Param("userid") String userid, @Param("meeting_id") Integer meeting_id);

	/**
	 * 获取所有模块
	 * 
	 * @return
	 */
	List<Integer> getAllModuleIds();

	/**
	 * 获取所有未报名模块
	 * 
	 * @return
	 */
	List<Integer> getNoSignupModuleIds();

	/**
	 * 获取所有已报名模块
	 * 
	 * @return
	 */
	List<Integer> getSignupModuleIds();

}
