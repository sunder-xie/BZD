package com.bizideal.whoami.facade.meeting.service;

import java.util.List;

import com.bizideal.whoami.facade.meeting.dto.MeetingHallDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingHallFacade
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-02 17:34:30
 */
public interface MeetingHallFacade {

	/**
	 * 添加会议厅
	 * 
	 * @return
	 */
	Integer addMeetingHall(MeetingHall meetingHall)
			throws CustomException;

	/**
	 * 根据会议厅id删除
	 * 
	 * @param hallId
	 * @return
	 */
	Integer delMeetingHall(Integer hallId)
			throws CustomException;

	/**
	 * 根据主键查询会议厅信息
	 * 
	 * @param hallId
	 * @return
	 */
	MeetingHall queryByIdMeetingHall(Integer hallId)
			throws CustomException;

	/**
	 * 更新会议厅
	 * 
	 * @param meetingHall
	 * @return
	 */
	Integer updateMeetingHall(MeetingHall meetingHall)
			throws CustomException;

	/**
	 * 用户的会议厅列表
	 * 
	 * @param userId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<MeetingHall> queryPageListByUserId(String userId, Integer page, Integer rows)
			throws CustomException;

	/**
	 * 用户默认进入的会议厅
	 * 
	 * @param userId
	 * @return
	 */
	MeetingHall queryByUserIdMeetingHall(String userId)
			throws CustomException;

	/**
	 * 分页查询所有会议厅信息
	 * 
	 * @param meetingHall
	 * @return
	 */
	PageInfo<MeetingHall> queryAllMeetingHall(Integer page, Integer rows, MeetingHall meetingHall)
			throws CustomException;

	/**
	 * 根据List<Integer> hall_ids 查询会议厅信息
	 * 
	 * @param hallId
	 * @return
	 */
	List<MeetingHall> queryByUserIdsMeeHall(List<Integer> hall_ids)
			throws CustomException;

	/**
	 * 热门会议厅
	 * @param meetingHall
	 * @param userId
	 * @return
	 */
	PageInfo<MeetingHallDto> listUserFollowHotHall(Integer page, Integer rows, String userId)
			throws CustomException;
	
	/**
	 * 根据会议厅名查询会议厅信息
	 * @param hallName
	 * @return
	 */
	List<MeetingHall> queryByHallName(String hallName)
			throws CustomException;
}
