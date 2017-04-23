package com.bizideal.whoami.facade.meeting.service.restful;

import java.util.List;
import java.util.Map;

import com.bizideal.whoami.facade.meeting.dto.MeetingInfoDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName MeetingInfoRestFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月6日
 */
public interface MeetingInfoRestFacade {

	/**
	 * 插入一条主会议记录
	 * @param meetingInfo
	 * @return
	 */
	int insertMeetingInfo(MeetingInfo meetingInfo)
			throws CustomException;
	
	/**
	 * 插入一条主会议记录
	 * @param subMeetingInfo
	 * @return
	 */
	Map<String,Object> insertSubMeetingInfo(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 更新一条会议记录
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> updateMeetingInfo(MeetingInfo meetingInfo)
			throws CustomException;

	/**
	 * 删除主会议记录
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> deleteMeetingInfoByIds(List<String> ids)
			throws CustomException;

	/**
	 * 删除一条子会议记录
	 * @param subMeetingInfo
	 * @return
	 */
	Map<String,Object> deleteSubMeetingInfoById(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 根据条件查询主会议信息
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> selectMeetingInfo(MeetingInfo meetingInfo)
			throws CustomException;
	
	/**
	 * 根据条件查询子子会议信息
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> selectSubMeetingInfo(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 根据会议ID字符串来逻辑删除会议信息
	 * @param ids
	 * @return
	 */
	Map<String,Object> deleteSubMeetingInfoByIds(List<String> ids)
			throws CustomException;
	
	/**
	 * 根据会议的名称来查询主会议
	 * 注：这里不是模糊查询,返回中如果是null就没有重复，否则返回重复的实体类
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> selectMeetingInfoByName(MeetingInfo meetingInfo)
			throws CustomException;
	
	/**
	 * 查询热门主会议
	 * 注：这里不是模糊查询,返回中如果是null就没有重复，否则返回重复的实体类
	 * @param meetingInfoDto
	 * @return
	 */
	Map<String,Object> selectPopMeetingInfo(MeetingInfoDto meetingInfoDto)
			throws CustomException;
}
