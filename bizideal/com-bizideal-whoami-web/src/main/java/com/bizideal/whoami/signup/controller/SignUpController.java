package com.bizideal.whoami.signup.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.facade.hotel.entity.HotelInfo;
import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;
import com.bizideal.whoami.facade.hotel.facade.HotelInfoFacade;
import com.bizideal.whoami.facade.hotel.facade.HotelUserLinkFacade;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingHallFacade;
import com.bizideal.whoami.facade.meeting.service.MeetingInfoFacade;
import com.bizideal.whoami.message.entity.TransactionMessage;
import com.bizideal.whoami.message.service.TransactionMessageService;
import com.bizideal.whoami.mqnotify.entity.MqMessage;
import com.bizideal.whoami.mqnotify.facade.MqMessageFacade;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.Dto.RoleMemberDto;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.signup.entity.DietInfo;
import com.bizideal.whoami.signup.entity.SignUpInfo;
import com.bizideal.whoami.signup.entity.SignupFieldMeetingLink;
import com.bizideal.whoami.signup.facade.DietInfoFacade;
import com.bizideal.whoami.signup.facade.SignUpInfoFacade;
import com.bizideal.whoami.signup.facade.SignupFieldFacade;
import com.bizideal.whoami.user.dto.SignUpMeetingDto;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.user.result.ResultStatus;
import com.bizideal.whoami.utils.MQConfigUtil;
import com.bizideal.whoami.utils.StringUtil;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName UserSignUpController
 * @Description 用户报名
 * @Author li.peng
 * @Date 2016-12-01 17:13:39
 */
@Controller
@RequestMapping(value = "/signup")
public class SignUpController {

	private Logger logger = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private MqMessageFacade mqMessageFacade;
	@Autowired
	private RoleModuleReadFacade roleModuleReadFacade;
	@Autowired
	private SignUpInfoFacade signUpInfoFacade;
	@Autowired
	private MeetingInfoFacade meetingInfoFacade;
	@Autowired
	private MeetingHallFacade meetingHallFacade;
	@Autowired
	private DietInfoFacade dietInfoFacade;
	@Autowired
	private HotelInfoFacade hotelInfoFacade;
	@Autowired
	private HotelUserLinkFacade hotelUserLinkFacade;
	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	@Autowired
	private SignupFieldFacade signupFieldFacade;
	@Autowired
	private TransactionMessageService transactionMessageService;

	/**
	 * 通用页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{jsp}")
	public String home(@PathVariable String jsp) {
		return "meeting/" + jsp;
	}

	/**
	 * 用户报名,使用可靠消息
	 * 
	 * @param userSignUpInfoDto
	 * @return
	 */
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	@ResponseBody
	public DubboxResult userSignUpMessage(@RequestBody SignUpInfoDto signUpInfoDto1, HttpServletRequest request) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
			MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
			signUpInfoDto1.setUserId(user.getUserId());
			signUpInfoDto1.setMeetingId(meetingInfo.getMeeId());
			signUpInfoDto1.setMeethallId(meetingInfo.getHallId());
			signUpInfoDto1.setCreateDatatime(System.currentTimeMillis());

			// 判断是否需要审核
			boolean isPend = false;// 1需要审核，0不审核
			if (StringUtils.isNotBlank(meetingInfo.getIsPend()) && "1".equals(meetingInfo.getIsPend())) {
				isPend = true;
			}

			// 构造需要发送的消息
			String signUpId = StringUtil.get32UUID(); // 生成报名主业务的主键id
			List<TransactionMessage> messages = new ArrayList<TransactionMessage>();
			List<String> messageIds = new ArrayList<String>();
			// 生成消息id
			String messageId1 = StringUtil.get32UUID();
			String messageId2 = StringUtil.get32UUID();
			String messageId3 = StringUtil.get32UUID();
			// 判断用户有没有选择酒店，如果没有选择酒店，则不向酒店报名队列中发送消息
			if (null != signUpInfoDto1.getHotelId() && null != signUpInfoDto1.getRoomId()) {
				messageIds.add(messageId1);
			}
			messageIds.add(messageId2);
			messageIds.add(messageId3);
			// 保存消息id
			String tempMessageIds = StringUtils.join(messageIds, ",");

