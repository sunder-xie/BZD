package com.bizideal.whoami.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.service.CaseVotingRecordService;
import com.bizideal.whoami.vote.entity.CaseVotingRecord;
import com.bizideal.whoami.vote.facade.CaseVotingRecordFacade;

/**
 * 
 * @ClassName CaseVotingRecordFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Component("caseVotingRecordFacade")
public class CaseVotingRecordFacadeImpl implements CaseVotingRecordFacade {

	@Autowired
	private CaseVotingRecordService caseVotingRecordService;

	@Override
	public int insert(CaseVotingRecord caseVotingRecord) {
		return caseVotingRecordService.insert(caseVotingRecord);
	}

	@Override
	public List<CaseVotingRecord> selectCaseVotingRecord(Integer caseId) {
		return caseVotingRecordService.selectCaseVotingRecord(caseId);
	}

}
