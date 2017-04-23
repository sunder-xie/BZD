package com.bizideal.whoami.rolemodule.service;

import java.util.List;

import com.bizideal.whoami.rolemodule.entity.HallModule;
import com.bizideal.whoami.rolemodule.entity.Module;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午12:56:10
 */
public interface ModuleService {
	/**
	 * 根据主会议id查询出应显示的模块
	 * 
	 * @param meetid
	 *            主会议id
	 * @return
	 */
	List<Module> selectHallModules(Integer meetid);

	/**
	 * 批量保存 主会议应显示的模块中间表 ===
	 * 
	 * @param hallModule
	 * @return
	 * @throws Exception
	 */
	int batchsaveHallModules(HallModule hallModule) throws Exception;

	/**
	 * 批量更新主会议应显示的模块中间表
	 * 
	 * @param hallModule
	 * @return
	 * @throws Exception
	 */
	int updateHallModules(HallModule hallModule) throws Exception;

	/**
	 * 查询主会议id和用户id存在用户角色中见表的记录
	 * 
	 * @param userid
	 *            用户id
	 * @param meethallid
	 *            主会议id
	 * @return
	 */
	long countUseridHallId(String userid, Integer meethallid);

	/**
	 * 获取所有模块
	 * 
	 * @return
	 */
	List<Module> getAllModules();
}
