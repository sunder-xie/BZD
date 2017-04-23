package com.bizideal.whoami.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.mapper.MeetingDynamicContentMapper;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicContent;
import com.bizideal.whoami.service.MeetingDynamicContentBiz;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName MeetingDynamicContentBizImpl
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 14:17:57
 */
@Service("meetingDynamicContentBiz")
public class MeetingDynamicContentBizImpl extends BaseBizImpl<MeetingDynamicContent>
		implements MeetingDynamicContentBiz {
  
	@Autowired
	private MeetingDynamicContentMapper meetingDynamicContentMapper;

	public Integer save(MeetingDynamicContent meetingDynamicContent) {
		return meetingDynamicContentMapper.insert(meetingDynamicContent);
	}

	public Integer deleteByWhere(MeetingDynamicContent meetingDynamicContent) {
		return meetingDynamicContentMapper.deleteBydynamicId(meetingDynamicContent);
	}
}
