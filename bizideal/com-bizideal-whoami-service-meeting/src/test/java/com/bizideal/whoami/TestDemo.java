package com.bizideal.whoami;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.core.meeting.biz.MeetingHallBiz;
import com.bizideal.whoami.core.meeting.biz.MeetingInfoBiz;
import com.bizideal.whoami.core.meeting.mapper.MeetingHallMapper;
import com.bizideal.whoami.core.meeting.mapper.MeetingInfoMapper;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;

public class TestDemo {

	@Test
	public void testDemo() {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		MeetingHallMapper mapper = applicationContext.getBean(MeetingHallMapper.class);
		MeetingHallBiz meetingHallBiz = applicationContext.getBean(MeetingHallBiz.class);
		// 执行查询，并分页

		List<Object> ids = new ArrayList<Object>();
		ids.add(11);
		ids.add(13);
		ids.add(15);

		Integer deleteByIds = meetingHallBiz.deleteByIds(MeetingHall.class, "hallId", ids);

		System.out.println("----------------------------" + deleteByIds);

	}

	@Test
	public void testMeeting() {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");

		// 从spring容器中获得Mapper的代理对象
		MeetingInfoMapper mapper = applicationContext.getBean(MeetingInfoMapper.class);
		MeetingInfoBiz meetingHallBiz = applicationContext.getBean(MeetingInfoBiz.class);
		// 执行查询，并分页
		MeetingInfo meeInfo = new MeetingInfo();

		meeInfo.setMeeName("guanguo");
		mapper.insert(meeInfo);
	}
}