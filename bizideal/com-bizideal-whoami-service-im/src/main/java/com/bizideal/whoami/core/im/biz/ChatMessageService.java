package com.bizideal.whoami.core.im.biz;

import org.codehaus.jackson.node.ObjectNode;

import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName ChatMessageService
 * @Description (获取聊天消息接口)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
public interface ChatMessageService {

	/**
	 * 获取聊天消息
	 * @param queryStrNode
	 */
	ObjectNode getChatMessages(ObjectNode queryStrNode) throws CustomException;

}