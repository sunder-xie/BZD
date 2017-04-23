package com.bizideal.whoami.vote.facade;

import java.util.List;
import java.util.Map;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.vote.entity.Demo;

/** 
 * @author  作者zhushangjin: 
 * @date 创建时间：2016年11月29日 下午1:46:20 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public interface DemoRestFacade {
	
	Demo insertDemo(Integer id);
	
	DubboxResult insetr(Demo demo);

	Demo get(Integer id);
	DubboxResult testList(List<Map<String, Object>> list);
	
	DubboxResult testMap(Map<String, Object> map);

}
