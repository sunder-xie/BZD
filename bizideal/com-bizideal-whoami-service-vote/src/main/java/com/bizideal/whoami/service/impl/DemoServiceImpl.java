package com.bizideal.whoami.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.mapper.DemoMapper;
import com.bizideal.whoami.service.DemoService;
import com.bizideal.whoami.vote.entity.Demo;

/** 
 * @author  作者 zhushangjin: 
 * @date 创建时间：2016年11月29日 下午2:51:42 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService{
	@Autowired
	DemoMapper demoMapper;

	@Override
	public int insertDemo(Demo demo) {	
		return demoMapper.insert(demo);
	}

	@Override
	public int insertTestTx() {
		Demo de1 = new Demo();
		Demo de2 = new Demo();
		demoMapper.insertSelective(de1);
//		Integer.parseInt("aaa");
		demoMapper.insertSelective(de2);
		return 0;
	}

}
