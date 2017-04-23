package com.bizideal.whoami.rolemodule.facadeimp;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadRestFacade;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午2:06:44
 */
@Component("roleModuleReadRestFacade")
@Path("moduleread")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
public class RoleModuleReadRestFacadeImp implements RoleModuleReadRestFacade {
	// @Autowired
	// ModuleService moduleService;
	// @Autowired
	// RoleModuleService roleModuleService;
	//
	// @Override
	// @GET
	// @Path("meet/{meetid : \\d+}")
	// @Produces({ ContentType.APPLICATION_JSON_UTF_8 })
	// public List<Module> selectHallModules(@PathParam("meetid") Integer meetid) {
	// // 根据主会议id查询该主会议应显示的功能模块
	// return moduleService.selectHallModules(meetid);
	// }
	//
	// // @Override
	// // @GET
	// // @Path("personal/{roleid : \\d+}")
	// // public Role findByRoleModulePersonal(@PathParam("roleid") int roleid) {
	// // // 根据角色id查询角色信息和权限 以及个人报名的限制
	// // return roleModuleService.findByRoleModulePersonal(roleid);
	// // }
	//
	// // @Override
	// // @GET
	// // @Path("unit/{roleid : \\d+}")
	// // public Role findByRoleModuleUnit(@PathParam("roleid") int roleid) {
	// // // 根据角色id查询角色信息和权限 以及单位报名的限制
	// // return roleModuleService.findByRoleModuleUnit(roleid);
	// // }
	//
	// @GET
	// @Path("role/{roleid : \\d+}")
	// @Override
	// public Role getRoleModuleByRoleId(@PathParam("roleid") int roleid) {
	// // 根据角色id获取角色信息和权限模块
	// return roleModuleService.getRoleModuleByRoleId(roleid);
	// }
	//
	// @GET
	// @Path("get/{roletype}/{meethallid}")
	// @Override
	// public List<Role> getRoleByCondition(@PathParam("roletype") String roleType, @PathParam("meethallid") Integer meetHallId) {
	// // 根据角色类型和会议厅id获取劫色列表
	// return roleModuleService.getRoleByCondition(roleType, meetHallId);
	// }
	//
	// @GET
	// @Path("getbytype/{roletype}")
	// @Override
	// public List<Role> getRoleByType(@PathParam("roletype") String roleType) {
	// // 根据角色类型获取劫色列表
	// return roleModuleService.getRoleByCondition(roleType, 0);
	// }
	//
	// @GET
	// @Path("getbyhallid/{meethallid}")
	// @Override
	// public List<Role> getRoleByHallid(@PathParam("meethallid") Integer meethallid) {
	// // 根据会议厅id获取角色列表
	// return roleModuleService.getRoleByCondition("", meethallid);
	// }

}
