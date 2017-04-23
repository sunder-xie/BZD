package com.bizideal.whoami.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageProvider {

	public static void main(String[] args) throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:spring/spring-context.xml");
		context.start();
		System.out.println("服务已经启动...");
		System.in.read();
	}
}
