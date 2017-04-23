package com.bizideal.whoami.rolemodule.facadeimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.enums.RoleAndMqEnums;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.entity.HallModule;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.QrcodeScan;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.entity.UserHallRoleLink;
import com.bizideal.whoami.rolemodule.entity.UserRoleLink;
import com.bizideal.whoami.rolemodule.facade.RoleModuleWriteFacade;
import com.bizideal.whoami.rolemodule.service.HallRoleModuleService;
import com.bizideal.whoami.rolemodule.service.ModuleService;
import com.bizideal.whoami.rolemodule.service.QrcodeScanService;
import com.bizideal.whoami.rolemodule.service.RoleModuleService;
import com.bizideal.whoami.rolemodule.service.UserHallRoleLinkService;
import com.bizideal.whoami.rolemodule.service.UserRoleLinkService;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午1:45:16
 */
@Component("roleModuleWriteFacade")
public class RoleModuleWriteFacadeImp implements RoleModuleWriteFacade {
	@Autowired
	ModuleService moduleService;
	@Autowired
	RoleModuleService roleModuleService;

	@Autowired
	UserRoleLinkService userRoleLinkService;

	@Autowired
	QrcodeScanService qrcodeScanService;
	@Autowired
	HallRoleModuleService hallRoleModuleService;
	@Autowired
	UserHallRoleLinkService userHallRoleLinkService;

	@Override
	public DubboxResult batchsaveHallModules(HallModule hallModule) {

		try {
			int i = moduleService.batchsaveHallModules(hallModule);
			if (i < 1) {
				return DubboxResult.build(RoleAndMqEnums.HALL_MODULE_BATCH.getErrcode() + "", RoleAndMqEnums.HALL_MODULE_BATCH.getErrmsg(), "批量新增失败");
			} else {
				return DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_EXCEPTION + "", RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(), e.getMessage());
		}

	}

	@Override
	public DubboxResult updateHallModules(HallModule hallModule) {
		try {
			int i = moduleService.updateHallModules(hallModule);
			if (i < 1) {
				return DubboxResult.build(RoleAndMqEnums.HALL_MODULE_UPDATE.getErrcode() + "", RoleAndMqEnums.HALL_MODULE_UPDATE.getErrmsg(), "修改主会议的显示模块失败");
			} else {
				return DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_EXCEPTION + "", RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(), e.getMessage());
		}
	}

