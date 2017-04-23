package com.bizideal.whoami;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName IMProvider
 * @Description TODO(程序启动方式)
 * @Author Zj.Qu
 * @Date 2017-01-06 13:24:06
 */
public class IMProvider {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:spring/spring-context.xml");
		context.start();
		System.out.println("服务已经启动...");
		System.in.read();
	}
}