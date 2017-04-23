package com.bizideal.whoami.service;

import java.util.List;

import com.bizideal.whoami.vote.entity.CaseVotingRecord;
import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 案例投票记录Service
 * 
 * @ClassName CaseVotingRecordService
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseVotingRecordService {

	/**
	 * 添加案例投票记录，自动增加案例投票总数，返回主键id到原对象，返回值为新增条数
	 * 
	 * @param caseVotingRecord
	 * @return
	 */
	int insert(CaseVotingRecord caseVotingRecord);

	/**
	 * 更新案例投票记录
	 * 
	 * @param caseVotingRecord
	 * @return
	 */
	int update(CaseVotingRecord caseVotingRecord);

	/**
	 * 删除案例投票记录
	 * 
	 * @param id
	 * @return
	 */
	int delete(int id);

	/**
	 * 根据案例id查询案例投票记录
	 * 
	 * @param caseId
	 * @return
	 */
	List<CaseVotingRecord> selectCaseVotingRecord(Integer caseId);

	/**
	 * 根据会议id删除 案例投票记录
	 * 
	 * @param list
	 * @return
	 */
	int deleteByMeetings(List<VoteInfo> list);
}