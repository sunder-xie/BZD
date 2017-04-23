package com.bizideal.whoami.member.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.member.entity.MemberTypeInfo;
import com.bizideal.whoami.member.entity.MemberUnitInfo;
import com.bizideal.whoami.member.facade.MemberTypeInfoFacade;
import com.bizideal.whoami.member.facade.MemberUnitInfoFacade;
import com.bizideal.whoami.utils.ExcelUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 sy
 * @data 创建时间：2017-3-21 09:29:23
 * @version 1.0
 */
@Controller
@RequestMapping("/member/unit")
public class MemberUnitController {

	@Autowired
	private MemberUnitInfoFacade memberUnitInfoFacade;
	@Autowired
	private MemberTypeInfoFacade memberTypeInfoFacade;
	@Value("${unitUpload}")
	private String unitUpload;

	/**
	 * 组织会员列表页面
	 * 
	 * @param request
	 * @param model
	 * @param search_搜索关键词
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("")
	public String unitList(HttpServletRequest request, Model model, String search) throws UnsupportedEncodingException {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");

		PageInfo<MemberUnitInfo> pageInfo = null;
		if (null == search || "".equals(search)) {
			pageInfo = memberUnitInfoFacade.selectListByHallId(meetingHall.getHallId(), 1, 1000);
		} else {
			search = new String(search.getBytes("iso8859-1"), "UTF8");
			pageInfo = memberUnitInfoFacade.selectListByHallIdAndString(meetingHall.getHallId(), search, 1, 1000);
			model.addAttribute("search", search);
		}
		model.addAttribute("list", pageInfo.getList());
		return "member/member-organize-list";
	}

	/**
	 * 跳转添加组织会员页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request, Model model) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		List<MemberTypeInfo> typeList = memberTypeInfoFacade.selectListByHallId(meetingHall.getHallId());
		model.addAttribute("typeList", typeList);
		return "member/member-organize-add";
	}

	/**
	 * 跳转修改组织会员页面
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, Model model, Integer id) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		List<MemberTypeInfo> typeList = memberTypeInfoFacade.selectListByHallId(meetingHall.getHallId());
		model.addAttribute("typeList", typeList);
		MemberUnitInfo unitInfo = memberUnitInfoFacade.selectById(id);
		MemberTypeInfo selectById = memberTypeInfoFacade.selectById(unitInfo.getMemberTypeId());
		unitInfo.setTypeName(selectById.getName());
		model.addAttribute("unitMember", unitInfo);
		return "member/member-organize-edit";
	}

	/**
	 * ajax验证组织是否重名
	 * 
	 * @param request
	 * @param unitName
	 * @param model
	 * @return
	 */
	@RequestMapping("/verifyUnitName")
	public @ResponseBody int verifyUnitName(HttpServletRequest request, String unitName, Model model) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		MemberUnitInfo info = memberUnitInfoFacade.selectByUnitNameAndHallId(unitName, meetingHall.getHallId());
		if (null == info) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * ajax添加组织会员
	 * 
	 * @param request
	 * @param t
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody Integer addMemberUnit(HttpServletRequest request, MemberUnitInfo t) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		t.setHallId(meetingHall.getHallId());
		return memberUnitInfoFacade.save(t);
	}

	/**
	 * ajax修改组织会员
	 * 
	 * @param request
	 * @param t
	 * @return
	 */
	@RequestMapping("/edit")
	public @ResponseBody Integer editMemberUnit(HttpServletRequest request, MemberUnitInfo t) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		t.setHallId(meetingHall.getHallId());
		return memberUnitInfoFacade.update(t);
	}

	/**
	 * ajax删除组织会员
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Integer deleteMemberUnit(HttpServletRequest request, Integer id) {

		return memberUnitInfoFacade.deleteById(id);
	}

	/**
	 * 跳转组织会员上传页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toExcelUpload")
	public String toExcel(HttpServletRequest request, Model model) {
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		model.addAttribute("hallId", meetingHall.getHallId());
		model.addAttribute("unitUpload", unitUpload);
		return "member/member-organize-upload";
	}

	/**
	 * ajax上传组织会员
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadExcel")
	@ResponseBody
	public ObjectNode upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		try {
			Map<String, String> map = ExcelUtils.getModelMap("member.properties");

			// 拿到当前会议厅下所有的角色
			List<MemberTypeInfo> typeInfos = memberTypeInfoFacade.selectListByHallId(meetingHall.getHallId());
			InputStream inputStream = file.getInputStream();
			List<MemberUnitInfo> memberUnitInfos = new ExcelUtils<MemberUnitInfo>().readBean(inputStream,
					MemberUnitInfo.class, map);
			for (int index = 0; index < memberUnitInfos.size(); index++) {
				MemberUnitInfo unit = memberUnitInfos.get(index);
				unit.setHallId(meetingHall.getHallId());
				// 做手机号码和角色编号检查
				if (StringUtils.isBlank(unit.getUnitName())) {
					node.put("errcode", 1);
					node.put("errmsg", "第" + (index + 1) + "行的组织名称为必填项");
					return node;
				}
				if (StringUtils.isBlank(unit.getTypeName())) {
					node.put("errcode", 1);
					node.put("errmsg", "第" + (index + 1) + "行的会员角色为必填项");
					return node;
				} else {
					int flag = 0;
					for (MemberTypeInfo type : typeInfos) {
						if (type.getName().equals(unit.getTypeName())) {
							flag = 1;
							unit.setMemberTypeId(type.getId());
						}
					}
					if (flag == 0) {
						node.put("errcode", 1);
						node.put("errmsg", "第" + (index + 1) + "行的角色非本会议厅下");
						return node;
					}
				}

				MemberUnitInfo selectByUnitNameAndHallId = memberUnitInfoFacade.selectByUnitNameAndHallId(
						unit.getUnitName(), meetingHall.getHallId());
				if (null != selectByUnitNameAndHallId) {
					node.put("errcode", 1);
					node.put("errmsg", "第" + (index + 1) + "行的组织名称重复");
					return node;
				}
			}
			Integer insertList = memberUnitInfoFacade.insertList(memberUnitInfos);
			node.put("errcode", 0);
			node.put("errmsg", "ok,成功添加" + insertList + "行组织会员信息");
		} catch (Exception e) {
			node.put("errcode", 1);
			node.put("errmsg", "发生系统异常");
			e.printStackTrace();
		}
		return node;
	}

	@RequestMapping("/selectList")
	public @ResponseBody List<MemberUnitInfo> selectListByHallIdAndString(HttpServletRequest request, String str) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		return memberUnitInfoFacade.selectListByMeeId(meetingInfo.getMeeId(), str);
	}
}
