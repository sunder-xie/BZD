package com.bizideal.whoami;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.bizideal.whoami.exception.entity.ExceptionLog;
import com.bizideal.whoami.exception.facade.ExceptionFacade;
import com.bizideal.whoami.mapper.ExceptionLogMapper;
import com.bizideal.whoami.service.ExceptionLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TestDemo {

	static ApplicationContext applicationContext ;
	static ExceptionLogService service ;
	static ExceptionLogMapper mapper;
	static ExceptionFacade mif;
	@BeforeClass
	public static void testBefore(){
		//创建一个spring容器
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		mapper = applicationContext.getBean(ExceptionLogMapper.class);
//		service=applicationContext.getBean(ExceptionLogMapper.class);
		mif=applicationContext.getBean(ExceptionFacade.class);
	}
	@Test
	public void queryExceptionLogs(){
		ExceptionLog exceptionLog = new ExceptionLog();
		exceptionLog.setStartDate("2016-01-01");
		exceptionLog.setEndDate("2018-01-01");
		exceptionLog.setPageNum(1);
		exceptionLog.setPageSize(10);
		PageHelper.startPage(exceptionLog.getPageNum(),exceptionLog.getPageSize());		
		List<ExceptionLog> list = mapper.queryExceptionLogs(exceptionLog);
		
		PageInfo<ExceptionLog> queryExceptionLogs = new PageInfo<ExceptionLog>(list);
		if(null != queryExceptionLogs  && queryExceptionLogs.getSize() > 0){
			queryExceptionLogs.getList();
		}		
		System.out.println();
	}
	
	/*public void testInsert(){
		CustomException serviceException=getNewServiceException();
		ExceptionLog ml=new ExceptionLog();
		ml.setServiceClassName(serviceException.getServiceClassName());
		ml.setCreateTime(new Date());
		ml.setMsg(serviceException.getMessage());
		ml.setServiceMethodName(serviceException.getServiceClassName());
		ml.setRemark("");
		System.out.println(mapper.insert(ml));
	}
	
	public CustomException getNewServiceException(){
		CustomException serviceException=new CustomException();
		serviceException.setServiceClassName(this.getClass().getSimpleName());
		serviceException.setServiceMethodName("TestSome");
		serviceException.setRemark("");
		return serviceException;
	}*/
}