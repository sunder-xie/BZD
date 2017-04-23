package com.bizideal.whoami.meetingservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.facade.hotel.entity.HotelInfo;
import com.bizideal.whoami.facade.hotel.facade.HotelInfoFacade;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingInfoFacade;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月11日 下午3:10:04
 * @version 1.0
 */
@Controller
@RequestMapping("/meetingService")
public class MeetingServiceController {

	@Autowired
	private MeetingInfoFacade meetingInfoFacade;
	@Autowired
	private HotelInfoFacade hotelInfoFacade;

	/* 选择查看会议列表或者是酒店列表 */
	@RequestMapping("/toChoosePage")
	public ModelAndView toChoosePage(ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Map<String, Object> map = hotelInfoFacade.hotelListQuery(meetingInfo.getMeeId());
		@SuppressWarnings("unchecked")
		List<HotelInfo> list = (List<HotelInfo>) map.get("hotelListQuery");
		modelAndView.addObject("hashotel", list != null && list.size() > 0);
		modelAndView.setViewName("service/meet-server-func");
		return modelAndView;
	}

	/* 跳转会议列表 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/meetlist")
	public ModelAndView meetingList(ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		List<SubMeetingInfo> meetingInfos = new ArrayList<SubMeetingInfo>();
		/* meetingInfos.add(meetingInfo); */
		SubMeetingInfo mInfo = new SubMeetingInfo();
		mInfo.setMeeParentId(meetingInfo.getMeeId());
		Map<String, Object> subMeetingMap = meetingInfoFacade.selectSubMeetingInfo(mInfo);
		if (null != subMeetingMap) {
			List<SubMeetingInfo> subMees = (List<SubMeetingInfo>) subMeetingMap.get("list");
			if (null != subMees && subMees.size() > 0) {
				for (SubMeetingInfo m : subMees) {
					meetingInfos.add(m);
				}

			}
		}

		modelAndView.addObject("meetingInfo", meetingInfo);
		modelAndView.addObject("meetingList", meetingInfos);
		modelAndView.setViewName("service/meet-server-list");
		return modelAndView;
	}

	/* 跳转酒店列表 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/hotellist")
	public ModelAndView hotelList(ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Map<String, Object> map = hotelInfoFacade.hotelListQuery(meetingInfo.getMeeId());
		List<HotelInfo> list = (List<HotelInfo>) map.get("hotelListQuery");
		modelAndView.addObject("hotelList", list);
		modelAndView.setViewName("service/hotel-server-list");
		return modelAndView;
	}

}
