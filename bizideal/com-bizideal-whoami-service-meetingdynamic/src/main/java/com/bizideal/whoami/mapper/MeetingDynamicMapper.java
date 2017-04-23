package com.bizideal.whoami.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamic;
import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamic;

/**
 * @ClassName MeetingDynamic
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 13:47:05
 */
public interface MeetingDynamicMapper extends Mapper<MeetingDynamic> {

	@Select("select * from meeting_dynamic d left join meeting_dynamic_content c on d.dynamic_id = c.dynamic_id where d.meeting_id = #{meetingId} and d.meet_hall_id = #{meetHallId}")
	List<MeetingDynamic> selectDynamic(MeetingDynamic meetingDynamic);

	@Select("select * from meeting_dynamic d left join meeting_dynamic_content c on d.dynamic_id = c.dynamic_id where d.dynamic_id = #{dynamicId}")
	MeetingDynamic selectDynamicOne(MeetingDynamic meetingDynamic);
	
	@Delete("delete from meeting_dynamic where dynamic_id = #{dynamicId}")
	Integer deleteBydynamicId(MeetingDynamic meetingDynamic);
	
	/**
	 * 查询最近插入的动态信息
	 * @param dynamic
	 * @return
	 */
	List<MeetingDynamic> selectCurrentDynamics(MeetingDynamic dynamic);
	
	/**
	 * 插入最近插入的动态信息
	 * @param list
	 * @return
	 */
	int addBatchDynamic(List<MeetingDynamic> list);
	
	/**
	 * 更新最近插入的动态信息
	 * @param list
	 * @return
	 */
	int updateBatchDynamic(List<MeetingDynamic> list);
	
	/**
	 * 根据动态的URL查询是否有重复
	 * @param list
	 * @return
	 */
	List<MeetingDynamic> selectDynamicByUrls(@Param("list") List<MeetingDynamic> list, @Param("meetingId") Integer meetingId);
	
	/**
	 * 批量删除会议动态
	 * @param dynamic
	 * @return
	 */
	int deleteDynamicBatch(MeetingDynamic dynamic);

}
