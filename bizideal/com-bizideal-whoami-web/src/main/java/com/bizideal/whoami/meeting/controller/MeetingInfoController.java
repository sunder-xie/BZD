package com.bizideal.whoami.meeting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.entity.BaseResData;
import com.bizideal.whoami.facade.meeting.dto.MeetingInfoDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingHallFacade;
import com.bizideal.whoami.facade.meeting.service.MeetingInfoFacade;
import com.bizideal.whoami.facade.meeting.service.MeetingLinkUserFacade;
import com.bizideal.whoami.handler.CustomExceptionHandlerController;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.entity.HallModule;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.Module;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.rolemodule.facade.RoleModuleWriteFacade;
import com.bizideal.whoami.signup.entity.SignUpInfo;
import com.bizideal.whoami.signup.entity.SignupFieldMeetingLink;
import com.bizideal.whoami.signup.entity.SignupFieldModuleLink;
import com.bizideal.whoami.signup.facade.SignUpInfoFacade;
import com.bizideal.whoami.signup.facade.SignupFieldFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.utils.RoleModuleUtils;
import com.bizideal.whoami.wechat.pojo.UpdateMeetingModulePojo;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName MeetingInfoController
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月11日
 */
@Controller
@RequestMapping("/meetingInfo")
public class MeetingInfoController extends CustomExceptionHandlerController {

	private Logger logger = LoggerFactory.getLogger(MeetingInfoController.class);

	@Autowired
	private MeetingInfoFacade meetingInfoFacade;

	@Autowired
	MeetingHallFacade meetingHallFacade;
	@Autowired
	MeetingLinkUserFacade meetingLinkUserFacade;
	@Autowired
	private RoleModuleReadFacade roleModuleReadFacade;
	@Autowired
	private RoleModuleWriteFacade roleModuleWriteFacade;

	@Autowired
	private SignUpInfoFacade signUpInfoFacade;

	@Autowired
	private SignupFieldFacade signupFieldFacade;

	@Value("${fastdfsurl}")
	private String fastdfsurl;
	@Value("${imageHallHead}")
	private String imageHallHead;// 会议厅头像
	@Value("${imageMeetingHead}")
	private String imageMeetingHead;// 会议头像

	@RequestMapping("/toCreateMeeting")
	public String toCreateMeeting(Model model, MeetingInfo meetingInfo) {
		model.addAttribute("hallId", meetingInfo.getHallId());
		return "meeting/creatMeet";
	}

	@RequestMapping("/toCreateSubMeeting")
	public String toCreateSubMeeting(Model model, SubMeetingInfo submeetingInfo) {
		// 根据主会议Id查询出完整的主会议信息
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setMeeId(submeetingInfo.getMeeParentId());
		meetingInfo = meetingInfoFacade.selectMeetingInfoById(meetingInfo);
		model.addAttribute("meetingInfo", meetingInfo);
		return "meeting/creat-chMeet";
	}

	@RequestMapping("/toCreateSuccess")
	public String toCreateSuccess(Model model, MeetingInfo meetingInfo) {
		model.addAttribute("meetingInfo", meetingInfo);
		return "meeting/creatmeet-success";
	}

	@RequestMapping("/toMeetRoom")
	public String toMeetRoom(Model model, MeetingInfo meetingInfo) {
		model.addAttribute("meetingInfo", meetingInfo);
		return "meeting/meetroom-index";
	}

	// 创建会议时创建子会议
	@RequestMapping("/toMeetList")
	public String toMeetList(Model model, SubMeetingInfo meetingInfo) {
		model.addAttribute("meetingInfo", meetingInfo);
		List list = (List) meetingInfoFacade.selectSubMeetingInfo(meetingInfo).get("list");
		model.addAttribute("list", list);
		return "meeting/ch-meet-list";
	}

	// 会议厅会议主页添加子会议
	@RequestMapping("/toSubMeetList")
	public String toSubMeetList(Model model, SubMeetingInfo meetingInfo) {
		model.addAttribute("meetingInfo", meetingInfo);
		List list = (List) meetingInfoFacade.selectSubMeetingInfo(meetingInfo).get("list");
		model.addAttribute("list", list);
		return "meeting/ch-subMeet-list";
	}

