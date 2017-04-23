package com.bizideal.whoami.hotel.service;

import java.util.Map;

import com.bizideal.whoami.facade.hotel.entity.RoomInfo;

/**
 * 酒店房间信息Service
 * 
 * @ClassName RoomInfoBiz
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
public interface RoomInfoBiz {

	/**
	 * 添加一个房间信息
	 * 
	 * @param roomInfo
	 * @return
	 */
	Map<String, Object> insertRoomInfo(RoomInfo roomInfo);

	/**
	 * 删除一个房间信息
	 * 
	 * @param roomInfo
	 * @return
	 */
	Map<String, Object> deleteRoomInfo(RoomInfo roomInfo);

	/**
	 * 更新一个房间信息
	 * 
	 * @param roomInfo
	 * @return
	 */
	Map<String, Object> updateRoomInfo(RoomInfo roomInfo);

	/**
	 * 根据房间名称和类型查询房间信息
	 * 
	 * @param roomInfo
	 * @return
	 */
	Map<String, Object> selectRoomInfoByRoomType(RoomInfo roomInfo);

	/**
	 * 根据酒店ID查询房间信息
	 * 
	 * @param roomInfo
	 * @return
	 */
	Map<String, Object> selectRoomInfoByHotelId(RoomInfo roomInfo);

	/**
	 * 根据房间ID查询房间信息
	 * 
	 * @param roomInfo
	 * @return
	 */
	Map<String, Object> selectRoomInfoById(RoomInfo roomInfo);

	/**
	 * 根据酒店id查询该酒店的房间信息
	 * 
	 * @param hotelId
	 * @return
	 */
	Map<String, Object> selectHotelRoomList(int hotelId);

	/**
	 * 调整房间的备用房间数
	 * 
	 * @param roomInfo
	 * @return
	 */
	Map<String, Object> adjustRoomInfo(RoomInfo roomInfo);
}
