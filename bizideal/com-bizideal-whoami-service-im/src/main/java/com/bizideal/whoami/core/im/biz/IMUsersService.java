package com.bizideal.whoami.core.im.biz;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import com.bizideal.whoami.im.entity.IMUser;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName IMUsersService
 * @Description (用户体系集成接口)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
public interface IMUsersService {

	/**
	 * 注册IM用户[单个]
	 * 给指定AppKey创建一个新的用户
	 * @param dataNode
	 * @return
	 */
	ObjectNode createNewIMUserSingle(IMUser user) throws CustomException;

	/**
	 * 注册IM用户[批量]
	 * 给指定AppKey创建一批用户
	 * @param dataArrayNode
	 * @return
	 */
	ObjectNode createNewIMUserBatch(ArrayNode dataArrayNode) throws CustomException;

	/**
	 * 注册IM用户[批量生成用户然后注册]
	 * 给指定AppKey创建一批用户
	 * @param usernamePrefix 生成用户名的前缀
	 * @param perNumber 批量注册时一次注册的数量
	 * @param totalNumber 生成用户注册的用户总数
	 * @return
	 */
	ObjectNode createNewIMUserBatchGen(String usernamePrefix, Long perNumber, Long totalNumber) throws CustomException;

	/**
	 * 获取IM用户
	 * @param userName  用户主键：username或者uuid
	 * @return
	 */
	ObjectNode getIMUsersByUserName(String userName) throws CustomException;

	/**
	 * 删除IM用户[单个]
	 * 删除指定AppKey下IM单个用户
	 * @param userName
	 * @return
	 */
	ObjectNode deleteIMUserByUserName(String userName) throws CustomException;

	/**
	 * 删除IM用户[批量]
	 * 批量指定AppKey下删除IM用户
	 * @param limit
	 * @param queryStr
	 * @return
	 */
	ObjectNode deleteIMUserByUsernameBatch(Integer limit, String queryStr) throws CustomException;

	/**
	 * 重置IM用户密码 提供管理员token
	 * @param userName
	 * @param dataObjectNode
	 * @return
	 */
	ObjectNode modifyIMUserPasswordWithAdminToken(String userName, ObjectNode dataObjectNode) throws CustomException;

	/**
	 * 添加好友[单个]
	 * @param ownerUserName
	 * @param friendUserName
	 * @return
	 */
	ObjectNode addFriendSingle(String ownerUserName, String friendUserName) throws CustomException;

	/**
	 * 解除好友关系
	 * @param ownerUserName
	 * @param friendUserName
	 * @return
	 */
	ObjectNode deleteFriendSingle(String ownerUserName, String friendUserName) throws CustomException;

	/**
	 * 查看好友
	 * @param ownerUserName
	 * @return
	 */
	ObjectNode getFriends(String ownerUserName) throws CustomException;

	/**
	 * IM用户登录
	 * @param ownerUserName
	 * @param password
	 * @return
	 */
	ObjectNode imUserLogin(String ownerUserName, String password) throws CustomException;

	/**
	 * 获取 IM 用户的黑名单
	 * @param ownerUserName
	 * @return
	 */
	ObjectNode getBlacklist(String ownerUserName) throws CustomException;

	/**
	 * 往 IM 用户的黑名单中加人,多个用户用逗号隔开
	 * @param ownerUserName
	 * @param usernames 
	 * @return
	 */
	ObjectNode addBlacklist(String ownerUserName, ObjectNode usernames) throws CustomException;

	/**
	 * 从 IM 用户的黑名单中减人
	 * @param ownerUserName
	 * @param blockedUserName
	 * @return
	 */
	ObjectNode delBlackList(String ownerUserName, String blockedUserName) throws CustomException;

	/**
	 * 查看用户状态
	 * @param userName
	 * @return
	 */
	ObjectNode userStatus(String userName) throws CustomException;

	
	/**
	 * 查询离线消息数
	 * @param ownerUserName
	 * @return
	 */
	ObjectNode offlineMsgCount(String ownerUserName) throws CustomException;

	/**
	 * 查询某条离线消息状态
	 * @param username
	 * @param msgID
	 * @return
	 */
	ObjectNode offlineMsgStatusByMsgId(String username, String msgID) throws CustomException;

	/**
	 * 用户账号禁用
	 * @param username
	 * @return
	 */
	ObjectNode deactivateUser(String username) throws CustomException;

	/**
	 * 用户账号解禁
	 * @param username
	 * @return
	 */
	ObjectNode activateUser(String username) throws CustomException;
	
	/**
	 * 强制用户下线
	 * @param username
	 * @return
	 */
	ObjectNode disconnectUser(String username) throws CustomException;

	

}