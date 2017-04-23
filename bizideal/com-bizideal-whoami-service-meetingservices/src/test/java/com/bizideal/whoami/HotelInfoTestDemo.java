package com.bizideal.whoami;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.facade.hotel.entity.HotelInfo;
import com.bizideal.whoami.hotel.mapper.HotelInfoMapper;
//import com.bizideal.whoami.hotel.service.DemoService;
import com.bizideal.whoami.hotel.service.HotelInfoBiz;

public class HotelInfoTestDemo {

	@Test
	public void insertTest() {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		HotelInfoMapper mapper = applicationContext.getBean(HotelInfoMapper.class);
		HotelInfoBiz hotelInfoBiz = applicationContext.getBean(HotelInfoBiz.class);

		HotelInfo hotelInfo = new HotelInfo();
		hotelInfo.setHotelId(1);
		hotelInfo.setHotelName("遇见");
		hotelInfo.setAddress("宝山区建伟路");
		hotelInfo.setMeetingHallId(1);
		hotelInfo.setMeetingId(1);
		hotelInfo.setPerson("小李");
		hotelInfo.setTel("13745692658");
		hotelInfo.setDsp("备注3");

		// mapper.insertHotelInfo(hotelInfo);
		Map<String, Object> map = hotelInfoBiz.deleteHotelInfoById(1);
		for (Entry<String, Object> m : map.entrySet()) {
			System.out.println(m.toString());
		}
		// try {
		// System.out.println(hotelInfoBiz.insertHotelInfo(hotelInfo));
		// } catch (Exception e) {
		// System.out.println(0);
		// e.printStackTrace();
		// }
	}

	@Test
	public void updateHotelInfo() {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		HotelInfoMapper mapper = applicationContext.getBean(HotelInfoMapper.class);
		HotelInfoBiz hotelInfoBiz = applicationContext.getBean(HotelInfoBiz.class);

		HotelInfo hotelInfo = new HotelInfo();
		hotelInfo.setHotelId(3);
		hotelInfo.setHotelName("遇见");
		hotelInfo.setAddress("宝山区建伟路");
		hotelInfo.setMeetingHallId(1);
		hotelInfo.setMeetingId(1);
		hotelInfo.setPerson("小李");
		hotelInfo.setTel("13745692658");
		hotelInfo.setDsp("备注");

		Map<String, Object> map = hotelInfoBiz.updateHotelInfo(hotelInfo);
		for (Entry<String, Object> m : map.entrySet()) {
			System.out.println(m.toString());
		}
	}

	@Test
	public void deleteHotelInfo() {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		HotelInfoMapper mapper = applicationContext.getBean(HotelInfoMapper.class);
		HotelInfoBiz hotelInfoBiz = applicationContext.getBean(HotelInfoBiz.class);

		HotelInfo hotelInfo = new HotelInfo();
		hotelInfo.setHotelId(3);

		mapper.deleteHotelInfoById(2);
	}

	@Test
	public void selectHotelInfo() {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		HotelInfoMapper mapper = applicationContext.getBean(HotelInfoMapper.class);
		HotelInfoBiz hotelInfoBiz = applicationContext.getBean(HotelInfoBiz.class);

		HotelInfo hotelInfo = new HotelInfo();
		hotelInfo.setHotelId(3);

		// HotelInfo info = mapper.selectHotelInfoByHotelId(1);
		// System.out.println(info.toString());
		// List<HotelInfo> list = hotelInfoBiz.selectHotelInfoByMeetingId(1);
		// for (HotelInfo hotelInfo2 : list) {
		// System.out.println(hotelInfo2.toString());
		// }

		// List<HotelInfo> selectAll = mapper.selectAll();
		// for (HotelInfo hotelInfo2 : selectAll) {
		// System.out.println(hotelInfo2.getHotelName());
		// }
		// HotelInfo info = mapper.verifyHotelName(1, "汉庭");
		// System.out.println(null == info);

		Map<String, Object> map = hotelInfoBiz.hotelListQuery(1);

		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}

	}

	@Test
	public void test1() {
		// String t = "    ".trim();
		// System.out.println(t);
		// System.out.println("".equals(t));
		// System.out.println(t.equals(null));
		//
		// HotelInfo hotelInfo = new HotelInfo();
		// System.out.println(hotelInfo.getHotelId());

		String t1 = null;
		// System.out.println(t1);
		System.out.println(t1 == null);
	}

	@Test
	public void testMQ() {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		HotelInfoMapper mapper = applicationContext.getBean(HotelInfoMapper.class);
		HotelInfoBiz hotelInfoBiz = applicationContext.getBean(HotelInfoBiz.class);
		Map<String, Object> map = hotelInfoBiz.selectHotelInfoByHotelId(1);
		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
	}
}
