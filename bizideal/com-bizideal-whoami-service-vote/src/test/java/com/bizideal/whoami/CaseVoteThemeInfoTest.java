package com.bizideal.whoami;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.mapper.CaseVoteThemeInfoMapper;
import com.bizideal.whoami.service.CaseVoteThemeInfoService;
import com.bizideal.whoami.vote.entity.CaseVoteThemeInfo;
import com.bizideal.whoami.vote.facade.CaseVoteThemeInfoFacade;

public class CaseVoteThemeInfoTest {

	ApplicationContext applicationContext = null;
	CaseVoteThemeInfoMapper mapper = null;
	CaseVoteThemeInfoService service = null;
	CaseVoteThemeInfoFacade facade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		mapper = applicationContext.getBean(CaseVoteThemeInfoMapper.class);
		service = applicationContext.getBean(CaseVoteThemeInfoService.class);
		facade = applicationContext.getBean(CaseVoteThemeInfoFacade.class);
	}

	@Test
	public void insertTest() {

		CaseVoteThemeInfo caseVoteThemeInfo = new CaseVoteThemeInfo();
		caseVoteThemeInfo.setName("主题2");
		caseVoteThemeInfo.setVoteId(28);
		caseVoteThemeInfo.setDsp("备注");
		Integer insertVoteInfo = facade.insert(caseVoteThemeInfo);
		System.out.println(caseVoteThemeInfo.getId());
		System.out.println(insertVoteInfo);
	}

	@Test
	public void updateTest() {

		CaseVoteThemeInfo caseVoteThemeInfo = new CaseVoteThemeInfo();
		caseVoteThemeInfo.setId(1);
		caseVoteThemeInfo.setName("主题1");
		caseVoteThemeInfo.setVoteId(28);
		caseVoteThemeInfo.setDsp("备注1");
		Integer integer = facade.update(caseVoteThemeInfo);
		System.out.println(integer);
	}

	@Test
	public void deleteTest() {

		Integer integer = facade.delete(1);
		System.out.println(integer);
	}

	@Test
	public void selectTest() {

		List<CaseVoteThemeInfo> selectVoteInfo = facade.selectCaseVoteThemeInfo(28, 0);
		System.out.println(selectVoteInfo);

		List<CaseVoteThemeInfo> list = facade.selectFuzzyByName(28, "主题");
		System.out.println(list);

		CaseVoteThemeInfo selectById = facade.selectById(1);
		System.out.println(selectById);
	}
}
