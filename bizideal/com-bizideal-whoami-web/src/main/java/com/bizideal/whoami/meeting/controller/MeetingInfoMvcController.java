package com.bizideal.whoami.meeting.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.croe.fastdfs.FastDFSClient;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingHallFacade;
import com.bizideal.whoami.facade.meeting.service.MeetingInfoFacade;
import com.bizideal.whoami.facade.meeting.service.MeetingLinkUserFacade;
import com.bizideal.whoami.member.entity.MemberTypeInfo;
import com.bizideal.whoami.member.facade.MemberTypeInfoFacade;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.entity.HallFunction;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.Module;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.entity.UserRoleLink;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.rolemodule.facade.RoleModuleWriteFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.HttpClientUtils;
import com.bizideal.whoami.utils.RoleModuleUtils;
import com.bizideal.whoami.wechat.config.WechatWebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName MeetingInfoController
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月11日
 */
@Controller
@RequestMapping("/meeting")
@SuppressWarnings("all")
public class MeetingInfoMvcController {

	private Logger logger = LoggerFactory.getLogger(MeetingInfoMvcController.class);

	@Autowired
	private MeetingInfoFacade meetingInfoFacade;
	@Autowired
	private RoleModuleReadFacade roleModuleReadFacade;
	@Autowired
	private RoleModuleWriteFacade roleModuleWriteFacade;
	@Autowired
	private MeetingHallFacade meetingHallFacade;

	@Autowired
	private MeetingLinkUserFacade meetingLinkUserFacade;
	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	@Autowired
	MemberTypeInfoFacade memberTypeInfoFacade;
	@Value("${fastdfsurl}")
	private String fastdfsurl;
	@Value("${imageHallHead}")
	private String imageHallHead;// 会议厅头像
	@Value("${imageMeetingHead}")
	private String imageMeetingHead;// 会议头像

	@Autowired
	WechatWebConfig wechatConfig;

