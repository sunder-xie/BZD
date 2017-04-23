package com.bizideal.whoami.weixin.service;

import com.bizideal.whoami.weixin.entity.WechatConfig;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 上午11:07:59
 */
public interface WechatConfigService {
	/**
	 * 根据环境配置获取公众号配置
	 * @param profile  环境配置
	 * @return
	 */
	WechatConfig getWechatConfig(String profile);

}
