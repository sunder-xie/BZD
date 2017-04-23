package com.bizideal.whoami.leaflets.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.facade.leaflets.entity.MeetingLeaflets;
import com.bizideal.whoami.facade.leaflets.facade.MeetingLeafletsFacade;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.user.entity.UserWeixinInfo;

@Controller
@RequestMapping("/meetingLeaflets")
public class MeetingLeafletsController {

	@Autowired
	private MeetingLeafletsFacade meetingLeafletsFacade;

	/**
	 * 跳转宣传添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/toMeetingLeaflets")
	public String toMeetingLeaflets(HttpServletRequest request, Model model) {

		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		MeetingLeaflets selectByMeeIdInUse = meetingLeafletsFacade.selectByMeeIdInUse(meetingInfo.getMeeId());
		if (null != selectByMeeIdInUse) {
			String url = selectByMeeIdInUse.getUrl();
			model.addAttribute("leafletName", selectByMeeIdInUse.getLeafletName());
			model.addAttribute("url", selectByMeeIdInUse.getUrl());
			model.addAttribute("remark", selectByMeeIdInUse.getRemark());
		}
		return "meeting_leaflets/meet-publicity";
	}

	/**
	 * 跳转宣传添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/seeMeetingLeaflets")
	public String seeMeetingLeaflets(HttpServletRequest request, Model model) {

		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		MeetingLeaflets selectByMeeIdInUse = meetingLeafletsFacade.selectByMeeIdInUse(meetingInfo.getMeeId());
		if (null != selectByMeeIdInUse) {
			String url = selectByMeeIdInUse.getUrl();
			return "redirect:" + url;
		} else {
			return "meeting_leaflets/meet-publicity-no";
		}
	}

	/**
	 * ajax提交宣传页添加
	 * 
	 * @param request
	 * @param model
	 * @param meetingLeaflets
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody int add(HttpServletRequest request, Model model, MeetingLeaflets meetingLeaflets) {

		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		meetingLeaflets.setMeeId(meetingInfo.getMeeId());
		meetingLeaflets.setCreateUser(user.getUserId());
		MeetingLeaflets selectByMeeIdInUse = meetingLeafletsFacade.selectByMeeIdInUse(meetingInfo.getMeeId());
		if (null == selectByMeeIdInUse) {
			return meetingLeafletsFacade.insertMeetingLeaflets(meetingLeaflets);
		} else {
			meetingLeaflets.setId(selectByMeeIdInUse.getId());
			return meetingLeafletsFacade.updateMeetingLeaflets(meetingLeaflets);
		}
	}
}
