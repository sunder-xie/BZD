package com.bizideal.whoami.knowledge.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeType;
import com.bizideal.whoami.knowledgebase.facade.KnowledgeTypeFacade;

/**
 * 知识库分类
 * 
 * @author sy
 * @date 2017-3-14 14:16:37
 */
@Controller
@RequestMapping("/knowledgeType")
public class KnowledgeTypeController {

	@Autowired
	private KnowledgeTypeFacade knowledgeTypeFacade;

	/**
	 * 管理员分类列表
	 * 
	 * @param request
	 * @param model
	 * @param parentId父分类id
	 * @return
	 */
	@RequestMapping("/adminList")
	public String adminList(HttpServletRequest request, Model model, Integer parentId) {

		String url;
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 如果父分类不存在
		if (parentId == null) {
			parentId = 0;
			// 跳转管理员主分类页面
			url = "/admin-repository/admin-repository-mainList";
		} else {
			KnowledgeType selectById = knowledgeTypeFacade.selectById(parentId);
			// 根据分类路径判断当前为几层
			String[] split = selectById.getTypeUrl().split("/");
			model.addAttribute("pType", selectById);
			model.addAttribute("typeLength", split.length);
			if ("0".equals(selectById.getIsType())) {
				// 判断当前分类是否未保存资料或者子分类，如果没有子分类时弹出添加分类框
				model.addAttribute("flag", selectById.getIsType());
			}
			// 跳转管理员子分类页面
			url = "/admin-repository/admin-repository-chList";
		}
		List<KnowledgeType> selectByMeetingId = knowledgeTypeFacade.selectByMeetingId(meetingInfo.getMeeId(), parentId);
		model.addAttribute("typeList", selectByMeetingId);
		return url;
	}

	/**
	 * ajax添加分类
	 * 
	 * @param request
	 * @param model
	 * @param type
	 * @return 返回1成功
	 */
	@RequestMapping("/add")
	public @ResponseBody int addType(HttpServletRequest request, Model model, KnowledgeType type) {

		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		type.setMeeId(meetingInfo.getMeeId());
		return knowledgeTypeFacade.insert(type);
	}

	/**
	 * ajax修改分类
	 * 
	 * @param request
	 * @param model
	 * @param type
	 * @return 返回1成功
	 */
	@RequestMapping("/edit")
	public @ResponseBody int editType(HttpServletRequest request, Model model, KnowledgeType type) {

		return knowledgeTypeFacade.update(type);
	}

	/**
	 * ajax删除分类
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return 返回1成功
	 */
	@RequestMapping("/delete")
	public @ResponseBody int deleteType(HttpServletRequest request, Model model, Integer id) {

		return knowledgeTypeFacade.delete(id);
	}

	/**
	 * 分类列表
	 * 
	 * @param request
	 * @param model
	 * @param parentId父分类id
	 * @return
	 */
	@RequestMapping("/list")
	public String tyepList(HttpServletRequest request, Model model, Integer parentId) {

		String url;
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 如果父分类不存在
		if (parentId == null) {
			parentId = 0;
			// 跳转主分类页面
			url = "/repository/repository-main-list";
		} else {
			KnowledgeType selectById = knowledgeTypeFacade.selectById(parentId);
			// 根据分类路径判断当前为几层
			String[] split = selectById.getTypeUrl().split("/");
			model.addAttribute("pType", selectById);
			model.addAttribute("typeLength", split.length);
			// 跳转子分类页面
			url = "/repository/repository-list";
		}
		List<KnowledgeType> selectByMeetingId = knowledgeTypeFacade.selectByMeetingId(meetingInfo.getMeeId(), parentId);
		model.addAttribute("typeList", selectByMeetingId);
		return url;
	}

	/**
	 * ajax查询子分类列表
	 * 
	 * @param request
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/chList")
	public @ResponseBody List<KnowledgeType> chList(HttpServletRequest request, Integer parentId) {

		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		List<KnowledgeType> selectByMeetingId = knowledgeTypeFacade.selectByMeetingId(meetingInfo.getMeeId(), parentId);
		return selectByMeetingId;
	}
}
