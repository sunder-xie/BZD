package com.bizideal.whoami;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.mapper.CaseInfoMapper;
import com.bizideal.whoami.service.CaseInfoService;
import com.bizideal.whoami.vote.entity.CaseInfo;
import com.bizideal.whoami.vote.facade.CaseInfoFacade;

public class CaseInfoTest {

	ApplicationContext applicationContext = null;
	CaseInfoMapper mapper = null;
	CaseInfoService service = null;
	CaseInfoFacade facade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		mapper = applicationContext.getBean(CaseInfoMapper.class);
		service = applicationContext.getBean(CaseInfoService.class);
		facade = applicationContext.getBean(CaseInfoFacade.class);
	}

	@Test
	public void insertTest() {

		CaseInfo caseInfo = new CaseInfo();
		caseInfo.setCaseType(1);
		caseInfo.setCaseVoteThemeId(2);
		caseInfo.setName("案例001");
		caseInfo.setProvince("上海");
		caseInfo.setThumbImgUrl("缩略图.jpg");
		caseInfo.setUrl("图片.jpg");
		caseInfo.setUnit("全国膝盖联合会");
		caseInfo.setUserId("me");
		Integer insertVoteInfo = facade.insert(caseInfo);
		System.out.println(caseInfo.getId());
		System.out.println(insertVoteInfo);
	}

	@Test
	public void updateTest() {

		CaseInfo caseInfo = new CaseInfo();
		caseInfo.setId(1);
		caseInfo.setCaseType(1);
		caseInfo.setCaseVoteThemeId(2);
		caseInfo.setName("案例001");
		caseInfo.setProvince("上海");
		caseInfo.setThumbImgUrl("缩略图.jpg");
		caseInfo.setUrl("图片.jpg");
		caseInfo.setUnit("全国鼻孔联合会");
		caseInfo.setUserId("me");
		Integer integer = facade.update(caseInfo);
		System.out.println(integer);
	}

	@Test
	public void deleteTest() {

		Integer integer = facade.delete(2);
		System.out.println(integer);
	}

	@Test
	public void selectTest() {

		CaseInfo selectVoteInfo = facade.selectById(1);
		System.out.println(selectVoteInfo);

		List<CaseInfo> list = facade.selectCaseInfo(2);
		System.out.println(list);

		List<CaseInfo> selectById = facade.selectFuzzyByString(2, "全国");
		System.out.println(selectById);
	}
}
