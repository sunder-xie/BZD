package com.bizideal.whoami.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.entity.UserFriend;

public interface HuanxinFriendMapper extends Mapper<UserFriend> {

	@Insert("INSERT INTO user_friend VALUES (#{userId},#{userIdFriend})")
	int insertOne(@Param("userId") String userId,
				  @Param("userIdFriend") String userIdFriend);

	@Select("SELECT uw.headimgurl,uw.sex,uw.nickname,uw.real_name,ui.phone,ui.user_id FROM user_friend u LEFT JOIN user_info ui ON u.user_friend_id = ui.user_id "
			+ " LEFT JOIN user_weixin_info uw ON u.user_friend_id = uw.user_id WHERE u.user_id = #{userId}")
	List<UserFriendDto> selectFriends(String userId);

	@Delete("DELETE FROM user_friend WHERE user_id = #{userId} AND user_friend_id = #{userIdFriend}")
	int deleteOne(@Param("userId") String userId,
				  @Param("userIdFriend") String userIdFriend);

	@Select("SELECT DISTINCT uw.headimgurl,uw.sex,uw.nickname,uw.real_name,ui.phone,ui.user_id,'N' AS isFriend FROM user_info ui LEFT JOIN user_weixin_info uw ON "
			+ " ui.user_id = uw.user_id WHERE ui.phone LIKE CONCAT('%',#{info},'%') OR uw.real_name LIKE CONCAT('%',#{info},'%') AND ui.user_id != #{userId}")
	List<UserFriendDto> searchFriend(@Param("userId") String userId,
									 @Param("info") String info);
}
