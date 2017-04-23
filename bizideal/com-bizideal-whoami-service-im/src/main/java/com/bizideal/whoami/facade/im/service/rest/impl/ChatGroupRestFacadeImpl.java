package com.bizideal.whoami.facade.im.service.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.core.im.biz.ChatGroupsService;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.im.facade.rest.ChatGroupsRestFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName ChatGroupRestFacadeImpl
 * @Description (群组管理)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
@Path("/chatGroup")
@Component("chatGroupsRestFacade")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class ChatGroupRestFacadeImpl implements ChatGroupsRestFacade{

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatGroupRestFacadeImpl.class);

	@Autowired
	private ChatGroupsService chatGroupsService;

	@GET
	public String getAllChatgroupids() throws CustomException{
		ObjectNode chatgroupidsNode = chatGroupsService.getAllChatgroupids();
		return chatgroupidsNode.toString();
	}

	@GET
	@Path("{group_ids}")//@Path("{sub_path:[A-Z]*}")  //对URI的动态部分，可以自定义校验正则表达式
	public String getGroupDetailsByChatgroupid(@PathParam("group_ids") String chatgroupIDs) throws CustomException{
		LOGGER.info("通过群ID获取群信息:"+chatgroupIDs);
		ObjectNode groupDetailNode = chatGroupsService.getGroupDetailsByChatgroupid(chatgroupIDs);
		return groupDetailNode.toString();
	}

	@POST
	public String creatChatGroups(ChatGroup chatGroup) throws CustomException{
		ObjectNode creatChatGroupNode = chatGroupsService.creatChatGroups(chatGroup);
		return creatChatGroupNode.toString();
	}

	@DELETE
	@Path("{group_id}")
	public String deleteChatGroups(@PathParam("group_id") String groupID) throws CustomException{
		ObjectNode deleteChatGroupNode = chatGroupsService.deleteChatGroups(groupID);
		return deleteChatGroupNode.toString();
	}

	@GET
	@Path("{group_id}/users")
	public String getAllMemberssByGroupId(@PathParam("group_id") String groupID) throws CustomException{
		ObjectNode getAllMemberssByGroupIdNode = chatGroupsService.getAllMemberssByGroupId(groupID);
		return getAllMemberssByGroupIdNode.toString();
	}

	@POST
	@Path("{group_id}/users/{username}")
	public String addUserToGroup(@PathParam("group_id") String groupID, @PathParam("username") String username) throws CustomException{
		ObjectNode addUserToGroupNode = chatGroupsService.addUserToGroup(groupID, username);
		return addUserToGroupNode.toString();
	}

	@DELETE
	@Path("{group_id}/users/{username}")
	public String deleteUserFromGroup(@PathParam("group_id") String groupID, @PathParam("username") String username) throws CustomException{
		ObjectNode deleteUserFromGroupNode = chatGroupsService.deleteUserFromGroup(groupID, username);
		return deleteUserFromGroupNode.toString();
	}

	@DELETE
	@Path("{group_id}/users")
	@Consumes("application/x-www-form-urlencoded")
	public String deleteUserFromGroupBatch(@PathParam("group_id") String groupID,
			@FormParam("usernames") String usernames) throws CustomException{
		ObjectNode deleteUserFromGroupNode = chatGroupsService.deleteUserFromGroupBatch(groupID, usernames);
		return deleteUserFromGroupNode.toString();
	}

	@GET
	@Path("users/{username}/joined_chatgroups")
	public String getJoinedChatgroupsForIMUser(@PathParam("username") String username) throws CustomException{

		ObjectNode getJoinedChatgroupsForIMUserNode = chatGroupsService.getJoinedChatgroupsForIMUser(username);
		return getJoinedChatgroupsForIMUserNode.toString();
	}

	@POST
	@Path("{chatgroupid}/users")
	@Consumes("application/x-www-form-urlencoded")
	public String addUsersToGroupBatch(@PathParam("chatgroupid") String chatgroupid,
			@FormParam("usernames") String usernames) throws CustomException{

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

	@PUT
	@Path("{group_id}")
	@Consumes("application/x-www-form-urlencoded")
	public String attornChatGroup(@PathParam("group_id") String groupId, @FormParam("newowner") String newowner) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.attornChatGroup(groupId, newowner);
		return objectNode.toString();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("update")
	public String updateChatGroup(ChatGroup chatGroup) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.updateChatGroup(chatGroup);
		return objectNode.toString();
	}

	@GET
	@Path("{group_id}/blocks/users")
	public String getBlockUsers(@PathParam("group_id") String groupId) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.getBlockUsers(groupId);
		return objectNode.toString();
	}

	@POST
	@Path("{group_id}/blocks/users/{username}")
	public String addUserToBlock(@PathParam("group_id") String groupId, @PathParam("username") String username) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.addUserToBlock(groupId, username);
		return objectNode.toString();
	}

	@POST
	@Path("{group_id}/blocks/users")
	@Consumes("application/x-www-form-urlencoded")
	public String addUserToBlockBatch(@PathParam("group_id") String groupId, @FormParam("usernames") String usernames) throws CustomException{
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

	@DELETE
	@Path("{group_id}/blocks/users/{username}")
	public String deleteUserFromBlock(@PathParam("group_id") String groupId, @PathParam("username") String username) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.deleteUserFromBlock(groupId, username);
		return objectNode.toString();
	}

	@DELETE
	@Path("{group_id}/blocks/users")
	@Consumes("application/x-www-form-urlencoded")
	public String deleteUserFromBlockBatch(@PathParam("group_id") String groupId,
			@FormParam("usernames") String usernames) throws CustomException{
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode = chatGroupsService.deleteUserFromBlockBatch(groupId, usernames);
		return objectNode.toString();
	}

}
