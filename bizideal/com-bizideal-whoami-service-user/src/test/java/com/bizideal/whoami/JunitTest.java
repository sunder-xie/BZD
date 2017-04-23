package com.bizideal.whoami;

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

import com.bizideal.whoami.croe.RedisClientTemplate;
import com.bizideal.whoami.user.dto.SignUpMeetingDto;
import com.bizideal.whoami.user.dto.UserInfoDto;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.HuanxinFriendRestFacade;
import com.bizideal.whoami.user.facade.UserInfoFacade;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.ProtostuffConvert;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月23日 上午9:19:32
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class JunitTest extends AbstractJUnit4SpringContextTests {

	ApplicationContext applicationContext = null;
	private UserWeixinInfoFacade userWeixinInfoFacade = null;
	private UserInfoFacade userInfoFacade = null;
	private HuanxinFriendRestFacade huanxinFriendRestFacade = null;
	private RedisClientTemplate redisClientTemplate = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
		// 从spring容器中获得Mapper的代理对象
		userWeixinInfoFacade = applicationContext
				.getBean(UserWeixinInfoFacade.class);
		userInfoFacade = applicationContext.getBean(UserInfoFacade.class);
		huanxinFriendRestFacade = applicationContext
				.getBean(HuanxinFriendRestFacade.class);
		redisClientTemplate = applicationContext
				.getBean(RedisClientTemplate.class);
	}

	@Test
	public void testWeixinLogin() {
		int i = 1;
		UserWeixinInfo userWeixinInfo = new UserWeixinInfo();
		userWeixinInfo.setUnionid("unionid" + i);
		userWeixinInfo.setOpenid("openid" + i);
		userWeixinInfo.setCountry("中国" + i);
		userWeixinInfo.setProvince("shanghai" + i);
		userWeixinInfo.setCity("sh" + i);
		userWeixinInfo.setNickname("nk" + i);
		userWeixinInfo.setRealName("liulq" + i);
		userWeixinInfo.setSubscribe("sub" + i);
		userWeixinInfo.setGroupid("groupid" + i);
		userWeixinInfo.setWeixin("weixin" + i);
		userWeixinInfo.setHeadimgurl("headimg" + i);
		UserWeixinInfo weixinLogin = userWeixinInfoFacade
				.weixinLogin(userWeixinInfo);
		System.out.println(weixinLogin);
	}

	@Test
	public void bindPhone() {
		UserWeixinInfo weixinInfo = new UserWeixinInfo();
		weixinInfo.setUnionid("obduMv-j89BbnE3bpMnCdaq45e0E");
		weixinInfo.setWeixin("weixin_test");
		weixinInfo.setPhone("15505510628");
		weixinInfo.setPassword("921228");
		weixinInfo.setMsgCode("163662");
		try {
			System.out.println(userInfoFacade.updateBindPhone(weixinInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sendMsg() {
		userWeixinInfoFacade.sendMsgCodeToAnyOne("13262852857");
	}

	@Test
	public void updatePassword() {
		UserWeixinInfo weixinInfo = new UserWeixinInfo();
		weixinInfo.setUserId("585c8f4d983cbc1840794e2e");
		weixinInfo.setPhone("13262852857");
		weixinInfo.setMsgCode("999998");
		weixinInfo.setPassword("921228");
		System.out.println(userInfoFacade.updatePassword(weixinInfo));
	}

	@Test
	public void testUpdateI() {
		UserWeixinInfo weixinInfo = new UserWeixinInfo();
		weixinInfo.setUserId("585c8f4d983cbc1840794e2e");
		weixinInfo.setNickname("llliii");
		weixinInfo
				.setHeadimgurl("group1/M00/00/00/CgEDcFhTjw-AbBUaAAB7z93vu5o960.png");
		System.out.println(userInfoFacade.updateImgAndName(weixinInfo));
	}

	@Test
	public void send() {
		ProtostuffConvert<String> proto = new ProtostuffConvert<String>(
				String.class);
		byte[] convert = proto.convert("999998");
		redisClientTemplate.putObject("13262852857", 3600 * 24, convert); // redis中保存120秒
	}

	@Test
	public void insertddd() {
		UserInfoDto dto = new UserInfoDto();
		dto.setPhone("13266652857");
		dto.setPassword("921228");
		dto.setMsgCode("999998");
		System.out.println("==========");
		System.out.println(userInfoFacade.insertRegister(dto));
		System.out.println("==========");
	}

	@Test
	public void selectByUserIds() {
		List<String> list = new ArrayList<String>();
		list.add("58746b89d41817193221a10e");
		list.add("585cb7b5d41817999d276108");
		list.add("5851ef28d4181793c129c970");
		List<UserWeixinInfo> selectByUserIds = userWeixinInfoFacade
				.selectByUserIds(list);
		System.out.println("==========");
		for (UserWeixinInfo string : selectByUserIds) {
			System.out.println(string);
		}
		System.out.println("==========");
	}

	@Test
	public void updateImgAndName() {
		/* 头像和昵称修改测试+真实姓名 */
		UserWeixinInfo weixinInfo = new UserWeixinInfo();
		weixinInfo.setUserId("5876e0c5d4181772b7ebfc20");
		weixinInfo
				.setHeadimgurl("group1/M00/00/07/CgEDcFh9doqABOHGAADSFbcZiGc038.png");
		weixinInfo.setNickname("liulq233");
		weixinInfo.setRealName("刘立庆");
		System.out.println("==========");
		System.out.println(userInfoFacade.updateImgAndName(weixinInfo));
		System.out.println("==========");
	}

	@Test
	public void selectBySignUp() {
		/* 头像和昵称测试 */
		SignUpMeetingDto signUpMeetingDto = new SignUpMeetingDto();
		signUpMeetingDto.setMeeId(379);
		signUpMeetingDto.setName("");
		signUpMeetingDto.setPageNum(1);
		signUpMeetingDto.setPageSize(10);
		PageInfo<UserWeixinInfo> selectBySignUp = userWeixinInfoFacade
				.selectBySignUp(signUpMeetingDto);
		System.out.println("==========");
		for (UserWeixinInfo userWeixinInfo : selectBySignUp.getList()) {
			System.out.println(userWeixinInfo);
		}
		System.out.println("==========");
	}

	public static void main(String[] args) {

	}
}
