package com.bizideal.whoami.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.facade.hotel.entity.RoomInfo;

/**
 * 酒店房间信息Mapper
 * 
 * @ClassName RoomInfoMapper
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月22日
 */
public interface RoomInfoMapper extends Mapper<RoomInfo> {

	/**
	 * 普通预定房间数减少（普通报名更换酒店信息，或取消报名）
	 * 
	 * @param roomId
	 * @param reduceNum
	 * @return
	 */
	int updateReduceRoomNum(@Param("roomId") Integer roomId, @Param("reduceNum") Integer reduceNum);

	/**
	 * 普通预定房间数增加（普通报名，或更换酒店信息）
	 * 
	 * @param roomId
	 * @param raiseNum
	 * @return
	 */
	int updateRaiseRoomNum(@Param("roomId") Integer roomId, @Param("raiseNum") Integer raiseNum);

	/**
	 * 备用房间预定数减少（备用房间：预留人员报名取消，或者预留人员更换酒店）
	 * 
	 * @param roomId
	 * @param reduceNum
	 * @return
	 */
	int updateReduceRoomNumReserved(@Param("roomId") Integer roomId, @Param("reduceNum") Integer reduceNum);

	/**
	 * 备用房间预定数增加（预留人员报名，或者预留人员更换酒店）
	 * 
	 * @param roomId
	 * @param raiseNum
	 * @return
	 */
	int updateRaiseRoomNumReserved(@Param("roomId") Integer roomId, @Param("raiseNum") Integer raiseNum);

	/**
	 * 根据酒店id删除房间
	 * 
	 * @param hotelId
	 * @return
	 */
	int deleteByHotelId(int hotelId);

	/**
	 * 根据酒店id查询该酒店的房间信息
	 * 
	 * @param hotelId
	 * @return
	 */
	List<RoomInfo> selectHotelRoomList(int hotelId);

	/**
	 * 根据会议ID查询所有房间信息
	 * 
	 * @param hotelId
	 * @return
	 */
	List<RoomInfo> getRoomByHotelId(@Param("hotelId") Integer hotelId);
}