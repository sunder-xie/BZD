package com.bizideal.whoami.facade.im.service.impl;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.im.biz.ChatGroupsService;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.im.facade.ChatGroupsFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName ChatGroupFacadeImpl
 * @Description (群组管理)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
@Component("chatGroupsFacade")
public class ChatGroupFacadeImpl implements ChatGroupsFacade{

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatGroupFacadeImpl.class);

	@Autowired
	private ChatGroupsService chatGroupsService;

	public String getAllChatgroupids() throws CustomException{
		ObjectNode chatgroupidsNode = chatGroupsService.getAllChatgroupids();
		return chatgroupidsNode.toString();
	}

	public String getGroupDetailsByChatgroupid(String chatgroupIDs) throws CustomException{
		LOGGER.info("通过群ID获取群信息:"+chatgroupIDs);
		ObjectNode groupDetailNode = chatGroupsService.getGroupDetailsByChatgroupid(chatgroupIDs);
		return groupDetailNode.toString();
	}

	public String creatChatGroups(ChatGroup chatGroup) throws CustomException{
		ObjectNode creatChatGroupNode = chatGroupsService.creatChatGroups(chatGroup);
		return creatChatGroupNode.toString();
	}

	public String deleteChatGroups(String groupID) throws CustomException{
		ObjectNode deleteChatGroupNode = chatGroupsService.deleteChatGroups(groupID);
		return deleteChatGroupNode.toString();
	}

	public String getAllMemberssByGroupId(String groupID) throws CustomException{
		ObjectNode getAllMemberssByGroupIdNode = chatGroupsService.getAllMemberssByGroupId(groupID);
		return getAllMemberssByGroupIdNode.toString();
	}

	public String addUserToGroup(String groupID, String username) throws CustomException{
		ObjectNode addUserToGroupNode = chatGroupsService.addUserToGroup(groupID, username);
		return addUserToGroupNode.toString();
	}

	public String deleteUserFromGroup(String groupID, String username) throws CustomException{
		ObjectNode deleteUserFromGroupNode = chatGroupsService.deleteUserFromGroup(groupID, username);
		return deleteUserFromGroupNode.toString();
	}

	public String deleteUserFromGroupBatch(String groupID,
			 String usernames) throws CustomException{
		ObjectNode deleteUserFromGroupNode = chatGroupsService.deleteUserFromGroupBatch(groupID, usernames);
		return deleteUserFromGroupNode.toString();
	}

	public String getJoinedChatgroupsForIMUser(String username) throws CustomException{

		ObjectNode getJoinedChatgroupsForIMUserNode = chatGroupsService.getJoinedChatgroupsForIMUser(username);
		return getJoinedChatgroupsForIMUserNode.toString();
	}

	public String addUsersToGroupBatch(String chatgroupid,
			 String usernames) throws CustomException{

		ArrayNode users = JsonNodeFactory.instance.arrayNode();
		for (String s : usernames.split(",")) {
			users.add(s);
		}
		// usernames.add("kenshinnuser_9");
		// usernames.add("kenshinnuser_10");
		ObjectNode usernamesNode = JsonNodeFactory.instance.objectNode();
		usernamesNode.put("usernames", users);
		ObjectNode addUserToGroupBatchNode = chatGroupsService.addUsersToGroupBatch(chatgroupid, usernamesNode);
		return addUserToGroupBatchNode.toString();
	}

	public String attornChatGroup(String groupId, String newowner) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.attornChatGroup(groupId, newowner);
		return objectNode.toString();
	}

	public String updateChatGroup(ChatGroup chatGroup) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.updateChatGroup(chatGroup);
		return objectNode.toString();
	}

	public String getBlockUsers(String groupId) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.getBlockUsers(groupId);
		return objectNode.toString();
	}

	public String addUserToBlock(String groupId, String username) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.addUserToBlock(groupId, username);
		return objectNode.toString();
	}

	public String addUserToBlockBatch(String groupId,  String usernames) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		ArrayNode users = JsonNodeFactory.instance.arrayNode();
		for (String s : usernames.split(",")) {
			users.add(s);
		}
		ObjectNode usernamesNode = JsonNodeFactory.instance.objectNode();
		usernamesNode.put("usernames", users);
		objectNode = chatGroupsService.addUserToBlockBatch(groupId, usernamesNode);
		return objectNode.toString();
	}

	public String deleteUserFromBlock(String groupId, String username) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.deleteUserFromBlock(groupId, username);
		return objectNode.toString();
	}

	public String deleteUserFromBlockBatch(String groupId,String usernames) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.deleteUserFromBlockBatch(groupId, usernames);
		return objectNode.toString();
	}

}
