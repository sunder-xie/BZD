package com.bizideal.whoami.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.mapper.MeetingDynamicImageMapper;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicImage;
import com.bizideal.whoami.service.MeetingDynamicImageBiz;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName MeetingDynamicImageBizImpl
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 14:18:29
 */
@Service("meetingDynamicImageBiz")
public class MeetingDynamicImageBizImpl extends BaseBizImpl<MeetingDynamicImage> implements MeetingDynamicImageBiz {

	@Autowired
	private MeetingDynamicImageMapper meetingDynamicImageMapper;

	public Integer save(MeetingDynamicImage meetingDynamicImage) {
		return meetingDynamicImageMapper.insert(meetingDynamicImage);
	}

	public Integer deleteByWhere(MeetingDynamicImage meetingDynamicImage) {
		return meetingDynamicImageMapper.deleteBydynamicId(meetingDynamicImage);
	}
}
