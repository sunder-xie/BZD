package com.bizideal.whoami.hotel.service;

import java.util.Map;

import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;

/**
 * 酒店用户入住Service
 * 
 * @ClassName HotelUserLinkBiz
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
public interface HotelUserLinkBiz {

	/**
	 * 添加酒店用户入住表信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	// public Map<String, Object> insert(HotelUserLink hotelUserLink);

	/**
	 * 根据HUId更新酒店用户入住表
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	Map<String, Object> updateFromManager(HotelUserLink hotelUserLink);

	/**
	 * 根据HUId更新酒店用户入住表
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	Map<String, Object> updateFromParticipator(HotelUserLink hotelUserLink);

	/**
	 * 根据HUId删除酒店用户入住表
	 * 
	 * @param hUId
	 * @return
	 */
	// public Map<String, Object> deleteHotelUserLinkByHUId(int hUId);

	/**
	 * 根据HUId查找唯一酒店用户入住信息
	 * 
	 * @param hUId
	 * @return
	 */
	Map<String, Object> selectByHUId(int hUId);

	/**
	 * 根据酒店id查询酒店用户入住信息list
	 * 
	 * @param hotelId
	 * @return
	 */
	Map<String, Object> selectByHotelIdAndQueryString(int hotelId, String queryString);

	/**
	 * 根据会议id查询酒店用户入住信息list
	 * 
	 * @param meetingId
	 * @return
	 */
	Map<String, Object> selectByMeetingId(int meetingId, String queryString);

	/**
	 * 管理员添加酒店用户入住表信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	Map<String, Object> insertFromManager(HotelUserLink hotelUserLink);

	/**
	 * 参加者添加酒店用户入住表信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	Map<String, Object> insertFromParticipator(HotelUserLink hotelUserLink);

	/**
	 * 管理员删除酒店用户入住表信息
	 * 
	 * @param hUId
	 * @return
	 */
	Map<String, Object> deleteFromManager(int hUId);

	/**
	 * 参加者删除酒店用户入住表信息
	 * 
	 * @param hUId
	 * @return
	 */
	Map<String, Object> deleteFromParticipator(int hUId);

	Map<String, Object> selectByMeetingIdAndUserId(int meetingId, String userId);

}
