package com.bizideal.whoami.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.sencevote.entity.SenceCaseVotingRecord;
import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 现场案例投票记录
 * 
 * @ClassName SenceCaseVotingRecordMapper
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月9日
 */
public interface SenceCaseVotingRecordMapper extends Mapper<SenceCaseVotingRecord> {

	/**
	 * 插入现场案例投票记录
	 * 
	 * @param senceCaseVotingRecord
	 * @return
	 */
	int insertSenceCaseVotingRecord(SenceCaseVotingRecord senceCaseVotingRecord);

	/**
	 * 根据会议id删除现场案例投票记录
	 * 
	 * @param list
	 * @return
	 */
	int deleteByMeetings(@Param("list") List<VoteInfo> list);

	/**
	 * 根据现场案例id查询现场案例投票记录
	 * 
	 * @param sence_case_id
	 * @return
	 */
	List<SenceCaseVotingRecord> selectBySenceCaseId(@Param("senceCaseId") Integer senceCaseId);
}