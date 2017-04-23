package com.bizideal.whoami.weixin.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;
import com.bizideal.whoami.weixin.mapper.WechatResponseMessageTextMapper;
import com.bizideal.whoami.weixin.service.WechatResponseMessageTextService;

/**
 * @author zhu_shangjin
 * @version 2016年12月22日 下午12:53:12
 */
@Service
public class WechatResponseMessageTextServiceImp implements
		WechatResponseMessageTextService {
	@Autowired
	WechatResponseMessageTextMapper wechatResponseMessageTextMapper;

	@Override
	public WechatResponseMessageText getResponseByKeyWords(String reqKeyWords) {
		// TODO Auto-generated method stub
		return wechatResponseMessageTextMapper.getResponseByKeyWords(reqKeyWords);
	}

}
