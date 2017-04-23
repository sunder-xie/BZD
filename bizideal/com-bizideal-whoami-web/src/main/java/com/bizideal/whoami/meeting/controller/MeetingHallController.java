package com.bizideal.whoami.meeting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.facade.meeting.dto.MeetingHallDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.service.HallLinkUserFacade;
import com.bizideal.whoami.facade.meeting.service.MeetingHallFacade;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.entity.HallFunction;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.UserHallRoleLink;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.rolemodule.facade.RoleModuleWriteFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.HttpClientUtils;
import com.bizideal.whoami.utils.RoleModuleUtils;
import com.bizideal.whoami.wechat.config.WebAppConfig;
import com.bizideal.whoami.wechat.config.WechatWebConfig;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingHallController
 * @Description TODO(会议厅)
 * @Author Zj.Qu
 * @Date 2016-12-11 15:58:04
 */
@Controller
@RequestMapping("/meetingHall")
public class MeetingHallController {

	@Autowired
	private MeetingHallFacade meetingHallFacade;
	@Autowired
	private HallLinkUserFacade hallLinkUserFacade;

	@Autowired
	private RoleModuleReadFacade roleModuleReadFacade;

	@Autowired
	private RoleModuleWriteFacade roleModuleWriteFacade;

	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	@Autowired
	WechatWebConfig wechatConfig;
	@Autowired
	WebAppConfig webAppConfig;

	@Value("${fastdfsurl}")
	private String fastdfsurl;// 文件服務器地址
	@Value("${imageHallHead}")
	private String imageHallHead;// 会议厅头像
	@Value("${imageHead}")
	private String imageHead;// 我的头像图片

