package com.bizideal.whoami.weixin.mapper;





import com.bizideal.whoami.weixin.entity.WechatConfig;

import tk.mybatis.mapper.common.Mapper;

/** 
 * @author  作者 zhushangjin: 
 * @date 创建时间：2016年11月29日 下午2:29:53 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public interface WechatConfigMapper extends Mapper<WechatConfig>{
	/**
	 * 根据微信公众号的环境配置获取微信公众号配置
	 * @param profile  微信的环境配置
	 * @return
	 */
	WechatConfig getConfigByProfile(String profile);
	

}
