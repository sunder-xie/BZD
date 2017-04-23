package com.bizideal.whoami.meeting.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.facade.meeting.entity.HallLinkUser;
import com.bizideal.whoami.facade.meeting.service.HallLinkUserFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.SessionUtil;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName HallLinkUserController
 * @Description TODO()
 * @Author Zj.Qu
 * @Date 2017-01-11 16:36:00
 */
@Controller
@RequestMapping("/hallLinkUser")
public class HallLinkUserController {

	@Autowired
	private HallLinkUserFacade hallLinkUserFacade;
	
	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	
	@Value("${fastdfsurl}")
	private String fastDFSurl;
	
	@RequestMapping("/toFansList")
	public String toFansList() {
		//跳转到粉丝列表
		return "meeting/fans-list";
	}
	//手机端用户关注的会议
	@RequestMapping("/followmeeting/{userId}")
	public String userFollowMeeting(@PathVariable("userId") String userId,HttpServletRequest request) {
		UserWeixinInfo user = userWeixinInfoFacade.selectByUserId(userId);
		request.getSession().setAttribute("user", user);
		return "mine/person-focusMeet";
	}
	//手机端用户关注的会议厅
	@RequestMapping("/followhall/{userId}")
	public String userFollowHall(@PathVariable("userId") String userId,HttpServletRequest request) {
		UserWeixinInfo user = userWeixinInfoFacade.selectByUserId(userId);
		request.getSession().setAttribute("user", user);
		return "mine/person-follow-hall";
	}
	
	@RequestMapping(value = "/follow/{hallId}", method = RequestMethod.POST)
	@ResponseBody
	public String followHall(@PathVariable Integer hallId, HttpServletRequest request) {
		String userId = SessionUtil.getSessionUserId(request);
		Integer result = hallLinkUserFacade.followHall(hallId, userId);
		return result == 1 ? "true" : "false";
	}

	@RequestMapping(value = "/follow/{hallId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String unfollowHall(@PathVariable Integer hallId, HttpServletRequest request) {
		String userId = SessionUtil.getSessionUserId(request);
		Integer result = hallLinkUserFacade.unfollowHall(hallId, userId);
		return result == 1 ? "true" : "false";
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public int countUserIdByHallId(HttpServletRequest request) {
		int sessionHallId = SessionUtil.getSessionHallId(request);
		return hallLinkUserFacade.countUserIdByHallId(sessionHallId);
	}

	@RequestMapping(value = "/users/{page}/{rows}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ListUserIdByHallId(@PathVariable("page") Integer page,
			@PathVariable("rows") Integer rows, HttpServletRequest request) {
		Map<String, Object> map= new HashMap<String, Object>();
		int sessionHallId = SessionUtil.getSessionHallId(request);
		PageInfo<HallLinkUser> pageInfo = hallLinkUserFacade.ListUserIdByHallId(page, rows, sessionHallId);
		map.put("pageInfo", pageInfo);
		map.put("fastDFSurl", fastDFSurl);
		return map;
	}

	@RequestMapping(value = "/halls/{page}/{rows}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ListHallByUserId(@PathVariable("page") Integer page,
			@PathVariable("rows") Integer rows, HttpServletRequest request) {
		Map<String, Object> map= new HashMap<String, Object>();
		String sessionUserId =SessionUtil.getSessionUserId(request);
		PageInfo<HallLinkUser> pageInfo = hallLinkUserFacade.ListHallByUserId(page, rows, sessionUserId);
		map.put("pageInfo", pageInfo);
		map.put("fastDFSurl", fastDFSurl);
		return map;
	}
	
}
