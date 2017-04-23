package com.bizideal.whoami.hotel.facade;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.facade.hotel.entity.RoomInfo;
import com.bizideal.whoami.facade.hotel.facade.RoomInfoFacade;
import com.bizideal.whoami.hotel.service.RoomInfoBiz;

/**
 * 
 * @ClassName RoomInfoFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
@Component("roomInfoFacade")
public class RoomInfoFacadeImpl implements RoomInfoFacade {

	@Autowired
	private RoomInfoBiz roomInfoBiz;

	@Override
	public Map<String, Object> insertRoomInfo(RoomInfo roomInfo) {

		return roomInfoBiz.insertRoomInfo(roomInfo);
	}

	@Override
	public Map<String, Object> deleteRoomInfo(RoomInfo roomInfo) {

		return roomInfoBiz.deleteRoomInfo(roomInfo);
	}

	@Override
	public Map<String, Object> updateRoomInfo(RoomInfo roomInfo) {

		return roomInfoBiz.updateRoomInfo(roomInfo);
	}

	@Override
	public Map<String, Object> selectRoomInfoByRoomType(RoomInfo roomInfo) {

		return roomInfoBiz.selectRoomInfoByRoomType(roomInfo);
	}

	@Override
	public Map<String, Object> selectRoomInfoByHotelId(RoomInfo roomInfo) {

		return roomInfoBiz.selectRoomInfoByHotelId(roomInfo);
	}

	@Override
	public Map<String, Object> selectRoomInfoById(RoomInfo roomInfo) {

		return roomInfoBiz.selectRoomInfoById(roomInfo);
	}

	@Override
	public Map<String, Object> selectHotelRoomList(int hotelId) {
		return roomInfoBiz.selectHotelRoomList(hotelId);
	}

	@Override
	public Map<String, Object> adjustRoomInfo(RoomInfo roomInfo) {
		return roomInfoBiz.adjustRoomInfo(roomInfo);
	}

}
