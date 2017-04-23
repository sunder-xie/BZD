package com.bizideal.whoami.facade.im.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.core.im.biz.ChatMessageService;
import com.bizideal.whoami.im.facade.ChatMessageFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName ChatMessageFacadeImpl
 * @Description TODO(获取聊天消息资源)
 * @Author Zj.Qu
 * @Date 2016-08-02 15:44:18
 */
@Path("/chatMessage")
@Component("chatMessageFacade")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class ChatMessageFacadeImpl implements ChatMessageFacade {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ChatMessageFacadeImpl.class);

	protected static final JsonNodeFactory factory = JsonNodeFactory.instance;

	@Autowired
	private ChatMessageService chatMessageService;

	public String latestMsg() throws CustomException{
		ObjectNode queryStrNode = factory.objectNode();
		queryStrNode.put("limit", "20");
		String messages = chatMessageService.getChatMessages(queryStrNode).toString();
		return messages;
	}

	public String sevenDayMsg() throws CustomException{
		// 聊天消息 获取7天以内的消息
		String currentTimestamp = String.valueOf(System.currentTimeMillis());
		String senvenDayAgo = String.valueOf(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
		ObjectNode queryStrNode = factory.objectNode();
		queryStrNode.put("ql", "select * where  timestamp > " + senvenDayAgo + " and timestamp < " + currentTimestamp);
		String messages = chatMessageService.getChatMessages(queryStrNode).toString();
		return messages;
	}

	public String pageMsg(int limit, int pageIndex) throws CustomException{

		ObjectNode queryStrNode = factory.objectNode();
		queryStrNode.put("limit", limit);
		ObjectNode messages = null;
		for (int i = 0; i < pageIndex; ++i) {
			messages = chatMessageService.getChatMessages(queryStrNode);
			if (null == messages.get("cursor")) {
				break;
			}
			String cursor = messages.get("cursor").asText();
			queryStrNode.put("cursor", cursor);
		}
		return messages.toString();
	}

	public String getChatMessages(ObjectNode queryStrNode) throws CustomException{
		return null;
	}
}
