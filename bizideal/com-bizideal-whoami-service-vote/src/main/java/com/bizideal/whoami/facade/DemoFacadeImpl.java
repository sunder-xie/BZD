package com.bizideal.whoami.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.service.DemoService;
import com.bizideal.whoami.vote.entity.Demo;
import com.bizideal.whoami.vote.facade.DemoFacade;

/** 
 * @author  作者 zhshangjin: 
 * @date 创建时间：2016年11月29日 下午2:51:42 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@Component("demoFacade")
public class DemoFacadeImpl implements DemoFacade{
	@Autowired
	DemoService demoService;

	@Override
	public int insertDemo(Demo demo) {	
		return demoService.insertDemo(demo);
	}

}
