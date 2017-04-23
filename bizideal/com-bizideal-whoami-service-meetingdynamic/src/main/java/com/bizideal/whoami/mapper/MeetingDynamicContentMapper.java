package com.bizideal.whoami.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicContent;
/**
 * @ClassName MeetingDynamicContentMapper
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 13:48:21
 */
public interface MeetingDynamicContentMapper extends Mapper<MeetingDynamicContent>{

	@Delete("delete from meeting_dynamic_content where dynamic_id = #{dynamicId}")
	Integer deleteBydynamicId(MeetingDynamicContent meetingDynamicContent);
	
	/**
	 * 插入最近插入的动态内容
	 * @param list
	 * @return
	 */
	int addBatchDynamic(List<MeetingDynamicContent> list);

	/**
	 * 删除所有的动态内容信息
	 * @param dynamicContent
	 * @return
	 */
	int deleteDynamicContentBatch(MeetingDynamicContent dynamicContent);

}