	/**
	 * 会议厅跳转主会议列表
	 * 
	 * @param meetingInfo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/selectMeeting_mvc")
	public String selectMeetingInfo(ModelMap mode, HttpServletRequest request) {
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		HallRole hallRole = roleModuleReadFacade.findHallRoleByUserIdHallId(userId, meetingHall.getHallId());
		List<HallFunction> allHallFunction = roleModuleReadFacade.getAllHallFunction();
		RoleModuleUtils.opinionRole(hallRole, allHallFunction, request, mode, userId, meetingHall);
		return "meeting/meetroom-index";
	}

	/**
	 * 会议厅下面主会议滑动加载
	 * 
	 * @param meetingInfo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/selectmeetingInfoSlide")
	@ResponseBody
	public PageInfo meetinginfoslide(@RequestBody MeetingInfo meetingInfo, HttpServletRequest request) {
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		meetingInfo.setHallId(meetingHall.getHallId());
		PageInfo<MeetingInfo> meetingInfoSlide = meetingInfoFacade.selectMeetingInfoByHallId(meetingInfo);
		List<MeetingInfo> meetingInfoDto = meetingInfoSlide.getList();
		String contextPath = request.getContextPath();
		for (MeetingInfo meetingInfo2 : meetingInfoDto) {
			if (null == meetingInfo2.getMeeCover() || meetingInfo2.getMeeCover().equals("")) {
				meetingInfo2.setMeeCover(contextPath + "/css/img/" + imageMeetingHead);
			} else {
				meetingInfo2.setMeeCover(fastdfsurl + meetingInfo2.getMeeCover());
			}
		}
		return meetingInfoSlide;
	}

	/**
	 * 会议主界面热门会议厅跳转热门会议厅
	 * 
	 * @param hallId
	 * @param mode
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectMeetingRoom/{hallId}")
	public String selectMeetingRoom(@PathVariable Integer hallId, ModelMap mode, HttpServletRequest request) {
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallId);
		// 获取会议厅的角色,会议厅模块
		HallRole hallRole = roleModuleReadFacade.findHallRoleByUserIdHallId(userId, meetingHall.getHallId());
		List<HallFunction> allHallFunction = roleModuleReadFacade.getAllHallFunction();

		if (!(null == meetingHall.getHallCover() || "".equals(meetingHall.getHallCover()))) {
			meetingHall.setHallCover(fastdfsurl + meetingHall.getHallCover());
		} else {
			meetingHall.setHallCover("/css/img/" + imageHallHead);
		}
		// 刷新会议厅的session
		request.getSession().setAttribute("existMeeHall", meetingHall);

		RoleModuleUtils.opinionRole(hallRole, allHallFunction, request, mode, userId, meetingHall);

		return "meeting/meetroom-index";
	}

	/**
	 * 主会议列表跳转子会议列表
	 * 
	 * @param meetingInfo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/selectSubMeeByParentId_mvc")
	public String selectSubMeetingInfo(int meeId, Model model, HttpServletRequest request) {

		// 做权限判断
		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setMeeId(meeId);
		meetingInfo = meetingInfoFacade.selectMeetingInfoById(meetingInfo);
		if (null == meetingInfo) {
			// 会议为空，跳转错误页面。。。。。

		} else {

			MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(meetingInfo.getHallId());

			Role role = roleModuleReadFacade.findRoleByUserIdHallId(user.getUserId(), meeId);

			HallRole hallrole = roleModuleReadFacade.findHallRoleByUserIdHallId(user.getUserId(), meetingHall.getHallId());

			RoleModuleUtils.meetingToPermission(hallrole, role, model, user, meetingHall, meetingInfo);
			// 查主会议下的子会议list
			SubMeetingInfo subMeetingInfo = new SubMeetingInfo();
			subMeetingInfo.setMeeParentId(meeId);
			Map<String, Object> subMeetingMap = meetingInfoFacade.selectSubMeetingInfo(subMeetingInfo);

			// 查询这个会议的总粉丝数
			Integer countMeetingFollow = meetingLinkUserFacade.countMeetingFollowByMeeId(meeId);
			// modelAndView.addObject("countMeetingFollow", countMeetingFollow);
			model.addAttribute("countMeetingFollow", countMeetingFollow);

			// modelAndView.setViewName("meeting/meetroom-meetinfo");
			model.addAttribute("subMeetingList", subMeetingMap.get("list"));
			// modelAndView.addObject("subMeetingList", subMeetingMap.get("list"));

			// 用来刷新会议头像
			String meeCover = meetingInfo.getMeeCover();
			if (!(null == meeCover || "".equals(meeCover))) {
				meetingInfo.setMeeCover(fastdfsurl + meeCover);
			} else {
				meetingInfo.setMeeCover("/css/img/" + imageMeetingHead);
			}
			request.getSession().setAttribute("click_meeting", meetingInfo);

		}
		return "meeting/meetroom-meetinfo";
	}

	/**
	 * 我管理的会议根据主会议ID查询会议所有信息
	 * 
	 * @param meetingInfo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/selectMeeByParentId_mvc")
	public String selectMeetingInfo(int meeId, Model model, HttpServletRequest request) {

		// 做权限判断
		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setMeeId(meeId);
		meetingInfo = meetingInfoFacade.selectMeetingInfoById(meetingInfo);
		if (null == meetingInfo) {

			// 会议为空，跳转错误页面。。。。。

		} else {

			MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(meetingInfo.getHallId());

			Role role = roleModuleReadFacade.findRoleByUserIdHallId(user.getUserId(), meeId);

			HallRole hallrole = roleModuleReadFacade.findHallRoleByUserIdHallId(user.getUserId(), meetingHall.getHallId());

			RoleModuleUtils.meetingToPermission(hallrole, role, model, user, meetingHall, meetingInfo);
			// 查主会议下的子会议list
			SubMeetingInfo subMeetingInfo = new SubMeetingInfo();
			subMeetingInfo.setMeeParentId(meeId);
			Map<String, Object> subMeetingMap = meetingInfoFacade.selectSubMeetingInfo(subMeetingInfo);

			// 查询这个会议的总粉丝数
			Integer countMeetingFollow = meetingLinkUserFacade.countMeetingFollowByMeeId(meeId);
			// modelAndView.addObject("countMeetingFollow", countMeetingFollow);
			model.addAttribute("countMeetingFollow", countMeetingFollow);

			// modelAndView.setViewName("meeting/meetroom-meetinfo");
			model.addAttribute("subMeetingList", subMeetingMap.get("list"));
			// modelAndView.addObject("subMeetingList", subMeetingMap.get("list"));

			// 用来刷新会议头像
			String meeCover = meetingInfo.getMeeCover();
			if (!(null == meeCover || "".equals(meeCover))) {
				meetingInfo.setMeeCover(fastdfsurl + meeCover);
			} else {
				meetingInfo.setMeeCover("/css/img/" + imageMeetingHead);
			}
			request.getSession().setAttribute("click_meeting", meetingInfo);

		}
		return "mine/person-managerMeetinfo";
	}

	/**
	 * 主会议列表主会议跳转到详情页面
	 * 
	 * @param meetingInfo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/selectmeetingInfo")
	public String meetinginfo() {
		return "meeting/meet-info";
	}

	/**
	 * 主会议跳转到详情页面编辑页面 Stirn
	 * 
	 * @param meetingInfo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/selectmeetingInfoEdit")
	public String meetinginfoEdit() {
		return "meeting/meet-info-edit";
	}

	/**
	 * 更新主会议会议
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateMeeting")
	@ResponseBody
	public ObjectNode userHeadUpate(@RequestParam("file") CommonsMultipartFile file, MeetingInfo meetingInfo, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		try {
			if (!(file.isEmpty())) {
				DiskFileItem fi = (DiskFileItem) file.getFileItem();
				File image = fi.getStoreLocation();
				String uploadFile = FastDFSClient.uploadFile(image, file.getOriginalFilename());
				meetingInfo.setMeeCover(uploadFile);
			}
			Map<String, Object> updateMeetingInfo = meetingInfoFacade.updateMeetingInfo(meetingInfo);
			MeetingInfo meetingInfoId = new MeetingInfo();
			meetingInfoId.setMeeId(meetingInfo.getMeeId());
			MeetingInfo selectMeetingInfoById = meetingInfoFacade.selectMeetingInfoById(meetingInfoId);
			// 用来刷新会议头像
			String meeCover = selectMeetingInfoById.getMeeCover();
			if (!(null == meeCover || "".equals(meeCover))) {
				selectMeetingInfoById.setMeeCover(fastdfsurl + meeCover);
			} else {
				selectMeetingInfoById.setMeeCover("/css/img/" + imageMeetingHead);
			}

			request.getSession().setAttribute("click_meeting", selectMeetingInfoById);
			node.put("errcode", Integer.valueOf((String) updateMeetingInfo.get("code")));
			return node;
		} catch (Exception e) {
			e.printStackTrace();
		}
		node.put("errcode", 0);
		return node;
	}

	/**
	 * 主会议列表子会议跳转到详情页面
	 * 
	 * @param meetingInfo
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/selectsubmeetingInfo")
	public String submeetinginfo(SubMeetingInfo subMeetingInfo, ModelMap mode) {
		SubMeetingInfo selectSubMeetingInfoById = meetingInfoFacade.selectSubMeetingInfoById(subMeetingInfo);
		mode.addAttribute("subMeetingInfo", selectSubMeetingInfoById);
		return "meeting/submeet-info";
	}

	/**
	 * 子会议跳转到详情页面编辑页面 String
	 * 
	 * @param meetingInfo
	 * @param modelAndView
	 * @return 11
	 */
	@RequestMapping("/selectSubmeetingInfoEdit")
	public String submeetinginfoEdit(SubMeetingInfo subMeetingInfo, ModelMap mode) {
		// 查询出子会议详细信息
		subMeetingInfo = meetingInfoFacade.selectSubMeetingInfoById(subMeetingInfo);
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setMeeId(subMeetingInfo.getMeeParentId());
		meetingInfo = meetingInfoFacade.selectMeetingInfoById(meetingInfo);
		mode.addAttribute("subMeetingInfo", subMeetingInfo);
		mode.addAttribute("meetingInfo", meetingInfo);
		return "meeting/submeet-info-edit";
	}

