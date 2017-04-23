package com.bizideal.whoami.rolemodule.facadeimp;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.rolemodule.facade.RoleModuleWriteRestFacade;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午2:15:52
 */
@Component("roleModuleWriteRestFacade")
@Path("modulewrite")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
public class RoleModuleWriteRestFacadeImp implements RoleModuleWriteRestFacade {
	// @Autowired
	// ModuleService moduleService;
	// @Autowired
	// RoleModuleService roleModuleService;
	//
	// @Override
	// @POST
	// @Path("addhallmodule")
	// @Consumes({ MediaType.APPLICATION_JSON })
	// public DubboxResult batchsaveHallModules(
	// List<Map<String, Integer>> hallModule) {
	// try {
	// int i = moduleService.batchsaveHallModules(hallModule);
	// if (i < 1) {
	// return DubboxResult.build(
	// RoleAndMqEnums.HALL_MODULE_BATCH.getErrcode() + "",
	// RoleAndMqEnums.HALL_MODULE_BATCH.getErrmsg(), "批量新增失败");
	// } else {
	// return DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK + "",
	// RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// return DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_EXCEPTION + "",
	// RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(),
	// e.getMessage());
	// }
	//
	// }
	//
	// @Override
	// @POST
	// @Path("updatehallmodule")
	// @Consumes({ MediaType.APPLICATION_JSON })
	// @Produces({ ContentType.APPLICATION_JSON_UTF_8 })
	// public DubboxResult updateHallModules(List<Map<String, Integer>> hallModule) {
	// try {
	// int i = moduleService.updateHallModules(hallModule);
	// if (i < 1) {
	// return DubboxResult.build(
	// RoleAndMqEnums.HALL_MODULE_UPDATE.getErrcode() + "",
	// RoleAndMqEnums.HALL_MODULE_UPDATE.getErrmsg(), "修改主会议的显示模块失败");
	// } else {
	// return DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK + "",
	// RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// return DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_EXCEPTION + "",
	// RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(),
	// e.getMessage());
	// }
	// }
	//
	// @Override
	// @POST
	// @Path("addrolemodule")
	// @Consumes({ MediaType.APPLICATION_JSON })
	// public DubboxResult insertRoleModule(Role role) {
	// DubboxResult result = null;
	// try {
	// int i = roleModuleService.insertRoleModule(role);
	// if (i > 0) {
	// result = DubboxResult.build(
	// RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "",
	// RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(),
	// role.getRoleId() + "");
	// } else {
	// result = DubboxResult.build(
	// RoleAndMqEnums.ROLE_ADD.getErrcode() + "",
	// RoleAndMqEnums.ROLE_ADD.getErrmsg(), "");
	// }
	// } catch (Exception e) {
	// result = DubboxResult.build(
	// RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrcode() + "",
	// RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(),
	// e.getMessage());
	// e.printStackTrace();
	// }
	// return result;
	// }
	//
	//
	// @Override
	// @POST
	// @Path("updaterolemodule")
	// @Consumes({ MediaType.APPLICATION_JSON })
	// public DubboxResult updateRoleModule(Role role) {
	// DubboxResult result = null;
	// try {
	// int i = roleModuleService.updateRoleModule(role);
	// if (i > 0) {
	// result = DubboxResult.build(
	// RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "",
	// RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(),
	// role.getRoleId() + "");
	// } else {
	// result = DubboxResult.build(
	// RoleAndMqEnums.ROLE_UPDATE.getErrcode() + "",
	// RoleAndMqEnums.ROLE_UPDATE.getErrmsg(), "");
	// }
	// } catch (Exception e) {
	// result = DubboxResult.build(
	// RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrcode() + "",
	// RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(),
	// e.getMessage());
	// e.printStackTrace();
	// }
	// return result;
	//
	// }
	//
	//
	// @GET
	// @Path("deleterolemodule/{roleid}")
	// @Override
	// public DubboxResult deleteRoleModule(@PathParam("roleid")int roleid) {
	// DubboxResult result = null;
	// try {
	// int i = roleModuleService.deleteRoleModule(roleid);
	// if (i > 0) {
	// result = DubboxResult.build(
	// RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "",
	// RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), roleid + "");
	// } else {
	// result = DubboxResult.build(
	// RoleAndMqEnums.ROLE_DELETE.getErrcode() + "",
	// RoleAndMqEnums.ROLE_DELETE.getErrmsg(), "");
	// }
	// } catch (Exception e) {
	// result = DubboxResult.build(
	// RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrcode() + "",
	// RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(),
	// e.getMessage());
	// e.printStackTrace();
	// }
	// return result;
	// }

}
