package com.bizideal.whoami.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;

/**
 * 酒店用户入住Mapper
 * 
 * @author sy
 * @date 2017-2-21 11:02:47
 */
public interface HotelUserLinkMapper extends Mapper<HotelUserLink> {

	/**
	 * 根据HUId更新酒店用户关联表
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	int updateByHUId(HotelUserLink hotelUserLink);

	/**
	 * 根据HUId删除酒店用户关联表信息
	 * 
	 * @param hUId
	 * @return
	 */
	int deleteByHUId(int hUId);

	/**
	 * 根据HotelId删除酒店用户关联表信息
	 * 
	 * @param hUId
	 * @return
	 */
	int deleteByHotelId(int hUId);

	/**
	 * 根据MeetingId删除酒店用户关联表信息
	 * 
	 * @param hUId
	 * @return
	 */
	int deleteByMeetingId(int hUId);

	/**
	 * 根据HUId查找唯一酒店用户关联信息
	 * 
	 * @param hUId
	 * @return
	 */
	HotelUserLink selectByHUId(int hUId);

	/**
	 * 根据酒店id查询酒店用户关联信息list
	 * 
	 * @param hotelId
	 * @return
	 */
	List<HotelUserLink> selectByHotelId(@Param("hotelId") int hotelId, @Param("queryString") String queryString);

	/**
	 * 根据会议id查询酒店用户关联信息list
	 * 
	 * @param meetingId
	 * @return
	 */
	List<HotelUserLink> selectByMeetingId(@Param("meetingId") int meetingId,
										  @Param("queryString") String queryString);

	HotelUserLink selectByMeetingIdAndUserId(@Param("meetingId") int meetingId, @Param("userId") String userId);

}