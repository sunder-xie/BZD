package com.bizideal.whoami.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeCollection;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.bizideal.whoami.knowledgebase.facade.KnowledgeCollectionFacade;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月23日 上午9:19:32
 * @version 1.0 知识库我的收藏部分接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class KnowledgeCollectionJunitTest extends AbstractJUnit4SpringContextTests {

	ApplicationContext applicationContext = null;
	private KnowledgeCollectionFacade knowledgeCollectionFacade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
		// 从spring容器中获得Mapper的代理对象
		knowledgeCollectionFacade = applicationContext
				.getBean(KnowledgeCollectionFacade.class);
	}

	@Test
	public void saveCollection() throws Exception {
		// 收藏接口
		KnowledgeCollection knowledgeCollection = new KnowledgeCollection();
		knowledgeCollection.setMeeId(233);
		knowledgeCollection.setFileId(4);
		knowledgeCollection.setUserId("1234567980");
		int insertInit = knowledgeCollectionFacade
				.saveCollection(knowledgeCollection);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void deleteCollection() throws Exception {
		// 取消收藏接口
		KnowledgeCollection knowledgeCollection=new KnowledgeCollection();
		knowledgeCollection.setMeeId(233);
		knowledgeCollection.setFileId(4);
		knowledgeCollection.setUserId("1234567980");
		int insertInit = knowledgeCollectionFacade.deleteCollection(knowledgeCollection);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void selectMyCollectionsByUserId() throws Exception {
		// 查询我的收藏接口
		KnowledgeCollection collection = new KnowledgeCollection();
		collection.setUserId("1234567980");
		collection.setTypeUrl("5");
		collection.setPageNum(1);
		collection.setPageSize(100);
		PageInfo<KnowledgeFile> insertInit = knowledgeCollectionFacade
				.selectMyCollectionsByUserId(collection);
		System.out.println("===========");
		for (KnowledgeFile knowledgeFile : insertInit.getList()) {
			System.out.println(knowledgeFile);
		}
		System.out.println("===========");
	}

}
