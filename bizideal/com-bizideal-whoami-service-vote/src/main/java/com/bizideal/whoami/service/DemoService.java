package com.bizideal.whoami.service;

import com.bizideal.whoami.vote.entity.Demo;

/** 
 * @author  作者 zhushangjin: 
 * @date 创建时间：2016年11月29日 下午2:51:42 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public interface DemoService{

	int insertDemo(Demo demo);
	
	int insertTestTx();

}
