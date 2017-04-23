package com.bizideal.whoami.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bizideal.whoami.user.entity.UserInfo;
import com.bizideal.whoami.user.entity.UserWeixinInfo;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午2:40:45
 * @version 1.0
 */
public interface UserInfoMapper extends Mapper<UserInfo> {

	@Update("UPDATE user_info SET phone = #{phone} WHERE user_id = #{userId}")
	void updatePhone(@Param("userId") String userId,
					 @Param("phone") String phone);

	@Select("SELECT * FROM user_info ui LEFT JOIN user_weixin_info uw ON ui.user_id = uw.user_id WHERE ui.phone = #{phone} AND ui.`password` = #{password}")
	List<UserWeixinInfo> login(UserInfo userInfo);

	@Update("UPDATE user_weixin_info SET user_id = #{userId} WHERE unionid = #{unionid}")
	int updateBindPhone(UserWeixinInfo userWeixinInfo);

	@Update("UPDATE user_weixin_info SET unionid = #{unionid} ,openid = #{openid} ,subscribe = #{subscribe} ,nickname = #{nickname} ,"
			+ "sex = #{sex} ,headimgurl = #{headimgurl} ,country = #{country} ,province = #{province} ,city = #{city} ,"
			+ "language = #{language} ,remark = #{remark} ,groupid = #{groupid} ,weixin = #{weixin} WHERE user_id = #{userId}")
	int updateMergeWeixin(UserWeixinInfo weixinInfo);

	@Delete("DELETE FROM user_weixin_info WHERE unionid = #{unionid} AND user_id IS NULL")
	int deleteMergeWeixin(String unionid);

	@Update("UPDATE user_info SET password = #{password} WHERE user_id = #{userId} ")
	int updatePassword(@Param("userId") String userId,
					   @Param("password") String password);

	@Select("SELECT * FROM user_info ui LEFT JOIN user_weixin_info uw ON ui.user_id = uw.user_id WHERE ui.phone = #{phone}")
	UserWeixinInfo selectByPhone(String phone);

	@Update("UPDATE user_weixin_info SET real_name = #{realName} WHERE user_id = #{userId}")
	int updateRealname(@Param("userId") String userId,
					   @Param("realName") String realName);
	
	@Update("UPDATE user_weixin_info SET nickname = #{nickname} WHERE user_id = #{userId}")
	int updateNickname(@Param("userId") String userId,
					   @Param("nickname") String nickname);

	@Update("UPDATE user_weixin_info SET headimgurl = #{headimgurl} WHERE user_id = #{userId}")
	int updateHeadimgurl(@Param("userId") String userId,
						 @Param("headimgurl") String headimgurl);

}