	/**
	 * 更新子会议会议
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateSubMeeting")
	@ResponseBody
	public ObjectNode userSubHeadUpate(SubMeetingInfo meetingInfo, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		Map<String, Object> updateSubMeetingInfo = meetingInfoFacade.updateSubMeetingInfo(meetingInfo);
		node.put("errcode", Integer.valueOf((String) updateSubMeetingInfo.get("code")));
		return node;
	}

	// 修改会议厅图片
	@RequestMapping(value = "/updateMeetingimage")
	@ResponseBody
	public ObjectNode updateMeetingRoom(@RequestParam("file") CommonsMultipartFile file, MeetingHall meetingHall, HttpServletRequest request) {
		// 获取会议厅Id
		MeetingHall meetingHallId = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		meetingHall.setHallId(meetingHallId.getHallId());
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		try {
			if (!(file.isEmpty())) {
				DiskFileItem fi = (DiskFileItem) file.getFileItem();
				File image = fi.getStoreLocation();
				String uploadFile = FastDFSClient.uploadFile(image, file.getOriginalFilename());
				meetingHall.setHallCover(uploadFile);
				Integer updateMeetingHall = meetingHallFacade.updateMeetingHall(meetingHall);
				if (updateMeetingHall == 1) {// 更新成功之后更新session
					meetingHall = meetingHallFacade.queryByIdMeetingHall(meetingHall.getHallId());
					if (!(null == meetingHall.getHallCover() || "".equals(meetingHall.getHallCover()))) {
						meetingHall.setHallCover(fastdfsurl + meetingHall.getHallCover());
					} else {
						meetingHall.setHallCover("/css/img/" + imageHallHead);
					}
					request.getSession().setAttribute("existMeeHall", meetingHall);
					node.put("errcode", updateMeetingHall);
				} else {// 更新失败
					node.put("errcode", 0);
				}
				return node;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		node.put("errcode", 0);
		return node;
	}

	// 修改会议厅名称或者会议厅简介
	@RequestMapping("/meetinghallName")
	@ResponseBody
	public ObjectNode meetinghallName(MeetingHall meetingHall, HttpServletRequest request) {

		MeetingHall meetingHallId = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		meetingHall.setHallId(meetingHallId.getHallId());
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		Integer updateMeetingHall = meetingHallFacade.updateMeetingHall(meetingHall);

		if (updateMeetingHall == 1) {// 更新成功之后更新session
			meetingHall = meetingHallFacade.queryByIdMeetingHall(meetingHall.getHallId());
			if (!(null == meetingHall.getHallCover() || "".equals(meetingHall.getHallCover()))) {
				meetingHall.setHallCover(fastdfsurl + meetingHall.getHallCover());
			} else {
				meetingHall.setHallCover("/css/img/" + imageHallHead);
			}

			request.getSession().setAttribute("existMeeHall", meetingHall);
			node.put("errcode", updateMeetingHall);
		} else {// 更新失败
			node.put("errcode", 0);
		}
		return node;
	}

	// 主页跳转到会议厅简介页面
	@RequestMapping("/roomToMeetingpro")
	public ModelAndView roomToMeetingpro(MeetingInfo meetingInfo, ModelAndView modelAndView, HttpServletRequest request) {
		Integer hallId = meetingInfo.getHallId();
		MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallId);
		modelAndView.setViewName("admin-meetroom/admin-mtrm-intro");
		modelAndView.addObject("meetingHall", meetingHall);
		return modelAndView;
	}

	@RequestMapping("/hallType")
	public ModelAndView createHallType(int hallType, ModelAndView modelAndView) {
		modelAndView.addObject("hallType", hallType);
		modelAndView.setViewName("meeting/person-creatMeetRoom-tep1");
		return modelAndView;
	}

	/**
	 * 主会议邀请
	 * 
	 * @return
	 */
	@RequestMapping("/invite/{hallId}/{meetid}")
	public String meetinvite(@PathVariable("hallId") Integer hallId, @PathVariable("meetid") Integer meetid, HttpServletRequest request) {

		String redirect = "/meeting/intomeet/" + hallId + "/" + meetid;
		if (request.getSession().getAttribute("user") != null) {
			return "redirect:" + redirect;
		}
		boolean isweixin = HttpClientUtils.isWeixin(request);
		if (isweixin) {
			String httpaddress = HttpClientUtils.getHttpAddress(request);

			String redirect_uri = httpaddress + "/weixinoauth/oauth";

			String weixinoauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wechatConfig.getAppId() + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_userinfo&state=" + redirect + "#wechat_redirect";
			return "redirect:" + weixinoauthUrl;
		} else {
			// return "meet_hall/inviteManager";
			return "redirect:" + redirect;
		}
	}

