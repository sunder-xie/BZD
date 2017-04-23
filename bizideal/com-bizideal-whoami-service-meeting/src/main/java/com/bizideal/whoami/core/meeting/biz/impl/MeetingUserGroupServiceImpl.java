package com.bizideal.whoami.core.meeting.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.bizideal.whoami.core.meeting.biz.MeetingUserGroupService;
import com.bizideal.whoami.core.meeting.mapper.MeetingGroupMapper;
import com.bizideal.whoami.core.meeting.mapper.MeetingUserGroupMapper;
import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.facade.meeting.dto.MeetingUsersDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;
import com.bizideal.whoami.facade.meeting.entity.MeetingUserGroup;
import com.bizideal.whoami.facade.meeting.enums.MeettingExceptionEnums;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.im.facade.ChatGroupsFacade;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 作者 sy:
 * @data 创建时间：2017年2月17日11:24:59
 * @version 1.0
 */
@Service("meetingUserGroupService")
public class MeetingUserGroupServiceImpl extends BaseBizImpl<MeetingUserGroup> implements MeetingUserGroupService {

	@Autowired
	private MeetingUserGroupMapper meetingUserGroupMapper;
	@Autowired
	private MeetingGroupMapper meetingGroupMapper;
	@Autowired
	private ChatGroupsFacade chatGroupsFacade;
	
	@Value("${fastdfsurl}")
	private String fastdfsurl;

