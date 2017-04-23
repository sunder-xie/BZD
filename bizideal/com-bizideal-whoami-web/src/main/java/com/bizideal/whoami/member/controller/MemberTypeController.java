package com.bizideal.whoami.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.member.entity.MemberTypeInfo;
import com.bizideal.whoami.member.facade.MemberTypeInfoFacade;

/**
 * @author 作者 sy
 * @data 创建时间：2017-3-21 09:29:23
 * @version 1.0
 */
@Controller
@RequestMapping("/member/type")
public class MemberTypeController {

	@Autowired
	private MemberTypeInfoFacade memberTypeInfoFacade;

	/**
	 * 会员角色列表页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String typeList(HttpServletRequest request, Model model, Integer addType) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");

		List<MemberTypeInfo> list = memberTypeInfoFacade.selectListByHallId(meetingHall.getHallId());
		model.addAttribute("list", list);
		if (null == addType) {
			model.addAttribute("addType", 0);
		} else {
			model.addAttribute("addType", addType);
		}
		return "member/member-role-list";
	}

	/**
	 * 跳转会员角色添加页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request, Model model) {

		return "member/member-role-creat";
	}

	/**
	 * 跳转会员角色修改页面
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, Model model, Integer id) {

		MemberTypeInfo info = memberTypeInfoFacade.selectById(id);
		model.addAttribute("type", info);
		return "member/member-role-edit";
	}

	/**
	 * ajax条件会员角色
	 * 
	 * @param request
	 * @param name
	 * @param type
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody Integer addType(HttpServletRequest request, String name, Integer type) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		MemberTypeInfo t = new MemberTypeInfo();
		t.setHallId(meetingHall.getHallId());
		t.setName(name);
		t.setType(type);
		return memberTypeInfoFacade.save(t);
	}

	/**
	 * ajax修改会员角色
	 * 
	 * @param request
	 * @param t
	 * @return
	 */
	@RequestMapping("/edit")
	public @ResponseBody Integer editType(HttpServletRequest request, MemberTypeInfo t) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		t.setHallId(meetingHall.getHallId());
		return memberTypeInfoFacade.update(t);
	}

	/**
	 * ajax删除会员角色
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Integer deleteType(HttpServletRequest request, Integer id) {

		return memberTypeInfoFacade.deleteById(id);
	}

	/**
	 * ajax验证同类型会员角色是否重名
	 * 
	 * @param request
	 * @param name
	 * @param type
	 * @return
	 */
	@RequestMapping("/verify")
	public @ResponseBody Integer verify(HttpServletRequest request, String name, Integer type) {
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		MemberTypeInfo info = memberTypeInfoFacade.selectByNameAndType(meetingHall.getHallId(), name, type);
		if (null == info) {
			return 0;
		} else {
			return 1;
		}
	}
}
