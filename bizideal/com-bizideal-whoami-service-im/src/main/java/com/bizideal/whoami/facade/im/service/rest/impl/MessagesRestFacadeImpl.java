package com.bizideal.whoami.facade.im.service.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.core.im.biz.MessagesService;
import com.bizideal.whoami.im.entity.IMMsg;
import com.bizideal.whoami.im.facade.rest.MessagesRestFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName MessagesRestFacadeImpl
 * @Description (消息处理接口)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */

@Path("/messages")
@Component("messagesRestFacade")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class MessagesRestFacadeImpl implements MessagesRestFacade{

	@Autowired
	private MessagesService messagesService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MessagesRestFacadeImpl.class);
    @POST
    @Path("sendMessages")
    public String sendMessages(IMMsg msg)throws CustomException{
        ObjectNode sendTxtMessageusernode = messagesService.sendMessages(msg);
        LOGGER.debug(msg.toString());
        return sendTxtMessageusernode.toString();
    }
	
    @POST
    @Path("sendOpenMessage")
    public String sendOpenMessage(IMMsg msg)throws CustomException{
        ObjectNode sendTxtMessageusernode = messagesService.sendOpenMessage(msg);
        return sendTxtMessageusernode.toString();
    }
    

    @POST
    @Path("sendGroupMessages/{group_id}")
    public String sendGroupMessages(@PathParam("group_id") String group_id,IMMsg msg)throws CustomException{
    	 ObjectNode sendTxtMessageusernode = messagesService.sendGroupMessages(group_id,msg);
         return sendTxtMessageusernode.toString();
    }
}
