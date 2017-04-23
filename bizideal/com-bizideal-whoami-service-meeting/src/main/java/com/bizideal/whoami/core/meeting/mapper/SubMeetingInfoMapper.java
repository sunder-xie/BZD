package com.bizideal.whoami.core.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;

public interface SubMeetingInfoMapper extends Mapper<SubMeetingInfo>{

	int deleteSubMeetingInfo(SubMeetingInfo subMeetingInfo);

	int deleteSubMeetingInfoByParentIds(@Param("ids") List<String> ids);

	int deleteSubMeetingInfoByIds(@Param("ids") List<String> ids);
}
