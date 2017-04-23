package com.bizideal.whoami.user.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.signup.facade.SignUpInfoFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserInfoFacade;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.RoleModuleUtils;
import com.bizideal.whoami.wechat.pojo.WeiXinUserInfoRepassword;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/userCenter")
public class UserCenterController {

	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	@Autowired
	private UserInfoFacade userInfoFacade;
	@Autowired
	private MeetingInfoFacade meetingInfoFacade;
	@Autowired
	private MeetingHallFacade meetingHallFacade;
	@Autowired
	private RoleModuleReadFacade roleModuleReadFacade;
	@Autowired
	private SignUpInfoFacade signUpInfoFacade;

	@Value("${fastdfsurl}")
	private String fastdfsurl;

	@Value("${imageMeetingHead}")
	private String imageMeetingHead;// 会议头像

	/*
	 * @Value("${imageHead}") private String imageHead;//我的头像图片
	 */
	// 跳转我的
	@RequestMapping("/user")
	public ModelAndView userCenter(ModelAndView modelAndView, HttpServletRequest request) {
		modelAndView.setViewName("mine/person-center");
		return modelAndView;
	}

	// 跳转我关注的会议
	@RequestMapping("/user/followmeeting")
	public ModelAndView userFollowMeeting(ModelAndView modelAndView, HttpServletRequest request) {
		modelAndView.setViewName("mine/person-focusMeet");
		return modelAndView;
	}

	// 跳转我关注的会议厅
	@RequestMapping("/user/followhall")
	public ModelAndView userFollowHall(ModelAndView modelAndView, HttpServletRequest request) {
		modelAndView.setViewName("mine/person-follow-hall");
		return modelAndView;
	}

	// 跳转我的头像昵称修改
	@RequestMapping("/userHead")
	public ModelAndView userHead(ModelAndView modelAndView, HttpServletRequest request) {
		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		modelAndView.setViewName("mine/person-data");
		return modelAndView;
	}

