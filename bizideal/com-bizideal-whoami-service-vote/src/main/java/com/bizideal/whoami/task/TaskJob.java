package com.bizideal.whoami.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.service.CaseVotingRecordService;
import com.bizideal.whoami.service.SenceCaseVotingRecordService;
import com.bizideal.whoami.service.VoteInfoService;
import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 定时触发器
 * 
 * @author sy
 * @date 2017-2-21 10:33:38
 */
@Service
public class TaskJob {
	@Autowired
	private VoteInfoService voteInfoService;
	@Autowired
	private CaseVotingRecordService caseVotingRecordService;
	@Autowired
	private SenceCaseVotingRecordService senceCaseVotingRecordService;

	public void autoDeleteJob() {
		// 自动删除任务,每天0点执行一次
		Date date = new Date();
		long time = date.getTime();

		// 查询过期案例投票并清空记录
		List<VoteInfo> caseList = voteInfoService.selectOverdueVote(time, 0);
		int autoDelete = caseVotingRecordService.deleteByMeetings(caseList);
		int emptyRecord = voteInfoService.updateEmptyFlag(caseList);

		// 查询过期现场案例投票并清空记录
		List<VoteInfo> senceList = voteInfoService.selectOverdueVote(time, 1);
		int deleteByMeetings = senceCaseVotingRecordService.deleteByMeetings(senceList);
		int updateEmptyFlag = voteInfoService.updateEmptyFlag(senceList);
	}
}
