package com.bizideal.whoami.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.rolemodule.entity.HallFunction;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.Module;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.user.entity.UserWeixinInfo;

public class RoleModuleUtils {

	// 更换module的名字,取交集
	public static void repeatModule(List<Module> meetingmodules, List<Module> modules2, List<Module> modulesjoiner) {
		for (Module modulesuper : meetingmodules) {
			for (Module module : modules2) {
				if ((module.getModuleId()) == (modulesuper.getModuleId())) {
					String moduleName = modulesuper.getModuleName();
					String[] splitName = moduleName.split(",");
					modulesuper.setModuleName(splitName[0]);
					String moduleUrl = modulesuper.getModuleUrl();
					String[] splitmoduleUrl = moduleUrl.split(",");
					modulesuper.setModuleUrl(splitmoduleUrl[0]);
					modulesjoiner.add(modulesuper);
				}
			}
		}
	}

	// 跟换module的名字
	public static void repeatMeeting(List<Module> meetingmodules, Integer index) {
		if (meetingmodules != null) {

			for (Module module : meetingmodules) {
				String moduleName = module.getModuleName();
				String[] splitName = moduleName.split(",");
				module.setModuleName(splitName[index]);
				String moduleUrl = module.getModuleUrl();
				String[] splitmoduleUrl = moduleUrl.split(",");
				module.setModuleUrl(splitmoduleUrl[index]);
			}
		}
	}

	// 普通管理员的会议跳转页面
	public static void modules(List<Module> meetingmodules, List<Module> modules) {
		for (Module module : modules) {
			for (Module modulesuper : meetingmodules) {
				if ((module.getModuleId()) == (modulesuper.getModuleId())) {
					String moduleName = module.getModuleName();
					String[] splitName = moduleName.split(",");
					modulesuper.setModuleName(splitName[1]);
					String moduleUrl = module.getModuleUrl();
					String[] splitmoduleUrl = moduleUrl.split(",");
					modulesuper.setModuleUrl(splitmoduleUrl[1]);
				}
			}
		}
	}

	// 把List转换成为String
	public static String listToString(List<String> list) {
		List listint = new ArrayList<>();
		for (String string : list) {
			listint.add(Integer.valueOf(string));
		}
		Collections.sort(listint);
		StringBuffer fields = new StringBuffer();
		for (int i = 0; i < listint.size(); i++) {
			if (i + 1 == listint.size()) {
				fields.append(listint.get(i));
			} else {
				fields.append(listint.get(i) + ",");
			}
		}
		return fields.toString();
	}

	// 用于修改权限跟换模块的名字的公共方法
	public static void ModuleNameReplace(List<Module> modules2, List<Module> modules, List<Module> ModulesNo, Integer index) {
		for (Module module : modules2) {
			for (Module Amodule : modules) {
				if ((module.getModuleId()) == (Amodule.getModuleId())) {
					ModulesNo.add(Amodule);
				}
			}
		}
		modules.removeAll(ModulesNo);// 移除模块
		for (Module modulesuper : modules) {// 跟换module的名字
			String moduleName = modulesuper.getModuleName();
			String[] splitName = moduleName.split(",");
			modulesuper.setModuleName(splitName[index]);
		}
		for (Module modulesuper : ModulesNo) {// 跟换module的名字
			String moduleName = modulesuper.getModuleName();
			String[] splitName = moduleName.split(",");
			modulesuper.setModuleName(splitName[index]);
		}
	}

	// 会议动态,会议导读,会议议程,会议资料的判断权限的方法
	public static void permissions(HallRole hallrole, Role role, String userId, MeetingInfo meetingInfo, MeetingHall meetingHall, int moduleIdP, Model model) {

		// 查询出会议的创建人
		if (userId.equals(meetingInfo.getCreateUser()) || userId.equals(meetingHall.getUserId())) {// 判断是否是本人
			model.addAttribute("permissions", "permissions");
			return;
		}
		if (hallrole != null) { // 判断是不是会议厅超级管理员
			if (Objects.equals(hallrole.getRoleType(), "manager") && Objects.equals(hallrole.getRoleStatus(), "default")) {
				model.addAttribute("permissions", "permissions");
				return;
			}
		}
		if (role == null) {// 普通会员没有权限
			model.addAttribute("permissions", "nopermissions");
		} else {// 判断是不是管理员
			List<Module> modules = role.getModules();
			if (Objects.equals(role.getRoleType(), "manager") && Objects.equals(role.getRoleStatus(), "default")) {
				model.addAttribute("permissions", "permissions");
			} else {
				// 普通管理员
				if ((role.getRoleType()).equals("manager")) {
					for (Module module : modules) {
						// 为会议动态的ModuleId
						if (moduleIdP == module.getModuleId()) {// 有修改权限
							model.addAttribute("permissions", "permissions");
							break;
						}
					}
				} else {// 普通的与会人员与没有权限的管理员
					model.addAttribute("permissions", "nopermissions");
				}
			}
		}
		return;
	}

	/**
	 * 判断会议是不是管理员的
	 * 
	 * @param hallrole
	 * @param role
	 * @param modelAndView
	 * @param user
	 * @param meetingHall
	 * @param meetingInfo
	 */
	public static void meetingToPermission(HallRole hallrole, Role role, Model model, UserWeixinInfo user, MeetingHall meetingHall, MeetingInfo meetingInfo) {
		// 判断是不是自己本人的主会议
		if (user.getUserId().equals(meetingInfo.getCreateUser()) || user.getUserId().equals(meetingHall.getUserId())) {
			model.addAttribute("permissions", "permissions");
			return;
		} else {

			if (hallrole != null) { // 判断是不是会议厅超级管理员
				if (Objects.equals(hallrole.getRoleType(), "manager") && Objects.equals(hallrole.getRoleStatus(), "default")) {
					model.addAttribute("permissions", "permissions");
					return;
				}
			}
			// 判断是不是有邀请会议的权限 默认超级管理员
			if (role != null) {
				if (Objects.equals(role.getRoleType(), "manager") && Objects.equals(role.getRoleStatus(), "default"))
					model.addAttribute("permissions", "permissions");
				return;
			}
		}
	}

	// 判断会议厅的超级管理员
	public static Boolean opinionRole(HallRole hallRole, List<HallFunction> allHallFunction, HttpServletRequest request, ModelMap mode, String userId, MeetingHall meetingHall) {
		// 判断是我创建的会议厅
		if (userId.equals(meetingHall.getUserId())) {
			mode.addAttribute("permissions", "permissions");
			return true;
		}

		if (hallRole != null) {
			// 超级管理员
			if (Objects.equals(hallRole.getRoleType(), "manager") && Objects.equals(hallRole.getRoleStatus(), "default")) {
				mode.addAttribute("permissions", "permissions");
				return true;
			}
			// 普通管理员
			if ("manager".equals(hallRole.getRoleType())) {

				for (HallFunction allhallFunction : allHallFunction) {
					for (HallFunction hallFunction : hallRole.getFunctions()) {
						if (allhallFunction.getFunctionId() == hallFunction.getFunctionId()) {
							mode.addAttribute("permissions" + hallFunction.getFunctionId(), "permissions" + hallFunction.getFunctionId());// Id用来区分是那个权限
						}
					}
				}
				return true;
			}
		}
		return false;
	}

}