package com.bizideal.whoami.core.im.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.im.biz.IMUsersService;
import com.bizideal.whoami.core.im.common.Constants;
import com.bizideal.whoami.core.im.common.HTTPMethod;
import com.bizideal.whoami.core.im.utils.ResteasyUtils;
import com.bizideal.whoami.core.im.vo.EndPoints;
import com.bizideal.whoami.im.entity.IMUser;
import com.bizideal.whoami.im.enums.ImFacadeEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;

/**
 * 
 * @ClassName IMUsersServiceImpl
 * @Description (用户体系集成接口)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
@Service("imUsersService")
public class IMUsersServiceImpl extends BaseServiceImpl implements IMUsersService{
	
	@Override
	public ObjectNode createNewIMUserSingle(IMUser user) throws CustomException{

		try{
	
			ObjectNode dataNode = JsonNodeFactory.instance.objectNode();
			dataNode.put("username", user.getUserName());
			dataNode.put("password", user.getPassword());
			dataNode.put("nickname", user.getNickname());
			
			ObjectNode objectNode = factory.objectNode();
			objectNode.removeAll();
	
			// check properties that must be provided
			if (null != dataNode && !dataNode.has("username")) {
				objectNode.put("message", "Property that named username must be provided .");
				return objectNode;
			}
			
			if (null != dataNode && !dataNode.has("password")) {
				objectNode.put("message", "Property that named password must be provided .");
				return objectNode;
			}
	
			try {
				objectNode = ResteasyUtils.sendRequest(EndPoints.USERS_TARGET, dataNode, credential, HTTPMethod.METHOD_POST, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_createNewIMUserSingle_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_createNewIMUserSingle_00_ERROR.getMsg());
			String params="";
			params=user+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode createNewIMUserBatch(ArrayNode dataArrayNode) throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	
			// check properties that must be provided
			if (dataArrayNode.isArray()) {
				for (JsonNode jsonNode : dataArrayNode) {
					if (null != jsonNode && !jsonNode.has("username")) {
						objectNode.put("message", "Property that named username must be provided .");
						return objectNode;
					}
					if (null != jsonNode && !jsonNode.has("password")) {
						objectNode.put("message", "Property that named password must be provided .");
						return objectNode;
					}
				}
			}
	
			try {
				ResteasyWebTarget webTarget = EndPoints.USERS_TARGET;
				objectNode = ResteasyUtils.sendRequest(webTarget, dataArrayNode, credential, HTTPMethod.METHOD_POST, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_createNewIMUserBatch_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_createNewIMUserBatch_00_ERROR.getMsg());
			String params="";
			params=dataArrayNode+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode createNewIMUserBatchGen(String usernamePrefix, Long perNumber, Long totalNumber) throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	
			if (totalNumber == 0 || perNumber == 0) {
				return objectNode;
			}
	
			System.out.println("你即将注册" + totalNumber + "个用户，如果大于" + perNumber + "了,会分批注册,每次注册" + perNumber + "个");
	
			ArrayNode genericArrayNode = genericArrayNode(usernamePrefix, totalNumber);
			if (totalNumber <= perNumber) {
				objectNode = createNewIMUserBatch(genericArrayNode);
			} else {
	
				for (int i = 0; i < genericArrayNode.size(); i++) {
					ArrayNode tmpArrayNode = factory.arrayNode();
					tmpArrayNode.add(genericArrayNode.get(i));
					// 300 records on one migration
					if ((i + 1) % perNumber == 0) {
						objectNode = createNewIMUserBatch(genericArrayNode);
						tmpArrayNode.removeAll();
						continue;
					}
	
					// the rest records that less than the times of 300
					if (i > (genericArrayNode.size() / perNumber * perNumber - 1)) {
						objectNode = createNewIMUserBatch(genericArrayNode);
						tmpArrayNode.removeAll();
					}
				}
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_createNewIMUserBatchGen_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_createNewIMUserBatchGen_00_ERROR.getMsg());
			String params="";
			params="usernamePrefix:"+usernamePrefix+","+"perNumber:"+perNumber+","+"totalNumber:"+totalNumber+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode getIMUsersByUserName(String userName) throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	
			// check properties that must be provided
			if (StringUtils.isEmpty(userName)) {
				objectNode.put("message", "The primaryKey that will be useed to query must be provided .");
				return objectNode;
			}
	
			try {
	
				ResteasyWebTarget webTarget = EndPoints.USERS_TARGET.path(userName);
	
				objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_getIMUsersByUserName_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_getIMUsersByUserName_00_ERROR.getMsg());
			String params="";
			params="userName:"+userName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode deleteIMUserByUserName(String userName) throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	
			try {
				ResteasyWebTarget webTarget = EndPoints.USERS_TARGET.path(userName);
				System.out.println(webTarget.getUri());
	
				objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_deleteIMUserByUserName_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_deleteIMUserByUserName_00_ERROR.getMsg());
			String params="";
			params="userName:"+userName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode deleteIMUserByUsernameBatch(Integer limit, String queryStr) throws CustomException{

		try{
	
			ObjectNode objectNode = factory.objectNode();
	
			try {
	
				ResteasyWebTarget webTarget = EndPoints.USERS_TARGET.queryParam("ql", queryStr)
							.queryParam("limit",String.valueOf(limit));
	
				objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_deleteIMUserByUsernameBatch_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_deleteIMUserByUsernameBatch_00_ERROR.getMsg());
			String params="";
			params="limit:"+limit+","+"queryStr:"+queryStr+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode modifyIMUserPasswordWithAdminToken(String userName, ObjectNode dataObjectNode) throws CustomException{

		try{
			
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(userName)) {
				objectNode.put("message",
						"Property that named userName must be provided，the value is username or uuid of imuser.");
	
				return objectNode;
			}
	
			if (null != dataObjectNode && !dataObjectNode.has("newpassword")) {
				objectNode.put("message", "Property that named newpassword must be provided .");
				return objectNode;
			}
	
			try {
				ResteasyWebTarget webTarget = EndPoints.USERS_TARGET.path(userName).path("password");
				objectNode = ResteasyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_PUT, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_modifyIMUserPasswordWithAdminToken_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_modifyIMUserPasswordWithAdminToken_00_ERROR.getMsg());
			String params="";
			params="userName:"+userName+","+dataObjectNode+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode addFriendSingle(String ownerUserName, String friendUserName) throws CustomException{

		try{
			
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(ownerUserName)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			if (StringUtils.isEmpty(friendUserName)) {
				objectNode.put("message",
						"The userName of friend must be provided，the value is username or uuid of imuser.");
	
				return objectNode;
			}
	
			try {
				ResteasyWebTarget webTarget = EndPoints.USERS_ADDFRIENDS_TARGET
						.resolveTemplate("ownerUserName", ownerUserName).resolveTemplate("friendUserName", friendUserName);
	
				ObjectNode body = factory.objectNode();
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_POST, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_addFriendSingle_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_addFriendSingle_00_ERROR.getMsg());
			String params="";
			params="ownerUserName:"+ownerUserName+","+"friendUserName:"+friendUserName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode deleteFriendSingle(String ownerUserName, String friendUserName) throws CustomException{

		try{
			
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(ownerUserName)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			if (StringUtils.isEmpty(friendUserName)) {
				objectNode.put("message",
						"The userName of friend must be provided，the value is username or uuid of imuser.");
	
				return objectNode;
			}
	
			try {
	
				ResteasyWebTarget webTarget = EndPoints.USERS_ADDFRIENDS_TARGET
						.resolveTemplate("ownerUserName", ownerUserName).resolveTemplate("friendUserName", friendUserName);
	
				ObjectNode body = factory.objectNode();
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_DELETE, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_deleteFriendSingle_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_deleteFriendSingle_00_ERROR.getMsg());
			String params="";
			params="ownerUserName:"+ownerUserName+","+"friendUserName:"+friendUserName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode getFriends(String ownerUserName) throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(ownerUserName)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			try {
				ResteasyWebTarget webTarget = EndPoints.USERS_ADDFRIENDS_TARGET
						.resolveTemplate("ownerUserName", ownerUserName).resolveTemplate("friendUserName", "");
	
				ObjectNode body = factory.objectNode();
				System.out.println(body.toString());
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_GET, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_getFriends_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_getFriends_00_ERROR.getMsg());
			String params="";
			params="ownerUserName:"+ownerUserName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode imUserLogin(String ownerUserName, String password) throws CustomException{

		try{
	
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(ownerUserName)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
			
			if (StringUtils.isEmpty(password)) {
				objectNode.put("message", "Your password must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			try {
				ObjectNode dataNode = factory.objectNode();
				dataNode.put("grant_type", "password");
				dataNode.put("username", ownerUserName);
				dataNode.put("password", password);
	
				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("Content-Type", "application/json"));
	
				objectNode = ResteasyUtils.sendRequest(EndPoints.TOKEN_APP_TARGET, dataNode, null, HTTPMethod.METHOD_POST,
						headers);
	
			} catch (Exception e) {
				throw new RuntimeException("Some errors ocuured while fetching a token by usename and passowrd .");
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_imUserLogin_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_imUserLogin_00_ERROR.getMsg());
			String params="";
			params="ownerUserName:"+ownerUserName+","+"password:"+password+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	/**
	 * 指定前缀和数量生成用户基本数据
	 * 
	 * @param usernamePrefix 前缀
	 * @param number 数量
	 * @return
	 */
	private ArrayNode genericArrayNode(String usernamePrefix, Long number) throws CustomException{
		ArrayNode arrayNode = factory.arrayNode();
		for (int i = 0; i < number; i++) {
			ObjectNode userNode = factory.objectNode();
			userNode.put("username", usernamePrefix + "_" + i);
			userNode.put("password", Constants.DEFAULT_PASSWORD);

			arrayNode.add(userNode);
		}

		return arrayNode;
	}

	@Override
	public ObjectNode getBlacklist(String ownerUserName) throws CustomException{

		try{
	
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(ownerUserName)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			try {
				ResteasyWebTarget webTarget = EndPoints.USERS_BLACKLIST_TARGET
						.resolveTemplate("ownerUserName", ownerUserName).resolveTemplate("blockedUserName", "");
	
				ObjectNode body = factory.objectNode();
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_GET, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
	
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_getBlacklist_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_getBlacklist_00_ERROR.getMsg());
			String params="";
			params="ownerUserName:"+ownerUserName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode addBlacklist(String ownerUserName, ObjectNode usernames) throws CustomException{

		try{
	
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(ownerUserName)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			if (null != usernames && !usernames.has("usernames")) {
				objectNode.put("message", "Property that named usernames must be provided .");
				return objectNode;
			}
	
			try {
				ResteasyWebTarget webTarget = EndPoints.USERS_BLACKLIST_TARGET
						.resolveTemplate("ownerUserName", ownerUserName).resolveTemplate("blockedUserName", "");
				objectNode = ResteasyUtils.sendRequest(webTarget, usernames, credential, HTTPMethod.METHOD_POST, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_addBlacklist_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_addBlacklist_00_ERROR.getMsg());
			String params="";
			params="ownerUserName:"+ownerUserName+","+usernames+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode delBlackList(String ownerUserName, String blockedUserName) throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(ownerUserName)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			try {
	
				ResteasyWebTarget webTarget = EndPoints.USERS_BLACKLIST_TARGET					
						.resolveTemplate("ownerUserName", ownerUserName)
						.resolveTemplate("blockedUserName", blockedUserName);
				ObjectNode body = factory.objectNode();
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_DELETE, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_delBlackList_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_delBlackList_00_ERROR.getMsg());
			String params="";
			params="ownerUserName:"+ownerUserName+","+"blockedUserName:"+blockedUserName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	public ObjectNode userStatus(String username) throws CustomException{

		try{
			
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(username)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			try {
	
				ResteasyWebTarget webTarget = EndPoints.USERS_TARGET.path(username).path("status");
				
				objectNode = ResteasyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
	
				String userStatus = objectNode.get("data").path(username).asText();
				if ("online".equals(userStatus)) {
					System.out.println(String.format("The status of user[%s] is : [%s] .", username, userStatus));
				} else if ("offline".equals(userStatus)) {
					System.out.println(String.format("The status of user[%s] is : [%s] .", username, userStatus));
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_userStatus_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_userStatus_00_ERROR.getMsg());
			String params="";
			params="username:"+username+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode offlineMsgCount(String ownerUserName) throws CustomException{

		try{
			
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(ownerUserName)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			try {
	
				ResteasyWebTarget webTarget = EndPoints.APPLICATION_TEMPLATE
						.resolveTemplate("ownerUserName", ownerUserName).path("offline_msg_count");
				ObjectNode body = factory.objectNode();
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_GET, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_offlineMsgCount_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_offlineMsgCount_00_ERROR.getMsg());
			String params="";
			params="ownerUserName:"+ownerUserName+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode offlineMsgStatusByMsgId(String username, String msgID) throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(username)) {
	
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
	
				return objectNode;
			}
	
			try {
	
				ResteasyWebTarget webTarget = EndPoints.APPLICATION_TEMPLATE.resolveTemplate("ownerUserName", username).path("offline_msg_status").path(msgID);
				ObjectNode body = factory.objectNode();
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_GET, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_offlineMsgStatusByMsgId_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_offlineMsgStatusByMsgId_00_ERROR.getMsg());
			String params="";
			params="username:"+username+","+"msgID:"+msgID+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode deactivateUser(String username) throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(username)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			try {
	
				ResteasyWebTarget webTarget = EndPoints.USERS_TARGET.path(username).path("deactivate");
				ObjectNode body = factory.objectNode();
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_POST, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_deactivateUser_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_deactivateUser_00_ERROR.getMsg());
			String params="";
			params="username:"+username+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode activateUser(String username) throws CustomException{

		try{
			
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(username)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			try {
	
				ResteasyWebTarget webTarget = EndPoints.USERS_TARGET.path(username).path("activate");
				ObjectNode body = factory.objectNode();
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_POST, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_activateUser_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_activateUser_00_ERROR.getMsg());
			String params="";
			params="username:"+username+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public ObjectNode disconnectUser(String username) throws CustomException{

		try{
			ObjectNode objectNode = factory.objectNode();
	
			if (StringUtils.isEmpty(username)) {
				objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
				return objectNode;
			}
	
			try {
				ResteasyWebTarget webTarget = EndPoints.APPLICATION_TEMPLATE.resolveTemplate("ownerUserName", username).path("disconnect");
				ObjectNode body = factory.objectNode();
				objectNode = ResteasyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_GET, null);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.IMUsersServiceImpl_disconnectUser_00_ERROR.getCode(),
					ImFacadeEnums.IMUsersServiceImpl_disconnectUser_00_ERROR.getMsg());
			String params="";
			params="username:"+username+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
}
