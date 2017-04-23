package com.bizideal.whoami.facade.im.service.impl;

import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.im.biz.MessagesService;
import com.bizideal.whoami.im.entity.IMMsg;
import com.bizideal.whoami.im.facade.MessagesFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName MessagesFacadeImpl
 * @Description (消息处理接口)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */

@Component("messagesFacade")
public class MessagesFacadeImpl implements MessagesFacade {

	@Autowired
	private MessagesService messagesService;

	public String sendMessages(IMMsg msg) throws CustomException{
		ObjectNode sendTxtMessageusernode = messagesService.sendMessages(msg);
		return sendTxtMessageusernode.toString();
	}

	public String sendOpenMessage(IMMsg msg) throws CustomException{
		ObjectNode sendTxtMessageusernode = messagesService.sendOpenMessage(msg);
		return sendTxtMessageusernode.toString();
	}

	public String sendGroupMessages(String group_id, IMMsg msg) throws CustomException{
		ObjectNode sendTxtMessageusernode = messagesService.sendGroupMessages(group_id, msg);
		return sendTxtMessageusernode.toString();
	}
}
