package com.bizideal.whoami;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;
import com.bizideal.whoami.facade.meetingmaterial.service.MeetingAttachReadFacade;
import com.bizideal.whoami.facade.meetingmaterial.service.MeetingAttachWriteFacade;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月23日 上午9:19:32
 * @version 1.0 会议资料模块接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class JunitTest extends AbstractJUnit4SpringContextTests {

	ApplicationContext applicationContext = null;
	private MeetingAttachWriteFacade meetingAttachWriteFacade;
	private MeetingAttachReadFacade meetingAttachReadFacade;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
		// 从spring容器中获得Mapper的代理对象
		meetingAttachWriteFacade = applicationContext
				.getBean(MeetingAttachWriteFacade.class);
		meetingAttachReadFacade = applicationContext
				.getBean(MeetingAttachReadFacade.class);
	}

	@Test
	public void queryById() {
		// 根据id查询数据
		MeetingAttach insertInit = meetingAttachReadFacade.queryById(446);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void queryListByWhere() {
		// 根据条件查询数据列表
		MeetingAttach attach = new MeetingAttach();
		attach.setAttachName("b945758fd74d");
		List<MeetingAttach> insertInit = meetingAttachReadFacade
				.queryListByWhere(attach);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void queryPageListByWhere() {
		// 分页查询数据列表
		MeetingAttach attach = new MeetingAttach();
		PageInfo<MeetingAttach> insertInit = meetingAttachReadFacade
				.queryPageListByWhere(1, 10, attach);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void saveSelective() {
		// 有选择的保存，null的属性不会保存，会使用数据库默认值
		MeetingAttach attach = new MeetingAttach();
		attach.setAttachId(455);
		attach.setAttachName("资料保存测试");
		attach.setAttachIsDown("0");
		Integer insertInit = meetingAttachWriteFacade.saveSelective(attach);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void updateSelective() {
		// 有选择的更新，选择不为null的字段作为插入字段
		MeetingAttach attach = new MeetingAttach();
		attach.setAttachId(455);
		attach.setAttachName("资料更新测试");
		attach.setAttachSuffix("pdf");
		Integer insertInit = meetingAttachWriteFacade.updateSelective(attach);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void deleteLogicById() {
		// 资料逻辑删除
		MeetingAttach attach = new MeetingAttach();
		attach.setAttachId(455);
		Integer insertInit = meetingAttachWriteFacade.deleteLogicById(attach);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}
}
