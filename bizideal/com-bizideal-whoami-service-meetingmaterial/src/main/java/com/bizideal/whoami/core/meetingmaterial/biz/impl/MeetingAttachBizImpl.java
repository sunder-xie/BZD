package com.bizideal.whoami.core.meetingmaterial.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.meetingmaterial.biz.MeetingAttachBiz;
import com.bizideal.whoami.core.meetingmaterial.mapper.MeetingAttachMapper;
import com.bizideal.whoami.croe.service.impl.BaseOpBizImpl;
import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;

/**
 * @ClassName MeetingAttachBizImpl
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-14 16:51:44
 */
@Service("meetingAttachBiz")
public class MeetingAttachBizImpl extends BaseOpBizImpl<MeetingAttach> implements MeetingAttachBiz{

	@Autowired
	private MeetingAttachMapper meetingAttachMapper;

	@Override
	public int updateCoverOther(MeetingAttach meetingAttach) {
		return meetingAttachMapper.updateCoverOther(meetingAttach);
	}
	
}
