package com.bizideal.whoami.leaflets.service;

import java.util.List;

import com.bizideal.whoami.facade.leaflets.entity.MeetingLeaflets;
import com.github.pagehelper.PageInfo;

public interface MeetingLeafletsBiz {

	/**
	 * 根据会议id查询正在使用的会议宣传页信息
	 * @param meeId
	 * @return
	 */
	MeetingLeaflets selectByMeeIdInUse(Integer meeId);
	
	/**
	 * 根据会议id查询会议所有的宣传页信息
	 * @param meeId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<MeetingLeaflets> selectByMeeId(Integer meeId, int pageNum, int pageSize);
	
	/**
	 * 根据id查询会议宣传页信息
	 * @param id
	 * @return
	 */
	MeetingLeaflets selectById(Integer id);
	
	/**
	 * 插入一条会议宣传页信息
	 * @param meetingLeaflets
	 * @return
	 */
	int insertMeetingLeaflets(MeetingLeaflets meetingLeaflets);
	
	/**
	 * 更新一条会议宣传页信息
	 * @param meetingLeaflets
	 * @return
	 */
	int updateMeetingLeaflets(MeetingLeaflets meetingLeaflets);
	
	/**
	 * 根据id删除一条会议宣传页信息
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
	
	/**
	 * 根据id删除多条宣传页信息
	 * @param ids
	 * @return
	 */
	int deleteByIds(List<Integer> ids);
	
}