			if (!isPend) {
				// 不审核
				signUpInfoDto1.setIsPend("1");
			} else {
				// 需要审核
				signUpInfoDto1.setIsPend("0");
			}

			// 生成三个dto,分别保存各自消息id,其它属性相同,监听消费成功后删除消息用
			SignUpInfoDto signUpInfoDto2 = new SignUpInfoDto();
			BeanUtils.copyProperties(signUpInfoDto1, signUpInfoDto2);
			SignUpInfoDto signUpInfoDto3 = new SignUpInfoDto();
			BeanUtils.copyProperties(signUpInfoDto1, signUpInfoDto3);
			signUpInfoDto1.setMessageId(messageId1);
			signUpInfoDto2.setMessageId(messageId2);
			signUpInfoDto3.setMessageId(messageId3);

			String messageBody1 = objectMapper.writeValueAsString(signUpInfoDto1);
			String messageBody2 = objectMapper.writeValueAsString(signUpInfoDto2);
			String messageBody3 = objectMapper.writeValueAsString(signUpInfoDto3);
			TransactionMessage message1 = new TransactionMessage(messageId1, messageBody1, MQConfigUtil.readConfig("HOTEL"));
			TransactionMessage message2 = new TransactionMessage(messageId2, messageBody2, MQConfigUtil.readConfig("USER"));
			TransactionMessage message3 = new TransactionMessage(messageId3, messageBody3, MQConfigUtil.readConfig("MEETING"));
			message1.setField1(signUpId); // 消息预留字段中保存主业务id，作消费时验证
			message2.setField1(signUpId);
			message3.setField1(signUpId);
			// 判断用户有没有选择酒店，如果没有选择酒店，则不向酒店报名队列中发送消息
			if (null != signUpInfoDto1.getHotelId() && null != signUpInfoDto1.getRoomId()) {
				messages.add(message1);
			}
			messages.add(message2);
			messages.add(message3);

			// 批量预存储消息
			int saveMessageWaitingConfirm = transactionMessageService.saveMessageWaitingConfirm(messages);
			if (saveMessageWaitingConfirm == 0) {
				// 消息预存储失败
				return DubboxResult.build("6400", "报名失败", null);
			}

			// 调用报名主业务逻辑
			signUpInfoDto1.setSignUpId(signUpId);
			signUpInfoDto1.setMessageIds(tempMessageIds);
			int result = signUpInfoFacade.userSignUp(signUpInfoDto1);
			if (result == 0) {
				// 主业务逻辑失败,删除消息
				transactionMessageService.deleteMessageByMessageId(messageIds);
				return DubboxResult.build("6400", "报名失败", null);
			}

