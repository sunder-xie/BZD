package com.bizideal.whoami.hotel.service;

import java.util.Map;

import com.bizideal.whoami.facade.hotel.entity.HotelInfo;

/**
 * 酒店信息Service
 * 
 * @ClassName HotelInfoBiz
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
public interface HotelInfoBiz {

	/**
	 * 添加酒店信息
	 * 
	 * @param hotelInfo
	 * @return
	 */
	Map<String, Object> insertHotelInfo(HotelInfo hotelInfo);

	/**
	 * 根据酒店id更新酒店信息
	 * 
	 * @param hotelInfo
	 * @return
	 */
	Map<String, Object> updateHotelInfo(HotelInfo hotelInfo);

	/**
	 * 根据酒店id删除酒店信息
	 * 
	 * @param hotelId
	 * @return
	 */
	Map<String, Object> deleteHotelInfoById(int hotelId);

	/**
	 * 根据酒店id查询唯一酒店信息
	 * 
	 * @param hotelId
	 * @return
	 */
	Map<String, Object> selectHotelInfoByHotelId(int hotelId);

	/**
	 * 根据会议id和模糊酒店名称查询酒店list
	 * 
	 * @param meetingId
	 * @param hotelName
	 * @return
	 */
	Map<String, Object> selectHotelInfoByMeetingIdAndLikeHotelName(int meetingId, String hotelName);

	/**
	 * 根据会议id查询酒店list
	 * 
	 * @param meetingId
	 * @return
	 */
	Map<String, Object> selectHotelInfoByMeetingId(int meetingId);

	/**
	 * 根据会议id和酒店名称验证是否酒店重名
	 * 
	 * @param meetingId
	 * @param hotelName
	 * @return
	 */
	Map<String, Object> verifyHotelName(int meetingId, String hotelName);

	/**
	 * 根据会议id查找酒店信息和剩余房间数
	 * 
	 * @param meetingId
	 * @return
	 */
	Map<String, Object> hotelListQuery(int meetingId);
}
