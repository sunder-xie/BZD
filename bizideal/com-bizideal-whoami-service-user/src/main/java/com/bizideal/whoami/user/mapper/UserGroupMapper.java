package com.bizideal.whoami.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.user.dto.GroupDto;
import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.entity.UserGroup;

public interface UserGroupMapper extends Mapper<UserGroup> {

	int insertList(List<UserGroup> list);

	int deleteList(Map<String, Object> map);

	@Delete("DELETE FROM user_group WHERE user_id = #{userId} AND group_id = #{groupId}")
	int deleteUsers(@Param("userId") String userId,
					@Param("groupId") String groupId);

	@Select("SELECT c.group_id,c.group_name FROM user_group u LEFT JOIN chat_groups c ON u.group_id = c.group_id WHERE u.user_id = #{userId} ")
	List<GroupDto> selectGroupIds(String userId);

	@Select("SELECT u.headimgurl FROM user_weixin_info u LEFT JOIN user_group g ON u.user_id = g.user_id WHERE g.group_id = #{groupId} LIMIT 0,9")
	List<String> selectHeadimgurls(String groupId);

	@Select("SELECT u.user_id,u.headimgurl,u.nickname,u.sex,u.real_name,ui.phone FROM user_weixin_info u LEFT JOIN user_group ug "
			+ " ON u.user_id = ug.user_id LEFT JOIN user_info ui ON u.user_id = ui.user_id WHERE ug.group_id = #{groupId}")
	List<UserFriendDto> selectGroupMembers(String groupId);

	@Delete("DELETE FROM user_group WHERE group_id = #{groupId}")
	int deleteGroup(String groupId);

	// 根据群ID统计人数
	@Select("SELECT COUNT(mg.group_id) count FROM user_group mg WHERE mg.group_id=#{groupId}")
	int getUserGroupCount(@Param("groupId") String groupId);

}
