package com.bizideal.whoami.mq;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.mqnotify.entity.MqMessage;
import com.bizideal.whoami.mqnotify.facade.MqMessageFacade;
import com.bizideal.whoami.pojo.ActiveMqQuenuesName;
import com.bizideal.whoami.pojo.DubboxResult;

public class TestDemo {

	@Test
	public void testDemo() {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
		System.out.println(11111);
		MqMessageFacade mqMessageFacade = applicationContext
				.getBean(MqMessageFacade.class);
		System.out.println(mqMessageFacade.toString());
		MqMessage mqMessage = new MqMessage();
		String json = "{\"status\":\"怪盗kidou\",\"msg\":\"24\",\"dataId\":\"ikidou@example.com\"}";
		mqMessage.setMessage(json);
		ActiveMqQuenuesName a = applicationContext.getBean(ActiveMqQuenuesName.class);
		System.out.println(a.getQuenuesNames());
	DubboxResult dubboxResult = mqMessageFacade.notifyMessage(mqMessage);
	System.out.println(dubboxResult.getDataId());
	}

}