package com.bizideal.whoami.facade.im.service.impl;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.im.biz.IMUsersService;
import com.bizideal.whoami.im.entity.IMUser;
import com.bizideal.whoami.im.facade.IMUsersFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName IMUsersFacadeImpl
 * @Description (用户体系集成表现层)
 * @Author Zj.Qu
 * @Date 2017-01-05 14:05:35
 */
@Component("imUsersFacade")
public class IMUsersFacadeImpl implements IMUsersFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(IMUsersFacadeImpl.class);

	@Autowired
	private IMUsersService iMUsersService;

	public String createNewIMUserSingle(IMUser user) throws CustomException{
		ObjectNode objectNode = iMUsersService.createNewIMUserSingle(user);
		return objectNode.toString();
	}

	public String imUserLogin(String username, String password) throws CustomException{
		ObjectNode imUserLoginNode = iMUsersService.imUserLogin(username, password);
		return imUserLoginNode.toString();
	}

	public String createNewIMUserBatchGen(String usernamePrefix, Long perNumber, Long totalNumber) throws CustomException{
		ObjectNode userBatchNode = iMUsersService.createNewIMUserBatchGen(usernamePrefix, perNumber, totalNumber);
		if (null != userBatchNode) {
			LOGGER.info("注册IM用户[批量]: " + userBatchNode.toString());
		}
		return userBatchNode.toString();
	}

	public String getIMUsersByUserName(String username) throws CustomException{
		ObjectNode usersNode = iMUsersService.getIMUsersByUserName(username);
		return usersNode.toString();
	}

	public String deleteIMUserByUserName(String username) throws CustomException{
		ObjectNode delUserNode = iMUsersService.deleteIMUserByUserName(username);
		return delUserNode.toString();
	}

	public String modifyIMUserPasswordWithAdminToken(String username, String newpassword) throws CustomException{

		ObjectNode datanode = JsonNodeFactory.instance.objectNode();
		datanode.put("newpassword", newpassword);
		// 重置IM用户密码
		iMUsersService.modifyIMUserPasswordWithAdminToken(username, datanode);

		// 重置IM用户密码后,IM用户登录
		ObjectNode imUserLoginNode = iMUsersService.imUserLogin(username, datanode.get("newpassword").asText());
		return imUserLoginNode.toString();
	}

	public String addFriendSingle(String ownerUserName, String friendUserName) throws CustomException{
		ObjectNode objectNode = iMUsersService.addFriendSingle(ownerUserName, friendUserName);
		return objectNode.toString();
	}

	public String deleteFriendSingle(String ownerUserName, String friendUserName) throws CustomException{
		ObjectNode deleteFriendSingleNode = iMUsersService.deleteFriendSingle(ownerUserName, friendUserName);
		return deleteFriendSingleNode.toString();
	}

	public String getFriends(String ownerUserName) throws CustomException{
		ObjectNode friendsNode = iMUsersService.getFriends(ownerUserName);
		return friendsNode.toString();
	}

	public String deleteIMUserByUsernameBatch(Integer limit) throws CustomException{
		ObjectNode delUserNode = iMUsersService.deleteIMUserByUsernameBatch(limit, null);
		return delUserNode.toString();
	}

	public String getBlacklist(String ownerUserName) throws CustomException{
		ObjectNode getBlacklist = iMUsersService.getBlacklist(ownerUserName);
		return getBlacklist.toString();
	}

	public String addBlacklist(String ownerUserName, String usernames) throws CustomException{

		ArrayNode users = JsonNodeFactory.instance.arrayNode();
		for (String s : usernames.split(",")) {
			users.add(s);
		}
		ObjectNode usernamesNode = JsonNodeFactory.instance.objectNode();
		usernamesNode.put("usernames", users);
		ObjectNode addBlacklist = iMUsersService.addBlacklist(ownerUserName, usernamesNode);
		return addBlacklist.toString();

	}

	public String delBlackList(String ownerUserName, String blockedUserName) throws CustomException{

		ObjectNode delBlackList = iMUsersService.delBlackList(ownerUserName, blockedUserName);
		return delBlackList.toString();
	}

	public String userStatus(String userName) throws CustomException{
		ObjectNode userStatus = iMUsersService.userStatus(userName);
		return userStatus.toString();
	}

	public String offlineMsgCount(String ownerUserName) throws CustomException{
		ObjectNode userStatus = iMUsersService.offlineMsgCount(ownerUserName);
		return userStatus.toString();
	}

	public String offlineMsgStatusByMsgId(String username, String msgID) throws CustomException{
		ObjectNode msgStatus = iMUsersService.offlineMsgStatusByMsgId(username, msgID);
		return msgStatus.toString();
	}

	public String deactivateUser(String username) throws CustomException{
		ObjectNode deactivateUser = iMUsersService.deactivateUser(username);
		return deactivateUser.toString();
	}

	public String activateUser(String username) throws CustomException{
		ObjectNode activateUser = iMUsersService.activateUser(username);
		return activateUser.toString();
	}

	public String disconnectUser(String username) throws CustomException{
		ObjectNode disconnectUser = iMUsersService.disconnectUser(username);
		return disconnectUser.toString();
	}

}
