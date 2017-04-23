package com.bizideal.whoami.rolemodule.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.rolemodule.entity.HallModule;
import com.bizideal.whoami.rolemodule.entity.Module;
import com.bizideal.whoami.rolemodule.mapper.HallModuleMapper;
import com.bizideal.whoami.rolemodule.mapper.ModuleMapper;
import com.bizideal.whoami.rolemodule.service.ModuleService;
import com.bizideal.whoami.rolemodule.utils.roleUtils;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午12:57:06
 */
@Service
public class ModuleServiceImp implements ModuleService {
	@Autowired
	ModuleMapper moduleMapper;
	@Autowired
	HallModuleMapper hallModuleMapper;

	// 根据主会议Id查询该模块显示菜单
	@Override
	public List<Module> selectHallModules(Integer meetid) {
		List modules = new ArrayList<Module>();
		String modulesId = moduleMapper.selectMeetingModulesId(meetid);
		if (modulesId != null) {
			modules = roleUtils.stringToList(modulesId);
			return moduleMapper.selectmeetingModules(modules);
		}
		return modules;
	}

	// 保存会议模块中间表记录
	@Override
	public int batchsaveHallModules(HallModule hallModule) throws Exception {
		// List list = new ArrayList<>();
		// for (Map<String, Integer> map : hallModule) {
		// map.get("module_id")
		// }

		// return hallModuleMapper.batchsaveHallModules(hallModule);
		// Example example = new Example(HallModule.class);
		// example.createCriteria().andEqualTo("meetingHallId", hallModule.getMeetingHallId()).andEqualTo("meetingId", hallModule.getMeetingId());
		// return hallModuleMapper.insertSelective(record)
		Map map = new HashMap<String, Object>();
		map.put("meetingHallId", hallModule.getMeetingHallId());
		map.put("meetingId", hallModule.getMeetingId());
		map.put("moduleId", hallModule.getModuleId());

		return hallModuleMapper.insertHallModule(map);
	}

	@Override
	public int updateHallModules(HallModule hallModule) throws Exception {
		// int meetid = hallModule.get(0).get("meet_id");
		// int i = moduleMapper.deleteHallModules(meetid);
		// i = moduleMapper.batchsaveHallModules(hallModule);
		// return i;
		Map map = new HashMap<String, Object>();
		map.put("meetingHallId", hallModule.getMeetingHallId());
		map.put("meetingId", hallModule.getMeetingId());
		map.put("moduleId", hallModule.getModuleId());
		return hallModuleMapper.updateHallModule(map);

	}

	@Override
	public long countUseridHallId(String userid, Integer meethallid) {

		return moduleMapper.countUseridHallId(userid, meethallid);
	}

	@Override
	public List<Module> getAllModules() {

		return moduleMapper.selectAll();
	}

}