	/**
	 * 主会议邀请
	 * 
	 * @param hallId
	 * @param meetid
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/intomeet/{hallId}/{meetid}")
	public String intomeet(@PathVariable("hallId") Integer hallId, @PathVariable("meetid") Integer meetid, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> maps = new HashMap<String, String>();
		UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
		// 查询主会议
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setMeeId(meetid);
		MeetingInfo meetingInfoById = meetingInfoFacade.selectMeetingInfoById(meetingInfo);
		// 先判断会议厅是否是该用户的 或 该用户是否已经是该会议厅的管理员 是
		// 不执进入主会议的功能，显示邀请页面
		// Integer.parseInt("DDD");
		MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallId);
		Role userrole = roleModuleReadFacade.findRoleByUserIdHallId(userWeixinInfo.getUserId(), meetid);
		if (Objects.equals(userWeixinInfo.getUserId(), meetingInfoById.getCreateUser())) {
			// 进入邀请页面
			return "meeting/meetinginvite";
		} else if (userrole == null) {
			// 进入会议厅页面
			return "redirect:/meetingInfo/" + meetid + "/" + hallId;
		} else {
			if (Objects.equals("manager", userrole.getRoleType()) && Objects.equals("default", userrole.getRoleStatus())) {// 超级管理员
				// 进入邀请页面
				return "meeting/meetinginvite";
			} else {// 普通人员,和普通管理员
				return "redirect:/meetingInfo/" + meetid + "/" + hallId;
			}
		}
	}

	/**
	 * 主会议跳转会议管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/meetingAdmin/manage")
	public String meetingManage(HttpServletRequest request, ModelMap mode) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 如何获取创建会议的用户Id
		UserWeixinInfo createMeetinguser = userWeixinInfoFacade.selectByUserId(meetingInfo.getCreateUser());
		// 获取会议管理员集合
		List<String> ManagerUserIdBymeeting = roleModuleReadFacade.getManagerUserIdByhallid(meetingInfo.getMeeId());
		List<UserWeixinInfo> list = new ArrayList<UserWeixinInfo>();
		for (String userWeixinInfoId : ManagerUserIdBymeeting) {
			UserWeixinInfo selectByUserId = userWeixinInfoFacade.selectByUserId(userWeixinInfoId);
			list.add(selectByUserId);
		}
		// 根据主会议下的管理角色
		List<Role> role = roleModuleReadFacade.getRoleByCondition("manager", meetingInfo.getMeeId());

		mode.addAttribute("createMeetinguser", createMeetinguser);
		mode.addAttribute("list", list);
		mode.addAttribute("role", role);
		mode.addAttribute("meeId", meetingInfo.getMeeId());
		mode.addAttribute("hallId", meetingInfo.getHallId());
		return "admin-meeting/admin-meetingtrm-administrator";
	}

	/**
	 * 跳转到会议管理员角色选择界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectMeetingRole")
	public String selectMeetingRole(UserRoleLink userRoleLink, HttpServletRequest request, ModelMap mode) throws Exception {
		// 管理员角色列表
		List<Role> roleList = roleModuleReadFacade.getRoleByCondition("manager", userRoleLink.getMeetingId());
		// 查询用户在主会议的角色
		Role role = roleModuleReadFacade.findRoleByUserIdHallId(userRoleLink.getUserId(), userRoleLink.getMeetingId());
		mode.addAttribute("roleList", roleList);
		mode.addAttribute("role", role);
		mode.addAttribute("userRoleLink", userRoleLink);
		return "admin-meeting/admin-meetingmtrm-selectRole";
	}

	/**
	 * 保存会议管理员角色选择界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectMeetingRoleSave")
	@ResponseBody
	public Boolean selectMeetingRoleSave(@RequestBody UserRoleLink userRoleLink, HttpServletRequest request, ModelMap mode) throws Exception {
		DubboxResult updateUserRole = roleModuleWriteFacade.updateUserRole(userRoleLink);
		return "0".equals(updateUserRole.getStatus()) ? true : false;
	}

	/**
	 * 删除主会议管理员
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deletemeetingetingManage/{userId}")
	@ResponseBody
	public Boolean deletemeetingetingManage(@PathVariable("userId") String userId, HttpServletRequest request, ModelMap mode) throws Exception {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		UserRoleLink UserRoleLink = new UserRoleLink();
		UserRoleLink.setUserId(userId);
		UserRoleLink.setMeetingId(meetingInfo.getMeeId());
		DubboxResult deleteUserRole = roleModuleWriteFacade.deleteUserRole(UserRoleLink);
		return "0".equals(deleteUserRole.getStatus()) ? true : false;
	}

	/**
	 * 删除主会议角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deletemeetingRole/{roleId}")
	@ResponseBody
	public Boolean deletemeetingRole(@PathVariable("roleId") Integer roleId) {
		DubboxResult deleteUserRole = roleModuleWriteFacade.deleteRoleModule(roleId);
		return "0".equals(deleteUserRole.getStatus()) ? true : false;
	}

	/**
	 * 主会议跳转会议管理员界面
	 * 
	 * @return
	 */
	@RequestMapping("/createMeetingAdmin/manage")
	public String createMeetingManage(HttpServletRequest request, ModelMap mode) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 查询主会议管理角色
		List<Role> roleManageList = roleModuleReadFacade.getRoleByCondition("manager", meetingInfo.getMeeId());

