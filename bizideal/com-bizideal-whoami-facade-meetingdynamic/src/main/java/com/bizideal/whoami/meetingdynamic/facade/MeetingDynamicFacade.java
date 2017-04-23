package com.bizideal.whoami.meetingdynamic.facade;

import java.util.Map;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamic;

/**
 * @ClassName MeetingDynamicFacade
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 15:18:00
 */
public interface MeetingDynamicFacade {
	
	/**
	 * 保存会议动态基本信息
	 * @param meetingDynamic
	 * @return
	 */
	Map<String,Object> insertMeetingDynamic(MeetingDynamic meetingDynamic);
	
	/**
	 * 修改会议动态基本信息
	 * @param meetingDynamic
	 * @return
	 */
	Map<String,Object> updateMeetingDynamic(MeetingDynamic meetingDynamic);
	
	/**
	 * 删除会议动态基本信息
	 * @param meetingDynamic
	 * @return
	 */
	Map<String,Object> deleteMeetingDynamic(MeetingDynamic meetingDynamic);
	
	/**
	 * 查询全部会议动态基本信息
	 * @param meetingDynamic
	 * @return
	 */
	Map<String,Object> selectMeetingDynamic(MeetingDynamic meetingDynamic);
	
	/**
	 * 查询一个会议动态基本信息
	 * @param meetingDynamic
	 * @return
	 */
	Map<String,Object> selectMeetingDynamicByDynamicId(MeetingDynamic meetingDynamic);
	
}
