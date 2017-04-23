package com.bizideal.whoami.facade.meetingmaterial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.meetingmaterial.biz.MeetingAttachBiz;
import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;
import com.bizideal.whoami.facade.meetingmaterial.service.MeetingAttachFacade;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingAttachFacadeImpl
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-14 16:53:10
 */
@Component("meetingAttachFacade")
public class MeetingAttachFacadeImpl implements MeetingAttachFacade {

	@Autowired
	private MeetingAttachBiz meetingAttachBiz;

	@Override
	public MeetingAttach queryById(int id) {
		return meetingAttachBiz.queryById(id);
	}

	@Override
	public List<MeetingAttach> queryListByWhere(MeetingAttach record) {
		return meetingAttachBiz.queryListByWhere(record);
	}

	@Override
	public PageInfo<MeetingAttach> queryPageListByWhere(Integer page, Integer rows, MeetingAttach record) {
		return meetingAttachBiz.queryPageListByWhere(page, rows, record);
	}

	@Override
	public Integer saveSelective(MeetingAttach t) {
		return meetingAttachBiz.saveSelective(t);
	}

	@Override
	public Integer updateSelective(MeetingAttach t) {
		return meetingAttachBiz.updateSelective(t);
	}

	@Override
	public Integer deleteLogicById(MeetingAttach t) {
		return meetingAttachBiz.deleteLogicById(t);
	}

}