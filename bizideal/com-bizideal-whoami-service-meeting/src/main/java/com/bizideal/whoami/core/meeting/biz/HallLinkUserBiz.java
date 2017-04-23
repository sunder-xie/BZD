package com.bizideal.whoami.core.meeting.biz;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.facade.meeting.entity.HallLinkUser;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName HallLinkUserBiz
 * @Description TODO(会议厅关注的用户)
 * @Author Zj.Qu
 * @Date 2017-01-10 16:37:19
 */
public interface HallLinkUserBiz extends BaseBiz<HallLinkUser> {
	
	/**
	 * 统计某个会议厅的关注人数
	 * @param hallId 会议厅id
	 * @return 关注数
	 */
	public int countUserIdByHallId(Integer hallId) 
			throws CustomException;

	/**
	 * 会议厅的粉丝信息
	 * @param hallId
	 * @return
	 */
	PageInfo<HallLinkUser> ListUserIdByHallId(Integer page, Integer rows, Integer hallId)
			throws CustomException;
	
	/**
	 * 我关注的会议厅
	 * @param page
	 * @param rows
	 * @param userId
	 * @return
	 */
	PageInfo<HallLinkUser> ListHallByUserId(Integer page, Integer rows, String userId)
			throws CustomException;
	
}