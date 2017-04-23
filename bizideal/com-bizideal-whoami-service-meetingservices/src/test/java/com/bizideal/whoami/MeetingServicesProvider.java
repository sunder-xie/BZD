package com.bizideal.whoami;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;
import com.bizideal.whoami.pojo.ActiveMqQuenuesName;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MeetingServicesProvider {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/spring-context.xml");
		context.start();
		System.out.println("服务已经启动...");
		System.in.read();
	}
}