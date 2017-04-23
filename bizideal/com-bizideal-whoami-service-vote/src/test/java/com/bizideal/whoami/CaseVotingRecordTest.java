package com.bizideal.whoami;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.mapper.CaseVotingRecordMapper;
import com.bizideal.whoami.service.CaseVotingRecordService;
import com.bizideal.whoami.vote.entity.CaseVotingRecord;
import com.bizideal.whoami.vote.entity.VoteInfo;
import com.bizideal.whoami.vote.facade.CaseVotingRecordFacade;

public class CaseVotingRecordTest {

	ApplicationContext applicationContext = null;
	CaseVotingRecordMapper mapper = null;
	CaseVotingRecordService service = null;
	CaseVotingRecordFacade facade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		mapper = applicationContext.getBean(CaseVotingRecordMapper.class);
		service = applicationContext.getBean(CaseVotingRecordService.class);
		facade = applicationContext.getBean(CaseVotingRecordFacade.class);
	}

	@Test
	public void insertTest() {

		Date date = new Date();
		CaseVotingRecord record = new CaseVotingRecord();
		record.setCaseId(1);
		record.setIpAddress("192.168.0.1");
		record.setMeetingId(1);
		record.setTime(date.getTime());
		record.setUserId("me");
		int insert = facade.insert(record);
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

		List<CaseVotingRecord> selectCaseVotingRecord = facade.selectCaseVotingRecord(1);
		System.out.println(selectCaseVotingRecord);
	}
}
