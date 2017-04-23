package com.bizideal.whoami.hotel.facade;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.facade.hotel.entity.HotelInfo;
import com.bizideal.whoami.facade.hotel.facade.HotelInfoFacade;
import com.bizideal.whoami.hotel.service.HotelInfoBiz;

/**
 * 
 * @ClassName HotelInfoFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
@Component("hotelInfoFacade")
public class HotelInfoFacadeImpl implements HotelInfoFacade {

	@Autowired
	private HotelInfoBiz hotelInfoBiz;

	@Override
	public Map<String, Object> insertHotelInfo(HotelInfo hotelInfo) {
		// 添加酒店信息
		return hotelInfoBiz.insertHotelInfo(hotelInfo);
	}

	@Override
	public Map<String, Object> updateHotelInfo(HotelInfo hotelInfo) {
		// 根据酒店id更新酒店信息
		return hotelInfoBiz.updateHotelInfo(hotelInfo);
	}

	@Override
	public Map<String, Object> deleteHotelInfoById(int hotelId) {
		// 根据酒店id删除酒店信息
		return hotelInfoBiz.deleteHotelInfoById(hotelId);
	}

	@Override
	public Map<String, Object> selectHotelInfoByHotelId(int hotelId) {
		// 根据酒店id查询唯一酒店信息
		return hotelInfoBiz.selectHotelInfoByHotelId(hotelId);
	}

	@Override
	public Map<String, Object> selectHotelInfoByMeetingIdAndLikeHotelName(int meetingId, String hotelName) {
		// TODO 根据会议id和模糊酒店名称查询酒店list
		return hotelInfoBiz.selectHotelInfoByMeetingIdAndLikeHotelName(meetingId, hotelName);
	}

	@Override
	public Map<String, Object> verifyHotelName(int meetingId, String hotelName) {
		// 根据会议id查询酒店list
		return hotelInfoBiz.verifyHotelName(meetingId, hotelName);
	}

	@Override
	public Map<String, Object> selectHotelInfoByMeetingId(int meetingId) {
		// 根据会议id和酒店名称验证是否酒店重名
		return hotelInfoBiz.selectHotelInfoByMeetingId(meetingId);
	}

	@Override
	public Map<String, Object> hotelListQuery(int meetingId) {
		return hotelInfoBiz.hotelListQuery(meetingId);
	}
}
