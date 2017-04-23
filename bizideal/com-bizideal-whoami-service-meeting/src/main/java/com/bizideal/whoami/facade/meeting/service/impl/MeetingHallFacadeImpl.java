package com.bizideal.whoami.facade.meeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.meeting.biz.MeetingHallBiz;
import com.bizideal.whoami.facade.meeting.dto.MeetingHallDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.service.MeetingHallFacade;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingHallMapper
 * @Description TODO(会议厅)
 * @Author Zj.Qu
 * @Date 2016-12-04 14:47:55
 */
@Component("meetingHallFacade")
public class MeetingHallFacadeImpl implements MeetingHallFacade {

	@Autowired
	private MeetingHallBiz meetingHallBiz;

	@Override
	public Integer addMeetingHall(MeetingHall meetingHall) 
			throws CustomException{
		return meetingHallBiz.saveSelective(meetingHall);
	}

	@Override
	public Integer delMeetingHall(Integer hallId) 
			throws CustomException{
		return meetingHallBiz.delLogicMeetingHall(hallId);
	}

	@Override
	public MeetingHall queryByIdMeetingHall(Integer hallId) 
			throws CustomException{
		return meetingHallBiz.queryById(hallId);
	}

	@Override
	public Integer updateMeetingHall(MeetingHall meetingHall) 
			throws CustomException{
		return meetingHallBiz.updateMeetingHall(meetingHall);
	}

	@Override
	public PageInfo<MeetingHall> queryPageListByUserId(String userId, Integer page, Integer rows) 
			throws CustomException{
		return meetingHallBiz.queryPageListByUserId(userId, page, rows);
	}

	@Override
	public MeetingHall queryByUserIdMeetingHall(String userId) 
			throws CustomException{
		return meetingHallBiz.queryByUserIdMeetingHall(userId);
	}

	@Override
	public PageInfo<MeetingHall> queryAllMeetingHall(Integer page,Integer rows,MeetingHall meetingHall) 
			throws CustomException{
		return meetingHallBiz.queryPageListByWhere(page, rows, meetingHall);
	}

	@Override
	public List<MeetingHall> queryByUserIdsMeeHall(List<Integer> hall_ids) 
			throws CustomException{
		return meetingHallBiz.queryByUserIdsMeeHall(hall_ids);
	}
	
	@Override
	public PageInfo<MeetingHallDto> listUserFollowHotHall(Integer page, Integer rows, String userId) 
			throws CustomException{
		return meetingHallBiz.listUserFollowHotHall(page, rows, userId);
	}

	@Override
	public List<MeetingHall> queryByHallName(String hallName) 
			throws CustomException{
		return meetingHallBiz.queryByHallName(hallName);
	}
	
}