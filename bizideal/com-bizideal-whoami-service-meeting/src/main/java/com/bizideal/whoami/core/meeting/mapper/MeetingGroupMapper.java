package com.bizideal.whoami.core.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;

/**
 * 聊天群Mapper
 * 
 * @author sy
 * @date 2017-2-21 10:11:32
 *
 */
public interface MeetingGroupMapper extends Mapper<MeetingGroups> {

	/**
	 * 根据群id删除聊天群
	 * 
	 * @param groupId
	 * @return
	 */
	@Delete("DELETE FROM meeting_groups WHERE group_id = #{groupId}")
	int deleteMeetingGroup(@Param("groupId") String groupId);

	/**
	 * 更新聊天群信息
	 * 
	 * @param MeetingGroups
	 * @return
	 */
	@Update("UPDATE meeting_groups SET group_name=#{groupname},group_description=#{groupDescription}, group_is_public=#{groupIsPublic}, "
			+ " group_maxusers=#{maxusers}, group_owner=#{groupOwner}, approval=#{approval}"
			+ "WHERE group_id = #{groupid} and mee_id = #{meeId}")
	int updateMeetingGroups(MeetingGroups meetingGroups);

	/**
	 * 根据群管理员查找聊天群
	 * 
	 * @param groupowner
	 * @return
	 */
	@Select("SELECT * FROM meeting_groups WHERE group_owner = #{groupowner}")
	List<MeetingGroups> selectGroupByuserId(@Param("groupowner") String groupowner);
}
