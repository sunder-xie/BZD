package com.bizideal.whoami.core.meeting.biz;

import java.util.List;

import com.bizideal.whoami.croe.service.BaseOpBiz;
import com.bizideal.whoami.facade.meeting.dto.MeetingHallDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingHallBiz
 * @Description TODO(会议厅业务层)
 * @Author Zj.Qu
 * @Date 2016-12-04 16:23:50
 */
public interface MeetingHallBiz extends BaseOpBiz<MeetingHall> {

	
	/**
	 * 根据用户编号查询会议厅
	 * @param userId 用户编号
	 * @param rows 行数
	 * @param page 页数
	 * @return
	 */
	PageInfo<MeetingHall> queryPageListByUserId(String userId, Integer rows, Integer page)
			throws CustomException;

	/**
	 * 用户最后离开的会议厅
	 * @param userId
	 * @return
	 */
	MeetingHall queryByUserIdMeetingHall(String userId)
			throws CustomException;
	
	/**
	 * 根据主键逻辑删除会议厅
	 * @param hallId
	 * @return
	 */
	Integer delLogicMeetingHall(Integer hallId)
			throws CustomException;
	
	/**
	 * 根据List<Integer> hall_ids 查询会议厅信息
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
	
	/**
	 * 更新会议厅
	 * 
	 * @param meetingHall
	 * @return
	 */
	Integer updateMeetingHall(MeetingHall meetingHall)
			throws CustomException;
}
