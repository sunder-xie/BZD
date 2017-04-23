package com.bizideal.whoami;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VoteProvider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:spring/spring-context.xml");
		context.start();
		System.out.println("服务已经启动...");
		System.in.read();

	}
}