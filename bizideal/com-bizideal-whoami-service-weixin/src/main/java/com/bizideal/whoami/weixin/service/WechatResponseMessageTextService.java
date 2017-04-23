package com.bizideal.whoami.weixin.service;

import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;

/**
 * @author zhu_shangjin
 * @version 2016年12月22日 下午12:50:23
 */
public interface WechatResponseMessageTextService {
	/**
	 * 根据关键字获取文本响应消息
	 * @param reqKeyWords  关键字
	 * @return
	 */
	WechatResponseMessageText getResponseByKeyWords(String reqKeyWords);

}
