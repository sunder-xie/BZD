//package com.bizideal.whoami;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.bizideal.whoami.facade.hotel.entity.Demo;
//import com.bizideal.whoami.hotel.mapper.DemoMapper;
////import com.bizideal.whoami.hotel.service.DemoService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//
//
//
//public class TestDemo {
//
//		@Test
//	public void testDemo() {
//		//创建一个spring容器
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//		
//		//从spring容器中获得Mapper的代理对象
//		DemoMapper mapper = applicationContext.getBean(DemoMapper.class);
//		DemoService demoService = applicationContext.getBean(DemoService.class);
//		
////		223213424
////		RedisClientTemplate redisClientTemplate = applicationContext.getBean(RedisClientTemplate.class);
////		redisClientTemplate.deteteByKey("223213424");
//		//执行查询，并分页
//		Demo demo = new Demo();
//		
//		demo.setName("demo");
//		mapper.insert(demo);
//		//mapper.insertSelective(demo);
////		int id = demoService.insertDemo(demo);
////		System.out.println(id);
//		PageHelper.startPage(1, 3);
//		List<Demo> list = mapper.selectAll();
//		PageInfo<Demo> pageInfo = new PageInfo<Demo>(list);
//		List<Demo> demos = pageInfo.getList();
//		for (Demo demo2 : demos) {
//			System.out.println(demo2.getName());
//		}
//	}
//		
//		
//		@Test
//		public void testTx() {
//			//测试事务
//			//创建一个spring容器
//			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
//			
//			//从spring容器中获得Mapper的代理对象
//			
//			DemoService demoService = applicationContext.getBean(DemoService.class);
////			demoService.insertTestTx();
//			
//
//		}
// }