	// 通过userId跳转会议厅
	@RequestMapping("/room")
	public String userIsMaatingHall(ModelMap mode, HttpServletRequest request) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		if (null != meetingHall) {
			HallRole hallRole = roleModuleReadFacade.findHallRoleByUserIdHallId(userId, meetingHall.getHallId());
			List<HallFunction> allHallFunction = roleModuleReadFacade.getAllHallFunction();
			Boolean opinionRole = RoleModuleUtils.opinionRole(hallRole, allHallFunction, request, mode, userId, meetingHall);
			if (opinionRole) {
				return "meeting/meet-room";
			}
		}
		// 查询最后用户操作的会议厅
		meetingHall = meetingHallFacade.queryByUserIdMeetingHall(userId);
		if (null != meetingHall) {
			if (!(null == meetingHall.getHallCover() || "".equals(meetingHall.getHallCover()))) {
				meetingHall.setHallCover(fastdfsurl + meetingHall.getHallCover());
			} else {
				meetingHall.setHallCover("/css/img/" + imageHallHead);
			}
			mode.addAttribute("permissions", "permissions");
			request.getSession().setAttribute("existMeeHall", meetingHall);
			return "meeting/meet-room";
		}
		return "meeting/no-meetRoom";
	}

	/**
	 * 通过会议厅ID切换会议厅
	 * 
	 * @param hallid
	 * @return
	 */
	@RequestMapping("/tomeetinghallIndex/{hallid}")
	public String tomeetinghallIndex(@PathVariable("hallid") Integer hallid, HttpServletRequest request, ModelMap mode) {
		MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallid);
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		HallRole hallRole = roleModuleReadFacade.findHallRoleByUserIdHallId(userId, meetingHall.getHallId());
		List<HallFunction> allHallFunction = roleModuleReadFacade.getAllHallFunction();

		if (null != meetingHall) {
			MeetingHall hall = new MeetingHall();
			hall.setHallId(meetingHall.getHallId());
			hall.setHallLeaveTime(System.currentTimeMillis());
			meetingHallFacade.updateMeetingHall(hall);

			if (!(null == meetingHall.getHallCover() || "".equals(meetingHall.getHallCover()))) {
				meetingHall.setHallCover(fastdfsurl + meetingHall.getHallCover());
			} else {
				meetingHall.setHallCover("/css/img/" + imageHallHead);
			}
			Boolean opinionRole = RoleModuleUtils.opinionRole(hallRole, allHallFunction, request, mode, userId, meetingHall);
			if (opinionRole) {
				// 切换会议厅刷新session
				request.getSession().setAttribute("existMeeHall", meetingHall);
				return "meeting/meet-room";
			}
		}
		return "meeting/no-meetRoom";
	}

	/**
	 * 创建会议厅跳转
	 * 
	 * @param hallid
	 * @return
	 */
	@RequestMapping("/{hallId}")
	public String queryByIdMeeHall(@PathVariable Integer hallId, ModelMap mode, HttpServletRequest request) {

		MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallId);
		if (null != meetingHall) {
			if (!(null == meetingHall.getHallCover() || "".equals(meetingHall.getHallCover()))) {
				meetingHall.setHallCover(fastdfsurl + meetingHall.getHallCover());
			} else {
				meetingHall.setHallCover("/css/img/" + imageHallHead);
			}
			mode.addAttribute("permissions", "permissions");// 创建会议厅之后肯定是自己的会议厅
			request.getSession().setAttribute("existMeeHall", meetingHall);
			return "meeting/meet-room";
		} else {
			return "meeting/no-meetRoom";
		}
	}

	@RequestMapping("/addMeetingHall")
	public ModelAndView addMeetingHall(MeetingHall meetingHall, ModelAndView modelAndView, HttpServletRequest request) throws Exception {
		// 需要验证码激活后才能用
		meetingHall.setDelFlag("1");
		Integer returnValue = meetingHallFacade.addMeetingHall(meetingHall);//
		if (returnValue > 0) {
			/* 创建会议厅之后创建默认角色 */
			roleModuleWriteFacade.insertDefaultHallRole(returnValue);
			modelAndView.addObject("createMeetHallId", returnValue);
			modelAndView.setViewName("meeting/person-creatMeetRoom-tep2");
			return modelAndView;
		} else {
			modelAndView.setViewName("meeting/person-creatMeetRoom-tep1");
			return modelAndView;
		}
	}

	// 用于判断会议厅名是否重复
	@RequestMapping("/existMeetHallName")
	@ResponseBody
	public Boolean existName(String hallName) {
		List<MeetingHall> queryByHallName = meetingHallFacade.queryByHallName(hallName);
		return !(null == queryByHallName || queryByHallName.size() == 0);
	}

	@RequestMapping("/createHallStep2")
	public ModelAndView updateCreateHallStep2(UserWeixinInfo userWeixinInfo, Integer createMeetHallId, ModelAndView modelAndView, HttpServletRequest request) {
		JSONObject json = userWeixinInfoFacade.updateCreateHallStep2(userWeixinInfo);
		if ("0".equals(json.get("errcode").toString())) {
			JSONObject user = json.getJSONObject("user");
			UserWeixinInfo weixinInfo = (UserWeixinInfo) JSONObject.toBean(user, UserWeixinInfo.class);
			String headimgurl = weixinInfo.getHeadimgurl();
			if (!("".equals(headimgurl) || null == headimgurl)) {
				if (!(headimgurl.startsWith("http"))) { // 判断用户的头像是否是默认头像
					weixinInfo.setHeadimgurl(fastdfsurl + headimgurl);
				}
			} else {
				weixinInfo.setHeadimgurl("/css/img/" + imageHead);
			}
			request.getSession().setAttribute("user", weixinInfo);

			MeetingHall meetingHall = new MeetingHall();
			meetingHall.setHallId(createMeetHallId);
			meetingHall.setDelFlag("0");
			Integer updateMeetingHall = meetingHallFacade.updateMeetingHall(meetingHall);
			modelAndView.addObject("createMeetHallId", createMeetHallId);
			modelAndView.setViewName("meeting/creatmeetroom-success");
		} else {
			modelAndView.setViewName("meeting/person-creatMeetRoom-tep2");
		}
		return modelAndView;
	}

	// 加载热门会议厅
	@RequestMapping("/selectMeetingHall")
	@ResponseBody
	public PageInfo<MeetingHallDto> selectPopMeetingInfo(@RequestBody MeetingHall meetingHall, ModelMap mode, HttpServletRequest request) {
		Integer pageNum = meetingHall.getPageNum();
		Integer pageSize = meetingHall.getPageSize();
		String contextPath = request.getContextPath();
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		PageInfo<MeetingHallDto> pageinfo = meetingHallFacade.listUserFollowHotHall(pageNum, pageSize, userId);
		List<MeetingHallDto> list = pageinfo.getList();
		for (MeetingHallDto meetinHallss : list) {
			if (null == meetinHallss.getHallCover() || "".equals(meetinHallss.getHallCover())) {
				meetinHallss.setHallCover(contextPath + "/css/img/" + imageHallHead);
			} else {
				String hallheadimage = meetinHallss.getHallCover();
				meetinHallss.setHallCover(fastdfsurl + hallheadimage);
			}
		}
		return pageinfo;
	}

	/**
	 * 跳转会议厅管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/tomanagepage")
	public ModelAndView tomanagePage(ModelAndView modelAndView, HttpServletRequest request) {
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");

		String httpaddress = HttpClientUtils.getHttpAddress(request);
		String meethallurl = "/meeting/selectMeetingRoom/" + meetingHall.getHallId();
		String meetHallUrl = httpaddress + meethallurl;
		modelAndView.addObject("meetHallUrl", meetHallUrl);
		modelAndView.setViewName("admin-meetroom/admin-mtrm-manage");
		return modelAndView;
	}

	/**
	 * 查询用户角色列表(管理员/参会人员)
	 * 
	 * @param request
	 * @param mode
	 * @return
	 */
	@RequestMapping("/selectcreatRole")
	public String selectcreatRole(HttpServletRequest request, ModelMap mode) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		Integer meetHallId = meetingHall.getHallId();
		// 管理员角色列表
		List<HallRole> managerlist = roleModuleReadFacade.getHallRoleByCondition("manager", meetHallId);
		mode.addAttribute("managerlist", managerlist);
		return "admin-meetroom/admin-mtrm-creatRole";
	}

	/**
	 * 删除角色
	 *
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/deletecreatRole/{roleid}")
	@ResponseBody
	public Boolean deletecreatRole(@PathVariable("roleid") Integer roleid) {
		HallRole hallrole = new HallRole();
		hallrole.setRoleId(roleid);
		try {
			DubboxResult deleteRoleModule = roleModuleWriteFacade.deleteHallRoleFunction(hallrole);
			return "0".equals(deleteRoleModule.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 跳转查询会议厅 管理员权限修改页面
	 *
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/upodateCreateMeetingRoleSelect/{roleId}")
	public String upodateCreateMeetingRoleSelect(@PathVariable("roleId") Integer roleId, ModelMap mode) {
		// 获取会议厅角色
		HallRole hallRoleByRoleid = roleModuleReadFacade.getHallRoleByRoleid(roleId);
		List<HallFunction> functions = hallRoleByRoleid.getFunctions();
		// 获取会议厅的所有功能
		List<HallFunction> allHallFunction = roleModuleReadFacade.getAllHallFunction();
		// 获取该角色没有的模块
		List<HallFunction> hallFunctionsNo = new ArrayList<HallFunction>();
		for (HallFunction allhallFunction : allHallFunction) {
			for (HallFunction hallFunction : functions) {
				if (allhallFunction.getFunctionId() == hallFunction.getFunctionId()) {
					hallFunctionsNo.add(allhallFunction);
				}
			}
		}
		// 移除模块
		allHallFunction.removeAll(hallFunctionsNo);
		mode.addAttribute("allHallFunction", allHallFunction);
		mode.addAttribute("hallFunctionsNo", hallFunctionsNo);
		return "admin-meetroom/update-Role";
	}

	/**
	 * 修改会议厅管理员权限
	 *
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/upodateCreateMeetingRole")
	@ResponseBody
	public Boolean upodateCreateMeetingRole(@RequestBody HallRole hallRole) {
		// 根据通用mapper方法做的查询
		HallRole role = roleModuleReadFacade.getHallRoleByRoleid(hallRole.getRoleId());
		role.setFunctionIds(hallRole.getFunctionIds());
		try {
			DubboxResult dubboxResult = roleModuleWriteFacade.updateHallRoleFunction(role);
			return "0".equals(dubboxResult.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 查询管理员权限模块
	 * 
	 * @param mode
	 * @return
	 */
	@RequestMapping("/creatRole")
	public String creatmanageRole(ModelMap mode) {
		List<HallFunction> allHallFunction = roleModuleReadFacade.getAllHallFunction();
		mode.addAttribute("allHallFunction", allHallFunction);
		return "admin-meetroom/creat-Role";
	}

	/**
	 * 新增管理员模块权限
	 * 
	 * @param role
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addmanageRole")
	@ResponseBody
	public Boolean addmanageRole(@RequestBody HallRole hallrole, HttpServletRequest request) throws Exception {
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		hallrole.setMeetHallId(meetingHall.getHallId());
		hallrole.setRoleType("manager");
		DubboxResult result = roleModuleWriteFacade.insertHallRoleFunctions(hallrole);
		return "0".equals(result.getStatus());
	}

	/**
	 * 判断会议厅名是否重复
	 * 
	 * @param role
	 * @param mode
	 * @return
	 */
	@RequestMapping("/confirmanageRole")
	@ResponseBody
	public Boolean confirmanageRole(String roleName, HttpServletRequest request) {
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		boolean existHallRoleName = roleModuleReadFacade.existHallRoleName(roleName, meetingHall.getHallId());
		return existHallRoleName;
	}

	/**
	 * 查询普通用户权限模块
	 * 
	 * @param mode
	 * @return
	 */
	// @RequestMapping("/creatjoinerRole")
	// public String creatjoinerRole(ModelMap mode) {
	//
	// List<Module> allModules = roleModuleReadFacade.getAllModules();
	// // 跟换Modul的名称
	// for (Module modulesuper : allModules) {
	// String moduleName = modulesuper.getModuleName();
	// String[] splitName = moduleName.split(",");
	// modulesuper.setModuleName(splitName[0]);
	// }
	// mode.addAttribute("allModules", allModules);
	// return "admin-meetroom/creat-joiner-Role";
	// }

	/**
	 * 新增普通用户模块权限
	 * 
	 * @param role
	 * @param mode
	 * @return
	 */
	// @RequestMapping("/addjoinerRole")
	// public String addjoinerRole(Role role, String ids) {
	// String[] split = StringUtils.split(ids, ",");
	// List<Integer> list = new ArrayList<Integer>();
	// for (int i = 0; i < split.length; i++) {
	// Integer in = Integer.valueOf(split[i]);
	// list.add(i, in);
	// }
	// role.setRoleType("joiner");
	// // role.setModuleIds(list);
	// DubboxResult insertRoleModule = roleModuleWriteFacade.insertRoleModule(role);
	// if (insertRoleModule.getStatus().equals("0")) {
	// return "redirect:/meetingHall/selectcreatRole";
	// } else {
	// return "redirect:/meetingHall/creatjoinerRole";
	// }
	// }

	/**
	 * 设置会议厅管理员
	 * 
	 * @param mode
	 * @return
	 */
	@RequestMapping("/toadminStrator")
	public String toadminStrator(HttpServletRequest request, ModelMap mode) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		String context = request.getContextPath();
		Integer meetHallId = meetingHall.getHallId();
		// 查询会议厅管理员集合
		List<String> managerUserIdByhallid = roleModuleReadFacade.getHallManagerUserIdByhallid(meetHallId);
		List<UserWeixinInfo> list = new ArrayList<UserWeixinInfo>();
		if (managerUserIdByhallid.size() != 0) {// 设置会议厅管理员的头像
			for (int i = 0; i < managerUserIdByhallid.size(); i++) {
				UserWeixinInfo userWeixinInfo = userWeixinInfoFacade.selectByUserId(managerUserIdByhallid.get(i));
				if (userWeixinInfo.getHeadimgurl().startsWith("/css/img/")) {
					userWeixinInfo.setHeadimgurl(context + "/css/img/" + imageHead);
					list.add(userWeixinInfo);
				} else {
					list.add(userWeixinInfo);
				}
			}
			mode.addAttribute("list", list);
		}
		// 查询出会议厅的角色类型
		List<HallRole> hallManagerRole = roleModuleReadFacade.getHallRoleByCondition("manager", meetHallId);
		// 获取会议厅的用户Id
		String userId = meetingHall.getUserId();
		UserWeixinInfo hallcreateUser = userWeixinInfoFacade.selectByUserId(userId);// 查出会议厅创建人
		mode.addAttribute("hallManagerRole", hallManagerRole);
		mode.addAttribute("hallId", meetHallId);
		mode.addAttribute("hallcreateUser", hallcreateUser);// 把创建用户放入mode
		return "admin-meetroom/admin-mtrm-administrator";
	}

	/**
	 * 删除管理员
	 * 
	 * @param userid
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteadminStrator/{userid}")
	@ResponseBody
	public Boolean deleteadminStrator(@PathVariable("userid") String userid, HttpServletRequest request) {

		UserHallRoleLink userRoleLink = new UserHallRoleLink();
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		Integer meetHallId = meetingHall.getHallId();
		userRoleLink.setMeetHallId(meetHallId);
		userRoleLink.setUserId(userid);
		try {
			DubboxResult deleteUserHallRole = roleModuleWriteFacade.deleteUserHallRole(userRoleLink);
			return "0".equals(deleteUserHallRole.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 切换会议厅(查询我创建的会议厅)
	 * 
	 * @param request
	 * @param mode
	 * @return
	 */
	@RequestMapping("/cutmeetingHall")
	public String cutmeetingHall(HttpServletRequest request, ModelMap mode) {

		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		String userId = user.getUserId();
		// 我创建的会议厅现在分页查询
		PageInfo<MeetingHall> meetingHallList = meetingHallFacade.queryPageListByUserId(userId, 1, 10);
		for (int i = 0; i < meetingHallList.getSize(); i++) {
			String hallCover = meetingHallList.getList().get(i).getHallCover();

			if (!(null == hallCover || "".equals(hallCover))) {
				meetingHallList.getList().get(i).setHallCover(fastdfsurl + hallCover);
			} else {
				meetingHallList.getList().get(i).setHallCover("/css/img/" + imageHallHead);
			}
		}
		mode.addAttribute("meetingHallList", meetingHallList);
		return "meeting/cut-meetroom";
	}

	/**
	 * 我管理的会议厅
	 * 
	 * @return
	 */
	@RequestMapping("/managemeetingHall")
	@ResponseBody
	public Map<String, Object> managemeetingHall(HttpServletRequest request) {

		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		String userId = user.getUserId();
		// 根据用户Id查询管理员的集合
		List<Integer> hallIdByUserId = roleModuleReadFacade.getMeetHallIdByUserId(userId);
		List<MeetingHall> queryByUserIdsMeeHall = null;
		if (!hallIdByUserId.isEmpty()) {
			queryByUserIdsMeeHall = meetingHallFacade.queryByUserIdsMeeHall(hallIdByUserId);
			String contextPath = request.getContextPath();// 获取项目路径
			for (MeetingHall meetingHall : queryByUserIdsMeeHall) {

				if (!(null == meetingHall.getHallCover() || "".equals(meetingHall.getHallCover()))) {
					meetingHall.setHallCover(fastdfsurl + meetingHall.getHallCover());
				} else {
					meetingHall.setHallCover(contextPath + "/css/img/" + imageHallHead);
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", queryByUserIdsMeeHall);
		return map;
	}

	/**
	 * 跳转选择会议厅管理员界面
	 * 
	 * @param request
	 * @param mode
	 * @return
	 */
	@RequestMapping("/selectMeetingHallRole")
	public String selectMeetingHallRole(UserHallRoleLink userHallRoleLink, HttpServletRequest request, ModelMap mode) {
		// 管理员角色列表
		List<HallRole> managerlist = roleModuleReadFacade.getHallRoleByCondition("manager", userHallRoleLink.getMeetHallId());
		HallRole hallRole = roleModuleReadFacade.findHallRoleByUserIdHallId(userHallRoleLink.getUserId(), userHallRoleLink.getMeetHallId());

		mode.addAttribute("managerlist", managerlist);
		mode.addAttribute("userId", userHallRoleLink.getUserId());
		mode.addAttribute("meetHallId", userHallRoleLink.getMeetHallId());
		mode.addAttribute("hallRoleId", hallRole.getRoleId());
		return "admin-meetroom/admin-mtrm-selectRole";
	}

	/**
	 * 修改会议厅角色保存
	 * 
	 * @param request
	 * @param mode
	 * @return
	 */
	@RequestMapping("/saveMeetingHallRole")
	@ResponseBody
	public Boolean saveMeetingHallRole(@RequestBody UserHallRoleLink userHallRoleLink, HttpServletRequest request, ModelMap mode) {
		// 管理员角色列表
		// TODO
		try {
			DubboxResult updateUserHallRole = roleModuleWriteFacade.updateUserHallRole(userHallRoleLink);
			return "0".equals(updateUserHallRole.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 查询管理员角色
	 * 
	 * @param request
	 * @param mode
	 * @return
	 */
	// @RequestMapping("/selectmanageRole/{userid}")
	// public String selectmanageRole(HttpServletRequest request, @PathVariable("userid") String userid, ModelMap mode) {
	//
	// MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
	// Integer meetHallId = meetingHall.getHallId();
	// // 管理员角色列表
	// List<Role> managerlist = roleModuleReadFacade.getRoleByCondition("manager", meetHallId);
	// mode.addAttribute("managerlist", managerlist);
	// mode.addAttribute("userid", userid);
	// return "admin-meetroom/admin-mtrm-selectRole";
	// }

	/**
	 * 邀请管理员页面
	 * 
	 * @return
	 */
	@RequestMapping("/invite/{hallId}/{roleId}")
	public ModelAndView inviteManager(@PathVariable("hallId") Integer hallId, @PathVariable("roleId") Integer roleId, HttpServletRequest request) {
		String httpaddress = HttpClientUtils.getHttpAddress(request);

		try {
			roleModuleReadFacade.setHallInviteUrl(hallId, roleId, 3600);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String redirect = "/meetingHall/tobemanager/" + hallId + "/" + roleId;
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("redirect:" + redirect);
		}
		boolean isweixin = HttpClientUtils.isWeixin(request);
		if (isweixin) {

			String redirect_uri = httpaddress + "/weixinoauth/oauth";

			String weixinoauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wechatConfig.getAppId() + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_userinfo&state=" + redirect + "#wechat_redirect";
			return new ModelAndView("redirect:" + weixinoauthUrl);
		} else {
			// return "meet_hall/inviteManager";
			return new ModelAndView("redirect:" + redirect);

		}

	}

	/**
	 * 成为管理员
	 * 
	 * @param hallId
	 * @param roleId
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/tobemanager/{hallId}/{roleId}")
	public String tobemanager(@PathVariable("hallId") Integer hallId, @PathVariable("roleId") Integer roleId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> maps = new HashMap<String, String>();
		HallRole role = roleModuleReadFacade.getHallRoleByRoleid(roleId);
		modelMap.addAttribute("role", role);

		try {
			UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
			// 先判断会议厅是否是该用户的 或 该用户是否已经是该会议厅的管理员 是
			// 不执行成为管理员的功能 显示邀请页面
			MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallId);
			HallRole userrole = roleModuleReadFacade.findHallRoleByUserIdHallId(userWeixinInfo.getUserId(), hallId);
			if (Objects.equals(userWeixinInfo.getUserId(), meetingHall.getUserId())) {
				return "meet_hall/admin-invite";
			} else if (userrole != null) {
				if (Objects.equals("manager", userrole.getRoleType())) {
					return "redirect:/meeting/selectMeetingRoom/" + hallId;
				}
			}

			// timeout 代表连接已超时或已经有人点击过该链接
			// already 该用户已经是该会议厅的管理员了
			// ok 成为管理员成功
			// error 成为管理员失败

			String info = roleModuleReadFacade.inviteHallManager(hallId, roleId, userWeixinInfo.getUserId());
			if (Objects.equals("timeout", info)) {
				maps.put("status", "400");
				maps.put("msg", "邀请链接超时");
				modelMap.addAttribute("tobemanager", maps);
				return "meet_hall/inviteManager";
			} else if (Objects.equals("already", info)) {
				maps.put("status", "401");
				maps.put("msg", "该用户已经是该会议厅的管理员了");
				modelMap.addAttribute("tobemanager", maps);
				return "meet_hall/inviteManager";
			} else if (Objects.equals("ok", info)) {
				// 成为管理员成功 跳转会议厅主业
				maps.put("status", "200");
				maps.put("msg", "成为管理员成功");
				modelMap.addAttribute("tobemanager", maps);
				return "redirect:/meeting/selectMeetingRoom/" + hallId;
			} else {
				maps.put("status", "404");
				maps.put("msg", "成为管理员失败");
				modelMap.addAttribute("tobemanager", maps);
				return "meet_hall/inviteManager";
			}

		} catch (Exception e) {
			e.printStackTrace();
			maps.put("status", "500");
			maps.put("msg", "服务器异常");
			modelMap.addAttribute("tobemanager", maps);
			return "meet_hall/inviteManager";
		}

	}

	/**
	 * 会议厅邀请卡
	 * 
	 * @param hallId
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping("/inviteCard/{hallId}")
	public String inviteCard(@PathVariable("hallId") Integer hallId, ModelMap modelMap, HttpServletRequest request) {
		MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallId);
		UserWeixinInfo userWeixinInfo = userWeixinInfoFacade.selectByUserId(meetingHall.getUserId());
		String httpaddress = HttpClientUtils.getHttpAddress(request);

		String url = httpaddress + "/meeting/selectMeetingRoom/" + meetingHall.getHallId();
		modelMap.addAttribute("meetHall", meetingHall);
		modelMap.addAttribute("user", userWeixinInfo);
		modelMap.addAttribute("url", url);
		return "meet_hall/admin-mtrm-invite";

	}

	/**
	 * 会议厅二维码
	 * 
	 * @param hallId
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping("/qrCode")
	public String qrCode(ModelMap modelMap, HttpServletRequest request) {
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		String httpaddress = HttpClientUtils.getHttpAddress(request);

		String url = httpaddress + "/meeting/selectMeetingRoom/" + meetingHall.getHallId();
		modelMap.addAttribute("url", url);
		return "meet_hall/meetroom-code";

	}

	@RequestMapping("/meetingHallQueryName")
	@ResponseBody
	public Boolean meetingHallQueryName(String meetingpwd) {
		List<MeetingHall> queryByHallName = meetingHallFacade.queryByHallName(meetingpwd);
		return !(null == queryByHallName || queryByHallName.size() == 0);
	}

}