	@Override
	public DubboxResult insertRoleModule(Role role) {
		DubboxResult result = null;
		try {
			int i = roleModuleService.insertRoleModule(role);
			if (i > 0) {
				result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), role.getRoleId() + "");
			} else {
				result = DubboxResult.build(RoleAndMqEnums.ROLE_ADD.getErrcode() + "", RoleAndMqEnums.ROLE_ADD.getErrmsg(), "");
			}
		} catch (Exception e) {
			result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(), e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DubboxResult deleteRoleModule(Role role) {
		DubboxResult result = null;
		try {
			int i = roleModuleService.deleteRoleModule(role);
			if (i > 0) {
				result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), role.getRoleId() + "");
			} else {
				result = DubboxResult.build(RoleAndMqEnums.ROLE_DELETE.getErrcode() + "", RoleAndMqEnums.ROLE_DELETE.getErrmsg(), "");
			}
		} catch (Exception e) {
			result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(), e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DubboxResult updateRoleModule(Role role) {
		DubboxResult result = null;
		try {
			int i = roleModuleService.updateRoleModule(role);
			if (i > 0) {
				result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), role.getRoleId() + "");
			} else {
				result = DubboxResult.build(RoleAndMqEnums.ROLE_UPDATE.getErrcode() + "", RoleAndMqEnums.ROLE_UPDATE.getErrmsg(), "");
			}
		} catch (Exception e) {
			result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(), e.getMessage());
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public DubboxResult updateRoleModuleMember(RoleMemberDto roleMemberDto) {
		DubboxResult result = null;
		try {
			int i = roleModuleService.updateRoleModuleMember(roleMemberDto);
			if (i > 0) {
				result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), roleMemberDto.getRoleId() + "");
			} else {
				result = DubboxResult.build(RoleAndMqEnums.ROLE_UPDATE.getErrcode() + "", RoleAndMqEnums.ROLE_UPDATE.getErrmsg(), "");
			}
		} catch (Exception e) {
			result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(), e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DubboxResult deleteRoleModule(int roleid) {
		DubboxResult result = null;
		try {
			int i = roleModuleService.deleteRoleModule(roleid);
			if (i > 0) {
				result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), roleid + "");
			} else {
				result = DubboxResult.build(RoleAndMqEnums.ROLE_DELETE.getErrcode() + "", RoleAndMqEnums.ROLE_DELETE.getErrmsg(), "");
			}
		} catch (Exception e) {
			result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_EXCEPTION.getErrmsg(), e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据新增用户角色关联
	 */
	@Override
	public DubboxResult insertUserRole(UserRoleLink userRoleLink) throws Exception {
		DubboxResult dubboxResult = null;
		int i = userRoleLinkService.insertUserRole(userRoleLink);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.USER_ROLE_ADD.getErrcode() + "", RoleAndMqEnums.USER_ROLE_ADD.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

	/**
	 * 根据用户id和会议厅id删除该会议厅下的用户角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	@Override
	public DubboxResult deleteUserRole(UserRoleLink userRoleLink) throws Exception {
		DubboxResult dubboxResult = null;
		int i = userRoleLinkService.deleteUserRole(userRoleLink);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.USER_ROLE_DELETE.getErrcode() + "", RoleAndMqEnums.USER_ROLE_DELETE.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

	/**
	 * 根据用户id和会议厅id更新该会议厅下的用户角色关联
	 * 
	 * @param userRoleLink
	 * @return
	 * @throws Exception
	 */
	@Override
	public DubboxResult updateUserRole(UserRoleLink userRoleLink) throws Exception {
		DubboxResult dubboxResult = null;
		int i = userRoleLinkService.updateUserRole(userRoleLink);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.USER_ROLE_UPDATE.getErrcode() + "", RoleAndMqEnums.USER_ROLE_UPDATE.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult insertDefaultRole(Integer meetid, Integer hallid) throws Exception {
		DubboxResult dubboxResult = null;
		int i = roleModuleService.insertDefault(meetid, hallid);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLE_ADD.getErrcode() + "", RoleAndMqEnums.ROLE_ADD.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult setQRCodeScan(QrcodeScan qrcodeScan) throws Exception {
		DubboxResult dubboxResult = null;
		int i = qrcodeScanService.insertOrUpdateCode(qrcodeScan);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLE_ADD.getErrcode() + "", RoleAndMqEnums.ROLE_ADD.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult updateQRCodeScan(String userid, String redirect) throws Exception {

		DubboxResult dubboxResult = null;
		int i = qrcodeScanService.updateQrcode(userid, redirect);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLE_ADD.getErrcode() + "", RoleAndMqEnums.ROLE_ADD.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

	/*
   * 
   * 
   * 
   */
	@Override
	public DubboxResult insertHallRoleFunctions(HallRole hallrole) throws Exception {
		DubboxResult result = null;

		int i = hallRoleModuleService.insertHallRoleFunctions(hallrole);
		if (i > 0) {
			result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), hallrole.getRoleId() + "");
		} else {
			result = DubboxResult.build(RoleAndMqEnums.ROLE_ADD.getErrcode() + "", RoleAndMqEnums.ROLE_ADD.getErrmsg(), "");
		}
		return result;
	}

	@Override
	public DubboxResult deleteHallRoleFunction(HallRole hallrole) throws Exception {
		DubboxResult result = null;

		int i = hallRoleModuleService.deleteHallRoleFunction(hallrole);
		if (i > 0) {
			result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), hallrole.getRoleId() + "");
		} else {
			result = DubboxResult.build(RoleAndMqEnums.ROLE_DELETE.getErrcode() + "", RoleAndMqEnums.ROLE_DELETE.getErrmsg(), "");
		}
		return result;
	}

	@Override
	public DubboxResult updateHallRoleFunction(HallRole hallrole) throws Exception {
		DubboxResult result = null;

		int i = hallRoleModuleService.updateHallRoleFunction(hallrole);
		if (i > 0) {
			result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), hallrole.getRoleId() + "");
		} else {
			result = DubboxResult.build(RoleAndMqEnums.ROLE_UPDATE.getErrcode() + "", RoleAndMqEnums.ROLE_UPDATE.getErrmsg(), "");
		}
		return result;
	}

	@Override
	public DubboxResult deleteHallRoleFunction(int roleid) throws Exception {
		DubboxResult result = null;

		int i = hallRoleModuleService.deleteHallRoleFunction(roleid);
		if (i > 0) {
			result = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), roleid + "");
		} else {
			result = DubboxResult.build(RoleAndMqEnums.ROLE_DELETE.getErrcode() + "", RoleAndMqEnums.ROLE_DELETE.getErrmsg(), "");
		}
		return result;
	}

	@Override
	public DubboxResult insertUserHallRole(UserHallRoleLink userhallrolelink) throws Exception {
		DubboxResult dubboxResult = null;
		int i = userHallRoleLinkService.insertUserHallRole(userhallrolelink);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.USER_ROLE_ADD.getErrcode() + "", RoleAndMqEnums.USER_ROLE_ADD.getErrmsg(), i + "");
		}
		return dubboxResult;

	}

	@Override
	public DubboxResult deleteUserHallRole(UserHallRoleLink userhallrolelink) throws Exception {
		DubboxResult dubboxResult = null;
		int i = userHallRoleLinkService.deleteUserHallRole(userhallrolelink);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.USER_ROLE_DELETE.getErrcode() + "", RoleAndMqEnums.USER_ROLE_DELETE.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult updateUserHallRole(UserHallRoleLink userhallrolelink) throws Exception {
		DubboxResult dubboxResult = null;
		int i = userHallRoleLinkService.updateUserHallRole(userhallrolelink);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.USER_ROLE_UPDATE.getErrcode() + "", RoleAndMqEnums.USER_ROLE_UPDATE.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult insertDefaultHallRole(Integer hallid) throws Exception {
		DubboxResult dubboxResult = null;
		int i = hallRoleModuleService.insertDefaultHallRole(hallid);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.USER_ROLE_ADD.getErrcode() + "", RoleAndMqEnums.USER_ROLE_ADD.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult insertRoleModuleJoiner(RoleMemberDto roleMemberDto) throws Exception {
		DubboxResult dubboxResult = null;
		int i = roleModuleService.insertRoleModuleJoiner(roleMemberDto);
		if (i > 0) {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.ROLEANDMQ_OK.getErrcode() + "", RoleAndMqEnums.ROLEANDMQ_OK.getErrmsg(), i + "");
		} else {
			dubboxResult = DubboxResult.build(RoleAndMqEnums.USER_ROLE_ADD.getErrcode() + "", RoleAndMqEnums.USER_ROLE_ADD.getErrmsg(), i + "");
		}
		return dubboxResult;
	}

}
