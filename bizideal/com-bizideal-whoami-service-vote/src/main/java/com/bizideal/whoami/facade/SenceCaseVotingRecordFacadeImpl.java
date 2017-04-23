package com.bizideal.whoami.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.sencevote.entity.SenceCaseVotingRecord;
import com.bizideal.whoami.sencevote.facade.SenceCaseVotingRecordFacade;
import com.bizideal.whoami.service.SenceCaseVotingRecordService;

/**
 * 
 * @ClassName SenceCaseVotingRecordFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
@Component("senceCaseVotingRecordFacade")
public class SenceCaseVotingRecordFacadeImpl implements SenceCaseVotingRecordFacade {

	@Autowired
	private SenceCaseVotingRecordService senceCaseVotingRecordService;

	@Override
	public int insert(SenceCaseVotingRecord senceCaseVotingRecord) {
		return senceCaseVotingRecordService.insert(senceCaseVotingRecord);
	}

	@Override
	public List<SenceCaseVotingRecord> selectBySenceCaseId(Integer caseId) {
		return senceCaseVotingRecordService.selectBySenceCaseId(caseId);
	}

}
