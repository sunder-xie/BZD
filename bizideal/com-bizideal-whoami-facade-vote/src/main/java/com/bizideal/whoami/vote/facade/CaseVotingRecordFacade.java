package com.bizideal.whoami.vote.facade;

import java.util.List;

import com.bizideal.whoami.vote.entity.CaseVotingRecord;

/**
 * 案例投票记录Facade
 * 
 * @ClassName CaseVotingRecordFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseVotingRecordFacade {

	/**
	 * 添加案例投票记录，自动增加案例投票总数，返回主键id到原对象，返回值为新增条数
	 * 
	 * @param caseVotingRecord
	 * @return
	 */
	int insert(CaseVotingRecord caseVotingRecord);

	/**
	 * 根据案例id查询案例投票记录
	 * 
	 * @param caseId
	 * @return
	 */
	List<CaseVotingRecord> selectCaseVotingRecord(Integer caseId);

}
