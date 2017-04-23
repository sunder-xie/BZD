package com.bizideal.whoami.user.facade;

import java.util.List;

import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.user.dto.GroupDto;
import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.entity.UserGroup;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月10日 下午3:02:35
 * @version 1.0
 */
public interface UserGroupRestFacade {

	/**
	 * 创建群，并加入群成员
	 * 
	 * @param chatGroup
	 * @return
	 */
	DubboxResult insertCreate(ChatGroup chatGroup);

	/**
	 * 批量加入群成员
	 * 
	 * @param userGroup
	 * @return DubboxResult
	 */
	DubboxResult insertAddUsers(UserGroup userGroup);

	/**
	 * 删除群成员
	 * 
	 * @param userGroup
	 * @return DubboxResult
	 */
	DubboxResult deleteUsers(UserGroup userGroup);

	/**
	 * 查询用户群列表
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	List<GroupDto> selectGroupIds(String userId);

	/**
	 * 查询群成员列表
	 * 
	 * @param groupId
	 *            群id
	 * @return
	 */
	List<UserFriendDto> selectGroupMembers(String groupId);

	/**
	 * 解散群
	 * 
	 * @param groupId
	 *            群id
	 * @return
	 */
	DubboxResult deleteGroup(String groupId);

	/**
	 * 修改群基本信息
	 * 
	 * @param chatGroup
	 * @return
	 */
	DubboxResult updateGroupName(ChatGroup chatGroup);
}
