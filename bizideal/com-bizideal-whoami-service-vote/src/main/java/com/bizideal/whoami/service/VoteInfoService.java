package com.bizideal.whoami.service;

import java.util.List;

import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 投票信息Service
 * 
 * @ClassName VoteInfoService
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface VoteInfoService {

	/**
	 * 添加一个投票信息,返回主键id到原对象，返回值为添加条数
	 * 
	 * @param voteInfo
	 * @return
	 */
	int insert(VoteInfo voteInfo);

	/**
	 * 更新一个投票信息，返回值为修改条数
	 * 
	 * @param voteInfo
	 * @return
	 */
	int update(VoteInfo voteInfo);

	/**
	 * 删除一个投票信息（逻辑删除），返回值为删除条数
	 * 
	 * @param voteInfo
	 * @return
	 */
	int delete(int id);

	/**
	 * 根据会议ID查询投票信息
	 * 
	 * @param voteInfo
	 * @return
	 */
	VoteInfo selectVoteInfo(Integer meetingId, int type);

	/**
	 * 根据时间和类型查询过期投票信息
	 * 
	 * @param time
	 * @param type
	 * @return
	 */
	List<VoteInfo> selectOverdueVote(long time, int type);

	/**
	 * 根据ID查询一个投票信息
	 * 
	 * @param voteInfo
	 * @return
	 */
	VoteInfo selectById(int id);

	/**
	 * 更新投票信息的清空标记
	 * 
	 * @param list
	 * @return
	 */
	int updateEmptyFlag(List<VoteInfo> list);
}