	// 会议管理会议主页添加子会议
	@RequestMapping("/toManagerSubMeetList")
	public String toManagerSubMeetList(Model model, SubMeetingInfo meetingInfo) {
		model.addAttribute("meetingInfo", meetingInfo);
		List list = (List) meetingInfoFacade.selectSubMeetingInfo(meetingInfo).get("list");
		model.addAttribute("list", list);
		return "meeting/ch-managerSubMeet-list";
	}

	@RequestMapping("/addMeeting")
	@ResponseBody
	public BaseResData<MeetingInfo> insertMeetingInfo(@RequestBody MeetingInfo meetingInfo, HttpServletRequest request) throws Exception {
		BaseResData<MeetingInfo> baseResData = new BaseResData<MeetingInfo>();
		baseResData.setCode("0");
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		meetingInfo.setCreateUser(userId);
		Integer meeId = meetingInfoFacade.insertMeetingInfo(meetingInfo);

		if (meeId > 0) {
			/* 创建主会议的默认角色 */
			meetingInfo.setMeeId(meeId);
			DubboxResult insertDefaultRole = roleModuleWriteFacade.insertDefaultRole(meetingInfo.getMeeId(), meetingInfo.getHallId());
			// 会议创建成功后，插入默认报名字段
			signupFieldFacade.insertInit(meetingInfo.getMeeId());
			baseResData.setCode("1");
			baseResData.setResdata(meetingInfo);
		} else {
			baseResData.setMsg("创建会议失败");
		}
		return baseResData;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> updateMeetingInfo(@RequestBody MeetingInfo meetingInfo, HttpServletRequest request) {
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		meetingInfo.setUpdateUser(userId);
		return meetingInfoFacade.updateMeetingInfo(meetingInfo);
	}

	@RequestMapping("/deleteSub")
	@ResponseBody
	public Map<String, Object> deleteSubMeetingInfoById(@RequestBody SubMeetingInfo meetingInfo) {
		// return meetingInfoFacade.deleteSubMeetingInfoById(meetingInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		if (meetingInfoFacade.deleteSubMeetingInfoByIdReal(meetingInfo) == 1) {
			map.put("code", "6350");
			map.put("msg", "删除子会议成功。");
		} else {
			map.put("code", "6351");
			map.put("msg", "删除子会议失败。");
		}
		return map;
	}

	@RequestMapping("/selectMeeting")
	@ResponseBody
	public Map<String, Object> selectMeetingInfo(@RequestBody MeetingInfo meetingInfo) {
		return meetingInfoFacade.selectMeetingInfo(meetingInfo);
	}

	@RequestMapping("/deleteSubMeetingByIds")
	@ResponseBody
	public Map<String, Object> deleteSubMeetingInfoByIds(@RequestParam List<String> ids) {
		return meetingInfoFacade.deleteSubMeetingInfoByIds(ids);
	}

	@RequestMapping("/selectMeetingByName")
	@ResponseBody
	public Map<String, Object> selectMeetingInfoByName(@RequestBody MeetingInfo meetingInfo) {
		return meetingInfoFacade.selectMeetingInfoByName(meetingInfo);
	}