		mode.addAttribute("roleManageList", roleManageList);
		return "admin-meeting/admin-meetingtrm-creatRole-manage";
	}

	/**
	 * 主会议跳转会议参会者界面
	 * 
	 * @return
	 */
	@RequestMapping("/createMeetingAdmin/joiner")
	public String createMeetingjoiner(HttpServletRequest request, ModelMap mode) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");

		// 查询主会议普通会员
		List<Role> rolejoinerList = roleModuleReadFacade.getRoleByCondition("joiner", meetingInfo.getMeeId());

		mode.addAttribute("rolejoinerList", rolejoinerList);

		return "admin-meeting/admin-meetingtrm-creatRole-joiner";
	}

	/**
	 * 查询管理员权限模块
	 * 
	 * @return
	 */
	@RequestMapping("/creatMeetingRole")
	public String creatMeetingRole(HttpServletRequest request, ModelMap mode) {
		List<Module> allModules = roleModuleReadFacade.getAllModules();
		// 跟换Modul的名称
		for (Module modulesuper : allModules) {
			String moduleName = modulesuper.getModuleName();
			String[] splitName = moduleName.split(",");
			modulesuper.setModuleName(splitName[1]);
		}
		mode.addAttribute("allModules", allModules);
		return "admin-meeting/creat-meeting-Role";
	}

	/**
	 * 查询普通用户权限模块
	 * 
	 * @return
	 */
	@RequestMapping("/creatMeetingJoinerRole")
	public String creatMeetingJoinerRole(HttpServletRequest request, ModelMap mode) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		List<Module> allModules = roleModuleReadFacade.getAllModules();
		// 跟换Modul的名称
		for (Module modulesuper : allModules) {
			String moduleName = modulesuper.getModuleName();
			String[] splitName = moduleName.split(",");
			modulesuper.setModuleName(splitName[0]);
		}
		// 根据该会议查询出会议厅的会员
		List<MemberTypeInfo> MemberTypeInfoPersonalList = memberTypeInfoFacade.selectListByHallIdAndType(meetingInfo.getHallId(), 1);
		List<MemberTypeInfo> MemberTypeInfoUnitList = memberTypeInfoFacade.selectListByHallIdAndType(meetingInfo.getHallId(), 0);
		// 根据会议Id查询出普通会员
		List<Role> roleList = roleModuleReadFacade.getRoleByCondition("joiner", meetingInfo.getMeeId());
		// 判断角色表中是否已经创建,如果已经创建就移除
		MemberTypeInfoPersonalList = removemember(MemberTypeInfoPersonalList, roleList, 1);
		MemberTypeInfoUnitList = removemember(MemberTypeInfoUnitList, roleList, 0);
		mode.addAttribute("MemberTypeInfoPersonal", MemberTypeInfoPersonalList);
		mode.addAttribute("MemberTypeInfoUnit", MemberTypeInfoUnitList);
		mode.addAttribute("allModules", allModules);
		return "admin-meeting/creat-meeting-joiner-Role";
	}

	// 用于做普通角色重复的移除的
	private List<MemberTypeInfo> removemember(List<MemberTypeInfo> MemberTypeInfoPersonalList, List<Role> roleList, Integer i) {
		// 用于移除的模块
		List<MemberTypeInfo> list = new ArrayList<MemberTypeInfo>();
		for (Role role : roleList) {
			for (MemberTypeInfo MemberTypeInfoPersonal : MemberTypeInfoPersonalList) {
				if (role.getMember_type_id() == MemberTypeInfoPersonal.getId() && role.getType() == i) {
					list.add(MemberTypeInfoPersonal);
				}
			}
		}
		MemberTypeInfoPersonalList.removeAll(list);
		return MemberTypeInfoPersonalList;
	}

	/**
	 * 创建管理员角色
	 * 
	 * @return
	 */
	@RequestMapping("/saveCreateMeetingAdmin")
	@ResponseBody
	public Boolean saveCreateMeetingAdmin(@RequestBody Role role, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		role.setMeetId(meetingInfo.getMeeId());
		MeetingHall meetingHallId = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		role.setMeetHallId(meetingHallId.getHallId());
		role.setRoleType("manager");
		role.setMember_type_id(0);// 管理员不需要和会员关联
		DubboxResult RoleModule = roleModuleWriteFacade.insertRoleModule(role);
		return "0".equals(RoleModule.getStatus()) ? true : false;
	}

	/**
	 * 创建普通用户角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveCreateMeetingJoinner")
	@ResponseBody
	public Boolean saveCreateMeetingJoinner(@RequestBody RoleMemberDto roleMemberDto, HttpServletRequest request) throws Exception {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		MemberTypeInfo mmberTypeInfo = memberTypeInfoFacade.selectById(roleMemberDto.getMember_type_id());
		roleMemberDto.setMeetHallId(meetingInfo.getHallId());
		roleMemberDto.setMeetId(meetingInfo.getMeeId());
		roleMemberDto.setType(mmberTypeInfo.getType());
		roleMemberDto.setRoleName(mmberTypeInfo.getName());
		roleMemberDto.setRoleType("joiner");
		DubboxResult RoleModule = roleModuleWriteFacade.insertRoleModuleJoiner(roleMemberDto);
		return "0".equals(RoleModule.getStatus()) ? true : false;
	}

	/**
	 * 修改普通用户角色权限
	 * 
	 * @return
	 */
	@RequestMapping("/upodateCreateMeetingJoinner")
	public String updateCreateMeetingJoinner(Integer roleId, HttpServletRequest request, ModelMap mode) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Role role = roleModuleReadFacade.getRoleModuleByRoleId(roleId);
		List<Module> modules2 = role.getModules();// 查询出角色有的模块
		// 查询出会议的模块
		List<Module> modules = roleModuleReadFacade.getAllModules();
		List<Module> ModulesNo = new ArrayList<Module>();// 用于移除的模块
		RoleModuleUtils.ModuleNameReplace(modules2, modules, ModulesNo, 0);
		mode.addAttribute("modules", modules);
		mode.addAttribute("ModulesNo", ModulesNo);
		if (role.getType() == null) { // 默认角色
			mode.addAttribute("roleId", roleId);
			return "admin-meeting/update-meeting-joiner-Role";
		} else if (role.getType() == 0) { // 单位
			RoleMemberDto roleDto = roleModuleReadFacade.getRoleModuleBymember_type_id(roleId);
			mode.addAttribute("roleDto", roleDto);
			return "admin-meeting/update-meeting-joiner-Role-Member-unit";

		} else { // 个人
			RoleMemberDto roleDto = roleModuleReadFacade.getRoleModuleBymember_type_id(roleId);
			mode.addAttribute("roleDto", roleDto);
			return "admin-meeting/update-meeting-joiner-Role-Member-personal";
		}
	}

	/**
	 * 更新普通会议角色
	 * 
	 * @return
	 */
	@RequestMapping("/updateCreateMeetingjoinerSave")
	@ResponseBody
	public Boolean updateCreateMeetingjoinerSave(@RequestBody Role role, HttpServletRequest request, ModelMap mode) {
		Role roleModuleByRoleId = roleModuleReadFacade.getRoleModuleByRoleId(role.getRoleId());
		roleModuleByRoleId.setModuleIds(role.getModuleIds());
		DubboxResult updateRoleModule = roleModuleWriteFacade.updateRoleModule(roleModuleByRoleId);
		return "0".equals(updateRoleModule.getStatus()) ? true : false;

	}

	/**
	 * 更新普通会员会议角色Member
	 * 
	 * @return
	 */
	@RequestMapping("/updateCreateMeetingjoinerMemberSave")
	@ResponseBody
	public Boolean updateCreateMeetingjoinerMemberSave(@RequestBody RoleMemberDto RoleMemberDto, HttpServletRequest request, ModelMap mode) {
		DubboxResult updateRoleModuleMember = roleModuleWriteFacade.updateRoleModuleMember(RoleMemberDto);
		return "0".equals(updateRoleModuleMember.getStatus()) ? true : false;
	}

	/**
	 * 修改管理用户角色权限
	 * 
	 * @return
	 */
	@RequestMapping("/upodateCreateMeetingmanage")
	public String updateCreateMeetingmanage(Integer roleId, HttpServletRequest request, ModelMap mode) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Role role = roleModuleReadFacade.getRoleModuleByRoleId(roleId);
		List<Module> modules2 = role.getModules();// 查询出角色有的模块
		// 查询出角色的模块
		List<Module> modules = roleModuleReadFacade.getAllModules();
		List<Module> ModulesNo = new ArrayList<Module>();// 用于移除的模块
		RoleModuleUtils.ModuleNameReplace(modules2, modules, ModulesNo, 1);
		mode.addAttribute("modules", modules);
		mode.addAttribute("ModulesNo", ModulesNo);
		mode.addAttribute("roleId", roleId);
		return "admin-meeting/update-meeting-Role";
	}

	/**
	 * 更新管理员会议角色
	 * 
	 * @return
	 */
	@RequestMapping("/upodateCreateMeetingmanageSave")
	@ResponseBody
	public Boolean updateCreateMeetingmanageSave(@RequestBody Role role, HttpServletRequest request, ModelMap mode) {
		Role roleModuleByRoleId = roleModuleReadFacade.getRoleModuleByRoleId(role.getRoleId());
		roleModuleByRoleId.setModuleIds(role.getModuleIds());
		DubboxResult updateRoleModule = roleModuleWriteFacade.updateRoleModule(roleModuleByRoleId);
		return "0".equals(updateRoleModule.getStatus()) ? true : false;
	}

	/**
	 * 判断角色名是否重复
	 * 
	 * @return
	 */
	@RequestMapping("/confimeRoleName")
	@ResponseBody
	public Boolean confimeRoleName(String roleName, HttpServletRequest request, Integer type) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		return roleModuleReadFacade.existRoleName(roleName, meetingInfo.getMeeId(), type); // 返回true时名称重复
	}

	/**
	 * 判断角色名是否重复
	 * 
	 * @return
	 */
	@RequestMapping("/confimeRoleNameManage")
	@ResponseBody
	public Boolean confimeRoleNameManage(String roleName, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		return roleModuleReadFacade.existRoleNameManage(roleName, meetingInfo.getMeeId()); // 返回true时名称重复
	}

	/**
	 * 邀请主会议管理员页面
	 * 
	 * @return
	 */
	// TODO
	@RequestMapping("/inviteMeeting/{hallId}/{meeId}/{roleId}")
	public ModelAndView inviteManager(@PathVariable("hallId") Integer hallId, @PathVariable("meeId") Integer meeId, @PathVariable("roleId") Integer roleId, HttpServletRequest request) {
		String httpaddress = HttpClientUtils.getHttpAddress(request);

		try {
			roleModuleReadFacade.setInviteUrl(meeId, roleId, hallId, 3600);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String redirect = "/meeting/tobemanager/" + hallId + "/" + meeId + "/" + roleId;
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("redirect:" + redirect);
		}
		boolean isweixin = HttpClientUtils.isWeixin(request);
		if (isweixin) {// 微信的网页授权

			String redirect_uri = httpaddress + "/weixinoauth/oauth";

			String weixinoauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wechatConfig.getAppId() + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_userinfo&state=" + redirect + "#wechat_redirect";
			return new ModelAndView("redirect:" + weixinoauthUrl);
		} else {
			// return "meet_hall/inviteManager";
			return new ModelAndView("redirect:" + redirect);

		}

	}

	/**
	 * 成为主会议管理员
	 * 
	 * @param hallId
	 * @param roleId
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/tobemanager/{hallId}/{meeId}/{roleId}")
	public String tobemanager(@PathVariable("hallId") Integer hallId, @PathVariable("meeId") Integer meeId, @PathVariable("roleId") Integer roleId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> maps = new HashMap<String, String>();
		Role meetRole = roleModuleReadFacade.getRoleModuleByRoleId(roleId);
		modelMap.addAttribute("role", meetRole);

		try {
			UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
			// 先判断会议厅是否是该用户的 或 该用户是否已经是主会议的管理员 是
			// 不执行成为管理员的功能 显示邀请页面
			MeetingInfo meetingInfoId = new MeetingInfo();
			meetingInfoId.setMeeId(meeId);
			Role roleByUserIdHallId = roleModuleReadFacade.findRoleByUserIdHallId(userWeixinInfo.getUserId(), meeId);
			// 查询出会议厅的角色
			HallRole hallRole = roleModuleReadFacade.findHallRoleByUserIdHallId(userWeixinInfo.getUserId(), hallId);
			MeetingInfo selectMeetingInfoById = meetingInfoFacade.selectMeetingInfoById(meetingInfoId);
			// 查询出会议厅
			MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(hallId);
			// 做角色判断
			if (Objects.equals(userWeixinInfo.getUserId(), selectMeetingInfoById.getCreateUser()) || Objects.equals(userWeixinInfo.getUserId(), meetingHall.getUserId())) {
				return "admin-meeting/admin-meeting-invite";
			} else if (hallRole != null) {
				if (Objects.equals("manager", hallRole.getRoleType())) {
					return "redirect:/meeting/selectSubMeeByParentId_mvc?meeId=" + meeId;
				}
			} else if (roleByUserIdHallId != null) {
				if (Objects.equals("manager", roleByUserIdHallId.getRoleType())) {
					return "redirect:/meeting/selectSubMeeByParentId_mvc?meeId=" + meeId;
				}
			}

			// timeout 代表连接已超时或已经有人点击过该链接
			// already 该用户已经是该会议厅的管理员了
			// ok 成为管理员成功
			// error 成为管理员失败

			String info = roleModuleReadFacade.inviteManager(meeId, roleId, hallId, userWeixinInfo.getUserId());
			if (Objects.equals("timeout", info)) {
				maps.put("status", "400");
				maps.put("msg", "邀请链接超时");
				modelMap.addAttribute("tobemanager", maps);
				return "admin-meeting/inviteMeetingManager";
			} else if (Objects.equals("already", info)) {
				maps.put("status", "401");
				maps.put("msg", "该用户已经是该会议的管理员了");
				modelMap.addAttribute("tobemanager", maps);
				return "admin-meeting/inviteMeetingManager";
			} else if (Objects.equals("ok", info)) {
				// 成为管理员成功 跳转会议厅主业
				maps.put("status", "200");
				maps.put("msg", "成为管理员成功");
				modelMap.addAttribute("tobemanager", maps);
				return "redirect:/meeting/selectSubMeeByParentId_mvc?meeId=" + meeId;
			} else {
				maps.put("status", "404");
				maps.put("msg", "成为管理员失败");
				modelMap.addAttribute("tobemanager", maps);
				return "admin-meeting/inviteMeetingManager";
			}

		} catch (Exception e) {
			e.printStackTrace();
			maps.put("status", "500");
			maps.put("msg", "服务器异常");
			modelMap.addAttribute("tobemanager", maps);
			return "admin-meeting/inviteMeetingManager";
		}

	}

	/**
	 * 得到开始时间的最大时间
	 * 
	 * @author chensujian
	 * @param request
	 * @return
	 */
	@RequestMapping("/getStartMaxTime")
	@ResponseBody
	public String getStartMaxTime(HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Integer meeId = meetingInfo.getMeeId();
		Long start = meetingInfoFacade.getFristStartTimeByParentMeeId(meeId);
		if (start == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date endDate = new Date(start);
		return sdf.format(endDate);
	}

	/**
	 * 得到结束时间的最小时间
	 * 
	 * @author chensujian
	 * @param request
	 * @return
	 */
	@RequestMapping("/getEndMinTime")
	@ResponseBody
	public String getEndMinTime(HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Integer meeId = meetingInfo.getMeeId();
		Long end = meetingInfoFacade.getLastEndTimeByParentMeeId(meeId);
		if (end == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date endDate = new Date(end);
		return sdf.format(endDate);
	}

}
