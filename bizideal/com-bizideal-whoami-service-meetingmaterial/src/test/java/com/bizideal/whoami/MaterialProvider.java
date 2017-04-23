package com.bizideal.whoami;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;

public class MaterialProvider {
	
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:spring/spring-context.xml");
		context.start();
		System.out.println("服务已经启动...");
		System.in.read();
	}
	
}