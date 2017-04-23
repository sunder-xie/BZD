package com.bizideal.whoami.signup.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.signup.entity.SignupFieldMeetingLink;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月15日 上午10:58:32
 * @version 1.0
 */
public interface SignupFieldMeetingLinkMapper extends
		Mapper<SignupFieldMeetingLink> {

	@Update("UPDATE signup_field_meeting_link SET field_ids = #{fieldIds} WHERE mee_id = #{meeId}")
	int updateField(SignupFieldMeetingLink link);

	@Select("SELECT * FROM signup_field_meeting_link WHERE mee_id = #{meeId}")
	SignupFieldMeetingLink selectSignupFieldMeetingLinkByMeeId(int meeId);
	
	@Update("INSERT INTO signup_field_meeting_link(mee_id ,field_ids ) VALUES ( #{meeId}, #{fieldIds} )")
	int insertInit(SignupFieldMeetingLink link);
}
