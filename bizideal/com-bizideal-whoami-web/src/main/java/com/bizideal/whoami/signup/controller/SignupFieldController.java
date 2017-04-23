package com.bizideal.whoami.signup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingInfoFacade;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.entity.Module;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.signup.entity.Identity;
import com.bizideal.whoami.signup.entity.SignupField;
import com.bizideal.whoami.signup.entity.SignupFieldMeetingLink;
import com.bizideal.whoami.signup.facade.IdentityFacade;
import com.bizideal.whoami.signup.facade.SignUpInfoFacade;
import com.bizideal.whoami.signup.facade.SignupFieldFacade;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月28日 上午9:50:26
 * @version 1.0
 */
@Controller
@RequestMapping("/signupField")
public class SignupFieldController {

	@Autowired
	private SignupFieldFacade signupFieldFacade;
	@Autowired
	private RoleModuleReadFacade roleModuleReadFacade;
	@Autowired
	private MeetingInfoFacade meetingInfoFacade;
	@Autowired
	private IdentityFacade identityFacade;
	@Autowired
	private SignUpInfoFacade signUpInfoFacade;

	/* 跳转身份列表 */
	@RequestMapping("/identityList")
	public ModelAndView identityList(ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Identity identity = new Identity();
		identity.setMeeId(meetingInfo.getMeeId());
		identity.setPageNum(1);
		identity.setPageSize(1000);
		PageInfo<Identity> byPage = identityFacade.getByPage(identity);
		modelAndView.addObject("identityList", byPage);
		modelAndView.setViewName("admin-signUp/admin-identity-list");
		return modelAndView;
	}

	/* ajax获取身份列表 */
	@RequestMapping("/identityListJson")
	@ResponseBody
	public PageInfo<Identity> identityListJson(HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Identity identity = new Identity();
		identity.setMeeId(meetingInfo.getMeeId());
		identity.setPageNum(1);
		identity.setPageSize(1000);
		PageInfo<Identity> byPage = identityFacade.getByPage(identity);
		return byPage;
	}

	/* 单个身份添加 */
	@RequestMapping("/addIdentity")
	@ResponseBody
	public DubboxResult addIdentity(@RequestBody Identity identity, ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		identity.setMeeId(meetingInfo.getMeeId());
		int byPage = identityFacade.insert(identity);
		if (byPage == 0) {
			return DubboxResult.build("1", "添加失败", null);
		}
		return DubboxResult.build("0", "添加成功", null);
	}

	/* 单个身份删除 */
	@RequestMapping("/delIdentity/{id}")
	@ResponseBody
	public DubboxResult delIdentity(@PathVariable("id") Integer id, ModelAndView modelAndView, HttpServletRequest request) {
		int byPage = identityFacade.deleteById(id);
		if (byPage == 0) {
			return DubboxResult.build("1", "删除失败", null);
		}
		return DubboxResult.build("0", "删除成功", null);
	}

	@RequestMapping("/updateIdentity")
	@ResponseBody
	public DubboxResult updateIdentity(@RequestBody Identity identity, ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		identity.setMeeId(meetingInfo.getMeeId());
		int byPage = identityFacade.updateById(identity);
		if (byPage == 0) {
			return DubboxResult.build("1", "更新失败", null);
		}
		return DubboxResult.build("0", "更新成功", null);
	}

	/* 跳转到报名字段编辑页面 */
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 查询该主会议选择了哪几个报名字段
		SignupFieldMeetingLink hasChoosedFieldLink = signupFieldFacade.selectSignupFieldMeetingLinkByMeeId(meetingInfo.getMeeId());
		String fieldIds = hasChoosedFieldLink.getFieldIds();
		String[] fieldIds1 = StringUtils.split(fieldIds, ",");
		List<Integer> hasChoosedFieldIds = new ArrayList<Integer>();
		for (String string : fieldIds1) {
			hasChoosedFieldIds.add(Integer.valueOf(string));
		}
		modelAndView.addObject("hasChoosedFieldIds", hasChoosedFieldIds);
		modelAndView.setViewName("admin-signUp/admin-signUpField-list");
		return modelAndView;
	}

	/* 跳转到报名字段编辑页面 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toedit")
	public ModelAndView toedit(ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 查询所有的子会议
		SubMeetingInfo m = new SubMeetingInfo();
		m.setMeeParentId(meetingInfo.getMeeId());
		Map<String, Object> selectSubMeetingInfo = meetingInfoFacade.selectSubMeetingInfo(m);
		// 查询会议厅下选择的所有模块
		List<Module> modules = roleModuleReadFacade.selectHallModules(meetingInfo.getMeeId());
		List<Integer> moduleIds = new ArrayList<Integer>();
		for (Module module : modules) {
			moduleIds.add(module.getModuleId());
		}
		// 判断有没有子会议，来确定要不要加上分会议选择
		if (null != selectSubMeetingInfo && null != selectSubMeetingInfo.get("list")) {
			List<SubMeetingInfo> subMeetingInfos = (List<SubMeetingInfo>) selectSubMeetingInfo.get("list");
			if (null != subMeetingInfos && subMeetingInfos.size() > 0) {
				// 999是字段和模块关联表中的数据
				moduleIds.add(999);
			}
		}
		// 查询可编辑报名字段，根据会议选择的模块确定
		List<SignupField> allFields = signupFieldFacade.selectSignupFieldsByModuids(moduleIds);
		// 查询会议已经选择了哪些字段
		SignupFieldMeetingLink hasChoosedFieldLink = signupFieldFacade.selectSignupFieldMeetingLinkByMeeId(meetingInfo.getMeeId());
		String fieldIds = hasChoosedFieldLink.getFieldIds();
		String[] fieldIds1 = StringUtils.split(fieldIds, ",");
		List<Integer> hasChoosedFieldIds = new ArrayList<Integer>();
		for (String string : fieldIds1) {
			hasChoosedFieldIds.add(Integer.valueOf(string));
		}
		List<SignupField> hasChoosedFields = new ArrayList<SignupField>();
		List<SignupField> notChoosedFields = new ArrayList<SignupField>();
		for (SignupField s : allFields) {
			if (hasChoosedFieldIds.contains(s.getFieldId())) {
				// 已经选择
				hasChoosedFields.add(s);
			} else {
				// 未选择
				notChoosedFields.add(s);
			}
		}
		modelAndView.addObject("hasChoosedFields", hasChoosedFields);
		modelAndView.addObject("notChoosedFields", notChoosedFields);
		modelAndView.setViewName("admin-signUp/admin-edit-signUp");
		return modelAndView;
	}

	/* 查询会议下报名字段 */
	@RequestMapping("/update")
	@ResponseBody
	public DubboxResult update(@RequestBody SignupFieldMeetingLink link, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		link.setMeeId(meetingInfo.getMeeId());
		//判断会议下有没有人进行报名，有，不让修改
		Integer couInteger = signUpInfoFacade.selectCountByMeeId(meetingInfo.getMeeId(), null);
		if (couInteger > 0) {
			return DubboxResult.build("2", "已经有用户报名，无法进行修改操作!", null);
		}
		return signupFieldFacade.updateField(link);
	}
}
