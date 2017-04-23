package com.bizideal.whoami.vote.facade;

import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 投票信息Facade
 * 
 * @ClassName VoteInfoFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface VoteInfoFacade {

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
	 * 根据ID删除多个投票信息
	 * 
	 * @param list
	 * @return
	 */
	// public int deleteVoteInfoByIds(List<Integer> list);

	/**
	 * 根据ID查询一个投票信息
	 * 
	 * @param voteInfo
	 * @return
	 */
	VoteInfo selectById(int id);

	/**
	 * 根据条件查询投票信息
	 * 
	 * @param voteInfo
	 * @return
	 */
	// public List<VoteInfo> selectVoteInfoSelective(VoteInfo voteInfo);

	/**
	 * 根据会议ID查询投票信息
	 * 
	 * @param voteInfo
	 * @return
	 */
	VoteInfo selectVoteInfo(Integer meetingId, int type);
}
