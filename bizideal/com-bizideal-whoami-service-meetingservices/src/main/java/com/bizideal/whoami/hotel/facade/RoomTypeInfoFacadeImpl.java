package com.bizideal.whoami.hotel.facade;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.facade.hotel.entity.RoomTypeInfo;
import com.bizideal.whoami.facade.hotel.facade.RoomTypeInfoFacade;
import com.bizideal.whoami.hotel.service.RoomTypeInfoBiz;

/**
 * 
 * @ClassName RoomTypeInfoFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
@Component("roomTypeInfoFacade")
public class RoomTypeInfoFacadeImpl implements RoomTypeInfoFacade{

	@Autowired
	private RoomTypeInfoBiz roomTypeInfoBiz;
	
	@Override
	public Map<String, Object> insertRoomTypeInfo(RoomTypeInfo roomTypeInfo) {
		return roomTypeInfoBiz.insertRoomTypeInfo(roomTypeInfo);
	}

	@Override
	public Map<String, Object> deleteRoomTypeInfoById(RoomTypeInfo roomTypeInfo) {
		
		return roomTypeInfoBiz.deleteRoomTypeInfoById(roomTypeInfo);
	}

	@Override
	public Map<String, Object> updateRoomTypeInfo(RoomTypeInfo roomTypeInfo) {
		
		return roomTypeInfoBiz.updateRoomTypeInfo(roomTypeInfo);
	}

	@Override
	public Map<String, Object> selectRoomTypeInfoById(RoomTypeInfo roomTypeInfo) {
		
		return roomTypeInfoBiz.selectRoomTypeInfoById(roomTypeInfo);
	}

	@Override
	public Map<String, Object> selectRoomTypeInfoByName(
			RoomTypeInfo roomTypeInfo) {
		
		return roomTypeInfoBiz.selectRoomTypeInfoByName(roomTypeInfo);
	}

	@Override
	public Map<String, Object> selectAllRoomTypeInfo() {
		
		return roomTypeInfoBiz.selectAllRoomTypeInfo();
	}

}
