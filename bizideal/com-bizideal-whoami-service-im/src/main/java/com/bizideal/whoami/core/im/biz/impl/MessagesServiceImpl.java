package com.bizideal.whoami.core.im.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.im.biz.MessagesService;
import com.bizideal.whoami.core.im.common.HTTPMethod;
import com.bizideal.whoami.core.im.utils.ResteasyUtils;
import com.bizideal.whoami.core.im.vo.EndPoints;
import com.bizideal.whoami.im.entity.IMMsg;
import com.bizideal.whoami.im.enums.ImFacadeEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;

/**
 * 
 * @ClassName MessagesServiceImpl
 * @Description (发送消息)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
@Service("messagesService")
public class MessagesServiceImpl extends BaseServiceImpl implements MessagesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessagesServiceImpl.class);

	@Override
	public ObjectNode sendMessages(IMMsg msg) throws CustomException{

		try{
	//		String targetType, ArrayNode target, ObjectNode msg, String from,
	//		ObjectNode ext
			ObjectNode ext = factory.objectNode();
			Date date=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = sdf.format(date);
			ext.put("meetId", msg.getMeetId());
			ext.put("meetName", msg.getMeetName());
			ext.put("subMeetId", msg.getSubMeetId()==null?"":msg.getSubMeetId());
			ext.put("subMeetName", msg.getSubMeetName()==null?"":msg.getSubMeetName());
			ext.put("title", msg.getTitle());
			ext.put("time", s);
			if(msg.getMsg().getType().equals("img")){
				ext.put("messageType", "news");
				ext.put("httpUrl", msg.getContent());
				ext.put("imageUrl", msg.getImageUrl());
			}else if(msg.getMsg().getType().equals("txt")){
				ext.put("messageType", "text");
				ext.put("content", msg.getContent());
			}
			
			return sendMsg(msg,ext);
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.MessagesServiceImpl_sendMessages_00_ERROR.getCode(),
					ImFacadeEnums.MessagesServiceImpl_sendMessages_00_ERROR.getMsg());
			String params="";
			params=msg+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
	
	private ObjectNode sendMsg(IMMsg msg,ObjectNode ext)throws CustomException{
		ObjectNode objectNode = factory.objectNode();

		ArrayNode target = JsonNodeFactory.instance.arrayNode();
		ObjectNode dataNode = factory.objectNode();
		ObjectNode msgNode = factory.objectNode();

		// check properties that must be provided
		if (!("users".equals(msg.getTarget_type()) || !"chatgroups".equals(msg.getTarget_type()))) {
			LOGGER.error("TargetType must be users or chatgroups .");

			objectNode.put("message", "TargetType must be users or chatgroups .");

			return objectNode;
		}

		try {
			// 构造消息体
			ObjectMapper m=new ObjectMapper();
			for(String s:msg.getTarget()){
				target.add(s);
			}
			
			dataNode.put("target_type", msg.getTarget_type());
			dataNode.put("target", target);
			if(msg.getMsg().getType().equals("txt")){
				msgNode=this.getTxtMessage(msg);
			}else if(msg.getMsg().getType().equals("img")){
				msgNode=this.getImgMessage(msg);
			}
			
//			msgNode=(ObjectNode) m.readTree(s);
			dataNode.put("msg", msgNode);
			dataNode.put("from", msg.getFrom());
			if(null!=ext){
				dataNode.put("ext", ext);
			}
			System.out.println(m.writeValueAsString(msg.getTarget()));

			ResteasyWebTarget webTarget = EndPoints.MESSAGES_TARGET;

			objectNode = ResteasyUtils.sendRequest(webTarget, dataNode, credential, HTTPMethod.METHOD_POST, null);

			objectNode = (ObjectNode) objectNode.get("data");
			String [] target1=msg.getTarget();
			for (int i = 0; i < target1.length; i++) {
				String resultStr = objectNode.path(target1[i]).asText();
				if ("success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] successfully .", target1[i]));
				} else if (!"success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] failed .",target1[i]));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 向某个群组的所有成员发送消息
	 * @param groupIds
	 * @param msg
	 * @return
	 */
	public ObjectNode sendGroupMessages(String groupId,IMMsg msg)throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	//		groupId="236500167363133864";
			JsonNode memberNode = factory.objectNode();
			// check appKey format
	
			try {
				ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.path(groupId).path("users");
				objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("groupId======"+groupId);
			System.out.println("msg======"+msg);
			System.out.println("objectNode======"+objectNode);
			memberNode=objectNode.get("data");
			System.out.println("memberNode======"+memberNode);
			int j=0;
			List<String> ls=new ArrayList<String>();
			while(memberNode.has(j)){
				JsonNode a=memberNode.get(j);
				if(a.has("owner")){
					ls.add(a.get("owner").asText());
				}else if(a.has("member")){
					ls.add(a.get("member").asText());
				}
				++j;
			}
			//组成用户目标
			String target[]=new String[ls.size()];
			for(int i=0;i<ls.size();++i){
				target[i]=ls.get(i);
			}
			msg.setTarget(target);
			ObjectNode ext = factory.objectNode();
	
			Date date=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = sdf.format(date);
			ext.put("meetId", msg.getMeetId());
			ext.put("meetName", msg.getMeetName());
			ext.put("subMeetId", msg.getSubMeetId()==null?"":msg.getSubMeetId());
			ext.put("subMeetName", msg.getSubMeetName()==null?"":msg.getSubMeetName());
			ext.put("title", msg.getTitle());
			ext.put("time", s);
			if(msg.getMsg().getType().equals("img")){
				ext.put("messageType", "news");
				ext.put("httpUrl", msg.getContent());
	//			ext.put("httpUrl", msg.getContent());
	//			ext.put("meetId", msg.getMeetId());
	//			ext.put("meetName", msg.getMeetName());
	//			ext.put("subMeetId", msg.getSubMeetId()==null?"":msg.getSubMeetId());
	//			ext.put("subMeetName", msg.getSubMeetName()==null?"":msg.getSubMeetName());
	//			ext.put("title", msg.getTitle());
				ext.put("imageUrl", msg.getImageUrl());
			}else if(msg.getMsg().getType().equals("txt")){
				ext.put("messageType", "text");
				ext.put("content", msg.getContent());
			}
			
			return this.sendMsg(msg,ext);
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.MessagesServiceImpl_sendGroupMessages_00_ERROR.getCode(),
					ImFacadeEnums.MessagesServiceImpl_sendGroupMessages_00_ERROR.getMsg());
			String params="";
			params="groupId:"+groupId+","+msg+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
	
	/**
	 * 拼装TXT消息
	 * @param msg
	 * @return
	 */
	private ObjectNode getTxtMessage(IMMsg msg)throws CustomException{
		
		ObjectNode msgContent = factory.objectNode();

		msgContent.put("type", msg.getMsg().getType());
		msgContent.put("msg",msg.getMsg().getMsg());
		
		return msgContent;
	}
	
	/**
	 * 拼装图片消息
	 * @param msg
	 * @return
	 */
	private ObjectNode getImgMessage(IMMsg msg)throws CustomException{
		
		ObjectNode msgContent = factory.objectNode();
		ObjectNode size = factory.objectNode();

		msgContent.put("type", msg.getMsg().getType());
		msgContent.put("url", msg.getMsg().getUrl());
		msgContent.put("filename", msg.getMsg().getFilename());
		msgContent.put("secret", msg.getMsg().getSecret());
		size.put("width", msg.getMsg().getSize().getWidth());
		size.put("height", msg.getMsg().getSize().getHeight());
		msgContent.put("size", size);
		
		return msgContent;
	}

	@Override
	public ObjectNode sendOpenMessage(IMMsg msg) throws CustomException{

		try{
	//		String targetType, ArrayNode target, ObjectNode msg, String from,
	//		ObjectNode ext
			ObjectNode objectNode = factory.objectNode();
	
			// check properties that must be provided
			if (!("users".equals(msg.getTarget_type()) || !"chatgroups".equals(msg.getTarget_type()))) {
				LOGGER.error("TargetType must be users or chatgroups .");
	
				objectNode.put("message", "TargetType must be users or chatgroups .");
	
				return objectNode;
			}
	
			try {
				// 构造消息体
				ObjectNode txtMsg = factory.objectNode();
				ObjectNode msgContent = factory.objectNode();
				ArrayNode target = JsonNodeFactory.instance.arrayNode();
				txtMsg.put("target_type", msg.getTarget_type());
				txtMsg.put("from", msg.getFrom());
				for(String s:msg.getTarget()){
					target.add(s);
				}
				txtMsg.put("target", target);
				
				if(msg.getMsg().getType().equals("txt")){
					//构造txt消息
					msgContent = getTxtMessage(msg);
				}else if(msg.getMsg().getType().equals("img")){
					//构造img消息
					msgContent = getImgMessage(msg);
				}
				txtMsg.put("msg", msgContent);
				//扩展消息组装
				ObjectNode extMsg = factory.objectNode();
				extMsg.put("meetId", msg.getMeetId());
				extMsg.put("groupId", msg.getGroupId());
				extMsg.put("meetName", msg.getMeetName());
				extMsg.put("userName", msg.getUserName());
				extMsg.put("subMeetId", msg.getSubMeetId());
				extMsg.put("messageType", msg.getMessageType());
				extMsg.put("userId", msg.getUserId());
				Date date=new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s = sdf.format(date);
				extMsg.put("time", s);
				txtMsg.put("ext", extMsg);
				
				
				ResteasyWebTarget webTarget = EndPoints.MESSAGES_TARGET;
	
				objectNode = ResteasyUtils.sendRequest(webTarget, txtMsg, credential, HTTPMethod.METHOD_POST, null);
	
				objectNode = (ObjectNode) objectNode.get("data");
				String [] target1=msg.getTarget();
				for (int i = 0; i < target1.length; i++) {
					String resultStr = objectNode.path(target1[i]).asText();
					if ("success".equals(resultStr)) {
						LOGGER.error(String.format("Message has been send to user[%s] successfully .", target1[i]));
					} else if (!"success".equals(resultStr)) {
						LOGGER.error(String.format("Message has been send to user[%s] failed .",target1[i]));
					}
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.MessagesServiceImpl_sendOpenMessage_00_ERROR.getCode(),
					ImFacadeEnums.MessagesServiceImpl_sendOpenMessage_00_ERROR.getMsg());
			String params="";
			params=msg+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
}
