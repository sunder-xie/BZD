package com.bizideal.whoami.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 投票信息
 * 
 * @ClassName VoteInfoMapper
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface VoteInfoMapper extends Mapper<VoteInfo> {

	/**
	 * 插入投票信息
	 * 
	 * @param voteInfo
	 * @return
	 */
	int insertVoteInfo(VoteInfo voteInfo);

	/**
	 * 根据时间和类型查询过期投票信息
	 * 
	 * @param time
	 * @param type
	 * @return
	 */
	List<VoteInfo> selectOverdueVote(@Param("time") long time, @Param("type") int type);

	/**
	 * 更新清空标记
	 * 
	 * @param list
	 * @return
	 */
	int updateEmptyFlag(@Param("list") List<VoteInfo> list);

}