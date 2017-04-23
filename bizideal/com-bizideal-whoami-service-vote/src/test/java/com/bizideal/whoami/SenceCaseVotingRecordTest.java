package com.bizideal.whoami;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.mapper.SenceCaseVotingRecordMapper;
import com.bizideal.whoami.sencevote.entity.SenceCaseVotingRecord;
import com.bizideal.whoami.sencevote.facade.SenceCaseVotingRecordFacade;
import com.bizideal.whoami.service.SenceCaseVotingRecordService;
import com.bizideal.whoami.vote.entity.VoteInfo;

public class SenceCaseVotingRecordTest {

	ApplicationContext applicationContext = null;
	SenceCaseVotingRecordMapper mapper = null;
	SenceCaseVotingRecordService service = null;

	SenceCaseVotingRecordFacade facade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		mapper = applicationContext.getBean(SenceCaseVotingRecordMapper.class);
		service = applicationContext.getBean(SenceCaseVotingRecordService.class);
		facade = applicationContext.getBean(SenceCaseVotingRecordFacade.class);
	}

	@Test
	public void insertTest() {

		Date date = new Date();
		SenceCaseVotingRecord record = new SenceCaseVotingRecord();
		record.setSenceCaseId(1);
		record.setMeetingId(1);
		record.setTime(date.getTime());
		record.setUserId("me");
		int insert = service.insert(record);
		System.out.println(insert);
	}

	@Test
	public void deleteTest() {

		List<VoteInfo> list = new ArrayList<VoteInfo>();
		int autoDelete = service.deleteByMeetings(list);
		System.out.println(autoDelete);
	}

	@Test
	public void selectTest() {

		SenceCaseVotingRecord record = new SenceCaseVotingRecord();
		// record.setMeetingId(1);
		record.setSenceCaseId(1);
		// record.setTime(1l);
		record.setUserId("me");
		int selectCount = service.selectCount(record);
		System.out.println(selectCount);
		int selectCount1 = service.selectCountByTimeAndMeetingAndUser(record, 2);
		System.out.println(selectCount1);
		
		List<SenceCaseVotingRecord> selectBySenceCaseId = facade.selectBySenceCaseId(1);
		System.out.println(selectBySenceCaseId);
	}
}
