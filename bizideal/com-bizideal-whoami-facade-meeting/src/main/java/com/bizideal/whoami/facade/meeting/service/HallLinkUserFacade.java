package com.bizideal.whoami.facade.meeting.service;

import com.bizideal.whoami.facade.meeting.entity.HallLinkUser;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName HallLinkUserFacade
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2017-01-11 09:51:24
 */
public interface HallLinkUserFacade {

	/**
	 * 关注会议厅
	 * 
	 * @param t
	 * @return
	 */
	Integer followHall(Integer hallId, String userId)
			throws CustomException;

	/**
	 * 取消关注的会议厅
	 * 
	 * @param t
	 * @return
	 */
	Integer unfollowHall(Integer hallId, String userId)
			throws CustomException;

	/**
	 * 某个会议厅关注的人数
	 * 
	 * @param hallId
	 * @return
	 */
	int countUserIdByHallId(Integer hallId)
			throws CustomException;

	/**
	 * 会议厅的粉丝信息
	 * 
	 * @param hallId
	 * @return
	 */
	PageInfo<HallLinkUser> ListUserIdByHallId(Integer page, Integer rows, Integer hallId)
			throws CustomException;

	/**
	 * 我关注的会议厅
	 * 
	 * @param page
	 * @param rows
	 * @param userId
	 * @return
	 */
	PageInfo<HallLinkUser> ListHallByUserId(Integer page, Integer rows, String userId)
			throws CustomException;
}