	@Override
	public boolean insertAddUsers(MeetingUserGroup meetingUserGroup) throws CustomException{
		try {
			if (null == meetingUserGroup) {
				throw new Exception(
						"{'code':'" + MeettingExceptionEnums.MeetingUserGroupServiceImpl_insertAddUsers_01_ERROR.getCode()
								+ "','msg':'"
								+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_insertAddUsers_01_ERROR.getMsg() + "'}");
			}
			ObjectMapper mapper = new ObjectMapper();
			String userIds = meetingUserGroup.getUserIds();
			String groupId = meetingUserGroup.getGroupId();
			if (StringUtils.isBlank(userIds) || StringUtils.isBlank(groupId)) {
				throw new Exception(
						"{'code':'"+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_insertAddUsers_01_ERROR.getCode()
								+ "','msg':'"
								+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_insertAddUsers_01_ERROR.getMsg() + "'}");
			}
			// 调用环信批量添加群成员
			String addString = chatGroupsFacade.addUsersToGroupBatch(groupId,
					userIds);
			if (StringUtils.isBlank(addString) || !"200".equals(mapper.readTree(addString).get("statusCode").asText())) {
				if(!"403".equals(mapper.readTree(addString).get("statusCode").asText())){
					throw new Exception("{'code':'"
							+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_insertAddUsers_02_ERROR.getCode()
							+ "','msg':'"
							+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_insertAddUsers_02_ERROR.getMsg() + "'}");
				}
			}
			// 数据库添加群成员
			List<MeetingUserGroup> meetingUserGroups = new ArrayList<MeetingUserGroup>();
			MeetingUserGroup group = null;
			for (String userId : StringUtils.split(userIds, ",")) {
				group = new MeetingUserGroup(userId, groupId);
				//判断数据幂等性
				MeetingUserGroup selectOne = meetingUserGroupMapper.selectOne(group);
				if(selectOne == null){
					meetingUserGroups.add(group);
				}
			}
			if(meetingUserGroups.size() > 0){
				int insertList = meetingUserGroupMapper.insertList(meetingUserGroups);
				if (insertList != meetingUserGroups.size()) {
					//回滚
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();		
					return false;
				}
			}
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_insertAddUsers_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_insertAddUsers_00_ERROR.getMsg());
			String params = null;
			if (null != meetingUserGroup) {
				params = meetingUserGroup.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e, params);
			throw customException;
		}
		return true;
	}

	@Override
	//事务注解
	@Transactional
	public boolean deleteUsers(MeetingUserGroup userGroup) 
			throws CustomException{
		try{
			if (null == userGroup) {
				throw new Exception(
						"{'code':'"+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteUsers_01_ERROR.getCode()
								+ "','msg':'"
								+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteUsers_01_ERROR.getMsg() + "'}");
			}
			ObjectMapper mapper = new ObjectMapper();
			String userIdStr = userGroup.getUserIds();
			String groupId = userGroup.getGroupId();
			if (StringUtils.isBlank(userIdStr) || StringUtils.isBlank(groupId)) {
				throw new Exception(
						"{'code':'"+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteUsers_01_ERROR.getCode()
								+ "','msg':'"
								+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteUsers_01_ERROR.getMsg() + "'}");
			}
			// 调用环信批量删除群成员
			String deleteString = chatGroupsFacade.deleteUserFromGroupBatch(groupId, userIdStr);
			if (StringUtils.isBlank(deleteString) || !"200".equals(mapper.readTree(deleteString).get("statusCode").asText())) {
				throw new Exception(
						"{'code':'" + MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteUsers_02_ERROR.getCode()
								+ "','msg':'"
								+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteUsers_02_ERROR.getMsg() + "'}");
			}
				// 数据库批量删除群成员
				Map<String, Object> delMap = new HashMap<String, Object>();
				String[] userIds = StringUtils.split(userIdStr, ",");
				delMap.put("groupId", groupId);
				delMap.put("userIds", userIds);
				int deleteList = meetingUserGroupMapper.deleteList(delMap);
				if (deleteList == userIds.length) {
					return true;
				}else{
					//回滚
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return false;
				}
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteUsers_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteUsers_00_ERROR.getMsg());
			String params = null;
			if(null != userGroup){
				params = userGroup.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public List<MeetingUsersDto> selectGroupMembers(String groupId) 
			throws CustomException{
		try{
			List<MeetingUsersDto> meetingUsersDtos = meetingUserGroupMapper.selectGroupMembers(groupId);
			return meetingUsersDtos;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_selectGroupMembers_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_selectGroupMembers_00_ERROR.getMsg());
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != groupId){
				params = "groupId:" + groupId.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public boolean deleteAllGroupMembers(String groupId) throws CustomException{
		try{
			if (StringUtils.isBlank(groupId)) {
				throw new Exception(
						"{'code':'" + MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteAllGroupMembers_01_ERROR.getCode()
								+ "','msg':'"
								+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteAllGroupMembers_01_ERROR.getMsg() + "'}");
			}
			
				ObjectMapper mapper = new ObjectMapper();
				// 调用环信解散群
				String deleteChatGroups = chatGroupsFacade.deleteChatGroups(groupId);
				if (StringUtils.isBlank(deleteChatGroups) || !"200".equals(mapper.readTree(deleteChatGroups).get("statusCode").asText())) {
					throw new Exception(
							"{'code':'" + MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteAllGroupMembers_02_ERROR.getCode()
									+ "','msg':'"
									+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteAllGroupMembers_02_ERROR.getMsg() + "'}");
				}
				// 本地数据库删除群和群成员
				int deleteGroup = meetingUserGroupMapper.deleteGroup(groupId);
				int deleteChatGroup = meetingGroupMapper.deleteMeetingGroup(groupId);
			return !(deleteGroup == 0 || deleteChatGroup == 0);
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteAllGroupMembers_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_deleteAllGroupMembers_00_ERROR.getMsg());
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != groupId){
				params = "groupId:" + groupId.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

	@Override
	public boolean updateMeetingGroups(MeetingGroups meetingGroups) 
			throws CustomException{
		ObjectMapper mapper = new ObjectMapper();
		try {
			//封装IM ChatGroup 实体
			ChatGroup chatGroup = new ChatGroup();
			chatGroup.setId(meetingGroups.getGroupId());
			chatGroup.setGroupid(meetingGroups.getGroupId());
			chatGroup.setGroupname(meetingGroups.getGroupName());
			chatGroup.setDescription(meetingGroups.getGroupDescription());
			chatGroup.setIsPublic(meetingGroups.isGroupIsPublic());
			chatGroup.setMaxusers(meetingGroups.getGroupMaxusers());
			chatGroup.setOwner(meetingGroups.getGroupOwner());
			chatGroup.setApproval(meetingGroups.isApproval());
			chatGroup.setName(meetingGroups.getGroupName());
		
			// 调用环信修改群
			String updateString = chatGroupsFacade.updateChatGroup(chatGroup);
			if (StringUtils.isBlank(updateString) || !"200".equals(mapper.readTree(updateString).get("statusCode").asText())) {
				throw new Exception(
						"{'code':'" + MeettingExceptionEnums.MeetingUserGroupServiceImpl_updateGroupName_01_ERROR.getCode()
								+ "','msg':'"
								+ MeettingExceptionEnums.MeetingUserGroupServiceImpl_updateGroupName_01_ERROR.getMsg() + "'}");
			}
			// 本地数据库修改群信息
			int result = meetingGroupMapper.updateMeetingGroups(meetingGroups);
			return result != 0;
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_updateGroupName_00_ERROR.getCode(),
					MeettingExceptionEnums.MeetingUserGroupServiceImpl_updateGroupName_00_ERROR.getMsg());
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != meetingGroups){
				params = "meetingGroups:" + meetingGroups.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}
}
