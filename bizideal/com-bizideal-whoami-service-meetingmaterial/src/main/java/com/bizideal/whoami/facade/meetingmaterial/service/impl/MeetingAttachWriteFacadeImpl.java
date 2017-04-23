package com.bizideal.whoami.facade.meetingmaterial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.meetingmaterial.biz.MeetingAttachBiz;
import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;
import com.bizideal.whoami.facade.meetingmaterial.service.MeetingAttachWriteFacade;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月16日 下午2:45:22
 * @version 1.0
 */
@Component("meetingAttachWriteFacade")
public class MeetingAttachWriteFacadeImpl implements MeetingAttachWriteFacade {
	@Autowired
	private MeetingAttachBiz meetingAttachBiz;

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

	@Override
	public int updateCoverOther(MeetingAttach meetingAttach) {
		return meetingAttachBiz.updateCoverOther(meetingAttach);
	}

}
