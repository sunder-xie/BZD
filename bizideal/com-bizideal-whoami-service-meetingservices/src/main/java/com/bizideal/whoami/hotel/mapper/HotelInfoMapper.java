package com.bizideal.whoami.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.facade.hotel.entity.HotelInfo;

/**
 * 酒店信息Mapper
 * 
 * @author sy
 * @date 2017-2-21 11:02:12
 */
public interface HotelInfoMapper extends Mapper<HotelInfo> {

	/**
	 * 添加酒店信息
	 * 
	 * @param hotelInfo
	 * @return
	 */
	int insertHotelInfo(HotelInfo hotelInfo);

	/**
	 * 根据酒店id更新酒店信息
	 * 
	 * @param hotelInfo
	 * @return
	 */
	int updateHotelInfo(HotelInfo hotelInfo);

	/**
	 * 根据酒店id查找唯一酒店信息
	 * 
	 * @param hotelId
	 * @return
	 */
	HotelInfo selectHotelInfoByHotelId(int hotelId);

	/**
	 * 根据会议id和模糊酒店名称查询酒店信息list，酒店名称为null时只根据会议id查询
	 * 
	 * @param meetingId
	 * @param hotelName
	 * @return
	 */
	List<HotelInfo> selectHotelInfoByMeetingIdAndLikeHotelName(@Param("meetingId") int meetingId,
															   @Param("hotelName") String hotelName);

	/**
	 * 根据会议id和酒店名称查询酒店信息来验证同一会议中酒店是否重名
	 * 
	 * @param meetingId
	 * @param hotelName
	 * @return
	 */
	HotelInfo verifyHotelName(@Param("meetingId") int meetingId, @Param("hotelName") String hotelName);

	/**
	 * 根据酒店id删除酒店信息
	 * 
	 * @param hotelId
	 * @return
	 */
	int deleteHotelInfoById(int hotelId);

	/**
	 * 根据会议id查找酒店信息和剩余房间数
	 * 
	 * @param meetingId
	 * @return
	 */
	List<HotelInfo> hotelListQuery(int meetingId);

	List<HotelInfo> selectHotel(HotelInfo hotelInfo);
}