package com.bizideal.whoami.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.facade.hotel.entity.HotelInfo;
import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;
import com.bizideal.whoami.facade.hotel.entity.RoomInfo;
import com.bizideal.whoami.facade.hotel.facade.HotelInfoFacade;
import com.bizideal.whoami.facade.hotel.facade.HotelUserLinkFacade;
import com.bizideal.whoami.facade.hotel.facade.RoomInfoFacade;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.signup.entity.SignUpInfo;
import com.bizideal.whoami.signup.facade.SignUpInfoFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.utils.ExcelUtil;

/**
 * @ClassName MeetingUserRoomController
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2017-01-05 15:01:55
 */
@Controller("meetingUserRoom")
@RequestMapping("/meetingUserRoom")
public class MeetingUserRoomController {

	@Autowired
	private HotelUserLinkFacade hotelUserLinkFacade;
	@Autowired
	private RoomInfoFacade roomInfoFacade;
	@Autowired
	private HotelInfoFacade hotelInfoFacade;
	@Autowired
	SignUpInfoFacade signUpInfoFacade;

	/**
	 * 查询用户酒店入住信息
	 * 
	 * @param meeid
	 * @param mode
	 * @return
	 */
	@RequestMapping("/selectUserRoom/{meeid}")
	public String selectUserRoom(@PathVariable("meeid") Integer meeid, ModelMap mode) {

		Map<String, Object> selectByMeetingId = hotelUserLinkFacade.selectByMeetingId(meeid, null);
		mode.addAttribute("hotelUserLinkList", selectByMeetingId.get("hotelUserLinkList"));
		return "admin-hotel/admin-hotel-userlist";
	}

	/**
	 * 删除用户入住酒店信息
	 * 
	 * @param hUId
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteUserRoom/{hUId}")
	public String deleteUserRoom(@PathVariable("hUId") Integer hUId, HttpServletRequest request) {

		Map<String, Object> deleteFromManager = hotelUserLinkFacade.deleteFromManager(hUId);
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		return "redirect:/meetingUserRoom/selectUserRoom/" + meetingInfo.getMeeId();
	}

	/**
	 * 用户酒店信息回显
	 * 
	 * @param hUId
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectUserRoomInfo/{hUId}")
	public String selectUserRoomInfo(@PathVariable("hUId") Integer hUId, HttpServletRequest request, ModelMap mode) {

		Map<String, Object> selectByHUId = hotelUserLinkFacade.selectByHUId(hUId);
		mode.addAttribute("hotelUserLink", selectByHUId.get("hotelUserLink"));
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Map<String, Object> selectHotelInfoByMeetingId = hotelInfoFacade.hotelListQuery(meetingInfo.getMeeId());
		if (selectHotelInfoByMeetingId != null) {
			mode.addAttribute("hotels", selectHotelInfoByMeetingId.get("hotelListQuery"));
		}
		return "admin-hotel/admin-userhotel-edit";
	}

	/**
	 * 查询房间类型
	 * 
	 * @param hotelid
	 * @return
	 */
	@RequestMapping("/selectRoomtype/{hotelid}")
	@ResponseBody
	public List<RoomInfo> selectRoomtype(@PathVariable("hotelid") Integer hotelid) {

		RoomInfo roomInfo = new RoomInfo();
		roomInfo.setHotelId(hotelid);
		Map<String, Object> selectRoomInfoByHotelId = roomInfoFacade.selectRoomInfoByHotelId(roomInfo);
		List<RoomInfo> list = (List<RoomInfo>) selectRoomInfoByHotelId.get("list");
		return list;
	}

	/**
	 * 更新用户房间信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	@RequestMapping("/updataUserRoom")
	public String updataUserRoom(HotelUserLink hotelUserLink, HttpServletRequest request) {

		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		hotelUserLinkFacade.updateFromParticipator(hotelUserLink);
		return "redirect:/meetingUserRoom/selectUserRoom/" + meetingInfo.getMeeId();
	}

	/**
	 * 模糊查询用户房间信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	@RequestMapping("/selectUserRoomInfo")
	public String selectUserRoomInfo(String parm, HttpServletRequest request, ModelMap mode) {

		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Map<String, Object> selectByMeetingId = hotelUserLinkFacade.selectByMeetingId(meetingInfo.getMeeId(), parm);
		mode.addAttribute("hotelUserLinkList", selectByMeetingId.get("hotelUserLinkList"));
		return "admin-hotel/admin-hotel-userlist";
	}

	/**
	 * 导出表
	 * 
	 * @return
	 */
	@RequestMapping("/exportUserRoomInfo")
	@ResponseBody
	public String exportUserRoomInfo(HttpServletRequest request) {

		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Map<String, Object> selectByMeetingId = hotelUserLinkFacade.selectByMeetingId(meetingInfo.getMeeId(), null);
		List<HotelUserLink> list = (List<HotelUserLink>) selectByMeetingId.get("hotelUserLinkList");
		String exportInfo = exportInfo(request, list);
		return exportInfo;
	}

