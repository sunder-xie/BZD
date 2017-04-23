package com.bizideal.whoami.core.meeting.mapper;

import java.util.List;

import com.bizideal.whoami.facade.meeting.dto.MeetingHallDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;

import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName MeetingHallMapper
 * @Description TODO(会议厅Mapper)
 * @Author Zj.Qu
 * @Date 2016-12-04 14:47:55
 */
public interface MeetingHallMapper extends Mapper<MeetingHall>{

	/**
	 * 根据用户id查询会议厅
	 * @param userId
	 * @return
	 */
	MeetingHall queryByUserIdMeetingHall(String userId);
	
	/**
	 * 根据多个用户查询会议厅
	 * @param hall_ids
	 * @return
	 */
	List<MeetingHall> queryByUserIdsMeeHall(List<Integer> hall_ids);
	
	/**
	 * 用户关注的会议厅
	 * @param userId
	 * @return
	 */
	List<MeetingHallDto> listUserFollowHotHall(String userId);
}