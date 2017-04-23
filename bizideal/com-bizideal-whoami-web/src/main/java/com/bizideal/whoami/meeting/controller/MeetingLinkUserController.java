package com.bizideal.whoami.meeting.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingInfoFacade;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.signup.facade.SignUpInfoFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.SessionUtil;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingLinkUserController
 * @Description TODO(用户关注的会议)
 * @Author Zj.Qu
 * @Date 2017-01-16 14:58:13
 */
@Controller
@RequestMapping("/meetingLinkUser")
public class MeetingLinkUserController {

	@Autowired
	private MeetingInfoFacade meetingInfoBizFacade;

	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;

	@Autowired
	private SignUpInfoFacade signUpInfoFacade;

	@Autowired
	private MeetingInfoFacade meetingInfoFacade;

	@Autowired
	private RoleModuleReadFacade roleModuleReadFacade;

	@Value("${fastdfsurl}")
	private String fastDFSurl;

	@Value("${imageMeetingHead}")
	private String imageMeetingHead;// 会议头像

	@RequestMapping(value = "/future/{page}/{rows}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> selectFocusMeetingInfoByUserIdFuture(@PathVariable("page") Integer page, @PathVariable("rows") Integer rows, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sessionUserId = SessionUtil.getSessionUserId(request);
		PageInfo<MeetingInfo> pageInfo = meetingInfoBizFacade.selectFocusMeetingInfoByUserIdFuture(sessionUserId, page, rows);
		map.put("pageInfo", pageInfo);
		map.put("fastDFSurl", fastDFSurl);
		return map;
	}

	@RequestMapping(value = "/over/{page}/{rows}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> selectFocusMeetingInfoByUserIdOver(@PathVariable("page") Integer page, @PathVariable("rows") Integer rows, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sessionUserId = SessionUtil.getSessionUserId(request);
		PageInfo<MeetingInfo> pageInfo = meetingInfoBizFacade.selectFocusMeetingInfoByUserIdOver(sessionUserId, page, rows);
		map.put("pageInfo", pageInfo);
		map.put("fastDFSurl", fastDFSurl);
		return map;
	}

	// app跳转我参与的会议
	@RequestMapping("/app/jionMeet/{userId}")
	public String appjionMeet(@PathVariable("userId") String userId, HttpServletRequest request, Model model) {
		UserWeixinInfo user = userWeixinInfoFacade.selectByUserId(userId);
		request.getSession().setAttribute("user", user);
		Date nowDate = new Date();
		model.addAttribute("nowDate", nowDate.getTime());
		List<Integer> userList = signUpInfoFacade.selectMeetingIdByUserId(userId);
		// 获取我参与的会议（未开始）
		PageInfo<MeetingInfo> meetingFuture = meetingInfoFacade.selectMeetingInfoByListFuture(userList, 1, 1000);
		model.addAttribute("meetingFuture", meetingFuture);
		// 获取我参与的会议（已结束）
		PageInfo<MeetingInfo> meetingOver = meetingInfoFacade.selectMeetingInfoByListOver(userList, 1, 1000);
		model.addAttribute("meetingOver", meetingOver);
		return "mine/person-jionMeet";
	}

	/**
	 * 跳转我管理的主会议App
	 * 
	 * @return
	 */
	@RequestMapping("/AppskipMymanageMeeting/{userId}")
	public String AppskipMyMeeting(@PathVariable String userId, HttpServletRequest request, Model model) {
		// UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		UserWeixinInfo user = userWeixinInfoFacade.selectByUserId(userId);
		request.getSession().setAttribute("user", user);
		// 根据用户Id查询用户管理主会议Id的集合
		List<Integer> userIdList = roleModuleReadFacade.getHallIdByUserId(user.getUserId());
		MeetingInfo meetingInfo = new MeetingInfo();
		List<MeetingInfo> list = new ArrayList<MeetingInfo>();

		if (userIdList.size() == 0) {
			return "mine/person-manageMeet-No";// 没有会议管理的页面
		} else {
			for (Integer integer : userIdList) {
				meetingInfo.setMeeId(integer);
				meetingInfo = meetingInfoFacade.selectMeetingInfoById(meetingInfo);
				/* 刷新会议头像 */
				if (!(null == meetingInfo.getMeeCover() || "".equals(meetingInfo.getMeeCover()))) {
					meetingInfo.setMeeCover(fastDFSurl + meetingInfo.getMeeCover());
				} else {
					meetingInfo.setMeeCover("/css/img/" + imageMeetingHead);
				}
				list.add(meetingInfo);
			}
			model.addAttribute("list", list);
			return "mine/person-manageMeet";
		}
	}

}