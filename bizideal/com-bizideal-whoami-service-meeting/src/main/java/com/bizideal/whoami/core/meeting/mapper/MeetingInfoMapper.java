package com.bizideal.whoami.core.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.facade.meeting.dto.MeetingInfoDto;
import com.bizideal.whoami.facade.meeting.dto.MeetingInfoSignUpDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;

/**
 * @ClassName MeetingInfoMapper
 * @Description TODO(会议信息)
 * @Author Zj.Qu
 * @Date 2016-12-04 14:48:07
 */
public interface MeetingInfoMapper extends Mapper<MeetingInfo>{

	/**
	 * 插入一条会议信息记录
	 * @param meetingInfo
	 * @return
	 */
	int insertMeetingInfo(MeetingInfo meetingInfo);
	
	/**
	 * 更新一条会议信息记录
	 * @param meetingInfo
	 * @return
	 */
	int updateMeetingInfo(MeetingInfo meetingInfo);

	/**
	 * 根据会议信息查询会议记录
	 * 注：在会议的创建时间和会议的是否删除两个地方需要处理，因为有默认值
	 * @param meetingInfo
	 * @return
	 */
	List<MeetingInfo> selectByConditions(MeetingInfo meetingInfo);
	
	/**
	 * 根据会议ID信息删除会议信息
	 * @param meetingInfo
	 * @return
	 */
	int deleteSubMeetingInfo(MeetingInfo meetingInfo);
	
	/**
	 * 根据会议厅ID删除会议信息
	 * @param meetingInfo
	 * @return
	 */
	int deleteMeetingInfoByHall(MeetingInfo meetingInfo);
	
	/**
	 * 根据主会议ID字符串，删除主会议信息
	 * @param ids
	 * @return
	 */
	int deleteMeetingInfoByIds(@Param("ids") List<String> ids);
	/**
	 * 根据会议ID的字符串拼接，删除多个会议
	 * @param ids
	 * @return
	 */
	int deleteSubMeetingInfoByIds(@Param("ids") List<String> ids);
	
	/**
	 * 根据会议名称查询会议，用于查询会议名称是否重复
	 * @param meetingInfo
	 * @return
	 */
	List<MeetingInfo> selectMeetingInfoByName(MeetingInfo meetingInfo);
	
	/**
	 * 根据用户ID查询参加的会议信息已结束的
	 * @param userId
	 * @return
	 */
	List<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdOver(@Param("list") List<Integer> list,
																 @Param("subList") List<Integer> subList, @Param("overTime") long overTime);
	
	/**
	 * 根据用户ID查询参加的会议信息未开始的
	 * @param userId
	 * @return
	 */
	List<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdFuture(@Param("list") List<Integer> list,
																   @Param("subList") List<Integer> subList, @Param("nowTime") long nowTime);
	
	/**
	 * 根据用户ID查询关注的会议信息未开始的
	 * @param userId
	 * @return
	 */
	List<MeetingInfo> selectFocusMeetingInfoByUserIdFuture(@Param("userId") String userId,
														   @Param("nowTime") long nowTime);

	/**
	 * 根据用户ID查询关注的会议信息已结束的
	 * @param userId
	 * @return
	 */
	List<MeetingInfo> selectFocusMeetingInfoByUserIdOver(@Param("userId") String userId,
														 @Param("overTime") long overTime);
	
	/**
	 * 根据用户查询热门会议信息
	 * @param meetingInfoDto
	 * @return
	 */
	List<MeetingInfoDto> selectPopMeetingInfoDto(MeetingInfoDto meetingInfoDto);
	
	/**
	 * 根据会议ID和用户报名会议的id list查询用户某一个主会议的子会议
	 * @param list
	 * @param meeParentId
	 * @return
	 */
	List<MeetingInfo> selectSubMeetingByParentIdList(@Param("list") List<Integer> list);
}