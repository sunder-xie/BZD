package com.bizideal.whoami.rolemodule;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
 
public class RoleModuleProvider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/spring-context.xml");
        context.start();
        System.out.println("服务已经启动...");
      System.in.read();
        
//		synchronized (RoleModuleProvider.class) {
//		while (true) {
//			try {
//				RoleModuleProvider.class.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
    }
}