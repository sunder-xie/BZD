package com.bizideal.whoami.facade.meeting.service.restful;

import javax.ws.rs.core.Response;

import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.pojo.DubboxResult;

/**
 * @ClassName MeetingHallRestFacade
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-02 17:34:30
 */
public interface MeetingHallRestFacade {

	/**
	 * 添加会议厅
	 * 
	 * @return
	 */
	DubboxResult addMeetingHall(MeetingHall meetingHall)
			throws CustomException;

	/**
	 * 根据会议厅id删除
	 * 
	 * @param hallId
	 * @return
	 */
	DubboxResult delMeetingHall(Integer hallId)
			throws CustomException;

	/**
	 * 根据主键查询会议厅信息
	 * 
	 * @param hallId
	 * @return
	 */
	Response queryByIdMeetingHall(Integer hallId)
			throws CustomException;

	/**
	 * 更新会议厅
	 * 
	 * @param meetingHall
	 * @return
	 */
	DubboxResult updateMeetingHall(MeetingHall meetingHall)
			throws CustomException;


	/**
	 * 用户的会议厅列表
	 * @param userId
	 * @param page
	 * @param rows
	 * @return
	 */
	Response queryPageListByUserId(String userId, Integer page, Integer rows)
			throws CustomException;
	
	/**
	 * 用户默认进入的会议厅
	 * @param userId
	 * @return
	 */
	Response queryByUserIdMeetingHall(String userId)
			throws CustomException;
}
