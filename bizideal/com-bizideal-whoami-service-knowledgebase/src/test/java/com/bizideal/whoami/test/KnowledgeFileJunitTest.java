package com.bizideal.whoami.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.bizideal.whoami.knowledgebase.facade.KnowledgeFileFacade;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月23日 上午9:19:32
 * @version 1.0 知识库资料部分接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class KnowledgeFileJunitTest extends
		AbstractJUnit4SpringContextTests {

	ApplicationContext applicationContext = null;
	private KnowledgeFileFacade knowledgeFileFacade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
		// 从spring容器中获得Mapper的代理对象
		knowledgeFileFacade = applicationContext
				.getBean(KnowledgeFileFacade.class);
	}

	@Test
	public void saveSelective() throws Exception {
		// 选择性保存，为null不保存，没用
		KnowledgeFile file = new KnowledgeFile();
		file.setName("知识库文件保存测试");
		file.setMeeId(233);
		file.setUrl("group1/MM/00/abc.txt");
		file.setSuffix("pdf");
		file.setIsDown("0");
		file.setDelFlag("0");
		file.setDsp("我是备注");
		file.setCreateUser("9999238189400");
		int insertInit = knowledgeFileFacade.saveSelective(file);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void updateSelective() throws Exception {
		// 选择性保存，为null不保存
		KnowledgeFile file = new KnowledgeFile();
		file.setId(3);
		file.setName("资料名称可以修改");
		file.setTypeId(23);
		file.setUpdateUser("123456790");
		file.setDsp("备注可以修改");
		int insertInit = knowledgeFileFacade.updateSelective(file);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void selectNotSigned() {
		// 查询会议下未分配的资料
		List<KnowledgeFile> selectFilesNotSign = knowledgeFileFacade
				.selectFilesNotSign(233);
		System.out.println("===========");
		for (KnowledgeFile knowledgeFile : selectFilesNotSign) {
			System.out.println(knowledgeFile);
		}
		System.out.println("===========");
	}

	@Test
	public void selectFilesByTypeId() {
		// 分页查询节点下的资料
		PageInfo<KnowledgeFile> files = knowledgeFileFacade
				.selectFilesByTypeId("1234567980", 2, 2, 3);
		System.out.println("===========");
		for (KnowledgeFile knowledgeFile : files.getList()) {
			System.out.println(knowledgeFile);
		}
		System.out.println("===========");
	}

	@Test
	public void saveFileOrUrl() {
		// 保存文件或url
		KnowledgeFile file = new KnowledgeFile();
		file.setName("知识库文件保存测试");
		file.setMeeId(233);
		file.setTypeId(24);
		file.setUrl("group1/MM/00/abcd.txt");
		file.setSuffix("jpg");
		file.setIsDown("0");
		file.setDelFlag("0");
		file.setDsp("我是备注233");
		file.setCreateUser("1234567980");
		int files = knowledgeFileFacade.saveFileOrUrl(file);
		System.out.println("===========");
		System.out.println(files);
		System.out.println("===========");
	}

}
