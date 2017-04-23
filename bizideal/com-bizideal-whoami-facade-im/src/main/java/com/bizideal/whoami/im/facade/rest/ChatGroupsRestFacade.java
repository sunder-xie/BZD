package com.bizideal.whoami.im.facade.rest;

import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName ChatGroupsRestFacade
 * @Description TODO(群组管理 接口)
 * @Author Zj.Qu
 * @Date 2017-01-09 09:50:30
 */
public interface ChatGroupsRestFacade {

	/**
	 * 获取所有群的编号
	 * 
	 * @return
	 */
	String getAllChatgroupids() throws CustomException;

	/**
	 * 修改群组
	 * @param chatGroup
	 * @return
	 */
	String updateChatGroup(ChatGroup chatGroup) throws CustomException;
	/**
	 * 获取一个或者多个群组的详情
	 * 
	 * @return
	 */
	String getGroupDetailsByChatgroupid(String chatgroupIDs) throws CustomException;

	/**
	 * 创建群组
	 */
	String creatChatGroups(ChatGroup chatGroup) throws CustomException;

	/**
	 * 删除群组
	 */
	String deleteChatGroups(String chatgroupid) throws CustomException;

	/**
	 * 获取群组中的所有成员
	 */
	String getAllMemberssByGroupId(String chatgroupid) throws CustomException;

	/**
	 * 在群组中添加一个人
	 */
	String addUserToGroup(String chatgroupid, String userName) throws CustomException;

	/**
	 * 在群组中减少一个人
	 */
	String deleteUserFromGroup(String chatgroupid, String userName) throws CustomException;

	/**
	 * 获取一个用户参与的所有群组
	 * 
	 * @param username
	 * @return
	 */
	String getJoinedChatgroupsForIMUser(String username) throws CustomException;

	/**
	 * 群组批量添加成员
	 * 
	 * @param toAddBacthChatgroupid
	 * @param usernames
	 * @return
	 */
	String addUsersToGroupBatch(String toAddBacthChatgroupid, String usernames) throws CustomException;

	/**
	 * 转让群组
	 * 
	 * @param groupId
	 * @param owner
	 * @return
	 */
	String attornChatGroup(String groupId, String owner) throws CustomException;

	/**
	 * 获取一个群组的所有黑名单
	 * 
	 * @param groupId
	 * @return
	 */
	String getBlockUsers(String groupId) throws CustomException;

	/**
	 * 添加用户到一个群组黑名单
	 * 
	 * @param groupId
	 * @param username
	 * @return
	 */
	String addUserToBlock(String groupId, String username) throws CustomException;

	/**
	 * 添加多个用户到一个群组黑名单
	 * 
	 * @param groupId
	 * @param usernamesNode
	 * @return
	 */
	String addUserToBlockBatch(String groupId, String usernamesNode) throws CustomException;

	/**
	 * 从一个群组的黑名单中移除一个用户
	 * 
	 * @param groupId
	 * @param username
	 * @return
	 */
	String deleteUserFromBlock(String groupId, String username) throws CustomException;

	/**
	 * 从一个群组的黑名单中移除多个用户
	 * 
	 * @param groupId
	 * @param usernames
	 * @return
	 */
	String deleteUserFromBlockBatch(String groupId, String usernames) throws CustomException;

	/**
	 * 在群组中减少多个人
	 * 
	 * @param groupID
	 * @param usernames
	 * @return
	 */
	String deleteUserFromGroupBatch(String groupID, String usernames) throws CustomException;
}