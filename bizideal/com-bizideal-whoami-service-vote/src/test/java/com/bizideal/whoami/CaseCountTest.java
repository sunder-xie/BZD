package com.bizideal.whoami;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.mapper.CaseCountMapper;
import com.bizideal.whoami.service.CaseCountService;
import com.bizideal.whoami.vote.entity.CaseCount;
import com.bizideal.whoami.vote.facade.CaseCountFacade;

public class CaseCountTest {

	ApplicationContext applicationContext = null;
	CaseCountMapper mapper = null;
	CaseCountService service = null;
	CaseCountFacade facade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		mapper = applicationContext.getBean(CaseCountMapper.class);
		service = applicationContext.getBean(CaseCountService.class);
		facade = applicationContext.getBean(CaseCountFacade.class);
	}

	@Test
	public void insertTest() {

	}

	@Test
	public void updateTest() {

	}

	@Test
	public void deleteTest() {

	}

	@Test
	public void selectTest() {

		CaseCount selectCaseCount = facade.selectCaseCount(1);
		System.out.println(selectCaseCount);
	}
}
