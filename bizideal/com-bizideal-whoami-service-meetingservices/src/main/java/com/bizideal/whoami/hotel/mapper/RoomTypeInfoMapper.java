package com.bizideal.whoami.hotel.mapper;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.facade.hotel.entity.RoomTypeInfo;

public interface RoomTypeInfoMapper  extends Mapper<RoomTypeInfo>{
	
	
	RoomTypeInfo selectRoomTypeInfoById(@Param("roomTypeId") Integer roomTypeId);
}