	// 更新头像
	@RequestMapping("/userHeadUpdate")
	@ResponseBody
	public ObjectNode userHeadUpate(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
		UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		try {
			if (!(file.isEmpty())) {
				DiskFileItem fi = (DiskFileItem) file.getFileItem();
				File image = fi.getStoreLocation();
				String uploadFile = FastDFSClient.uploadFile(image, file.getOriginalFilename());
				userWeixinInfo.setHeadimgurl(uploadFile);
				int updateImgAndName = userInfoFacade.updateImgAndName(userWeixinInfo);
				UserWeixinInfo userInfo = userWeixinInfoFacade.selectByUserId(userWeixinInfo.getUserId());
				request.getSession().setAttribute("user", userInfo);
				node.put("errcode", updateImgAndName);
				return node;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		node.put("errcode", 0);
		return node;
	}

	// 更新昵称
	@RequestMapping("/userNickName")
	@ResponseBody
	public ObjectNode userHeadUpatenickName(String nickName, HttpServletRequest request) {
		UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		try {
			userWeixinInfo.setNickname(nickName);
			userWeixinInfo.setHeadimgurl(null);
			int updateImgAndName = userInfoFacade.updateImgAndName(userWeixinInfo);
			UserWeixinInfo userInfo = userWeixinInfoFacade.selectByUserId(userWeixinInfo.getUserId());
			request.getSession().setAttribute("user", userInfo);
			node.put("errcode", updateImgAndName);
			return node;
		} catch (Exception e) {
			e.printStackTrace();
		}
		node.put("errcode", 0);
		return node;
	}

	// 更新真实姓名
	@RequestMapping("/userRealName")
	@ResponseBody
	public ObjectNode userHeadUpatereakName(String realName, HttpServletRequest request) {
		UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		try {
			userWeixinInfo.setRealName(realName);
			userWeixinInfo.setHeadimgurl(null);
			int updateImgAndName = userInfoFacade.updateImgAndName(userWeixinInfo);
			UserWeixinInfo userInfo = userWeixinInfoFacade.selectByUserId(userWeixinInfo.getUserId());
			request.getSession().setAttribute("user", userInfo);
			node.put("errcode", updateImgAndName);
			return node;
		} catch (Exception e) {
			e.printStackTrace();
		}
		node.put("errcode", 0);
		return node;
	}

	// 跳转到更改密码页面
	@RequestMapping("/setPassW")
	public ModelAndView setPassWord(ModelAndView modelAndView, HttpServletRequest request) {
		UserWeixinInfo userInfo = userWeixinInfoFacade.selectByUserId(((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId());
		modelAndView.setViewName("mine/setting-password");
		modelAndView.addObject("userInfo", userInfo);
		return modelAndView;
	}

	// 获取验证码
	@RequestMapping("/getMsg")
	@ResponseBody
	public JSONObject getMsg(@RequestBody UserWeixinInfo user, HttpServletRequest request) {
		return userWeixinInfoFacade.sendMsgCodeToHasRegistered(user.getPhone());
	}

	// 更改密码
	@RequestMapping("/updateUserPassW")
	@ResponseBody
	public JSONObject updateUserPassW(@RequestBody WeiXinUserInfoRepassword ruser, HttpServletRequest request) {
		// 判断密码和重密码是否一致
		if (!(ruser.getPassword()).equals(ruser.getRepassword())) {
			return null;
		}

		UserWeixinInfo weixinInfo = new UserWeixinInfo();
		weixinInfo.setUserId(ruser.getUserId());
		weixinInfo.setPhone(ruser.getPhone());
		weixinInfo.setMsgCode(ruser.getMsgCode());
		weixinInfo.setPassword(ruser.getPassword());

		// 修改密码开始
		JSONObject updatePassword = userInfoFacade.updatePassword(weixinInfo);
		Integer errcode = (Integer) updatePassword.get("errcode");
		if (errcode == 0) {
			request.getSession().removeAttribute("user");
		}
		return updatePassword;
	}

	// 跳转我参与的会议
	@RequestMapping("/jionMeet")
	public String jionMeet(HttpServletRequest request, Model model) {

		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		Date nowDate = new Date();
		model.addAttribute("nowDate", nowDate.getTime());

		List<Integer> userList = signUpInfoFacade.selectMeetingIdByUserId(userId);
		// 获取我参与的会议（未开始）
		PageInfo<MeetingInfo> meetingFuture = meetingInfoFacade.selectMeetingInfoByListFuture(userList, 1, 1000);
		List<MeetingInfo> list = meetingFuture.getList();
		if (list != null) {
			for (MeetingInfo meetingInfo1 : list) {// 刷新会议头像
				if (null == meetingInfo1.getMeeCover() || meetingInfo1.getMeeCover().equals("")) {
					meetingInfo1.setMeeCover("/css/img/" + imageMeetingHead);
				} else {
					meetingInfo1.setMeeCover(fastdfsurl + meetingInfo1.getMeeCover());
				}
			}
		}
		model.addAttribute("meetingFuture", meetingFuture);
		// 获取我参与的会议（已结束）
		PageInfo<MeetingInfo> meetingOver = meetingInfoFacade.selectMeetingInfoByListOver(userList, 1, 1000);
		List<MeetingInfo> listOver = meetingOver.getList();
		if (listOver != null) {
			for (MeetingInfo meetingInfo2 : listOver) {// 刷新会议头像
				if (null == meetingInfo2.getMeeCover() || meetingInfo2.getMeeCover().equals("")) {
					meetingInfo2.setMeeCover("/css/img/" + imageMeetingHead);
				} else {
					meetingInfo2.setMeeCover(fastdfsurl + meetingInfo2.getMeeCover());
				}
			}
		}
		model.addAttribute("meetingOver", meetingOver);
		return "mine/person-jionMeet";
	}

	// 跳转我参与的会议的子会议列表
	@RequestMapping("/jionMeetRoom")
	public String jionMeetRoom(HttpServletRequest request, int meeId, Model model) {

		// 做权限判断
		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setMeeId(meeId);
		meetingInfo = meetingInfoFacade.selectMeetingInfoById(meetingInfo);
		MeetingHall meetingHall = meetingHallFacade.queryByIdMeetingHall(meetingInfo.getHallId());
		Role role = roleModuleReadFacade.findRoleByUserIdHallId(user.getUserId(), meeId);
		HallRole hallrole = roleModuleReadFacade.findHallRoleByUserIdHallId(user.getUserId(), meetingHall.getHallId());
		// 用于判断权限
		RoleModuleUtils.meetingToPermission(hallrole, role, model, user, meetingHall, meetingInfo);
		List<Integer> meeSubList = signUpInfoFacade.selectSubMeetingIdByUserId(user.getUserId(), meeId);
		if (meeSubList.size() != 0 && meeSubList != null) {
			List<SubMeetingInfo> list = meetingInfoFacade.selectSubMeetingInfoByList(meeSubList);
			model.addAttribute("subMeetingList", list);
		}
		// 用来刷新会议头像
		String meeCover = meetingInfo.getMeeCover();
		if (!(null == meeCover || "".equals(meeCover))) {
			meetingInfo.setMeeCover(fastdfsurl + meeCover);
		} else {
			meetingInfo.setMeeCover("/css/img/" + imageMeetingHead);
		}
		request.getSession().setAttribute("click_meeting", meetingInfo);

		return "mine/person-jionMeetRoom";
	}

	/**
	 * 跳转我管理的主会议
	 * 
	 * @return
	 */
	@RequestMapping("/skipMymanageMeeting")
	public String skipMyMeeting(HttpServletRequest request, Model model) {
		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		// 根据用户Id查询用户管理主会议Id的集合
		List<Integer> userIdList = roleModuleReadFacade.getHallIdByUserId(user.getUserId());
		List<MeetingInfo> list = new ArrayList<MeetingInfo>();

		if (userIdList.size() == 0) {
			return "mine/person-manageMeet-No";// 没有会议管理的页面
		} else {
			for (Integer integer : userIdList) {
				MeetingInfo meetingInfo = new MeetingInfo();
				meetingInfo.setMeeId(integer);
				MeetingInfo meetingInfo_rec = meetingInfoFacade.selectMeetingInfoById(meetingInfo);
				/* 刷新会议头像 */
				if(meetingInfo_rec != null){
					if (!(null == meetingInfo_rec.getMeeCover() || "".equals(meetingInfo_rec.getMeeCover()))) {
						meetingInfo_rec.setMeeCover(fastdfsurl + meetingInfo_rec.getMeeCover());
					} else {
						meetingInfo_rec.setMeeCover("/css/img/" + imageMeetingHead);
					}
					list.add(meetingInfo_rec);
				}
			}
			if(list.size() == 0){
				return "mine/person-manageMeet-No";// 没有会议管理的页面
			}
			model.addAttribute("list", list);
			return "mine/person-manageMeet";
		}
	}

	/**
	 * 我的二维码
	 * 
	 * @return
	 */
	@RequestMapping("/mycode")
	public String mycode() {
		return "mine/person-code";
	}
}
