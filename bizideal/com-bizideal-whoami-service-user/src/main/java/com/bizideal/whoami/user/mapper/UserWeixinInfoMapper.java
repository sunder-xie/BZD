package com.bizideal.whoami.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.dto.SignUpInfoEditDto;
import com.bizideal.whoami.user.dto.SignUpMeetingDto;
import com.bizideal.whoami.user.entity.UserWeixinInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午3:10:55
 * @version 1.0
 */
public interface UserWeixinInfoMapper extends Mapper<UserWeixinInfo> {

	@Select("SELECT * FROM user_weixin_info uw LEFT JOIN user_info ui ON uw.user_id=ui.user_id WHERE uw.unionid = #{unionid}")
	UserWeixinInfo selectByUnionid(String unionid);

	@Select("SELECT phone FROM user_info WHERE phone = #{phone}")
	String selectPhone(String phone);

	int updateSignUpInfo(UserWeixinInfo weixinInfo);

	@Update("UPDATE user_weixin_info SET real_name= #{realName},weixin=#{weixin} WHERE user_id= #{userId}")
	int updateCreateHallStep2(UserWeixinInfo userWeixinInfo);

	@Select("SELECT * FROM user_weixin_info uw LEFT JOIN user_info ui ON uw.user_id=ui.user_id WHERE uw.user_id = #{userId}")
	UserWeixinInfo selectByUserId(String userId);

	List<UserWeixinInfo> selectByUserIds(List<String> userIds);

	int updateImgAndName(UserWeixinInfo userWeixinInfo);

	List<UserWeixinInfo> selectBySignUp(SignUpMeetingDto signUpMeetingDto);
	
	@Update("UPDATE user_info SET phone = #{phone} WHERE user_id = #{userId}")
	int updatePhone(SignUpInfoEditDto signUpInfoEditDto);
	
	@Update("UPDATE user_weixin_info SET sex = #{sex},real_name = #{realName} WHERE user_id = #{userId}")
	int updateSignUp(SignUpInfoEditDto signUpInfoEditDto);

}
