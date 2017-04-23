package com.bizideal.whoami.facade.meeting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.meeting.biz.HallLinkUserBiz;
import com.bizideal.whoami.facade.meeting.entity.HallLinkUser;
import com.bizideal.whoami.facade.meeting.service.HallLinkUserFacade;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName HallLinkUserFacadeImpl
 * @Description TODO(会议厅关注的用户)
 * @Author Zj.Qu
 * @Date 2017-01-11 09:50:12
 */
@Component("hallLinkUserFacade")
public class HallLinkUserFacadeImpl implements HallLinkUserFacade {

	@Autowired
	private HallLinkUserBiz hallLinkUserBiz;

	@Override
	public Integer followHall(Integer hallId, String userId) 
			throws CustomException{
		HallLinkUser hallLinkUser = new HallLinkUser();
		hallLinkUser.setHallId(hallId);
		hallLinkUser.setUserId(userId);
		return hallLinkUserBiz.saveSelective(hallLinkUser);
	}
	
	@Override
	public Integer unfollowHall(Integer hallId, String userId) 
			throws CustomException{
		HallLinkUser hallLinkUser = new HallLinkUser();
		hallLinkUser.setHallId(hallId);
		hallLinkUser.setUserId(userId);
		return hallLinkUserBiz.deleteByWhere(hallLinkUser);
	}
	
	@Override
	public int countUserIdByHallId(Integer hallId) 
			throws CustomException{
		return hallLinkUserBiz.countUserIdByHallId(hallId);
	}

	@Override
	public PageInfo<HallLinkUser> ListHallByUserId(Integer page, Integer rows, String userId) 
			throws CustomException{
		return hallLinkUserBiz.ListHallByUserId(page, rows, userId);
	}

	@Override
	public PageInfo<HallLinkUser> ListUserIdByHallId(Integer page, Integer rows, Integer hallId) 
			throws CustomException{
		return hallLinkUserBiz.ListUserIdByHallId(page, rows, hallId);
	}
	
}