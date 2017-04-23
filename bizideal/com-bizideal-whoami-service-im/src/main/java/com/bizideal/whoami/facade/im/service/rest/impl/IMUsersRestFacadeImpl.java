package com.bizideal.whoami.facade.im.service.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.core.im.biz.IMUsersService;
import com.bizideal.whoami.im.entity.IMUser;
import com.bizideal.whoami.im.facade.rest.IMUsersRestFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName IMUsersRestFacadeImpl
 * @Description (用户体系集成表现层)
 * @Author Zj.Qu
 * @Date 2017-01-05 14:05:35
 */
@Path("/users")
@Component("imUsersRestFacade")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class IMUsersRestFacadeImpl implements IMUsersRestFacade{

	private static final Logger LOGGER = LoggerFactory.getLogger(IMUsersRestFacadeImpl.class);

	@Autowired
	private IMUsersService iMUsersService;

	@POST
	public String createNewIMUserSingle(IMUser user) throws CustomException{
		ObjectNode objectNode = iMUsersService.createNewIMUserSingle(user);
		return objectNode.toString();
	}

	@POST
	@Path("login")
	public String imUserLogin(@FormParam("username") String username, @FormParam("password") String password) throws CustomException{

		ObjectNode imUserLoginNode = iMUsersService.imUserLogin(username, password);
		return imUserLoginNode.toString();
	}

	@GET
	@Path("createNewIMUserBatchGen")
	public String createNewIMUserBatchGen(@FormParam("usernamePrefix") String usernamePrefix,
			@FormParam("perNumber")  Long perNumber,
			@FormParam("totalNumber") Long totalNumber) throws CustomException{
		ObjectNode userBatchNode = iMUsersService.createNewIMUserBatchGen(usernamePrefix, perNumber,
				totalNumber);
		if (null != userBatchNode) { 
			LOGGER.info("注册IM用户[批量]: " + userBatchNode.toString());
		}
		return userBatchNode.toString();
	}

	@GET
	@Path("{username}")
	public String getIMUsersByUserName(@PathParam("username") String username) throws CustomException{
		ObjectNode usersNode = iMUsersService.getIMUsersByUserName(username);
		return usersNode.toString();
	}


	@DELETE
	@Path("{username}")
	public String deleteIMUserByUserName(@PathParam("username") String username) throws CustomException{
		ObjectNode delUserNode = iMUsersService.deleteIMUserByUserName(username);
		return delUserNode.toString();
	}
	
	@PUT
	@Path("{username}/password")
	public String modifyIMUserPasswordWithAdminToken(@PathParam("username") String username,
			@FormParam("newpassword") String newpassword) throws CustomException{
		
		ObjectNode datanode = JsonNodeFactory.instance.objectNode();
		datanode.put("newpassword", newpassword);
		//重置IM用户密码
		iMUsersService.modifyIMUserPasswordWithAdminToken(username, datanode);

		//重置IM用户密码后,IM用户登录
		ObjectNode imUserLoginNode = iMUsersService.imUserLogin(username, datanode.get("newpassword").asText());
		return imUserLoginNode.toString();
	}

	@POST
	@Path("{owner_username}/contacts/users/{friend_username}")
	public String addFriendSingle(@PathParam("owner_username") String ownerUserName,
			@PathParam("friend_username") String friendUserName) throws CustomException{

		ObjectNode objectNode = iMUsersService.addFriendSingle(ownerUserName, friendUserName);
		return objectNode.toString();
	}

	@DELETE
	@Path("{owner_username}/contacts/users/{friend_username}")
	public String deleteFriendSingle(@PathParam("owner_username") String ownerUserName,
			@PathParam("friend_username") String friendUserName) throws CustomException{

		ObjectNode deleteFriendSingleNode = iMUsersService.deleteFriendSingle(ownerUserName, friendUserName);
		return deleteFriendSingleNode.toString();
	}
	
	@GET
	@Path("{owner_username}/contacts/users")
	public String getFriends(@PathParam("owner_username") String ownerUserName) throws CustomException{
		ObjectNode friendsNode = iMUsersService.getFriends(ownerUserName);
		return friendsNode.toString();
	}

	@DELETE
	public String deleteIMUserByUsernameBatch(@QueryParam("limit") @DefaultValue("10") Integer limit) throws CustomException{
		ObjectNode delUserNode = iMUsersService.deleteIMUserByUsernameBatch(limit, null);
		return delUserNode.toString();
	}

	@GET
	@Path("{owner_username}/blocks/users")
	public String getBlacklist(@PathParam("owner_username") String ownerUserName) throws CustomException{
		ObjectNode getBlacklist =iMUsersService.getBlacklist(ownerUserName);
		return getBlacklist.toString();
	}
	
	@POST
	@Path("{owner_username}/blocks/users")
	public String addBlacklist(@PathParam("owner_username") String ownerUserName,@FormParam("usernames")  String  usernames) throws CustomException{

		ArrayNode users= JsonNodeFactory.instance.arrayNode();
		for(String s:usernames.split(",")){
			users.add(s);
		}
		ObjectNode usernamesNode = JsonNodeFactory.instance.objectNode();
		usernamesNode.put("usernames", users);
		ObjectNode addBlacklist = iMUsersService.addBlacklist(ownerUserName, usernamesNode);
		return addBlacklist.toString();

	}

	
	@DELETE
	@Path("{owner_username}/blocks/users/{blocked_username}")
	public String delBlackList(@PathParam("owner_username") String ownerUserName,
			@PathParam("blocked_username") String blockedUserName) throws CustomException{
		
		ObjectNode delBlackList = iMUsersService.delBlackList(ownerUserName, blockedUserName);
		return delBlackList.toString();
	}
	
	
	@GET
	@Path("{username}/status")
	public String userStatus(@PathParam("username") String userName) throws CustomException{
		ObjectNode userStatus=iMUsersService.userStatus(userName);
		return userStatus.toString();
	}
	
	@GET
	@Path("{owner_username}/offline_msg_count")
	public String offlineMsgCount(@PathParam("owner_username") String ownerUserName) throws CustomException{
		ObjectNode userStatus=iMUsersService.offlineMsgCount(ownerUserName);
		return userStatus.toString();
	}
	
	@GET
	@Path("{username}/offline_msg_status/{msg_id}")
	public String offlineMsgStatusByMsgId(@PathParam("username") String username, @PathParam("msg_id") String msgID) throws CustomException{
		ObjectNode msgStatus = iMUsersService.offlineMsgStatusByMsgId(username, msgID);
		return msgStatus.toString();
	}
	
	@POST
	@Path("{username}/deactivate")
	public String deactivateUser(@PathParam("username") String username) throws CustomException{
		ObjectNode deactivateUser = iMUsersService.deactivateUser(username);
		return deactivateUser.toString();
	}

	@POST
	@Path("{username}/activate")
	public String activateUser(@PathParam("username") String username) throws CustomException{
		ObjectNode activateUser = iMUsersService.activateUser(username);
		return activateUser.toString();
	}

	@GET
	@Path("{username}/disconnect")
	public String disconnectUser(@PathParam("username") String username) throws CustomException{
		ObjectNode disconnectUser = iMUsersService.disconnectUser(username);
		return disconnectUser.toString();
	}

}
