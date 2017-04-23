package com.bizideal.whoami;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.entity.HallModule;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.service.ModuleService;
import com.bizideal.whoami.rolemodule.service.RoleModuleService;

public class CopyOfR {

	@Test
	public void adfasfd() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		// 从spring容器中获得Mapper的代理对象
		ModuleService modulemapper = applicationContext.getBean(ModuleService.class);
		HallModule HallModule = new HallModule();
		// HallModule [meetingHallId=503, meetingId=627, moduleId=1,2,3,4,6,9]
		HallModule.setMeetingHallId(503);
		HallModule.setMeetingId(627);
		HallModule.setModuleId("1,2,3,4");
		modulemapper.updateHallModules(HallModule);

	}

	@Test
	public void adfafd() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		// 从spring容器中获得Mapper的代理对象
		RoleModuleService modulemapper = applicationContext.getBean(RoleModuleService.class);
		RoleMemberDto roleMemberDto = new RoleMemberDto();
		roleMemberDto.setCost("111");
		roleMemberDto.setType(0);
		roleMemberDto.setRoleName("望江");
		roleMemberDto.setPeopleNumber(111);
		roleMemberDto.setMember_type_id(5555);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		roleMemberDto.setModuleIds(list);

		modulemapper.insertRoleModuleJoiner(roleMemberDto);

	}

	@Test
	public void adfasdfafd() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		// 从spring容器中获得Mapper的代理对象
		RoleModuleService modulemapper = applicationContext.getBean(RoleModuleService.class);
		// RoleMemberDto roleModuleBymember_type_id = modulemapper.getRoleModuleBymember_type_id(24, 1);
		// System.out.println(roleModuleBymember_type_id);
	}

	@Test
	public void adfasdfaaghdfd() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		// 从spring容器中获得Mapper的代理对象
		RoleModuleService modulemapper = applicationContext.getBean(RoleModuleService.class);
		// RoleMemberDto roleModuleBymember_type_id = modulemapper.getRoleModuleBymember_type_id(24, 1);
		// System.out.println(roleModuleBymember_type_id);
		Role existRoleNameManage = modulemapper.existRoleNameManage("11", 627);
		System.out.println(existRoleNameManage);

	}

}