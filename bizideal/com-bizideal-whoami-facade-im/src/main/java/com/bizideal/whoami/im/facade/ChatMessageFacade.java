package com.bizideal.whoami.im.facade;

import org.codehaus.jackson.node.ObjectNode;

import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName ChatMessageFacade
 * @Description (获取聊天消息接口)
 * @Author Zj.Qu
 * @Date 2017-01-05 10:02:17
 */
public interface ChatMessageFacade {

	/**
	 * 获取聊天消息
	 * @param queryStrNode
	 */
	String getChatMessages(ObjectNode queryStrNode) throws CustomException;

}