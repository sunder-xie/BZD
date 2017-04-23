package com.bizideal.whoami.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.vote.entity.CaseVotingRecord;
import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 案例投票记录
 * 
 * @ClassName CaseVotingRecordMapper
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseVotingRecordMapper extends Mapper<CaseVotingRecord> {

	/**
	 * 插入案例投票记录
	 * 
	 * @param caseVotingRecord
	 * @return
	 */
	int insertCaseVotingRecord(CaseVotingRecord caseVotingRecord);

	/**
	 * 根据会议id删除案例投票记录
	 * 
	 * @param list
	 * @return
	 */
	int deleteByMeetings(@Param("list") List<VoteInfo> list);

	/**
	 * 根据案例id查找案例投票记录
	 * 
	 * @param caseId
	 * @return
	 */
	List<CaseVotingRecord> selectCaseVotingRecord(@Param("caseId") Integer caseId);

}