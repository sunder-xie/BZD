package com.bizideal.whoami.weixin.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.weixin.entity.WechatConfig;
import com.bizideal.whoami.weixin.mapper.WechatConfigMapper;
import com.bizideal.whoami.weixin.service.WechatConfigService;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 上午11:08:29
 */
@Service
public class WechatConfigServiceImp implements WechatConfigService{
	@Autowired
	WechatConfigMapper wechatConfigMapper;

	@Override
	public WechatConfig getWechatConfig(String profile) {
		return wechatConfigMapper.getConfigByProfile(profile);
		
	}

}
