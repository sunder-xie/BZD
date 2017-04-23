package com.bizideal.whoami;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.mapper.VoteInfoMapper;
import com.bizideal.whoami.service.VoteInfoService;
import com.bizideal.whoami.vote.entity.VoteInfo;
import com.bizideal.whoami.vote.facade.VoteInfoFacade;

public class VoteInfoTest {

	ApplicationContext applicationContext = null;
	VoteInfoMapper mapper = null;
	VoteInfoService service = null;
	VoteInfoFacade facade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		mapper = applicationContext.getBean(VoteInfoMapper.class);
		service = applicationContext.getBean(VoteInfoService.class);
		facade = applicationContext.getBean(VoteInfoFacade.class);
	}

	@Test
	public void insertTest() {

		VoteInfo voteInfo = new VoteInfo();
		voteInfo.setMeetingId(1);
		voteInfo.setType(1);
		voteInfo.setCreateUserId("me");
		voteInfo.setDsp("备注");
		voteInfo.setEndTime((new Date()).getTime() + 1 * 24 * 60 * 60 * 1000l);
		voteInfo.setCreateTime((new Date()).getTime());
		Integer insertVoteInfo = facade.insert(voteInfo);
		System.out.println(voteInfo.getId());
		System.out.println(insertVoteInfo);
	}

	@Test
	public void updateTest() {

		VoteInfo voteInfo = new VoteInfo();
		voteInfo.setId(28);
		voteInfo.setMeetingId(1);
		voteInfo.setType(1);
		voteInfo.setCreateUserId("me");
		voteInfo.setDsp("备注121");
		voteInfo.setEndTime((new Date()).getTime());
		voteInfo.setCreateTime((new Date()).getTime());
		Integer integer = facade.update(voteInfo);
		System.out.println(integer);
	}

	@Test
	public void deleteTest() {

		Integer integer = facade.delete(29);
		System.out.println(integer);
	}

	@Test
	public void emptyTest() {

		List<VoteInfo> list = new ArrayList<VoteInfo>();
		Integer integer = service.updateEmptyFlag(list);
		System.out.println(integer);
	}

	@Test
	public void selectTest() {

		VoteInfo selectVoteInfo = facade.selectVoteInfo(1, 0);
		System.out.println(selectVoteInfo);

		VoteInfo voteInfo = new VoteInfo();
		voteInfo.setMeetingId(1);
		voteInfo.setType(1);
		voteInfo.setCreateUserId("me");
		voteInfo.setDsp("备注");
		voteInfo.setEndTime((new Date()).getTime());
		// voteInfo.setCreateTime((new Date()).getTime());

		Date date = new Date();
		long time = date.getTime() + 60 * 24 * 60 * 60 * 1000l;
		List<VoteInfo> list = service.selectOverdueVote(time, 1);
		System.out.println(list);

		VoteInfo selectById = facade.selectById(1);
		System.out.println(selectById);
	}
}
