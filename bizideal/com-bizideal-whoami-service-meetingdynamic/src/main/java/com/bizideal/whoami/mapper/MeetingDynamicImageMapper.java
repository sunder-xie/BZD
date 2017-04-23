package com.bizideal.whoami.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicImage;

import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName MeetingDynamicImage
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 13:49:00
 */
public interface MeetingDynamicImageMapper extends Mapper<MeetingDynamicImage> {

	@Delete("delete from meeting_dynamic_image where dynamic_id = #{dynamicId}")
	Integer deleteBydynamicId(MeetingDynamicImage meetingDynamicImage);

	@Select("select * from meeting_dynamic_image where dynamic_id = #{dynamicId}")
	List<MeetingDynamicImage> selectBydynamicId(MeetingDynamicImage meetingDynamicImage);

}
