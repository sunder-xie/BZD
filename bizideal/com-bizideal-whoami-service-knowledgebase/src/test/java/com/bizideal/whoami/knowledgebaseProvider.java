package com.bizideal.whoami;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class knowledgebaseProvider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:spring/spring-context.xml");
		context.start();
		System.out.println("服务已经启动dd...");
		System.in.read();
	}
}