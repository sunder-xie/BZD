//package com.bizideal.whoami;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import tk.mybatis.mapper.entity.Example;
//
//import com.bizideal.whoami.rolemodule.entity.Module;
//import com.bizideal.whoami.rolemodule.entity.Role;
//import com.bizideal.whoami.rolemodule.entity.UserHallRoleLink;
//import com.bizideal.whoami.rolemodule.entity.UserRoleLink;
//import com.bizideal.whoami.rolemodule.mapper.HallFunctionMapper;
//import com.bizideal.whoami.rolemodule.mapper.HallRoleModuleMapper;
//import com.bizideal.whoami.rolemodule.mapper.ModuleMapper;
//import com.bizideal.whoami.rolemodule.mapper.RoleModuleMapper;
//import com.bizideal.whoami.rolemodule.mapper.RolePersonalMapper;
//import com.bizideal.whoami.rolemodule.mapper.RoleUnitMapper;
//import com.bizideal.whoami.rolemodule.mapper.UserHallRoleLinkMapper;
//import com.bizideal.whoami.rolemodule.mapper.UserRoleLinkMapper;
//import com.bizideal.whoami.rolemodule.service.ModuleService;
//import com.bizideal.whoami.rolemodule.service.RoleModuleService;
//import com.bizideal.whoami.rolemodule.service.UserRoleLinkService;
//
//public class RoleModuleTestDemo {
//
//	@Test
//	public void roleModelMapper() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//
//		// 从spring容器中获得Mapper的代理对象
//		RoleModuleMapper mapper = applicationContext.getBean(RoleModuleMapper.class);
//
//		ModuleMapper moduleMapper = applicationContext.getBean(ModuleMapper.class);
//		List<Integer> moduleids = moduleMapper.getAllModuleIds();
//		for (Integer integer : moduleids) {
//			System.out.println(integer);
//		}
//		Role upRole = new Role();
//		upRole.setRoleId(78);
//		upRole.setRoleName("dadda");
//		mapper.updateByPrimaryKeySelective(upRole);
//
//		List<String> ss = mapper.getManagerUserIdByhallid(1);
//
//		// Role rolesRole = mapper.findRoleModuleUnitByUseridHallId("584f8a17d418177e037e0941", 1);
//		// System.out.println(rolesRole);
//		//
//		// Role rp = mapper.findByRoleModulePersonal(1);
//		// System.out.println(3333);
//		// rp = mapper.findByRoleModuleUnit(5);
//		//
//		// Role role = new Role();
//		// role.setMeetHallId(1);
//		// role.setRoleRemark("beizhu");
//		// role.setRoleName("name1");
//		// role.setRoleType("与会人员");
//		// List<Role> rrrr = mapper.getRoleByCondition("", 1);
//		// for (Role role2 : rrrr) {
//		// System.out.println(role2.getRoleId());
//		// }
//		// // mapper.insert(role);
//		// System.out.println(role.getRoleId());
//
//		Module module = new Module();
//		module.setModuleName("会议报名");
//		module.setModuleRemark("备注");
//		module.setModuleStatus("状态");
//		module.setModuleType("类型");
//		module.setModuleUrl("url");
//		// moduleMapper.insert(module);
//		System.out.println(module.getModuleId());
//		// List<Module> list = mapper.selectModules(3);
//		// for (Module module2 : list) {
//		// System.out.println(module2.getModuleName());
//		// }
//		// Role r = mapper.findById(2);
//		// System.out.println(r.getRoleName());
//		// for (Module module3 : r.getModules()) {
//		// System.out.println(module3.getModuleName());
//		// }
//		Map<String, Integer> roleModuleMap = new HashMap<String, Integer>();
//		roleModuleMap.put("role_id", 3);
//		roleModuleMap.put("module_id", 5);
//		int i = mapper.saveRoleModules(roleModuleMap);
//		// int i = mapper.findRoleModule(roleModuleMap);
//		System.out.println(i);
//		Map<String, Integer> roleModuleMap1 = new HashMap<String, Integer>();
//		roleModuleMap1.put("role_id", 4);
//		roleModuleMap1.put("module_id", 5);
//		Map<String, Integer> roleModuleMap2 = new HashMap<String, Integer>();
//		roleModuleMap2.put("role_id", 4);
//		roleModuleMap2.put("module_id", 6);
//		List<Map<String, Integer>> listss = new ArrayList<Map<String, Integer>>();
//		listss.add(roleModuleMap1);
//		listss.add(roleModuleMap2);
//		// int s = mapper.batchsaveRoleModules(listss);
//		// int s = mapper.deleteRoleModules(4);
//		int s = 0;
//		System.out.println(s);
//		Role rm = mapper.findByRoleModuleId(3);
//		for (Module module99 : rm.getModules()) {
//			System.out.println(module99.getModuleName());
//		}
//	}
//
//	@Test
//	public void testService() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//
//		// 从spring容器中获得Mapper的代理对象
//		RoleModuleService service = applicationContext.getBean(RoleModuleService.class);
//		// String ok = service.setInviteUrl(1, 2);
//		// System.out.println(ok);
//		Role rs = service.findRoleByUserIdHallId("dddd", 2);
//		service.insertDefault(1, 408);
//		String oks = service.inviteManager(1, 2, 55, "ddd");
//		System.out.println(StringUtils.isBlank(oks));
//		Role role = new Role();
//		role.setMeetHallId(1);
//		role.setRoleRemark("beizhu");
//		role.setRoleName("99999");
//		role.setRoleType("与会人员99999");
//		role.setRoleId(9);
//		List<Integer> moduleids = new ArrayList<Integer>();
//
//		moduleids.add(3);
//		moduleids.add(4);
//		// role.setModuleIds(moduleids);
//		// service.insertRoleModule(role);
//		// service.updateRoleModule(role);
//		// service.deleteRoleModule(2);
//		System.out.println(role.getRoleId());
//
//		Role r = service.getRoleModuleByRoleId(9);
//		// System.out.println(r.getRoleName());
//		// for (Module module3 : r.getModules()) {
//		// System.out.println(module3.getModuleName());
//		// }
//		// Map<String, Integer> roleModuleMap = new HashMap<String, Integer>();
//		// roleModuleMap.put("role_id", 3);
//		// roleModuleMap.put("module_id", 5);
//		// int i = mapper.saveRoleModules(roleModuleMap);
//		// // int i = mapper.findRoleModule(roleModuleMap);
//		// System.out.println(i);
//		// Map<String, Integer> roleModuleMap1 = new HashMap<String, Integer>();
//		// roleModuleMap1.put("role_id", 4);
//		// roleModuleMap1.put("module_id", 5);
//		// Map<String, Integer> roleModuleMap2 = new HashMap<String, Integer>();
//		// roleModuleMap2.put("role_id", 4);
//		// roleModuleMap2.put("module_id", 6);
//		// List<Map<String, Integer>> listss = new
//		// ArrayList<Map<String,Integer>>();
//		// listss.add(roleModuleMap1);
//		// listss.add(roleModuleMap2);
//		// // int s = mapper.batchsaveRoleModules(listss);
//		// // int s = mapper.deleteRoleModules(4);
//		// int s = 0;
//		// System.out.println(s);
//		// Role rm = mapper.findByRoleModuleId(3);
//		// for (Module module99 : rm.getModules()) {
//		// System.out.println(module99.getModuleName());
//		// }
//		List<Role> rrrr = service.getRoleByCondition("", 1);
//		System.out.println(rrrr.size());
//	}
//
//	@Test
//	public void modelMapper() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//
//		// 从spring容器中获得Mapper的代理对象
//		ModuleMapper moduleMapper = applicationContext.getBean(ModuleMapper.class);
//		long l = moduleMapper.countUseridHallId("584f8a17d418177e037e0941", 1);
//		System.out.println(l);
//		// List<Module> list = moduleMapper.selectHallModules(118);
//		// for (Module module : list) {
//		// System.out.println(module.getModuleName());
//		// }
//		Map<String, Integer> map1 = new HashMap<String, Integer>();
//		Map<String, Integer> map2 = new HashMap<String, Integer>();
//		Map<String, Integer> map3 = new HashMap<String, Integer>();
//		map1.put("meet_hall_id", 168);
//		map1.put("meet_id", 118);
//		map1.put("module_id", 1);
//
//		map2.put("meet_hall_id", 168);
//		map2.put("meet_id", 118);
//		map2.put("module_id", 4);
//
//		map3.put("meet_hall_id", 168);
//		map3.put("meet_id", 118);
//		map3.put("module_id", 3);
//		List<Map<String, Integer>> list1 = new ArrayList<Map<String, Integer>>();
//		list1.add(map1);
//		list1.add(map3);
//		list1.add(map2);
//		ModuleService moduleService = applicationContext.getBean(ModuleService.class);
//
//		// int i = moduleMapper.batchsaveHallModules(list1);
//		// int i = moduleService.updateHallModules(list1);
//		// System.out.println(i);
//	}
//
//	@Test
//	public void personUnit() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//
//		// 从spring容器中获得Mapper的代理对象
//		RolePersonalMapper rolePersonalMapper = applicationContext.getBean(RolePersonalMapper.class);
//
//		RoleUnitMapper roleUnitMapper = applicationContext.getBean(RoleUnitMapper.class);
//		int i = rolePersonalMapper.deleteByRoleId(3);
//		System.out.println(i);
//		i = roleUnitMapper.deleteByRoleId(6);
//		System.out.println(i);
//
//	}
//
//	@Test
//	public void userrole() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//
//		// 从spring容器中获得Mapper的代理对象
//		UserRoleLinkMapper userRoleLinkMapper = applicationContext.getBean(UserRoleLinkMapper.class);
//		List<Integer> hallids = userRoleLinkMapper.getHallIdByUserId("dddid2");
//
//		List<UserRoleLink> ss = new ArrayList<UserRoleLink>();
//		UserRoleLink userRoleLinks = null;
//		for (int i = 0; i < 5; i++) {
//			userRoleLinks = new UserRoleLink("dddid" + i, i, i);
//			ss.add(userRoleLinks);
//
//		}
//		int is = userRoleLinkMapper.batchsaveUserRoleLink(ss);
//		UserRoleLinkService serv = applicationContext.getBean(UserRoleLinkService.class);
//		UserRoleLink userRoleLink = new UserRoleLink();
//		userRoleLink.setUserId("userid");
//		userRoleLink.setRoleId(1);
//		userRoleLink.setMeetHallId(1);
//		// int u = serv.updateUserRole(userRoleLink);
//		// System.out.println(u);
//		int d = serv.deleteUserRole(userRoleLink);
//		System.out.println(d);
//
//		int i = userRoleLinkMapper.insert(userRoleLink);
//		System.out.println(i);
//		userRoleLink.setRoleId(3);
//		// userRoleLinkMapper.delete(userRoleLink);
//
//		Example example = new Example(UserRoleLink.class);
//		example.createCriteria().andEqualTo("userId", "userid").andEqualTo("meetHallId", 1);
//		// userRoleLinkMapper.deleteByExample(example);
//		userRoleLinkMapper.updateByExample(userRoleLink, example);
//
//	}
//
//	@Test
//	public void hallrole() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//
//		// 从spring容器中获得Mapper的代理对象
//		HallFunctionMapper hallFunctionMapper = applicationContext.getBean(HallFunctionMapper.class);
//
//		// List<HallFunction> functions = hallFunctionMapper.selectAll();
//		// for (HallFunction hallFunction : functions) {
//		// System.out.println(hallFunction.getFunctionName());
//		// }
//		UserHallRoleLinkMapper userHallRoleLinkmapper = applicationContext.getBean(UserHallRoleLinkMapper.class);
//		List<UserHallRoleLink> ls = new ArrayList<UserHallRoleLink>();
//		ls.add(new UserHallRoleLink("11", 1, 1));
//		ls.add(new UserHallRoleLink("22", 2, 2));
//		// int i = userHallRoleLinkmapper.batchsaveUserHallRoleLink(ls);
//		// System.out.println(i);
//		long c = userHallRoleLinkmapper.countUseridHallId("11", 1);
//		System.out.println(c);
//
//		List<Integer> hallid = userHallRoleLinkmapper.getHallIdByUserId("11");
//		for (Integer integer : hallid) {
//			System.out.println(integer);
//		}
//		HallRoleModuleMapper hallrolemodulemapper = applicationContext.getBean(HallRoleModuleMapper.class);
//		List<Map<String, Integer>> maps = new ArrayList<Map<String, Integer>>();
//		for (int i = 0; i < 2; i++) {
//			Map<String, Integer> map = new HashMap<String, Integer>();
//			map.put("function_id", i + 6);
//			map.put("role_id", i + 6);
//			maps.add(map);
//			// hallrolemodulemapper.saveRoleModules(map);
//		}
//
//		// int cc = hallrolemodulemapper.batchsaveRoleModules(maps);
//		// System.out.println(cc);
//		int d = hallrolemodulemapper.deleteRoleModules(4);
//		System.out.println(d);
//
//	}
//
//	@Test
//	public void modules() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//		// 从spring容器中获得Mapper的代理对象
//		ModuleMapper modulemapper = applicationContext.getBean(ModuleMapper.class);
//		String selectMeetingModulesId = modulemapper.selectMeetingModulesId(1000);
//
//		System.out.println(selectMeetingModulesId);
//		// List stringToList = stringToList(selectMeetingModulesId);
//		// System.out.println(stringToList);
//
//		// List<Module> selectmeetingModules = modulemapper.selectmeetingModules(stringToList);
//		// System.out.println(selectmeetingModules);
//
//	}
//
//	@Test
//	public void modulesupdate() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//		// 从spring容器中获得Mapper的代理对象
//		RoleModuleMapper modulemapper = applicationContext.getBean(RoleModuleMapper.class);
//		Role findByRoleModuleId = modulemapper.findByRoleModuleId(921);
//		System.out.println(findByRoleModuleId);
//
//	}
//
//	@Test
//	public void modulesupdatesdajkfjk() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//		// 从spring容器中获得Mapper的代理对象
//		RoleModuleMapper modulemapper = applicationContext.getBean(RoleModuleMapper.class);
//		Role findByRoleModuleId = modulemapper.findRoleByUserIdHallId("587ecd5b86427077a8901913", 471);
//		System.out.println(findByRoleModuleId);
//
//	}
//
//	@Test
//	public void adfasfd() throws Exception {
//		// 创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//		// 从spring容器中获得Mapper的代理对象
//		RoleModuleService modulemapper = applicationContext.getBean(RoleModuleService.class);
//		Role findByRoleModuleId = modulemapper.getDefaultManagerByMeetId(622);
//		System.out.println(findByRoleModuleId);
//
//	}
//
// }