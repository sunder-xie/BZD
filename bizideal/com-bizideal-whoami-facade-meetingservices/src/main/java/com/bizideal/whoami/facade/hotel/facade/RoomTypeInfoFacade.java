package com.bizideal.whoami.facade.hotel.facade;

import java.util.Map;

import com.bizideal.whoami.facade.hotel.entity.RoomTypeInfo;

/**
 * 房间类型信息Facade
 * 
 * @ClassName RoomTypeInfoFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
public interface RoomTypeInfoFacade {

	/**
	 * 插入一条房间类型信息
	 * 
	 * @param roomTypeInfo
	 * @return
	 */
	Map<String, Object> insertRoomTypeInfo(RoomTypeInfo roomTypeInfo);

	/**
	 * 根据ID删除房间信息
	 * 
	 * @param roomTypeInfo
	 * @return
	 */
	Map<String, Object> deleteRoomTypeInfoById(RoomTypeInfo roomTypeInfo);

	/**
	 * 更新房间信息
	 * 
	 * @param roomTypeInfo
	 * @return
	 */
	Map<String, Object> updateRoomTypeInfo(RoomTypeInfo roomTypeInfo);

	/**
	 * 根据ID查询房间信息
	 * 
	 * @param roomTypeInfo
	 * @return
	 */
	Map<String, Object> selectRoomTypeInfoById(RoomTypeInfo roomTypeInfo);

	/**
	 * 根据房间名称查询房间信息
	 * 
	 * @param roomTypeInfo
	 * @return
	 */
	Map<String, Object> selectRoomTypeInfoByName(RoomTypeInfo roomTypeInfo);

	/**
	 * 查询所有的房间信息
	 * 
	 * @return
	 */
	Map<String, Object> selectAllRoomTypeInfo();
}
