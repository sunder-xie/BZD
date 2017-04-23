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
import com.bizideal.whoami.member.entity.MemberPersonInfo;
import com.bizideal.whoami.member.entity.MemberTypeInfo;
import com.bizideal.whoami.member.facade.MemberPersonInfoFacade;
import com.bizideal.whoami.member.facade.MemberTypeInfoFacade;
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
@RequestMapping("/member/person")
public class MemberPersonController {

	@Autowired
	private MemberPersonInfoFacade memberPersonInfoFacade;
	@Autowired
	private MemberTypeInfoFacade memberTypeInfoFacade;
	@Value("${personalUpload}")
	private String personalUpload;

	/**
	 * 个人会员列表页面
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

		PageInfo<MemberPersonInfo> pageInfo = null;
		if (null == search || "".equals(search)) {
			pageInfo = memberPersonInfoFacade.selectListByHallId(meetingHall.getHallId(), 1, 1000);
		} else {
			search = new String(search.getBytes("iso8859-1"), "UTF8");
			pageInfo = memberPersonInfoFacade.selectListByHallIdAndString(meetingHall.getHallId(), search, 1, 1000);
			model.addAttribute("search", search);
		}
		model.addAttribute("list", pageInfo.getList());
		return "member/member-person-list";
	}

	/**
	 * 跳转添加个人会员页面
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
		return "member/member-person-add";
	}

	/**
	 * 跳转修改个人会员页面
	 * 
	 * @param request
	 * @param model
	 * @param phone
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, Model model, String phone) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		List<MemberTypeInfo> typeList = memberTypeInfoFacade.selectListByHallId(meetingHall.getHallId());
		model.addAttribute("typeList", typeList);
		MemberPersonInfo unitInfo = memberPersonInfoFacade.selectByPhoneAndHallId(phone, meetingHall.getHallId());
		MemberTypeInfo selectById = memberTypeInfoFacade.selectById(unitInfo.getMemberTypeId());
		unitInfo.setTypeName(selectById.getName());
		model.addAttribute("personMember", unitInfo);
		return "member/member-person-edit";
	}

	/**
	 * ajax验证个人电话是否重复
	 * 
	 * @param request
	 * @param phone
	 * @param model
	 * @return
	 */
	@RequestMapping("/verifyPhone")
	public @ResponseBody int verifyPhone(HttpServletRequest request, String phone, Model model) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		MemberPersonInfo info = memberPersonInfoFacade.selectByPhoneAndHallId(phone, meetingHall.getHallId());
		if (null == info) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * ajax添加个人会员
	 * 
	 * @param request
	 * @param t
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody Integer addMemberUnit(HttpServletRequest request, MemberPersonInfo t) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		t.setHallId(meetingHall.getHallId());
		return memberPersonInfoFacade.save(t);
	}

	/**
	 * ajax修改个人会员
	 * 
	 * @param request
	 * @param t
	 * @return
	 */
	@RequestMapping("/edit")
	public @ResponseBody Integer editMemberUnit(HttpServletRequest request, MemberPersonInfo t) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		t.setHallId(meetingHall.getHallId());
		return memberPersonInfoFacade.update(t);
	}

	/**
	 * ajax删除个人会员
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Integer deleteMemberUnit(HttpServletRequest request, Integer id) {

		return memberPersonInfoFacade.deleteById(id);
	}

	/**
	 * 跳转上传个人会员页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toExcelUpload")
	public String toExcel(HttpServletRequest request, Model model) {
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		model.addAttribute("hallId", meetingHall.getHallId());
		model.addAttribute("personalUpload", personalUpload);
		return "member/member-person-upload";
	}

	/**
	 * ajax上传个人会员
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
			List<MemberPersonInfo> memberPersonInfos = new ExcelUtils<MemberPersonInfo>().readBean(inputStream,
					MemberPersonInfo.class, map);
			for (int index = 0; index < memberPersonInfos.size(); index++) {
				MemberPersonInfo person = memberPersonInfos.get(index);
				person.setHallId(meetingHall.getHallId());
				// 做手机号码和角色编号检查
				if (StringUtils.isBlank(person.getPersonName())) {
					node.put("errcode", 1);
					node.put("errmsg", "第" + (index + 1) + "行的个人名称为必填项");
					return node;
				}
				if (StringUtils.isBlank(person.getPhone())) {
					node.put("errcode", 1);
					node.put("errmsg", "第" + (index + 1) + "行的个人电话为必填项");
					return node;
				}
				if (StringUtils.isBlank(person.getTypeName())) {
					node.put("errcode", 1);
					node.put("errmsg", "第" + (index + 1) + "行的会员角色为必填项");
					return node;
				} else {
					int flag = 0;
					for (MemberTypeInfo type : typeInfos) {
						if (type.getName().equals(person.getTypeName())) {
							flag = 1;
							person.setMemberTypeId(type.getId());
						}
					}
					if (flag == 0) {
						node.put("errcode", 1);
						node.put("errmsg", "第" + (index + 1) + "行的角色非本会议厅下");
						return node;
					}
				}

				MemberPersonInfo personInfo = memberPersonInfoFacade.selectByPhoneAndHallId(person.getPhone(),
						meetingHall.getHallId());
				if (null != personInfo) {
					node.put("errcode", 1);
					node.put("errmsg", "第" + (index + 1) + "行的个人电话重复");
					return node;
				}
			}
			Integer insertList = memberPersonInfoFacade.insertList(memberPersonInfos);
			node.put("errcode", 0);
			node.put("errmsg", "ok,成功添加" + insertList + "行个人会员信息");
		} catch (Exception e) {
			node.put("errcode", 1);
			node.put("errmsg", "发生系统异常");
			e.printStackTrace();
		}
		return node;
	}
}
