package com.bizideal.whoami;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;
import com.bizideal.whoami.hotel.mapper.HotelUserLinkMapper;
//import com.bizideal.whoami.hotel.service.DemoService;
import com.bizideal.whoami.hotel.service.HotelUserLinkBiz;

public class HULinkTestDemo {

	/*
	 * @Test public void testDemo() { // 创建一个spring容器 ApplicationContext
	 * applicationContext = new ClassPathXmlApplicationContext(
	 * "classpath:spring/spring-context.xml");
	 * 
	 * // 从spring容器中获得Mapper的代理对象 DemoMapper mapper =
	 * applicationContext.getBean(DemoMapper.class); // DemoService demoService
	 * = // applicationContext.getBean(DemoService.class);
	 * 
	 * // 223213424 // RedisClientTemplate redisClientTemplate = //
	 * applicationContext.getBean(RedisClientTemplate.class); //
	 * redisClientTemplate.deteteByKey("223213424"); // 执行查询，并分页 Demo demo = new
	 * Demo();
	 * 
	 * demo.setName("demo"); mapper.insert(demo); //
	 * mapper.insertSelective(demo); // int id = demoService.insertDemo(demo);
	 * // System.out.println(id); PageHelper.startPage(1, 3); List<Demo> list =
	 * mapper.selectAll(); PageInfo<Demo> pageInfo = new PageInfo<Demo>(list);
	 * List<Demo> demos = pageInfo.getList(); for (Demo demo2 : demos) {
	 * System.out.println(demo2.getName()); } }
	 * 
	 * @Test public void testTx() { // 测试事务 // 创建一个spring容器 ApplicationContext
	 * applicationContext = new ClassPathXmlApplicationContext(
	 * "classpath:spring/spring-context.xml");
	 * 
	 * // 从spring容器中获得Mapper的代理对象
	 * 
	 * DemoService demoService = applicationContext.getBean(DemoService.class);
	 * // demoService.insertTestTx();
	 * 
	 * }
	 */

	@Test
	public void insertTestDemo() {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		HotelUserLinkMapper mapper = applicationContext.getBean(HotelUserLinkMapper.class);
		HotelUserLinkBiz service = applicationContext.getBean(HotelUserLinkBiz.class);

		HotelUserLink hotelUserLink = new HotelUserLink();
		hotelUserLink.setRoomId(1);
		hotelUserLink.setHotelId(1);
		hotelUserLink.setMeetingId(1);
		hotelUserLink.setMeetingHallId(1);
		hotelUserLink.setUserId("1");
		hotelUserLink.setDsp("备注1");

		int insert = mapper.insert(hotelUserLink);
		System.out.println(insert);
	}

	@Test
	public void updateTestDemo() {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		HotelUserLinkMapper mapper = applicationContext.getBean(HotelUserLinkMapper.class);
		HotelUserLinkBiz service = applicationContext.getBean(HotelUserLinkBiz.class);

		HotelUserLink hotelUserLink = new HotelUserLink();
		hotelUserLink.sethUId(3);
		hotelUserLink.setRoomId(123);
		hotelUserLink.setHotelId(123);
		hotelUserLink.setMeetingId(123);
		hotelUserLink.setMeetingHallId(123);
		hotelUserLink.setUserId("1");
		hotelUserLink.setDsp("备注123");

		// int data = mapper.updateHotelUserLinkByHUId(hotelUserLink);
		int data = mapper.updateByPrimaryKey(hotelUserLink);
		System.out.println(data);
	}

	@Test
	public void deleteTestDemo() {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		HotelUserLinkMapper mapper = applicationContext.getBean(HotelUserLinkMapper.class);
		HotelUserLinkBiz service = applicationContext.getBean(HotelUserLinkBiz.class);

		HotelUserLink hotelUserLink = new HotelUserLink();
		hotelUserLink.sethUId(1);
		hotelUserLink.setRoomId(123);
		hotelUserLink.setHotelId(123);
		hotelUserLink.setMeetingId(123);
		hotelUserLink.setMeetingHallId(123);
		hotelUserLink.setUserId("1");
		hotelUserLink.setDsp("备注123");

		Integer key = 1;

		int data = mapper.deleteByHUId(2);
		System.out.println(data);
	}

	@Test
	public void selectTestDemo() {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		HotelUserLinkMapper mapper = applicationContext.getBean(HotelUserLinkMapper.class);
		HotelUserLinkBiz service = applicationContext.getBean(HotelUserLinkBiz.class);

		HotelUserLink hotelUserLink = mapper.selectByHUId(4);
		System.out.println(hotelUserLink);
	}
}