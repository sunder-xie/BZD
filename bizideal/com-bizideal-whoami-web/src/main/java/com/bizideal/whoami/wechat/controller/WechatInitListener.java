package com.bizideal.whoami.wechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;

import com.bizideal.whoami.croe.JedisDataSource;
import com.bizideal.whoami.croe.RedisClientTemplate;
import com.bizideal.whoami.wechat.service.CoreService;
import com.bizideal.whoami.wechat.service.WechatInitService;
/**
 * @author zhu_shangjin
 * @version 2017年1月3日 下午2:49:45
 * @param <ContextRefreshedEvent>
 */
@Component
public class WechatInitListener implements ApplicationListener<ContextRefreshedEvent>{
	private static final Logger LOG = LoggerFactory
			.getLogger(WechatInitListener.class);
	@Autowired
	WechatInitService wechatInitService;
     //从微信公众号获取用户并插入数据库
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		LOG.info("微信初始化用户表和用户openid表");
		try {
			//wechatInitService.initWechatTotalUser();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

}