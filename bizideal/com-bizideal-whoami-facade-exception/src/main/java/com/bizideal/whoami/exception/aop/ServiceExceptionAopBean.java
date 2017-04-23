package com.bizideal.whoami.exception.aop;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizideal.whoami.exception.facade.ExceptionFacade;
import com.bizideal.whoami.pojo.CustomException;

public class ServiceExceptionAopBean {

	@Autowired
	private ExceptionFacade exceptionFacade;
	
	public void afterThrowing(Throwable e){
		exceptionFacade.insertServiceException((CustomException) e);
		System.out.println(((CustomException) e).getServiceClassName());
		System.out.println(((CustomException) e).getServiceMethodName());
		e.printStackTrace();
	}
}
