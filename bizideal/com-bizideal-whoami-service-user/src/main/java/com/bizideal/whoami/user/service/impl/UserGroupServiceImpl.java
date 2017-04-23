package com.bizideal.whoami.user.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.im.facade.ChatGroupsFacade;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.user.dto.GroupDto;
import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.entity.ChatGroups;
import com.bizideal.whoami.user.entity.UserGroup;
import com.bizideal.whoami.user.mapper.ChatGroupMapper;
import com.bizideal.whoami.user.mapper.UserGroupMapper;
import com.bizideal.whoami.user.service.UserGroupService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月10日 下午3:02:35
 * @version 1.0
 */
@Service("userGroupService")
public class UserGroupServiceImpl extends BaseBizImpl<UserGroup> implements
		UserGroupService {

	@Autowired
	private UserGroupMapper userGroupMapper;
	@Autowired
	private ChatGroupMapper chatGroupMapper;
	@Autowired
	private ChatGroupsFacade chatGroupsFacade;
	@Value("${fastdfsurl}")
	private String fastdfsurl;

	@Override
	public DubboxResult insertCreate(ChatGroup chatGroup) {
		ObjectMapper mapper = new ObjectMapper();
		String createString = chatGroupsFacade.creatChatGroups(chatGroup);
		try {
			if (StringUtils.isBlank(createString)
					|| !"200".equals(mapper.readTree(createString)
							.get("statusCode").asText())) {
				return DubboxResult.build("1", "调用im创建群失败", null);
			}
			// 插入chat_group
			ChatGroups chatGroups = new ChatGroups();
			String groupId = mapper.readTree(createString).get("data")
					.get("groupid").asText();
			chatGroups.setGroupId(groupId);
			chatGroups.setGroupDescription(chatGroup.getDescription());
			chatGroups.setGroupIsPublic(chatGroup.getIsPublic());
			chatGroups.setApproval(chatGroup.getApproval());
			chatGroups.setGroupMaxusers(chatGroup.getMaxusers().intValue());
			chatGroups.setGroupName(chatGroup.getGroupname());
			chatGroups.setGroupOwner(chatGroup.getOwner());
			chatGroupMapper.insert(chatGroups);
			// 批量插入群成员
			String owner = chatGroup.getOwner();
			String member = chatGroup.getMember();
			List<UserGroup> userGroups = new ArrayList<UserGroup>();
			UserGroup userGroup = null;
			if (StringUtils.isBlank(member)) {
				userGroup = new UserGroup(owner, groupId);
				userGroups.add(userGroup);
			} else {
				String[] split = StringUtils.split(member, ",");
				for (String userId : split) {
					// 群成员
					userGroup = new UserGroup(userId, groupId);
					userGroups.add(userGroup);
				}
				userGroup = new UserGroup(owner, groupId);
				userGroups.add(userGroup);
			}
			userGroupMapper.insertList(userGroups);
			return DubboxResult.build("0", "ok", groupId);
		} catch (IOException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			return DubboxResult.build("1", "系统异常:" + e.getMessage(), null);
		}
	}

	@Override
	public DubboxResult insertAddUsers(UserGroup userGroup) {
		if (null == userGroup) {
			return DubboxResult.build("1", "空参数", null);
		}
		ObjectMapper mapper = new ObjectMapper();
		String userIds = userGroup.getUserIds();
		String groupId = userGroup.getGroupId();
		if (StringUtils.isBlank(userIds) || StringUtils.isBlank(groupId)) {
			return DubboxResult.build("1", "空参数", null);
		}
		// 调用环信批量添加群成员
		String addString = chatGroupsFacade.addUsersToGroupBatch(groupId,
				userIds);
		try {
			if (StringUtils.isBlank(addString)
					|| !"200".equals(mapper.readTree(addString)
							.get("statusCode").asText())) {
				return DubboxResult.build("1", "调用im添加群成员失败", null);
			}
			// 数据库添加群成员
			List<UserGroup> userGroups = new ArrayList<UserGroup>();
			UserGroup group = null;
			for (String userId : StringUtils.split(userIds, ",")) {
				group = new UserGroup(userId, groupId);
				userGroups.add(group);
			}
			userGroupMapper.insertList(userGroups);
			return DubboxResult.build("0", "ok", null);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			e.printStackTrace();
			return DubboxResult.build("1", "系统异常:" + e.getMessage(), null);
		}
	}

	@Override
	public DubboxResult deleteUsers(UserGroup userGroup) {
		if (null == userGroup) {
			return DubboxResult.build("1", "空参数", null);
		}
		ObjectMapper mapper = new ObjectMapper();
		String userIds = userGroup.getUserIds();
		String groupId = userGroup.getGroupId();
		if (StringUtils.isBlank(userIds) || StringUtils.isBlank(groupId)) {
			return DubboxResult.build("1", "空参数", null);
		}
		// 调用环信批量删除群成员
		String deleteString = chatGroupsFacade.deleteUserFromGroupBatch(
				groupId, userIds);
		try {
			if (StringUtils.isBlank(deleteString)
					|| !"200".equals(mapper.readTree(deleteString)
							.get("statusCode").asText())) {
				return DubboxResult.build("1", "调用im删除群成员失败", null);
			}
			// 数据库批量删除群成员
			Map<String, Object> map = new HashMap<String, Object>();
			String[] split = StringUtils.split(userIds, ",");
			map.put("groupId", groupId);
			map.put("userIds", split);
			int deleteList = userGroupMapper.deleteList(map);
			if (deleteList != split.length) {
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return DubboxResult.build("1", "数据库删除失败", null);
			}
			return DubboxResult.build("0", "ok", null);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			e.printStackTrace();
			return DubboxResult.build("1", "系统异常:" + e.getMessage(), null);
		}
	}

	@Override
	public List<GroupDto> selectGroupIds(String userId) {
		List<GroupDto> selectGroupIds = userGroupMapper.selectGroupIds(userId);
		for (GroupDto groupDto : selectGroupIds) {
			List<String> headimgurls = userGroupMapper
					.selectHeadimgurls(groupDto.getGroupId());
			for (int i = 0; i < headimgurls.size(); i++) {
				if (StringUtils.isBlank(headimgurls.get(i))
						|| StringUtils.startsWith(headimgurls.get(i),
								"http://wx")) {
					continue;
				}
				// 补全文件服务器地址
				headimgurls.set(i, fastdfsurl + headimgurls.get(i));
			}
			groupDto.setHeadimgurls(userGroupMapper.selectHeadimgurls(groupDto
					.getGroupId()));
		}
		return selectGroupIds;
	}

	@Override
	public List<UserFriendDto> selectGroupMembers(String groupId) {
		List<UserFriendDto> users = userGroupMapper.selectGroupMembers(groupId);
		for (UserFriendDto userFriendDto : users) {
			if (StringUtils.isBlank(userFriendDto.getHeadimgurl())
					|| StringUtils.startsWith(userFriendDto.getHeadimgurl(),
							"http://wx")) {
				continue;
			}
			userFriendDto.setHeadimgurl(fastdfsurl
					+ userFriendDto.getHeadimgurl());
		}
		return users;
	}

	@Override
	public DubboxResult deleteAllGroupMembers(String groupId) {
		if (StringUtils.isBlank(groupId)) {
			return DubboxResult.build("1", "参数为空", null);
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			// 调用环信解散群
			String deleteChatGroups = chatGroupsFacade
					.deleteChatGroups(groupId);
			if (StringUtils.isBlank(deleteChatGroups)
					|| !"200".equals(mapper.readTree(deleteChatGroups)
							.get("statusCode").asText())) {
				return DubboxResult.build("1", "调用im解散群失败", null);
			}
			// 本地数据库删除群和群成员
			int deleteGroup = userGroupMapper.deleteGroup(groupId);
			int deleteChatGroup = chatGroupMapper.deleteChatGroup(groupId);
			if (deleteGroup == 0 || deleteChatGroup == 0) {
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return DubboxResult.build("1", "本地删除数据库失败", null);
			}
			return DubboxResult.build("0", "ok", null);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			return DubboxResult.build("1", "系统异常:" + e.getMessage(), null);
		}
	}

	@Override
	public DubboxResult updateGroupName(ChatGroup chatGroup) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			// 调用环信修改群
			String updateString = chatGroupsFacade.updateChatGroup(chatGroup);
			if (StringUtils.isBlank(updateString)
					|| !"200".equals(mapper.readTree(updateString)
							.get("statusCode").asText())) {
				return DubboxResult.build("1", "调用im修改群信息失败", null);
			}
			// 本地数据库修改群信息
			int result = chatGroupMapper.updateGroupName(chatGroup);
			if (result == 0) {
				return DubboxResult.build("1", "本地修改失败", null);
			}
			return DubboxResult.build("0", "ok", null);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			return DubboxResult.build("1", "系统异常:" + e.getMessage(), null);
		}
	}
}
