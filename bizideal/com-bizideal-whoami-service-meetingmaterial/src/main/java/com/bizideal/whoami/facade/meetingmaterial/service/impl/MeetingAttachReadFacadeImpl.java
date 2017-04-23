package com.bizideal.whoami.facade.meetingmaterial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.meetingmaterial.biz.MeetingAttachBiz;
import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;
import com.bizideal.whoami.facade.meetingmaterial.service.MeetingAttachReadFacade;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月16日 下午2:43:45
 * @version 1.0
 */
@Component("meetingAttachReadFacade")
public class MeetingAttachReadFacadeImpl implements MeetingAttachReadFacade {

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
	public PageInfo<MeetingAttach> queryPageListByWhere(Integer page,
			Integer rows, MeetingAttach record) {
		return meetingAttachBiz.queryPageListByWhere(page, rows, record);
	}

}