			if (!isPend) {
				// 不审核,确认并发送
				transactionMessageService.confirmAndSendMessage(messageIds);
				request.getSession().setAttribute("signup_status", "0");
				return DubboxResult.build("0", "报名成功", null);
			} else {
				// 审核,不发送消息，等待管理员审核通过
				request.getSession().setAttribute("signup_status", "1");
				return DubboxResult.build("0", "报名成功", null);
			}

		} catch (Exception e) {
			logger.error("用户报名异常" + e.toString());
			return DubboxResult.build("6401", "服务器异常", null);
		}
	}

	/**
	 * 用户报名,向消息队列中发送报名消息
	 * 
	 * @param userSignUpInfoDto
	 * @return
	 */
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userSignUp(@RequestBody SignUpInfoDto signUpInfoDto, HttpServletRequest request) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
			MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
			signUpInfoDto.setSignUpId(StringUtil.get32UUID()); // 主键改成VARCHAR
			signUpInfoDto.setUserId(user.getUserId());
			signUpInfoDto.setMeetingId(meetingInfo.getMeeId());
			signUpInfoDto.setMeethallId(meetingInfo.getHallId());
			signUpInfoDto.setCreateDatatime(System.currentTimeMillis());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("SignUpInfo", signUpInfoDto);
			map.put("hotelInfo", signUpInfoDto);
			map.put("UserInfo", signUpInfoDto);
			map.put("MeetingGroup", signUpInfoDto);
			MqMessage message = new MqMessage();
			String userSignUp = objectMapper.writeValueAsString(map);
			message.setMessage(userSignUp);
			// 调用 mq dubbo接口向队列中发送消息
			DubboxResult notifyMessage = mqMessageFacade.notifyMessage(message);
			if (null == notifyMessage || !"200".equals(notifyMessage.getStatus())) {
				// 发送失败
				return ResultStatus.signUpFail();
			} else {
				// 如果接口响应200,消息发送成功
				return ResultStatus.signUpSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultStatus.serviceError();
		}
	}

	/* 管理员点击报名模块 */
	@RequestMapping("/toSignUpManage")
	public ModelAndView toSignUpManagePage(ModelAndView modelAndView, HttpServletRequest request) {
		modelAndView.setViewName("admin-signUp/admin-signUp-func");
		return modelAndView;
	}

	/* 根据姓名/性别/手机号搜索报名人员 */
	/*
	 * @RequestMapping("/search") public ModelAndView search(SignUpMeetingDto
	 * signUpMeetingDto, ModelAndView modelAndView, HttpServletRequest request)
	 * { MeetingInfo meetingInfo = (MeetingInfo)
	 * request.getSession().getAttribute("click_meeting");
	 * signUpMeetingDto.setMeeId(meetingInfo.getMeeId());
	 * signUpMeetingDto.setPageNum(1); signUpMeetingDto.setPageSize(1000);
	 * PageInfo<UserWeixinInfo> selectBySignUp =
	 * userWeixinInfoFacade.selectBySignUp(signUpMeetingDto);
	 * modelAndView.addObject("signUpInfos", selectBySignUp);
	 * modelAndView.addObject("searchValue", signUpMeetingDto.getName());
	 * modelAndView.setViewName("admin-signUp/admin-user-list"); return
	 * modelAndView; }
	 */

	/* 报名人员列表,滑动加载 */
	@RequestMapping("/list/search")
	@ResponseBody
	public PageInfo<UserWeixinInfo> signUpList(@RequestBody SignUpMeetingDto signUpMeetingDto, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		signUpMeetingDto.setMeeId(meetingInfo.getMeeId());
		signUpMeetingDto.setPageSize(10);
		PageInfo<UserWeixinInfo> selectBySignUp = userWeixinInfoFacade.selectBySignUp(signUpMeetingDto);
		return selectBySignUp;
	}

	/* 跳转报名人员列表页面 */
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 判断是否需要审核
		boolean isPend = false;// 1需要审核，0不审核
		if (StringUtils.isNotBlank(meetingInfo.getIsPend()) && "1".equals(meetingInfo.getIsPend())) {
			isPend = true;
		}
		modelAndView.addObject("isPend", isPend);
		modelAndView.setViewName("admin-signUp/admin-user-list");
		return modelAndView;
	}

	/* 跳转报名详情页 */
	@RequestMapping("/toedit/{userId}")
	public ModelAndView toedit(@PathVariable("userId") String userId, ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 判断是否需要审核
		boolean isPend = false;// 1需要审核，0不审核
		if (StringUtils.isNotBlank(meetingInfo.getIsPend()) && "1".equals(meetingInfo.getIsPend())) {
			isPend = true;
		}
		// 个人基本信息
		UserWeixinInfo weixinInfo = userWeixinInfoFacade.selectByUserId(userId);
		// 查询饮食信息,每个会议都有
		DietInfo dietInfo = new DietInfo();
		dietInfo.setHallId(meetingInfo.getHallId().intValue());
		dietInfo.setMeeId(meetingInfo.getMeeId().intValue());
		dietInfo.setPageNum(1);
		dietInfo.setPageSize(1000);
		JSONArray diets = dietInfoFacade.selectByPage(dietInfo).getJSONArray("rows");
		// 报名信息
		SignUpInfo signUpInfo = new SignUpInfo();
		signUpInfo.setUserId(userId);
		signUpInfo.setMeetingId(meetingInfo.getMeeId());
		SignUpInfo mySignUpInfo = signUpInfoFacade.selectSignUpInfoByUserIdMeeId(signUpInfo);
		modelAndView.addObject("isPend", isPend);
		modelAndView.addObject("diets", diets);
		modelAndView.addObject("tempUser", weixinInfo);
		modelAndView.addObject("mySignUpInfo", mySignUpInfo);
		modelAndView.setViewName("admin-signUp/admin-user-info");
		return modelAndView;
	}

	/**
	 * 跳转报名页面,查询子会议信息，或报名回显
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tosignuppage")
	public ModelAndView tosignUpPage(ModelAndView modelAndView, HttpServletRequest request) {
		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");

		// 查询该主会议选择了哪几个报名字段
		SignupFieldMeetingLink hasChoosedFieldLink = signupFieldFacade.selectSignupFieldMeetingLinkByMeeId(meetingInfo.getMeeId());
		String fieldIds = hasChoosedFieldLink.getFieldIds();
		String[] fieldIds1 = StringUtils.split(fieldIds, ",");
		List<Integer> hasChoosedFieldIds = new ArrayList<Integer>();
		for (String string : fieldIds1) {
			hasChoosedFieldIds.add(Integer.valueOf(string));
		}

		// 判断是否可以报名
		boolean flag_startSignUp = false; // 报名是否已经开始
		boolean flag_endSignUp = false; // 报名是否已经结束
		Long signupStartTime = meetingInfo.getSignupStartTime();
		Long signupEndTime = meetingInfo.getSignupEndTime();
		if (null == signupStartTime || signupStartTime < System.currentTimeMillis()) {
			flag_startSignUp = true;
		}
		if (null != signupEndTime && signupEndTime < System.currentTimeMillis()) {
			flag_endSignUp = true;
		}

		// 判断有没有报过名
		boolean flag_hasSigned = false;
		SignUpInfo signUpInfo = new SignUpInfo();
		signUpInfo.setUserId(user.getUserId());
		signUpInfo.setMeetingId(meetingInfo.getMeeId());
		SignUpInfo mySignUpInfo = signUpInfoFacade.selectSignUpInfoByUserIdMeeId(signUpInfo);

		// 报名如果未通过，进入审核中页面
		if (null != mySignUpInfo && "0".equals(mySignUpInfo.getIsPend())) {
			modelAndView.addObject("status", "1");
			modelAndView.setViewName("user/sign-up-success");
			return modelAndView;
		}

		HotelUserLink myHotelLink = null;
		if (null != mySignUpInfo) {
			// 已经报名,查询报名信息
			flag_hasSigned = true;
			// 查询酒店报名信息
			Map<String, Object> myHotelMap = hotelUserLinkFacade.selectByMeetingIdAndUserId(meetingInfo.getMeeId(), user.getUserId());
			if (null != myHotelMap && myHotelMap.containsKey("entity")) {
				// 选择了酒店
				myHotelLink = (HotelUserLink) myHotelMap.get("entity");
			} else {
				myHotelLink = new HotelUserLink();
			}
		} else {
			mySignUpInfo = new SignUpInfo();
			myHotelLink = new HotelUserLink();
		}
		// 查询所有的子会议
		SubMeetingInfo m = new SubMeetingInfo();
		m.setMeeParentId(meetingInfo.getMeeId());
		Map<String, Object> selectSubMeetingInfo = meetingInfoFacade.selectSubMeetingInfo(m);
		// 查询饮食信息,每个会议都有
		DietInfo dietInfo = new DietInfo();
		dietInfo.setHallId(meetingInfo.getHallId().intValue());
		dietInfo.setMeeId(meetingInfo.getMeeId().intValue());
		dietInfo.setPageNum(1);
		dietInfo.setPageSize(1000);
		JSONArray diets = dietInfoFacade.selectByPage(dietInfo).getJSONArray("rows");
		boolean flag_sex = true;// true表示已经正确填写了性别
		boolean flag_realName = true;// true表示已经正确填写了真实姓名
		String sex = user.getSex();
		if (!"男".equals(sex) && !"女".equals(sex)) {
			flag_sex = false;
		}
		String realName = user.getRealName();
		if (StringUtils.isBlank(realName)) {
			flag_realName = false;
		}
		// 查询所有子会议
		if (null == selectSubMeetingInfo) {
			modelAndView.addObject("subMeetingInfos", null);
		} else {
			modelAndView.addObject("subMeetingInfos", selectSubMeetingInfo.get("list"));
		}
		// 查询所有的酒店
		Map<String, Object> map = hotelInfoFacade.hotelListQuery(meetingInfo.getMeeId());
		List<HotelInfo> list = (List<HotelInfo>) map.get("hotelListQuery");
		modelAndView.addObject("flag_startSignUp", flag_startSignUp);
		modelAndView.addObject("flag_endSignUp", flag_endSignUp);
		modelAndView.addObject("hotelList", list);
		modelAndView.addObject("hasChoosedFieldIds", hasChoosedFieldIds);
		modelAndView.addObject("myHotelLink", myHotelLink);
		modelAndView.addObject("mySignUpInfo", mySignUpInfo);
		modelAndView.addObject("flag_hasSigned", flag_hasSigned);
		modelAndView.addObject("flag_sex", flag_sex);
		modelAndView.addObject("flag_realName", flag_realName);
		modelAndView.addObject("diets", diets);
		modelAndView.addObject("tempUser", user);
		modelAndView.setViewName("user/sign-up");
		return modelAndView;
	}

	/* 跳转报名成功页面 */
	@RequestMapping(value = "/success")
	public ModelAndView toSuccess(ModelAndView modelAndView, HttpServletRequest request) {
		String status = (String) request.getSession().getAttribute("signup_status");
		request.getSession().removeAttribute("signup_status");
		modelAndView.addObject("status", status);
		modelAndView.setViewName("user/sign-up-success");
		return modelAndView;
	}

	/* 管理员通过报名审核 */
	@RequestMapping("/signUpPass")
	@ResponseBody
	public DubboxResult signUpPass(@RequestBody SignUpInfo signUpInfo, HttpServletRequest request) {
		SignUpInfo s = signUpInfoFacade.selectBySignUpId(signUpInfo.getId());
		String messageIds = s.getMessageIds();
		if (StringUtils.isBlank(messageIds)) {
			// 防止消息为空
			return DubboxResult.build("1", "操作失败", null);
		}
		// 在数据库中通过审核
		int result = signUpInfoFacade.updatePendById(s.getId(), "1", "");
		if (result == 0) {
			return DubboxResult.build("1", "操作失败", null);
		}
		List<String> list = new ArrayList<String>(Arrays.asList(StringUtils.split(messageIds, ",")));
		// 确认并发送消息
		transactionMessageService.confirmAndSendMessage(list);
		return DubboxResult.build("0", "操作成功", null);
	}

	/* 管理员审核不通过 */
	@RequestMapping("/signUpReject")
	@ResponseBody
	public DubboxResult signUpReject(@RequestBody SignUpInfo signUpInfo, HttpServletRequest request) {
		SignUpInfo s = signUpInfoFacade.selectBySignUpId(signUpInfo.getId());
		// 在数据库中拒绝审核,不删除数据
		int result = signUpInfoFacade.updatePendById(s.getId(), "2", signUpInfo.getRejectReason());
		if (result == 0) {
			return DubboxResult.build("1", "操作失败", null);
		}
		return DubboxResult.build("0", "操作成功", null);
	}

	/* 查询该单位应缴费用和最大可报名人数和已经报名人数 */
	@RequestMapping("/selectCountAndPay/{typeId}/{unitId}")
	@ResponseBody
	public RoleMemberDto selectCountAndPay(@PathVariable("typeId") Integer typeId, @PathVariable("unitId") Integer unitId, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 查询该单位应缴费用和最大可报名人数
		RoleMemberDto dto = roleModuleReadFacade.getRoleModuleBymember_type_id(meetingInfo.getMeeId(), typeId, 0);
		// 查询单位下已经报名人数
		int count = signUpInfoFacade.selectCountByUnitIdMeeId(meetingInfo.getMeeId(), unitId);
		dto.setRoleId(count);
		return dto;
	}

	public static void main(String[] args) throws ParseException {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(sdf.parse("2017-03-22 08:10:10").getTime());
	}
}
