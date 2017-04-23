package com.bizideal.whoami;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.facade.hotel.entity.RoomInfo;
import com.bizideal.whoami.hotel.mapper.RoomInfoMapper;

public class TestRoomInfo {

	static ApplicationContext applicationContext;
	static RoomInfoMapper roomInfoMapper;

	@Before
	public void testBefore() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		roomInfoMapper = applicationContext.getBean(RoomInfoMapper.class);
	}

	@Test
	public void testInsert() {
		RoomInfo room = new RoomInfo();
		room.setCost(123.23);
		room.setDsp("中午");
		room.setRoomNumber(23);
		//room.setRoomTypeId(123);
		room.setHotelId(123);
		System.out.println(roomInfoMapper.insert(room));
		System.out.println(3 < 5 - 2);
	}

	@Test
	public void testSelect() {
		List<RoomInfo> roomList = roomInfoMapper.selectHotelRoomList(1);
		System.out.println(roomList.toString());
	}
}
