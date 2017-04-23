package com.bizideal.whoami.core.im.biz.impl;


import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.im.biz.ChatGroupsService;
import com.bizideal.whoami.core.im.common.HTTPMethod;
import com.bizideal.whoami.core.im.utils.ResteasyUtils;
import com.bizideal.whoami.core.im.vo.EndPoints;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.im.enums.ImFacadeEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;

/**
 * 
 * @ClassName ChatGroupsServiceImpl
 * @Description (群组管理)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
@Service("chatGroupsService")
public class ChatGroupsServiceImpl  extends BaseServiceImpl implements ChatGroupsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatGroupsServiceImpl.class);

	@Override
	public ObjectNode getAllChatgroupids() throws CustomException{

		try{
	
			try{
				ObjectNode objectNode = factory.objectNode();
				try {
					ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET;
					objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_getAllChatgroupids_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_getAllChatgroupids_00_ERROR.getMsg());
				String params="";
				params="";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_getAllChatgroupids_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_getAllChatgroupids_00_ERROR.getMsg());
			String params="";
			params="";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode updateChatGroup(ChatGroup chatGroup)throws CustomException{

		try{
	
			try{
				ObjectNode objectNode = factory.objectNode();
				ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
				dataObjectNode.put("groupname", chatGroup.getGroupname());
				dataObjectNode.put("description", chatGroup.getDescription());
				dataObjectNode.put("maxusers", chatGroup.getMaxusers());
		//		dataObjectNode.put("approval", chatGroup.getApproval());
		//		dataObjectNode.put("public", chatGroup.getIsPublic());
				// check properties that must be provided
				if (!dataObjectNode.has("groupname")) {
					LOGGER.error("Property that named groupname must be provided .");
					objectNode.put("message", "Property that named groupname must be provided .");
					return objectNode;
				}
				if (!dataObjectNode.has("description")) {
					LOGGER.error("Property that named desc must be provided .");
					objectNode.put("message", "Property that named desc must be provided .");
					return objectNode;
				}
				if (!dataObjectNode.has("maxusers")) {
					LOGGER.error("Property that named maxusers must be provided .");
					objectNode.put("message", "Property that named maxusers must be provided .");
					return objectNode;
				}
				try {
		//			newOwner.put("newowner", owner);
		
					ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.path(chatGroup.getGroupid());
		
					objectNode = ResteasyUtils.sendRequest(webTarget, dataObjectNode, credential,
							HTTPMethod.METHOD_PUT, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_updateChatGroup_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_updateChatGroup_00_ERROR.getMsg());
				String params="";
				params=chatGroup+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_updateChatGroup_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_updateChatGroup_00_ERROR.getMsg());
			String params="";
			params=chatGroup+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
	@Override
	public ObjectNode getGroupDetailsByChatgroupid(String chatgroupIDs) throws CustomException{

		try{
	
			try{
				ObjectNode objectNode = factory.objectNode();
		
				// check appKey format
		
				try {
		
					ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.path(chatgroupIDs);
		
					objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_getGroupDetailsByChatgroupid_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_getGroupDetailsByChatgroupid_00_ERROR.getMsg());
				String params="";
				params="chatgroupIDs:"+chatgroupIDs+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_getGroupDetailsByChatgroupid_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_getGroupDetailsByChatgroupid_00_ERROR.getMsg());
			String params="";
			params="chatgroupIDs:"+chatgroupIDs+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode creatChatGroups(ChatGroup chatGroup) throws CustomException{

		try{
	
			try{
		
				ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
				dataObjectNode.put("groupname", chatGroup.getGroupname());
				dataObjectNode.put("desc", chatGroup.getDescription());
				dataObjectNode.put("approval", chatGroup.getApproval());
				dataObjectNode.put("public", chatGroup.getIsPublic());
				dataObjectNode.put("maxusers", chatGroup.getMaxusers());
				dataObjectNode.put("owner", chatGroup.getOwner());
				ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
				String mStr=chatGroup.getMember();
				if(null!=mStr && mStr.length()>0){
					String [] members=mStr.split(",");
					for(String s:members){
						arrayNode.add(s);
					}
				}
				dataObjectNode.put("members", arrayNode);
		
				ObjectNode objectNode = factory.objectNode();
		
				// check appKey format
		
				// check properties that must be provided
				if (!dataObjectNode.has("groupname")) {
					LOGGER.error("Property that named groupname must be provided .");
		
					objectNode.put("message", "Property that named groupname must be provided .");
		
					return objectNode;
				}
				if (!dataObjectNode.has("desc")) {
					LOGGER.error("Property that named desc must be provided .");
		
					objectNode.put("message", "Property that named desc must be provided .");
		
					return objectNode;
				}
				if (!dataObjectNode.has("public")) {
					LOGGER.error("Property that named public must be provided .");
		
					objectNode.put("message", "Property that named public must be provided .");
		
					return objectNode;
				}
				if (!dataObjectNode.has("approval")) {
					LOGGER.error("Property that named approval must be provided .");
		
					objectNode.put("message", "Property that named approval must be provided .");
		
					return objectNode;
				}
				if (!dataObjectNode.has("owner")) {
					LOGGER.error("Property that named owner must be provided .");
		
					objectNode.put("message", "Property that named owner must be provided .");
		
					return objectNode;
				}
				if (!dataObjectNode.has("members") || !dataObjectNode.path("members").isArray()) {
					LOGGER.error("Property that named members must be provided .");
		
					objectNode.put("message", "Property that named members must be provided .");
		
					return objectNode;
				}
		
				try {
		
					ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET;
		
					objectNode = ResteasyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_POST, null);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_creatChatGroups_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_creatChatGroups_00_ERROR.getMsg());
				String params="";
				params=chatGroup+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_creatChatGroups_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_creatChatGroups_00_ERROR.getMsg());
			String params="";
			params=chatGroup+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode deleteChatGroups(String chatgroupid) throws CustomException{

		try{
	
			try{
				ObjectNode objectNode = factory.objectNode();
		
				// check appKey format
		
				try {
		
					ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.path(chatgroupid);
		
					objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteChatGroups_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteChatGroups_00_ERROR.getMsg());
				String params="";
				params="chatgroupid:"+chatgroupid+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteChatGroups_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteChatGroups_00_ERROR.getMsg());
			String params="";
			params="chatgroupid:"+chatgroupid+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode getAllMemberssByGroupId(String chatgroupid) throws CustomException{

		try{
	
			try{
		
				ObjectNode objectNode = factory.objectNode();
		
				// check appKey format
		
				try {
		
					ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.path(chatgroupid).path("users");
		
					objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_getAllMemberssByGroupId_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_getAllMemberssByGroupId_00_ERROR.getMsg());
				String params="";
				params="chatgroupid:"+chatgroupid+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_getAllMemberssByGroupId_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_getAllMemberssByGroupId_00_ERROR.getMsg());
			String params="";
			params="chatgroupid:"+chatgroupid+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode addUserToGroup(String chatgroupid, String userName) throws CustomException{

		try{
	
			try{
		
				ObjectNode objectNode = factory.objectNode();
		
				// check appKey format
		
				try {
		
					ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.path(chatgroupid).path("users").path(userName);
		
					objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_POST, null);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_addUserToGroup_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_addUserToGroup_00_ERROR.getMsg());
				String params="";
				params="chatgroupid:"+chatgroupid+","+"userName:"+userName+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_addUserToGroup_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_addUserToGroup_00_ERROR.getMsg());
			String params="";
			params="chatgroupid:"+chatgroupid+","+"userName:"+userName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode deleteUserFromGroup(String chatgroupid, String userName) throws CustomException{

		try{
	
			try{
				ObjectNode objectNode = factory.objectNode();
		
				// check appKey format
		
				try {
					ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.path(chatgroupid).path("users").path(userName);
		
					objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromGroup_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromGroup_00_ERROR.getMsg());
				String params="";
				params="chatgroupid:"+chatgroupid+","+"userName:"+userName+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromGroup_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromGroup_00_ERROR.getMsg());
			String params="";
			params="chatgroupid:"+chatgroupid+","+"userName:"+userName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode getJoinedChatgroupsForIMUser(String username) throws CustomException{

		try{
	
			try{
				ObjectNode objectNode = factory.objectNode();
				// check appKey format
		
				if (StringUtils.isBlank(username.trim())) {
					LOGGER.error("Property that named username must be provided .");
					objectNode.put("message", "Property that named username must be provided .");
					return objectNode;
				}
		
				try {
					ResteasyWebTarget webTarget = EndPoints.USERS_TARGET.path(username).path("joined_chatgroups");
					objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
					// objectNode =
					// ResteasyUtils.sendRequest(EndPoints.USERS_TARGET.path(username).path("joined_chatgroups"),
					// null,
					// credential, HTTPMethod.METHOD_GET, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_getJoinedChatgroupsForIMUser_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_getJoinedChatgroupsForIMUser_00_ERROR.getMsg());
				String params="";
				params="username:"+username+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_getJoinedChatgroupsForIMUser_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_getJoinedChatgroupsForIMUser_00_ERROR.getMsg());
			String params="";
			params="username:"+username+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode addUsersToGroupBatch(String toAddBacthChatgroupid, ObjectNode usernames) throws CustomException{

		try{
	
			try{
				ObjectNode objectNode = factory.objectNode();
				// check appKey format
		
				if (StringUtils.isBlank(toAddBacthChatgroupid.trim())) {
					LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
					objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
					return objectNode;
				}
				// check properties that must be provided
				if (null != usernames && !usernames.has("usernames")) {
					LOGGER.error("Property that named usernames must be provided .");
					objectNode.put("message", "Property that named usernames must be provided .");
					return objectNode;
				}
		
				try {
					// ResteasyWebTarget webTarget =
					// EndPoints.USERS_TARGET.resolveTemplate("org_name",
					// APPKEY.split("#")[0])
					// .resolveTemplate("app_name",
					// APPKEY.split("#")[1]).path(username).path("joined_chatgroups");
					objectNode = ResteasyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET.path(toAddBacthChatgroupid).path("users"),
							usernames, credential, HTTPMethod.METHOD_POST, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_addUsersToGroupBatch_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_addUsersToGroupBatch_00_ERROR.getMsg());
				String params="";
				params="toAddBacthChatgroupid:"+toAddBacthChatgroupid+","+usernames+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_addUsersToGroupBatch_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_addUsersToGroupBatch_00_ERROR.getMsg());
			String params="";
			params="toAddBacthChatgroupid:"+toAddBacthChatgroupid+","+usernames+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode attornChatGroup(String groupId, String owner) throws CustomException{

		try{
	
			try{
		
				ObjectNode objectNode = factory.objectNode();
				// check appKey format
		
				if (StringUtils.isBlank(groupId.trim())) {
					LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
					objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
					return objectNode;
				}
				// check properties that must be provided
				if (null == owner || owner.equals("")) {
					LOGGER.error("Property that named usernames must be provided .");
					objectNode.put("message", "Property that named usernames must be provided .");
					return objectNode;
				}
				try {
		
					ObjectNode newOwner = factory.objectNode();
					newOwner.put("newowner", owner);
					objectNode = ResteasyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET.path(groupId), newOwner, credential,
							HTTPMethod.METHOD_PUT, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_attornChatGroup_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_attornChatGroup_00_ERROR.getMsg());
				String params="";
				params="groupId:"+groupId+","+"owner:"+owner+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_attornChatGroup_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_attornChatGroup_00_ERROR.getMsg());
			String params="";
			params="groupId:"+groupId+","+"owner:"+owner+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode getBlockUsers(String groupId) throws CustomException{

		try{
	
			try{
		
				ObjectNode objectNode = factory.objectNode();
				// check appKey format
		
				if (StringUtils.isBlank(groupId.trim())) {
					LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
					objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
					return objectNode;
				}
				try {
		
					objectNode = ResteasyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET
		
							.path(groupId).path("blocks").path("users"), null, credential, HTTPMethod.METHOD_GET, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_getBlockUsers_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_getBlockUsers_00_ERROR.getMsg());
				String params="";
				params="groupId:"+groupId+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_getBlockUsers_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_getBlockUsers_00_ERROR.getMsg());
			String params="";
			params="groupId:"+groupId+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode addUserToBlock(String groupId, String username) throws CustomException{

		try{
	
			try{
		
				ObjectNode objectNode = factory.objectNode();
				// check appKey format
		
				if (StringUtils.isBlank(groupId.trim())) {
					LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
					objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
					return objectNode;
				}
				if (null == username || username.equals("")) {
					LOGGER.error("Property that named usernames must be provided .");
					objectNode.put("message", "Property that named usernames must be provided .");
					return objectNode;
				}
				try {
		
					objectNode = ResteasyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET
		
							.path(groupId).path("blocks").path("users").path(username), null, credential,
							HTTPMethod.METHOD_POST, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_addUserToBlock_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_addUserToBlock_00_ERROR.getMsg());
				String params="";
				params="groupId:"+groupId+","+"username:"+username+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_addUserToBlock_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_addUserToBlock_00_ERROR.getMsg());
			String params="";
			params="groupId:"+groupId+","+"username:"+username+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode addUserToBlockBatch(String groupId, ObjectNode usernames) throws CustomException{

		try{
	
			try{
		
				ObjectNode objectNode = factory.objectNode();
				// check appKey format
		
				if (StringUtils.isBlank(groupId.trim())) {
					LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
					objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
					return objectNode;
				}
				// check properties that must be provided
				if (null == usernames || !usernames.has("usernames")) {
					LOGGER.error("Property that named usernames must be provided .");
					objectNode.put("message", "Property that named usernames must be provided .");
					return objectNode;
				}
				try {
					objectNode = ResteasyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET
		
							.path(groupId).path("blocks").path("users"), usernames, credential, HTTPMethod.METHOD_POST, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_addUserToBlockBatch_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_addUserToBlockBatch_00_ERROR.getMsg());
				String params="";
				params="groupId:"+groupId+","+usernames+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_addUserToBlockBatch_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_addUserToBlockBatch_00_ERROR.getMsg());
			String params="";
			params="groupId:"+groupId+","+usernames+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode deleteUserFromBlock(String groupId, String username) throws CustomException{

		try{
	
			try{
		
				ObjectNode objectNode = factory.objectNode();
				// check appKey format
		
				if (StringUtils.isBlank(groupId.trim())) {
					LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
					objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
					return objectNode;
				}
				if (null == username || username.equals("")) {
					LOGGER.error("Property that named usernames must be provided .");
					objectNode.put("message", "Property that named usernames must be provided .");
					return objectNode;
				}
				try {
		
					objectNode = ResteasyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET
		
							.path(groupId).path("blocks").path("users").path(username), null, credential,
							HTTPMethod.METHOD_DELETE, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromBlock_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromBlock_00_ERROR.getMsg());
				String params="";
				params="groupId:"+groupId+","+"username:"+username+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromBlock_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromBlock_00_ERROR.getMsg());
			String params="";
			params="groupId:"+groupId+","+"username:"+username+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode deleteUserFromBlockBatch(String groupId, String usernames) throws CustomException{

		try{
	
			try{
		
				ObjectNode objectNode = factory.objectNode();
		
				if (StringUtils.isBlank(groupId.trim())) {
					LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
					objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
					return objectNode;
				}
				// check properties that must be provided
				if (null == usernames || usernames.equals("")) {
					LOGGER.error("Property that named usernames must be provided .");
					objectNode.put("message", "Property that named usernames must be provided .");
					return objectNode;
				}
				try {
					objectNode = ResteasyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET.path(groupId).path("blocks").path("users").path(usernames),
							null, credential, HTTPMethod.METHOD_DELETE, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromBlockBatch_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromBlockBatch_00_ERROR.getMsg());
				String params="";
				params="groupId:"+groupId+","+"usernames:"+usernames+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromBlockBatch_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromBlockBatch_00_ERROR.getMsg());
			String params="";
			params="groupId:"+groupId+","+"usernames:"+usernames+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode deleteUserFromGroupBatch(String groupID, String usernames) throws CustomException{

		try{
	
			try{
		
				ObjectNode objectNode = factory.objectNode();
		
				if (StringUtils.isBlank(groupID.trim())) {
					LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
					objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
					return objectNode;
				}
				// check properties that must be provided
				if (null == usernames || usernames.equals("")) {
					LOGGER.error("Property that named usernames must be provided .");
					objectNode.put("message", "Property that named usernames must be provided .");
					return objectNode;
				}
		
				try {
					ResteasyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.path(groupID).path("users").path(usernames);
		
					objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return objectNode;
		
			}catch(Exception e){
				CustomException customException = new CustomException(
						Thread.currentThread().getStackTrace()[1].getClassName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromGroupBatch_00_ERROR.getCode(),
						ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromGroupBatch_00_ERROR.getMsg());
				String params="";
				params="groupID:"+groupID+","+"usernames:"+usernames+"";
				ExceptionUtilsCls.putCustomException(customException, e , params);
				throw customException;
			}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromGroupBatch_00_ERROR.getCode(),
					ImFacadeEnums.ChatGroupsServiceImpl_deleteUserFromGroupBatch_00_ERROR.getMsg());
			String params="";
			params="groupID:"+groupID+","+"usernames:"+usernames+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
}
