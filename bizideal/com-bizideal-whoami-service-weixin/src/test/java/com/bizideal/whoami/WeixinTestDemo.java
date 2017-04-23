package com.bizideal.whoami;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.bizideal.whoami.weixin.entity.WechatConfig;
import com.bizideal.whoami.weixin.entity.WechatImage;
import com.bizideal.whoami.weixin.entity.WechatNews;
import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;
import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;
import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;
import com.bizideal.whoami.weixin.mapper.WechatConfigMapper;
import com.bizideal.whoami.weixin.mapper.WechatImageMapper;
import com.bizideal.whoami.weixin.mapper.WechatNewsMapper;
import com.bizideal.whoami.weixin.mapper.WechatRequestMessageTextMapper;
import com.bizideal.whoami.weixin.mapper.WechatResponseMessageTextMapper;
import com.bizideal.whoami.weixin.mapper.WechatTotalUserMapper;
import com.bizideal.whoami.weixin.mapper.WechatUserInfoMapper;
import com.bizideal.whoami.weixin.service.WechatUserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class WeixinTestDemo {

	@Test
	public void wechatConfigMapper() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
		WechatNewsMapper wechatNewsMapper = applicationContext.getBean(WechatNewsMapper.class);
		WechatImageMapper wechatImageMapper = applicationContext.getBean(WechatImageMapper.class);
		List<WechatImage> wechatImages = new ArrayList<WechatImage>();
		for (int i = 0; i < 2; i++) {
			WechatImage wechatImage = new WechatImage();
			wechatImage.setMediaId(i+"");
			wechatImage.setName("ddd"+i);
			wechatImage.setUrl("url"+i);
			wechatImage.setUpdateTime(new Date().getTime());
			wechatImages.add(wechatImage);
			
		}
		int iss = wechatImageMapper.batchsaveImage(wechatImages);
		PageHelper.startPage(1, 2);
		List<WechatNews> allnews = wechatNewsMapper.getNewsByPage();
		
		
		PageInfo<WechatNews> pageInfo = new PageInfo<WechatNews>(allnews);
		allnews = pageInfo.getList();
		for (WechatNews wechatNews : allnews) {
			System.out.println(wechatNews.getNewsItems().size());
		}
		WechatNews news = wechatNewsMapper.getWechatNewsByMediaId("11");
		news = wechatNewsMapper.getWechatNewsByMediaId("22");
		WechatConfigMapper wechatConfigMapper = applicationContext.getBean(WechatConfigMapper.class);
		List<WechatConfig> list = wechatConfigMapper.selectAll();
		System.out.println(111);
		WechatConfig wechatConfig = wechatConfigMapper.selectAll().get(0);
		System.out.println(wechatConfig.getEncodingAesKey());

	
	}

	@Test
	public void totalusermapper() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
          WechatTotalUserMapper wechatTotalUserMapper = applicationContext.getBean(WechatTotalUserMapper.class);
          List<WechatTotalUser> wechatTotalUsers = new ArrayList<WechatTotalUser>();
          List<String> openidsss = new ArrayList<String>();
          openidsss.add("1");
          openidsss.add("2");
          openidsss.add("3");
          List<String> gets = wechatTotalUserMapper.getFromDBByOpenids(openidsss);
          WechatTotalUser w = null;
          for (int i = 0; i <8; i++) {
			w = new WechatTotalUser(i+1, i+1+"");
			wechatTotalUsers.add(w);
		}
		
          List<WechatTotalUser> getlist = wechatTotalUserMapper.getTotalusersByOpenids(wechatTotalUsers);
          System.out.println(1111);
          List<String> openids = new ArrayList<String>();
          for (int i = 0; i < 10; i++) {
			openids.add(i+"aa");
		}
//       int i =   wechatTotalUserMapper.batchSaveTotalUser(openids);
//       System.out.println(i);
          getlist = wechatTotalUserMapper.getTotalByOpenids(openids);
          System.out.println(11111);
          int h = wechatTotalUserMapper.deleteTotalUser("3");
          System.out.println(h);
	}

	@Test
	public void wechatuserMapper() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
		WechatUserInfoMapper wechatUserInfoMapper = applicationContext.getBean(WechatUserInfoMapper.class);
		List<WechatTotalUser> wechatTotalUsers = new ArrayList<WechatTotalUser>();
        WechatTotalUser w = null;
        for (int i = 0; i <8; i++) {
			w = new WechatTotalUser(i+1, i+1+"");
			wechatTotalUsers.add(w);
		}
		List<WechatUserInfo> list = wechatUserInfoMapper.getUserByOpenids(wechatTotalUsers);
		System.out.println(111111);
		System.out.println(111111);
		
		  List<String> openids = new ArrayList<String>();
          for (int i = 0; i < 10; i++) {
			openids.add(i+"");
		}
		list = wechatUserInfoMapper.getUserinfoByOpenids(openids);
		System.out.println();
		int h = wechatUserInfoMapper.deleteWechatUser("3");
		List<WechatUserInfo> wechatUserInfos = new ArrayList<WechatUserInfo>();
		WechatUserInfo wechatUserInfo = null;
		for (int i = 0; i < 3; i++) {
			wechatUserInfo = new WechatUserInfo();
			wechatUserInfo.setCity("shanghai"+i);
			wechatUserInfo.setCountry("china"+i);
			wechatUserInfo.setGroupid(1+i);
			wechatUserInfo.setHeadimgurl("headimgurl"+i);
			wechatUserInfo.setMotherLanguage("zh_CN");
			wechatUserInfo.setNickname("nickname"+i);
			wechatUserInfo.setOpenid("openid"+i);
			wechatUserInfo.setProvince("province"+i);
			wechatUserInfo.setRemark("");
			wechatUserInfo.setSex(1);
			wechatUserInfo.setSubscribe(1+"");
			wechatUserInfo.setSubscribeTime("subscribeTime");
			wechatUserInfo.setUnionid("unionid");
			wechatUserInfos.add(wechatUserInfo);
		}
		int g = wechatUserInfoMapper.batchSaveUser(wechatUserInfos);
		System.out.println(g);
	}

	@Test
	public void userinfoservice() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
		WechatUserInfoService wechatUserInfoService = applicationContext.getBean(WechatUserInfoService.class);
		WechatAccessToken wechatAccessToken = new WechatAccessToken();
		wechatAccessToken.setExpiresIn(60);
		wechatAccessToken.setGenerateTime(14286845L);
		wechatAccessToken.setToken("token");
		String ok = wechatUserInfoService.setWechhatAccessToken(wechatAccessToken);

		WechatAccessToken ass = wechatUserInfoService.getWechatAccessToken();
		System.out.println(111);
		System.out.println(2222);
	}
	
	
	@Test
	public void responsemapper() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
//		WechatResponseMessageTextMapper responsemapper = applicationContext.getBean(WechatResponseMessageTextMapper.class);
//		WechatResponseMessageText responseMessageText = responsemapper.getResponseByKeyWords("1");
//		System.out.println(1111);
		WechatRequestMessageTextMapper requestMapper = applicationContext.getBean(WechatRequestMessageTextMapper.class);
		WechatRequestMessageText req = requestMapper.getReqAndRespByKey("11");
		System.out.println(111);
		req = requestMapper.getRequestByKey("11");
		System.out.println("dddddd");
		
	}

}