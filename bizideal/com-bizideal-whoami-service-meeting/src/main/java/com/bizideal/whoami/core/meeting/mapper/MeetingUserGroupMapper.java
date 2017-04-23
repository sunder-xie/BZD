package com.bizideal.whoami.core.meeting.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.facade.meeting.dto.MeetingGroupDto;
import com.bizideal.whoami.facade.meeting.dto.MeetingUsersDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingUserGroup;

/**
 * 用户聊天群Mapper
 * 
 * @author sy
 * @date 2017-2-21 10:12:23
 */
public interface MeetingUserGroupMapper extends Mapper<MeetingUserGroup> {

	/**
	 * 批量插入用户聊天群信息
	 * 
	 * @param list
	 * @return
	 */
	int insertList(List<MeetingUserGroup> list);

	/**
	 * 批量删除用户聊天群信息
	 * 
	 * @param map
	 * @return
	 */
	int deleteList(Map<String, Object> map);

	/**
	 * 根据用户id和群id删除用户聊天群信息
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	@Delete("DELETE FROM meeting_user_group WHERE user_id = #{userId} AND group_id = #{groupId}")
	int deleteUsers(@Param("userId") String userId, @Param("groupId") String groupId);

	/**
	 * 根据用户id查找用户聊天群信息
	 * 
	 * @param userId
	 * @return
	 */
	@Select("SELECT c.group_id,c.group_name FROM user_group u LEFT JOIN meeting_groups c ON u.group_id = c.group_id WHERE u.user_id = #{userId} ")
	List<MeetingGroupDto> selectGroupIds(String userId);

	/**
	 * 根据群id查找用户头像图片URL
	 * 
	 * @param groupId
	 * @return
	 */
	@Select("SELECT u.headimgurl FROM user_weixin_info u LEFT JOIN meeting_user_group g ON u.user_id = g.user_id WHERE g.group_id = #{groupId} LIMIT 0,9")
	List<String> selectHeadimgurls(String groupId);

	/**
	 * 根据群id查找用户好友信息
	 * 
	 * @param groupId
	 * @return
	 */
	@Select("SELECT u.user_id,u.headimgurl,u.nickname,u.sex,u.real_name,ui.phone FROM user_weixin_info u LEFT JOIN meeting_user_group mug "
			+ " ON u.user_id = mug.user_id LEFT JOIN user_info ui ON u.user_id = ui.user_id WHERE mug.group_id = #{groupId}")
	List<MeetingUsersDto> selectGroupMembers(String groupId);

	/**
	 * 根据群id删除用户聊天群信息
	 * 
	 * @param groupId
	 * @return
	 */
	@Delete("DELETE FROM meeting_user_group WHERE group_id = #{groupId}")
	int deleteGroup(String groupId);

	/**
	 * 根据群ID统计人数
	 * 
	 * @param groupId
	 * @return
	 */
	@Select("SELECT COUNT(mg.group_id) count FROM meeting_user_group mg WHERE mg.group_id=#{groupId}")
	int getUserGroupCount(@Param("groupId") String groupId);

}
