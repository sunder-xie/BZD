package com.bizideal.whoami;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeType;
import com.bizideal.whoami.knowledgebase.mapper.KnowledgeTypeMapper;
import com.bizideal.whoami.knowledgebase.service.KnowledgeTypeService;

public class KnowledgeTypeTest {
	ApplicationContext applicationContext = null;
	KnowledgeTypeMapper mapper = null;
	KnowledgeTypeService service = null;

	// CaseInfoFacade facade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		mapper = applicationContext.getBean(KnowledgeTypeMapper.class);
		service = applicationContext.getBean(KnowledgeTypeService.class);
		// facade = applicationContext.getBean(CaseInfoFacade.class);
	}

	@Test
	public void insertTest() {
		KnowledgeType knowledgeType = new KnowledgeType();
		knowledgeType.setMeeId(1);
		knowledgeType.setType("type1-1-1-1-1");
		knowledgeType.setParentId(4);
		int insert = service.insert(knowledgeType);
		System.out.println(insert);
	}

	@Test
	public void deleteTest() {

		int delete = service.delete(15);
		System.out.println(delete);
	}
}
