package com.bizideal.whoami.core.meetingmaterial.mapper;

import org.apache.ibatis.annotations.Update;

import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;

import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName MeetingAttachMapper
 * @Description TODO((会议附件:会议议程,会议导读))
 * @Author Zj.Qu
 * @Date 2016-12-14 16:52:12
 */
public interface MeetingAttachMapper extends Mapper<MeetingAttach> {

	@Update("UPDATE meeting_attach SET del_flag = 1,update_user = #{updateUser},update_time = #{updateTime} WHERE hall_id = #{hallId} AND parmee_id = #{parmeeId} AND submee_id = #{submeeId} AND attach_type = #{attachType}")
	int updateCoverOther(MeetingAttach meetingAttach);

}