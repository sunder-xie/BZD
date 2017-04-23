package com.bizideal.whoami.core.im.biz.impl;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.node.ObjectNode;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.im.biz.ChatMessageService;
import com.bizideal.whoami.core.im.common.HTTPMethod;
import com.bizideal.whoami.core.im.utils.ResteasyUtils;
import com.bizideal.whoami.core.im.vo.EndPoints;
import com.bizideal.whoami.im.enums.ImFacadeEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;

/**
 * 
 * @ClassName ChatMessageServiceImpl
 * @Description (聊天记录)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
@Service("chatMessageService")
public class ChatMessageServiceImpl extends BaseServiceImpl implements ChatMessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatMessageServiceImpl.class);

    @Override
	public ObjectNode getChatMessages(ObjectNode queryStrNode)  throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
			// check appKey format
			try {
				ResteasyWebTarget webTarget = EndPoints.CHATMESSAGES_TARGET;
				if (null != queryStrNode && null != queryStrNode.get("ql") && !StringUtils.isEmpty(queryStrNode.get("ql").asText())) {
					webTarget = webTarget.queryParam("ql", queryStrNode.get("ql").asText());
				}
				if (null != queryStrNode && null != queryStrNode.get("limit") &&!StringUtils.isEmpty(queryStrNode.get("limit").asText())) {
					webTarget = webTarget.queryParam("limit", queryStrNode.get("limit").asText());
				}
				if (null != queryStrNode && null != queryStrNode.get("cursor") &&!StringUtils.isEmpty(queryStrNode.get("cursor").asText())) {
					webTarget = webTarget.queryParam("cursor", queryStrNode.get("cursor").asText());
				}
				objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
			} catch (Exception e) {
				LOGGER.error("聊天出错",e);
			}
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatMessageServiceImpl_getChatMessages_00_ERROR.getCode(),
					ImFacadeEnums.ChatMessageServiceImpl_getChatMessages_00_ERROR.getMsg());
			String params="";
			params=queryStrNode+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

}
