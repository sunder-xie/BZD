package com.bizideal.whoami.core.meeting.mapper;

import java.util.List;

import com.bizideal.whoami.facade.meeting.entity.HallLinkUser;

import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName HallLinkUserMapper
 * @Description TODO(会议厅关注的用户)
 * @Author Zj.Qu
 * @Date 2017-01-10 16:12:01
 */
public interface HallLinkUserMapper extends Mapper<HallLinkUser> {

	/**
	 * 统计某个会议厅的关注人数
	 * @param hallId 会议厅id
	 * @return 关注数
	 */
	int countUserIdByHallId(Integer hallId);
	
	/**
	 * 会议厅的粉丝信息
	 * @param hallId
	 * @return
	 */
	List<HallLinkUser> ListUserIdByHallId(Integer hallId);
	
	/**
	 * 我关注的会议厅
	 * @param userId
	 * @return
	 */
	List<HallLinkUser> ListHallByUserId(String userId);
	
}