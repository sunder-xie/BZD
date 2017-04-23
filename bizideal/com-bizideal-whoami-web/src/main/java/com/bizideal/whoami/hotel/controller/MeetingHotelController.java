package com.bizideal.whoami.hotel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.facade.hotel.entity.HotelInfo;
import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;
import com.bizideal.whoami.facade.hotel.entity.RoomInfo;
import com.bizideal.whoami.facade.hotel.facade.HotelInfoFacade;
import com.bizideal.whoami.facade.hotel.facade.HotelUserLinkFacade;
import com.bizideal.whoami.facade.hotel.facade.RoomInfoFacade;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;

/**
 * @ClassName MeetingHotelController
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-27 16:43:07
 */
@Controller("meetingHotel")
@RequestMapping("/meetingHotel")
public class MeetingHotelController {

	@Autowired
	private HotelInfoFacade hotelInfoFacade;

	@Autowired
	private RoomInfoFacade roomInfoFacade;

	@Autowired
	private HotelUserLinkFacade hotelUserLinkFacade;

	/**
	 * 跳转酒店编辑查询页面
	 * 
	 * @param meetingid
	 * @return
	 */
	@RequestMapping("/tohotelPage")
	public String tohotelPage(HttpServletRequest request) {
		return "admin-hotel/admin-hotel-func";
	}

