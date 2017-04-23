package com.bizideal.whoami.weixin.mapper;

import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2016年12月22日 上午10:19:54
 */
public interface WechatResponseMessageTextMapper extends Mapper<WechatResponseMessageText>{
	/**
	 * 根据关键字获取响应消息
	 * @param reqKeyWords  关键字
	 * @return
	 */
	WechatResponseMessageText getResponseByKeyWords(String reqKeyWords);
	/**
	 * 根据关键字删除响应
	 * @param reqKeyWords 关键字
	 * @return
	 * @throws Exception
	 */
	int deleteRespByKey(String reqKeyWords) throws Exception;

}
