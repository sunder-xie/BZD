package com.bizideal.whoami.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.signup.entity.SignupField;
import com.bizideal.whoami.signup.entity.SignupFieldMeetingLink;
import com.bizideal.whoami.signup.entity.SignupFieldModuleLink;
import com.bizideal.whoami.signup.facade.SignUpInfoFacade;
import com.bizideal.whoami.signup.facade.SignupFieldFacade;
import com.bizideal.whoami.utils.StringUtil;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月23日 上午9:19:32
 * @version 1.0 报名字段编辑接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class JunitTest extends AbstractJUnit4SpringContextTests {

	ApplicationContext applicationContext = null;
	private SignupFieldFacade signupFieldFacade = null;
	private SignUpInfoFacade signUpInfoFacade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		// 从spring容器中获得Mapper的代理对象
		signupFieldFacade = applicationContext.getBean(SignupFieldFacade.class);
		signUpInfoFacade = applicationContext.getBean(SignUpInfoFacade.class);
	}

	@Test
	public void testWeixinLogin() throws Exception {
		// 新创建会议时，插入一条纪录
		DubboxResult insertInit = signupFieldFacade.insertInit(99);
		System.out.println("===========");
		System.out.println(insertInit.getStatus());
		System.out.println(insertInit.getMsg());
		System.out.println("===========");
	}

	@Test
	public void selectSignupFieldsByModuids() throws Exception {
		// 查询可编辑报名字段，根据会议选择的模块确定
		List<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(8);
		List<SignupField> insertInit = signupFieldFacade.selectSignupFieldsByModuids(list);
		System.out.println("===========");
		for (SignupField signupField : insertInit) {
			System.out.println(signupField);
		}
		System.out.println("===========");
	}

	@Test
	public void selectSignupFieldMeetingLinkByMeeId() throws Exception {
		// 查询会议已经选择了哪些字段
		SignupFieldMeetingLink insertInit = signupFieldFacade.selectSignupFieldMeetingLinkByMeeId(2);
		System.out.println("===========");
		System.out.println(insertInit);
		System.out.println("===========");
	}

	@Test
	public void updateField() {
		// 根据会议id，修改会议下的报名字段
		// 哈哈哈哈哈哈
		SignupFieldMeetingLink list = new SignupFieldMeetingLink(0, 2, "2,7,8,111");
		DubboxResult insertInit = signupFieldFacade.updateField(list);
		System.out.println("===========");
		System.out.println(insertInit.getStatus());
		System.out.println(insertInit.getMsg());
		System.out.println("===========");
	}

	@Test
	public void insertBasicField() {
		// 新增基础字段
		SignupField signupField = new SignupField();
		signupField.setFieldName("identity");
		signupField.setFieldBasics(false);
		signupField.setFieldDsp("身份");
		DubboxResult insertInit = signupFieldFacade.insertBasicField(signupField);
		System.out.println("===========");
		System.out.println(insertInit.getStatus());
		System.out.println(insertInit.getMsg());
		System.out.println("===========");
	}

	@Test
	public void updateBasicField() {
		// 更新基础字段
		SignupField signupField = new SignupField();
		signupField.setFieldId(7);
		signupField.setFieldName("identity11");
		signupField.setFieldBasics(true);
		signupField.setFieldDsp("身份1111");
		DubboxResult insertInit = signupFieldFacade.updateBasicField(signupField);
		System.out.println("===========");
		System.out.println(insertInit.getStatus());
		System.out.println(insertInit.getMsg());
		System.out.println("===========");
	}

	@Test
	public void insertSignupFieldModuleLink() {
		// 将单个字段分配给某个模块
		SignupFieldModuleLink signupField = new SignupFieldModuleLink();
		signupField.setFieldId(3);
		signupField.setModuleId(98);
		signupField.setDsp("测试，随便填");
		DubboxResult insertInit = signupFieldFacade.insertSignupFieldModuleLink(signupField);
		System.out.println("===========");
		System.out.println(insertInit.getStatus());
		System.out.println(insertInit.getMsg());
		System.out.println("===========");
	}

	@Test
	public void updateSignupFieldModuleLink() {
		// 修改将单个字段分配给某个模块
		SignupFieldModuleLink signupField = new SignupFieldModuleLink();
		signupField.setFieldId(3);
		signupField.setModuleId(97);
		signupField.setDsp("测试，随便填11222");
		DubboxResult insertInit = signupFieldFacade.updateSignupFieldModuleLink(signupField);
		System.out.println("===========");
		System.out.println(insertInit.getStatus());
		System.out.println(insertInit.getMsg());
		System.out.println("===========");
	}

	@Test
	public void deleteSignupFieldModuleLink() {
		// 删除某个字段的模块关联
		SignupFieldModuleLink signupField = new SignupFieldModuleLink();
		signupField.setFieldId(3);
		signupField.setModuleId(9811);
		signupField.setDsp("测试，随便填77777");
		DubboxResult insertInit = signupFieldFacade.deleteSignupFieldModuleLink(signupField);
		System.out.println("===========");
		System.out.println(insertInit.getStatus());
		System.out.println(insertInit.getMsg());
		System.out.println("===========");
	}

	@Test
	public void userSignUp() {
		// 删除某个字段的模块关联
		SignUpInfoDto dto = new SignUpInfoDto();
		dto.setSignUpId(StringUtil.get32UUID());
		dto.setCreateDatatime(System.currentTimeMillis());
		dto.setEmail("2333555@qq.com");
		dto.setUserId("58c0cf2f864270150cd000d6");
		dto.setHotelId(10);
		dto.setRoomId(59);
		dto.setDietId(2);
		dto.setMeethallId(482);
		dto.setMeetingId(597);
		dto.setPhone("15810026197");
		dto.setRealName("liulq");
		dto.setSex("男");
		int userSignUp = signUpInfoFacade.userSignUp(dto);
		System.out.println("===========");
		System.out.println(userSignUp);
		System.out.println("===========");
	}

}
