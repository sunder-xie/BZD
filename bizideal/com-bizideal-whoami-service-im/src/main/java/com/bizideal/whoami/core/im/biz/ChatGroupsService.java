package com.bizideal.whoami.core.im.biz;

import org.codehaus.jackson.node.ObjectNode;

import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName ChatGroupsService
 * @Description (群组管理 接口)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
public interface ChatGroupsService {

	/**
	 * 获取所有群的编号
	 * 
	 * @return
	 */
	ObjectNode getAllChatgroupids() throws CustomException;

	/**
	 * 修改群组
	 * @param chatGroup
	 * @return
	 */
	ObjectNode updateChatGroup(ChatGroup chatGroup) throws CustomException;
	/**
	 * 获取一个或者多个群组的详情
	 * 
	 * @return
	 */
	ObjectNode getGroupDetailsByChatgroupid(String chatgroupIDs) throws CustomException;

	/**
	 * 创建群组
	 */
	ObjectNode creatChatGroups(ChatGroup chatGroup) throws CustomException;

	/**
	 * 删除群组
	 */
	ObjectNode deleteChatGroups(String chatgroupid) throws CustomException;

	/**
	 * 获取群组中的所有成员
	 */
	ObjectNode getAllMemberssByGroupId(String chatgroupid) throws CustomException;

	/**
	 * 在群组中添加一个人
	 */
	ObjectNode addUserToGroup(String chatgroupid, String userName) throws CustomException;

	/**
	 * 在群组中减少一个人
	 */
	ObjectNode deleteUserFromGroup(String chatgroupid, String userName) throws CustomException;

	/**
	 * 获取一个用户参与的所有群组
	 * 
	 * @param username
	 * @return
	 */
	ObjectNode getJoinedChatgroupsForIMUser(String username) throws CustomException;

	/**
	 * 群组批量添加成员
	 * 
	 * @param toAddBacthChatgroupid
	 * @param usernames
	 * @return
	 */
	ObjectNode addUsersToGroupBatch(String toAddBacthChatgroupid, ObjectNode usernames) throws CustomException;

	/**
	 * 转让群组
	 * 
	 * @param groupId
	 * @param owner
	 * @return
	 */
	ObjectNode attornChatGroup(String groupId, String owner) throws CustomException;

	/**
	 * 获取一个群组的所有黑名单
	 * 
	 * @param groupId
	 * @return
	 */
	ObjectNode getBlockUsers(String groupId) throws CustomException;

	/**
	 * 添加用户到一个群组黑名单
	 * 
	 * @param groupId
	 * @param username
	 * @return
	 */
	ObjectNode addUserToBlock(String groupId, String username) throws CustomException;

	/**
	 * 添加多个用户到一个群组黑名单
	 * 
	 * @param groupId
	 * @param usernamesNode
	 * @return
	 */
	ObjectNode addUserToBlockBatch(String groupId, ObjectNode usernamesNode) throws CustomException;

	/**
	 * 从一个群组的黑名单中移除一个用户
	 * 
	 * @param groupId
	 * @param username
	 * @return
	 */
	ObjectNode deleteUserFromBlock(String groupId, String username) throws CustomException;

	/**
	 * 从一个群组的黑名单中移除多个用户
	 * 
	 * @param groupId
	 * @param usernames
	 * @return
	 */
	ObjectNode deleteUserFromBlockBatch(String groupId, String usernames) throws CustomException;

	/**
	 * 在群组中减少多个人
	 * 
	 * @param groupID
	 * @param usernames
	 * @return
	 */
	ObjectNode deleteUserFromGroupBatch(String groupID, String usernames) throws CustomException;
}