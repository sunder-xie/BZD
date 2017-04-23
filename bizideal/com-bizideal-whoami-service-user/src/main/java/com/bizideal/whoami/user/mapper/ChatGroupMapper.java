package com.bizideal.whoami.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.user.entity.ChatGroups;

public interface ChatGroupMapper extends Mapper<ChatGroups> {

	@Delete("DELETE FROM chat_groups WHERE group_id = #{groupId}")
	int deleteChatGroup(@Param("groupId") String groupId);

	@Update("UPDATE chat_groups SET group_name=#{groupName},group_description=#{groupDescription},"
			+ "group_maxusers=#{groupMaxusers} WHERE group_id = #{groupId}")
	int updateChatGroup(@Param("groupId") String groupId,
						@Param("groupName") String groupName,
						@Param("groupDescription") String groupDescription,
						@Param("groupMaxusers") String groupMaxusers,
						@Param("groupOwner") String groupOwner,
						@Param("groupIsPublic") String groupIsPublic,
						@Param("approval") String approval);

	@Update("UPDATE chat_groups SET group_name=#{groupname},group_description=#{description},group_maxusers=#{maxusers} WHERE group_id = #{groupid}")
	int updateGroupName(ChatGroup chatGroup);

	@Select("SELECT * FROM chat_groups WHERE group_owner = #{groupowner}")
	List<ChatGroups> selectGroupByuserId(
			@Param("groupowner") String groupowner);
}