	/**
	 * 增加一条
	 * 
	 * @param meetingInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/addSubMeeting")
	@ResponseBody
	public Map<String, Object> insertSubMeetingInfo(@RequestBody SubMeetingInfo meetingInfo, HttpServletRequest request) {
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		meetingInfo.setCreateUser(userId);
		return meetingInfoFacade.insertSubMeetingInfo(meetingInfo);
	}

	@RequestMapping("/deleteMeetingByIds")
	@ResponseBody
	public Map<String, Object> deleteMeetingInfoByIds(@RequestParam List<String> ids) {
		return meetingInfoFacade.deleteMeetingInfoByIds(ids);
	}

	@RequestMapping("/selectSubMeeByParentId")
	@ResponseBody
	public Map<String, Object> selectSubMeetingInfo(@RequestBody SubMeetingInfo meetingInfo) {
		return meetingInfoFacade.selectSubMeetingInfo(meetingInfo);
	}

	/**
	 * 加载热门会议
	 * 
	 * @param meetingInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectPopMeetingInfo")
	@ResponseBody
	public Map selectPopMeetingInfo(@RequestBody MeetingInfoDto meetingInfo, HttpServletRequest request) {
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		meetingInfo.setUserId(userId);
		Map<String, Object> selectPopMeetingInfo = meetingInfoFacade.selectPopMeetingInfo(meetingInfo);
		PageInfo<MeetingInfoDto> PagePopMeetingInfo = (PageInfo<MeetingInfoDto>) selectPopMeetingInfo.get("page");
		List<MeetingInfoDto> list = (List<MeetingInfoDto>) PagePopMeetingInfo.getList();
		String contextPath = request.getContextPath();
		for (MeetingInfoDto meetingInfo2 : list) {
			if (null == meetingInfo2.getMeeCover() || meetingInfo2.getMeeCover().equals("")) {
				meetingInfo2.setMeeCover(contextPath + "/css/img/" + imageMeetingHead);
			} else {
				meetingInfo2.setMeeCover(fastdfsurl + meetingInfo2.getMeeCover());
			}
		}

		return selectPopMeetingInfo;
	}

	// 热门会议关注
	@RequestMapping("/meetingbetton")
	@ResponseBody
	public String meetingbetton(@RequestBody MeetingInfoDto meetingInfo, HttpServletRequest request) {
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		Integer meeId = meetingInfo.getMeeId();
		Integer followMeeting = meetingLinkUserFacade.followMeeting(meeId, userId);
		return followMeeting.toString();
	}

	// 热门会议取消关注
	@RequestMapping("/cancelMeetingbetton")
	@ResponseBody
	public String cancelmeetingbetton(@RequestBody MeetingInfoDto meetingInfo, HttpServletRequest request) {
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		Integer meeId = meetingInfo.getMeeId();
		Integer followMeeting = meetingLinkUserFacade.unfollowMeeting(meeId, userId);
		return followMeeting.toString();
	}

	// 热门会议跳转会议主页
	@RequestMapping(value = "/{meeId}/{hallId}")
	public String meetingMode(@PathVariable("meeId") Integer meeId, @PathVariable("hallId") Integer hallId, ModelMap mode, HttpServletRequest request) {
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		// 新增功能会议厅的超级管理员可以管理会议
		// TODO
		HallRole hallrole = roleModuleReadFacade.findHallRoleByUserIdHallId(userId, hallId);
		// 查询出会议的角色 findRoleByUserIdHallId
		Role role = roleModuleReadFacade.findRoleByUserIdHallId(userId, meeId);
		// 查询出会议的创建人
		MeetingInfo meetingInfoId = new MeetingInfo();
		meetingInfoId.setMeeId(meeId);
		MeetingInfo selectMeetingInfoById = meetingInfoFacade.selectMeetingInfoById(meetingInfoId);
		String createUseruserId = selectMeetingInfoById.getCreateUser();
		// 查询出主会议的模块
		List<Module> meetingmodules = roleModuleReadFacade.selectHallModules(meeId);

		// 刷新会议的session
		String meeCover = selectMeetingInfoById.getMeeCover();
		if (!(null == meeCover || "".equals(meeCover))) {
			selectMeetingInfoById.setMeeCover(fastdfsurl + meeCover);
		} else {
			selectMeetingInfoById.setMeeCover("/css/img/" + imageMeetingHead);
		}
		request.getSession().setAttribute("click_meeting", selectMeetingInfoById);
		// 刷新会议厅的session
		MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallId);

		if (!(null == meetingHall.getHallCover() || "".equals(meetingHall.getHallCover()))) {
			meetingHall.setHallCover(fastdfsurl + meetingHall.getHallCover());
		} else {
			meetingHall.setHallCover("/css/img/" + imageHallHead);
		}
		request.getSession().setAttribute("existMeeHall", meetingHall);

		if (userId.equals(createUseruserId) || userId.equals(meetingHall.getUserId())) { // 判断会议是不是自己本人或者会议厅是不是本人
			RoleModuleUtils.repeatMeeting(meetingmodules, 1);
			mode.addAttribute("role", "SuperManager");
			mode.addAttribute("modules", meetingmodules);
			return "meeting/meet-func-list";
		}
		if (hallrole != null) { // 判断是不是会议厅超级管理员
			if (Objects.equals(hallrole.getRoleType(), "manager") && Objects.equals(hallrole.getRoleStatus(), "default")) {
				RoleModuleUtils.repeatMeeting(meetingmodules, 1);
				mode.addAttribute("role", "SuperManager");
				mode.addAttribute("modules", meetingmodules);
				return "meeting/meet-func-list";
			}
		}
		// 做权限判断
		if (null == role) {
			// 判断有没有报过名
			SignUpInfo signUpInfo = new SignUpInfo();
			signUpInfo.setUserId(userId);
			signUpInfo.setMeetingId(meeId);
			SignUpInfo mySignUpInfo = signUpInfoFacade.selectSignUpInfoByUserIdMeeId(signUpInfo);
			if (null != mySignUpInfo) {// 已报名人员
				Role roleSign = roleModuleReadFacade.getDefaultSignByMeetId(meeId);
				List<Module> modules2 = roleSign.getModules();
				List<Module> modulesjoiner = new ArrayList<Module>();// 取两个模块的交集
				RoleModuleUtils.repeatModule(meetingmodules, modules2, modulesjoiner);
				mode.addAttribute("role", "joiner");
				mode.addAttribute("modules", modulesjoiner);
			} else {// 未报名人员

				Role roleNoSign = roleModuleReadFacade.getDefaultNoSignByMeetId(meeId);
				List<Module> modules3 = roleNoSign.getModules();
				List<Module> modulesjoiner = new ArrayList<Module>();// 取两个模块的交集
				RoleModuleUtils.repeatModule(meetingmodules, modules3, modulesjoiner);
				mode.addAttribute("role", "joiner");
				mode.addAttribute("modules", modulesjoiner);
			}
		} else {
			if ((Objects.equals(role.getRoleType(), "manager") && Objects.equals(role.getRoleStatus(), "default"))) {// 超级管理员
				RoleModuleUtils.repeatMeeting(meetingmodules, 1);// 更改module名
				mode.addAttribute("role", "SuperManager");
				mode.addAttribute("modules", meetingmodules);
			} else {
				if ("manager".equals(role.getRoleType())) {
					List<Module> modules = role.getModules();// 查询出角色的模块
					RoleModuleUtils.repeatMeeting(meetingmodules, 0);
					RoleModuleUtils.modules(meetingmodules, modules);
					mode.addAttribute("role", "manager");
					mode.addAttribute("modules", meetingmodules);
				} else {// 普通的与会人员
					List<Module> modulesjoiner = new ArrayList<Module>();// 取两个模块的交集
					List<Module> modules = role.getModules();// 查询出角色的模块
					RoleModuleUtils.repeatModule(meetingmodules, modules, modulesjoiner);
					mode.addAttribute("role", "joiner");
					mode.addAttribute("modules", modulesjoiner);
				}
			}
		}
		return "meeting/meet-func-list";
	}

	// 跳转会议模块编辑
	@RequestMapping("/editMeetingModule/{meeId}/{hallId}")
	public String editMeetingModule(@PathVariable("meeId") String meeId, @PathVariable("hallId") Integer hallId, ModelMap mode, HttpServletRequest request) {
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		Role role = roleModuleReadFacade.findRoleByUserIdHallId(userId, hallId);
		MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallId);
		// 查询所有的模块'
		List<Module> Modules = roleModuleReadFacade.getAllModules();
		// 用于移除的模块
		List<Module> ModulesNo = new ArrayList<Module>();
		// 查询会议下的所有模块
		List<Module> meetingmodules = roleModuleReadFacade.selectHallModules(Integer.valueOf(meeId));
		// 查出没有的模块
		if (meetingmodules != null) {
			for (Module module : meetingmodules) {
				for (Module Amodule : Modules) {
					if ((module.getModuleId()) == (Amodule.getModuleId())) {
						ModulesNo.add(Amodule);
					}
				}
			}
		}
		Modules.removeAll(ModulesNo);
		RoleModuleUtils.repeatMeeting(meetingmodules, 1);// 跟换Modul的名称
		RoleModuleUtils.repeatMeeting(Modules, 1);
		mode.addAttribute("Modules", Modules);
		mode.addAttribute("meetingmodules", meetingmodules);
		mode.addAttribute("meeId", meeId);
		mode.addAttribute("hallId", hallId);
		return "/meeting/meet-func-list-edit";
	}

	// 更新主会议的模块
	@RequestMapping("/updateMeetingModule")
	@ResponseBody
	public String editMeetingModule(@RequestBody UpdateMeetingModulePojo updateMeetingModulePojo) {
		DubboxResult dubboxResult = null;
		List<String> module_ids = updateMeetingModulePojo.getModule_id();
		List<Map<String, Integer>> hallModule = new ArrayList<Map<String, Integer>>();
		List<Integer> moduleIds = new ArrayList<Integer>(); // 用户新选择的模块列表
		for (String module_id : module_ids) {
			Map map = new HashMap<String, Integer>();
			map.put("meet_hall_id", updateMeetingModulePojo.getMeet_hall_id());
			map.put("meet_id", updateMeetingModulePojo.getMeet_id());
			map.put("module_id", Integer.valueOf(module_id));
			hallModule.add(map);
			moduleIds.add(Integer.valueOf(module_id));
		}

		List<Module> selectHallModules = roleModuleReadFacade.selectHallModules(updateMeetingModulePojo.getMeet_id());
		HallModule hallmodule = new HallModule(updateMeetingModulePojo.getMeet_hall_id(), updateMeetingModulePojo.getMeet_id(), RoleModuleUtils.listToString(module_ids));

		// 判断为不为空
		if (selectHallModules.size() == 0) { // 为空执行插入语句
			dubboxResult = roleModuleWriteFacade.batchsaveHallModules(hallmodule);
		} else { // 不为空执行插入语句
			dubboxResult = roleModuleWriteFacade.updateHallModules(hallmodule);
		}

		if ("ROLEANDMQ_OK".equals(dubboxResult.getStatus())) {
			// 模块更新成功后判断，会议下报名字段是否需要变化
			// 查询会议已经选择了哪些字段
			SignupFieldMeetingLink hasChoosedFieldLink = signupFieldFacade.selectSignupFieldMeetingLinkByMeeId(updateMeetingModulePojo.getMeet_id());
			String fieldIds = hasChoosedFieldLink.getFieldIds();
			String[] fieldIds1 = StringUtils.split(fieldIds, ",");
			List<Integer> hasChoosedFieldIds = new ArrayList<Integer>();
			for (String string : fieldIds1) {
				hasChoosedFieldIds.add(Integer.valueOf(string));
			}
			int size = hasChoosedFieldIds.size();
			List<SignupFieldModuleLink> selectAllLinks = signupFieldFacade.selectAllLinks();
			for (SignupFieldModuleLink s : selectAllLinks) {
				Integer m = s.getModuleId();
				Integer f = s.getFieldId();
				if (!moduleIds.contains(m) && hasChoosedFieldIds.contains(f)) {
					hasChoosedFieldIds.remove(f);
				}
			}
			if (hasChoosedFieldIds.size() != size) {
				// 有变化，更新会议下的报名字段
				String newFieldIds = StringUtils.join(hasChoosedFieldIds, ",");
				SignupFieldMeetingLink link = new SignupFieldMeetingLink();
				link.setMeeId(updateMeetingModulePojo.getMeet_id());
				link.setFieldIds(newFieldIds);
				// 更新已选报名编辑字段
				DubboxResult updateField = signupFieldFacade.updateField(link);
			}
		}
		return dubboxResult.getStatus();
	}

}
