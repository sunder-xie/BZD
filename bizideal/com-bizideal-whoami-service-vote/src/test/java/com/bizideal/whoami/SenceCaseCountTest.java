package com.bizideal.whoami;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.mapper.SenceCaseCountMapper;
import com.bizideal.whoami.sencevote.entity.SenceCaseCount;
import com.bizideal.whoami.sencevote.facade.SenceCaseCountFacade;
import com.bizideal.whoami.service.SenceCaseCountService;

public class SenceCaseCountTest {

	ApplicationContext applicationContext = null;
	SenceCaseCountMapper mapper = null;
	SenceCaseCountService service = null;

	SenceCaseCountFacade facade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		mapper = applicationContext.getBean(SenceCaseCountMapper.class);
		service = applicationContext.getBean(SenceCaseCountService.class);
		facade = applicationContext.getBean(SenceCaseCountFacade.class);
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

		SenceCaseCount selectSenceCaseCount = facade.selectSenceCaseCount(4);
		System.out.println(selectSenceCaseCount);
	}
}
