package com.bizideal.whoami.message.consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	private static final Log LOG = LogFactory.getLog(App.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:spring/spring-context.xml");
		try {
			context.start();
			LOG.info("== context start");
		} catch (Exception e) {
			context.close();
			LOG.error("== application start error:", e);
			return;
		}
		synchronized (App.class) {
			while (true) {
				try {
					App.class.wait();
				} catch (InterruptedException e) {
					LOG.error("== synchronized error:", e);
				}
			}
		}
	}
}