	/**
	 * 跳转酒店信息添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/toaddhotelPage")
	public ModelAndView toaddhotelPage(ModelAndView modelAndView) {
		modelAndView.setViewName("admin-hotel/admin-hotel-add");
		return modelAndView;
	}

	/**
	 * 跳转酒店房间信息添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/toaddRoomPage/{hotelId}")
	public ModelAndView toaddRoomPage(@PathVariable("hotelId") Integer hotelId, ModelAndView modelAndView) {
		modelAndView.addObject("hotelId", hotelId);
		modelAndView.setViewName("admin-hotel/admin-hotelRoom-add");
		return modelAndView;
	}

	/**
	 * 根据会议id查询酒店信息
	 * 
	 * @param meetingid
	 * @param mode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectHotelInfoByMeetingId/{meetingid}")
	public ModelAndView selectHotelInfoByMeetingId(@PathVariable("meetingid") Integer meetingid, ModelAndView modelAndView) {
		Map<String, Object> selectHotelInfoByMeetingId = hotelInfoFacade.hotelListQuery(meetingid);
		if (selectHotelInfoByMeetingId != null) {
			List<HotelInfo> hotelInfos = (List<HotelInfo>) selectHotelInfoByMeetingId.get("hotelListQuery");
			for (HotelInfo hotelInfo : hotelInfos)
				if (hotelInfo.getRoomSum() == null)
					hotelInfo.setRoomSum(0);
			modelAndView.addObject("hotelListQuery", selectHotelInfoByMeetingId.get("hotelListQuery"));
		}
		modelAndView.setViewName("admin-hotel/admin-hotel-list");
		return modelAndView;
	}

	@RequestMapping("/selectHotelInfo/{meetingid}")
	@ResponseBody
	public List<HotelInfo> selectHotelInfo(@PathVariable("meetingid") Integer meetingid) {
		Map<String, Object> selectHotelInfoByMeetingId = hotelInfoFacade.hotelListQuery(meetingid);
		List<HotelInfo> list = (List<HotelInfo>) selectHotelInfoByMeetingId.get("hotelListQuery");
		return list;
	}

	/**
	 * 删除酒店信息确认是否已有人入住
	 * 
	 * @param hUId
	 * @param request
	 * @return
	 */
	// TODO
	@RequestMapping("/deleteUserRoomConfirm")
	@ResponseBody
	public Boolean deleteUserRoomConfirm(Integer hUId, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Map<String, Object> selectByMeetingId = hotelUserLinkFacade.selectByMeetingId(meetingInfo.getMeeId(), null);
		List<HotelUserLink> list = (List<HotelUserLink>) selectByMeetingId.get("hotelUserLinkList");
		for (HotelUserLink hotelUserLink : list) {
			if (hotelUserLink.getHotelId().intValue() == hUId.intValue()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 删除酒店信息
	 * 
	 * @param hotelid
	 * @return
	 */
	@RequestMapping("/deleteHotelInfoById/{hotelId}")
	@ResponseBody
	public Map<String, Object> deleteHotelInfoById(@PathVariable("hotelId") Integer hotelId, HttpServletRequest request) {

		Map<String, Object> deleteHotelInfoById = hotelInfoFacade.deleteHotelInfoById(hotelId);
		// MeetingInfo meetingInfo = (MeetingInfo)
		// request.getSession().getAttribute("click_meeting");
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("meeid", meetingInfo.getMeeId());
		return deleteHotelInfoById;
	}

	/**
	 * 编辑酒店信息回显
	 * 
	 * @param hotelid
	 * @return
	 */
	@RequestMapping("/selectHotelInfoByHotelId/{hotelId}")
	public ModelAndView selectHotelInfoByHotelId(@PathVariable("hotelId") Integer hotelId, ModelAndView modelAndView) {

		Map<String, Object> selectHotelInfoByHotelId = hotelInfoFacade.selectHotelInfoByHotelId(hotelId);
		if (selectHotelInfoByHotelId != null) {
			modelAndView.addObject("hotelInfo", selectHotelInfoByHotelId.get("hotelInfo"));
		}
		modelAndView.setViewName("admin-hotel/admin-hotel-edit");
		return modelAndView;
	}

	/**
	 * 保存修改的酒店信息
	 * 
	 * @param hotelInfo
	 * @return
	 */
	@RequestMapping("/updataHotelInfo")
	public String updataHotelInfo(HotelInfo hotelInfo, HttpServletRequest request) {
		hotelInfoFacade.updateHotelInfo(hotelInfo);
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		return "redirect:/meetingHotel/selectHotelInfoByMeetingId/" + meetingInfo.getMeeId();
	}

	/**
	 * 添加酒店信息
	 * 
	 * @param hotelInfo
	 * @return
	 */
	@RequestMapping("/addHotelInfo")
	public ModelAndView addHotelInfo(HotelInfo hotelInfo, HttpServletRequest request, ModelAndView modelAndView) {
		Map<String, Object> insertHotelInfo = hotelInfoFacade.insertHotelInfo(hotelInfo);
		if ("6370".equals(insertHotelInfo.get("code"))) {
			HotelInfo hInfo = (HotelInfo) insertHotelInfo.get("entity");
			int hotelId = hInfo.getHotelId();
			modelAndView.addObject("hotelId", hotelId);
			modelAndView.setViewName("admin-hotel/admin-hotel-addroom");
		} else {
			modelAndView.addObject("hotelName", hotelInfo.getHotelName());
			modelAndView.addObject("person", hotelInfo.getPerson());
			modelAndView.addObject("address", hotelInfo.getAddress());
			modelAndView.addObject("tel", hotelInfo.getTel());
			modelAndView.addObject("msg", insertHotelInfo.get("msg"));
			modelAndView.setViewName("admin-hotel/admin-hotel-add");
		}
		return modelAndView;
	}

	/**
	 * 添加酒店房间信息
	 * 
	 * @param roomInfo
	 * @param mode
	 * @return
	 */
	@RequestMapping("/addRoom")
	public String addRoom(RoomInfo roomInfo) {
		roomInfoFacade.insertRoomInfo(roomInfo);
		return "redirect:/meetingHotel/selectRooms?hotelId=" + roomInfo.getHotelId();
	}

	/**
	 * 添加酒店房间信息
	 * 
	 * @param roomInfo
	 * @param mode
	 * @return
	 */
	@RequestMapping("/selectRooms")
	public ModelAndView selectRooms(int hotelId, ModelAndView modelAndView) {
		modelAndView.addObject("hotelId", hotelId);
		modelAndView.setViewName("admin-hotel/admin-hotel-addroom");
		return modelAndView;
	}

	/**
	 * 查询房间信息
	 * 
	 * @param hotelid
	 * @return
	 */
	@RequestMapping("/selectroomInfo/{hotelId}")
	@ResponseBody
	public Map<String, Object> selectroomInfo(@PathVariable("hotelId") Integer hotelId) {
		RoomInfo roomInfo = new RoomInfo();
		roomInfo.setHotelId(hotelId);
		Map<String, Object> selectRoomInfoByHotelId = roomInfoFacade.selectRoomInfoByHotelId(roomInfo);
		return selectRoomInfoByHotelId;
	}

	/**
	 * 根据房间id查询房间信息
	 * 
	 * @param roomlid
	 * @param mode
	 * @return
	 */
	@RequestMapping("/selectroomInfoByid/{roomlid}")
	public ModelAndView selectroomInfoByid(@PathVariable("roomlid") Integer roomlid, ModelAndView modelAndView) {

		RoomInfo roomInfo = new RoomInfo();
		roomInfo.setRoomId(roomlid);
		Map<String, Object> selectRoomInfoById = roomInfoFacade.selectRoomInfoById(roomInfo);
		modelAndView.addObject("roomInfo", selectRoomInfoById.get("entity"));
		modelAndView.setViewName("admin-hotel/admin-hotelRoom-info");
		return modelAndView;
	}

	/**
	 * 编辑回显房间信息
	 * 
	 * @param roomlid
	 * @param mode
	 * @return
	 */
	@RequestMapping("/editroomInfo/{roomlid}")
	public ModelAndView editroomInfo(@PathVariable("roomlid") Integer roomlid, ModelAndView modelAndView) {

		RoomInfo roomInfo = new RoomInfo();
		roomInfo.setRoomId(roomlid);
		Map<String, Object> selectRoomInfoById = roomInfoFacade.selectRoomInfoById(roomInfo);
		RoomInfo room = (RoomInfo) selectRoomInfoById.get("entity");
		// 查询出房间剩余数量用于提示还有多少房间
		Integer roomNumber = room.getRoomNumber() - room.getNormalReservedNum() - room.getSpareReservedNum();
		modelAndView.addObject("roomInfo", selectRoomInfoById.get("entity"));
		modelAndView.addObject("roomNumber", roomNumber);
		modelAndView.setViewName("admin-hotel/admin-hotelRoom-edit");
		return modelAndView;
	}

	// /**
	// * 更新房间信息时确认房间数
	// *
	// * @param roomInfo
	// * @return
	// */
	// @RequestMapping("/updataroomInfoConfirm")
	// @ResponseBody
	// public String updataroomInfoConfirm(RoomInfo roomInfo) {
	// roomInfoFacade.updateRoomInfo(roomInfo);
	// RoomInfo roomInfo = new RoomInfo();
	// roomInfo.setRoomId(roomlid);
	// Map<String, Object> selectRoomInfoById = roomInfoFacade.selectRoomInfoById(roomInfo);
	// return ;
	// }

	/**
	 * 更新房间信息
	 * 
	 * @param roomInfo
	 * @return
	 */
	@RequestMapping("/updataroomInfo")
	@ResponseBody
	public String updataroomInfo(RoomInfo roomInfo) {
		Map<String, Object> updateRoomInfo = roomInfoFacade.updateRoomInfo(roomInfo);
		return (String) updateRoomInfo.get("code");
	}

	/**
	 * 删除房间信息
	 * 
	 * @param roomInfo
	 * @return
	 */
	@RequestMapping("/deleteroomInfo/{roomid}")
	@ResponseBody
	public Map<String, Object> deleteroomInfo(@PathVariable("roomid") Integer roomid) {
		RoomInfo roomInfo = new RoomInfo();
		roomInfo.setRoomId(roomid);
		Map<String, Object> deleteRoomInfo = roomInfoFacade.deleteRoomInfo(roomInfo);
		return deleteRoomInfo;
	}

}