	/**
	 * 导出数据到excel
	 * 
	 * @param request
	 * @param list
	 * @return
	 */
	public String exportInfo(HttpServletRequest request, List<HotelUserLink> list) {

		String path = request.getSession().getServletContext().getRealPath("/") + "userroominfoexcel.xlsx";
		String[] title = { "用户姓名", "性别", "手机号", "房间类型" };
		List<String[]> titles = new ArrayList<String[]>();
		titles.add(title);
		List<String[]> datas = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String data[] = new String[4];
			data[0] = list.get(i).getUserName();
			data[1] = list.get(i).getSex();
			data[2] = list.get(i).getPhone();
			data[3] = list.get(i).getRoomType();
			datas.add(data);
		}
		List<List<String[]>> data_ = new ArrayList<List<String[]>>();
		data_.add(datas);
		String[] sheetName = { "用戶房间信息表" };
		try {
			ExcelUtil.writeToFile(path, sheetName, titles, data_);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userroominfoexcel.xlsx";
	}

	/**
	 * 查询我的酒店信息
	 * 
	 * @param meeid
	 * @param hallid
	 * @return
	 */
	@RequestMapping("/selectmyRoomInfo")
	public String selectmyRoomInfo(HttpServletRequest request, ModelMap mode) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		// 根据用户Id,huiyiId查询我的酒店信息
		Map<String, Object> selectByMeetingIdAndUserId = hotelUserLinkFacade.selectByMeetingIdAndUserId(meetingInfo.getMeeId(), user.getUserId());
		// 根据用户ID和会议ID查询报名会议没有
		SignUpInfo signUpInfo = new SignUpInfo();
		signUpInfo.setUserId(user.getUserId());
		signUpInfo.setMeetingId(meetingInfo.getMeeId());
		signUpInfo = signUpInfoFacade.selectSignUpInfoByUserIdMeeId(signUpInfo);

		// 没有报名跳转到报名页面
		if (null == signUpInfo) {
			mode.addAttribute("meetid", meetingInfo.getMeeId());
			return "meeting/pleaseSign";
		} else {
			if (selectByMeetingIdAndUserId.containsKey("entity")) {
				mode.addAttribute("entity", selectByMeetingIdAndUserId.get("entity"));
				return "admin-hotel/hotel";
			} else {
				// 完善酒店信息
				return "admin-hotel/addhotel";
			}
		}

	}

	/**
	 * 酒店所有房间信息
	 * 
	 * @param hotelid
	 * @return
	 */
	@RequestMapping("/selectallRoomInfo/{hotelid}")
	@ResponseBody
	public Map<String, Object> selectallRoomInfo(@PathVariable("hotelid") Integer hotelid, HttpServletRequest request) {

		RoomInfo roomInfo = new RoomInfo();
		roomInfo.setHotelId(hotelid);
		Map<String, Object> selectHotelRoomList = roomInfoFacade.selectRoomInfoByHotelId(roomInfo);
		return selectHotelRoomList;
	}

	/**
	 * 根据酒店id查询一个酒店基本信息
	 * 
	 * @param hotelid
	 * @return
	 */
	@RequestMapping("/selectHotelinfoByid/{hotelid}")
	@ResponseBody
	public Map<String, Object> selectHotelinfoByid(@PathVariable("hotelid") Integer hotelid) {

		Map<String, Object> selectHotelInfoByHotelId = hotelInfoFacade.selectHotelInfoByHotelId(hotelid);
		return selectHotelInfoByHotelId;
	}

	@RequestMapping("/selectHotelInfo/{meetingid}")
	@ResponseBody
	public List<HotelInfo> selectHotelInfo(@PathVariable("meetingid") Integer meetingid) {

		Map<String, Object> selectHotelInfoByMeetingId = hotelInfoFacade.hotelListQuery(meetingid);
		List<HotelInfo> list = (List<HotelInfo>) selectHotelInfoByMeetingId.get("hotelListQuery");
		return list;
	}

	/**
	 * 用户更新酒店房间信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	@RequestMapping("/updatamyroomInfo")
	public String updatamyroomInfo(HotelUserLink hotelUserLink, HttpServletRequest request) {
		Map<String, Object> updateFromParticipator = hotelUserLinkFacade.updateFromParticipator(hotelUserLink);
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// 修改成功后刷新当前页
		return "redirect:/meetingUserRoom/selectmyRoomInfo/";
		// 跳转会议管理页面
		// return "redirect:/meetingInfo/"+meetingInfo.getMeeId()+"/"+meetingInfo.getHallId();
	}

	/**
	 * 用户添加酒店信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	@RequestMapping("/useraddHotelInfo")
	public String useraddHotelInfo(HotelUserLink hotelUserLink, HttpServletRequest request) {

		Map<String, Object> insertFromParticipator = hotelUserLinkFacade.insertFromParticipator(hotelUserLink);
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		// MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		return "redirect:/meetingInfo/" + meetingInfo.getMeeId() + "/" + meetingInfo.getHallId();
	}

}
