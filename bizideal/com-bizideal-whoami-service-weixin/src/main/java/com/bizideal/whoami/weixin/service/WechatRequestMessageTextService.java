package com.bizideal.whoami.weixin.service;

import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;

/**
 * @author zhu_shangjin
 * @version 2016年12月22日 下午12:49:57
 */
public interface WechatRequestMessageTextService {
    /**
     * 根据关键字获取文本请求消息和响应消息
     * @param reqKeyWords  关键字
     * @return
     */
	WechatRequestMessageText getReqAndRespByKey(String reqKeyWords);
/**
 * 根据关键字获取文本请求消息（不包含响应消息）
 * @param reqKeyWords 关键字
 * @return
 */
WechatRequestMessageText getRequestByKey(String reqKeyWords);
	/**
	 * 新增自动回复请求规则（含响应）
	 * @param wechatRequestMessageText  
	 * @return
	 * @throws Exception
	 */
	int insertReqAndResp(WechatRequestMessageText wechatRequestMessageText) throws Exception;
	/**
	 * 根据关键字删除请求和响应
	 * @param keyWords 关键字
	 * @return
	 * @throws Exception
	 */
	int deleteReqAndRespByKey(String keyWords) throws Exception;
	/**
	 *更新请求和响应消息
	 * @param wechatRequestMessageText
	 * @return
	 * @throws Exception
	 */
	int updateReqAndResp(WechatRequestMessageText wechatRequestMessageText) throws Exception;

}
