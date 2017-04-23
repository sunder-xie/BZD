package com.bizideal.whoami;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.member.entity.MemberPersonInfo;
import com.bizideal.whoami.member.entity.MemberTypeInfo;
import com.bizideal.whoami.member.entity.MemberUnitInfo;
import com.bizideal.whoami.member.facade.MemberPersonInfoFacade;
import com.bizideal.whoami.member.facade.MemberTypeInfoFacade;
import com.bizideal.whoami.member.facade.MemberUnitInfoFacade;

public class NewMemberTest {

	ApplicationContext applicationContext = null;
	MemberTypeInfoFacade typeInfoFacade = null;
	MemberUnitInfoFacade unitInfoFacade = null;
	MemberPersonInfoFacade personInfoFacade = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		typeInfoFacade = applicationContext.getBean(MemberTypeInfoFacade.class);
		unitInfoFacade = applicationContext.getBean(MemberUnitInfoFacade.class);
		personInfoFacade = applicationContext.getBean(MemberPersonInfoFacade.class);
	}

	@Test
	public void save1() {
		MemberTypeInfo t = new MemberTypeInfo();
		t.setHallId(1);
		t.setName("会员分类005");
		t.setType(0);
		Integer save = typeInfoFacade.save(t);
		System.out.println(save);
	}

	@Test
	public void update1() {
		MemberTypeInfo t = new MemberTypeInfo();
		t.setId(1);
		t.setHallId(2);
		t.setName("会员分类001");
		t.setType(0);
		Integer update = typeInfoFacade.update(t);
		System.out.println(update);
	}

	@Test
	public void delete1() {
		System.out.println(typeInfoFacade.deleteById(2));
	}

	@Test
	public void select1() {
		System.out.println(typeInfoFacade.selectById(1));
		System.out.println(typeInfoFacade.selectListByHallId(377));
	}

	@Test
	public void save2() {
		MemberUnitInfo t = new MemberUnitInfo();
		t.setHallId(1);
		t.setMemberTypeId(1);
		t.setUnitName("组织会员006");
		t.setDsp("备注");
		System.out.println(unitInfoFacade.save(t));
	}

	@Test
	public void update2() {
		MemberUnitInfo t = new MemberUnitInfo();
		t.setId(1);
		t.setHallId(2);
		t.setMemberTypeId(12);
		t.setUnitName("组织会员001");
		t.setDsp("备注");
		System.out.println(unitInfoFacade.update(t));
	}

	@Test
	public void delete2() {
		System.out.println(unitInfoFacade.deleteById(2));
	}

	@Test
	public void select2() {
		System.out.println(unitInfoFacade.selectById(1));
		List<MemberUnitInfo> list = unitInfoFacade.selectListByHallId(1, 1, 500).getList();
		System.out.println("list1==============");
		for (MemberUnitInfo memberUnitInfo : list) {
			System.out.println(memberUnitInfo);
		}
		List<MemberUnitInfo> list2 = unitInfoFacade.selectListByHallIdAndString(1, "3", 1, 500).getList();
		System.out.println("list2==============");
		for (MemberUnitInfo memberUnitInfo : list2) {
			System.out.println(memberUnitInfo);
		}
		List<MemberUnitInfo> list3 = unitInfoFacade.selectListByTypeId(1, 1, 500).getList();
		System.out.println("list3==============");
		for (MemberUnitInfo memberUnitInfo : list3) {
			System.out.println(memberUnitInfo);
		}
	}

	@Test
	public void save3() {
		MemberPersonInfo t = new MemberPersonInfo();
		t.setDsp("备注");
		t.setHallId(1);
		t.setMemberTypeId(12);
		t.setPersonName("小王6");
		t.setPhone("13999999999");
		System.out.println(personInfoFacade.save(t));
	}

	@Test
	public void update3() {
		MemberPersonInfo t = new MemberPersonInfo();
		t.setId(1);
		t.setDsp("备注");
		t.setHallId(5);
		t.setMemberTypeId(12);
		t.setPersonName("小王");
		t.setPhone("13999998888");
		System.out.println(personInfoFacade.update(t));
	}

	@Test
	public void delete3() {
		System.out.println(personInfoFacade.deleteById(4));
	}

	@Test
	public void select3() {
		System.out.println(personInfoFacade.selectByPhoneAndHallId("13999998888", 1));
		List<MemberPersonInfo> list = personInfoFacade.selectListByHallId(5, 1, 500).getList();
		System.out.println("list1============");
		for (MemberPersonInfo memberPersonInfo : list) {
			System.out.println(memberPersonInfo);
		}
		List<MemberPersonInfo> list2 = personInfoFacade.selectListByTypeId(12, 1, 500).getList();
		System.out.println("list2============");
		for (MemberPersonInfo memberPersonInfo : list2) {
			System.out.println(memberPersonInfo);
		}
	}

}
