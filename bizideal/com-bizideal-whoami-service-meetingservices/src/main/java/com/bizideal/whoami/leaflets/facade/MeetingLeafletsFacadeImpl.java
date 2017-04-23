package com.bizideal.whoami.leaflets.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.facade.leaflets.entity.MeetingLeaflets;
import com.bizideal.whoami.facade.leaflets.facade.MeetingLeafletsFacade;
import com.bizideal.whoami.leaflets.service.MeetingLeafletsBiz;
import com.github.pagehelper.PageInfo;

@Component("meetingLeafletsFacade")
public class MeetingLeafletsFacadeImpl implements MeetingLeafletsFacade{

	@Autowired
	private MeetingLeafletsBiz meetingLeafletsBiz;
	
	@Override
	public MeetingLeaflets selectByMeeIdInUse(Integer meeId) {
		return meetingLeafletsBiz.selectByMeeIdInUse(meeId);
	}

	@Override
	public PageInfo<MeetingLeaflets> selectByMeeId(Integer meeId,int pageNum,int pageSize) {
		return meetingLeafletsBiz.selectByMeeId(meeId,pageNum,pageSize);
	}

	@Override
	public MeetingLeaflets selectById(Integer id) {
		return meetingLeafletsBiz.selectById(id);
	}

	@Override
	public int insertMeetingLeaflets(MeetingLeaflets meetingLeaflets) {
		return meetingLeafletsBiz.insertMeetingLeaflets(meetingLeaflets);
	}

	@Override
	public int updateMeetingLeaflets(MeetingLeaflets meetingLeaflets) {
		return meetingLeafletsBiz.updateMeetingLeaflets(meetingLeaflets);
	}

	@Override
	public int deleteById(Integer id) {
		return meetingLeafletsBiz.deleteById(id);
	}

	@Override
	public int deleteByIds(List<Integer> ids) {
		return meetingLeafletsBiz.deleteByIds(ids);
	}

}
