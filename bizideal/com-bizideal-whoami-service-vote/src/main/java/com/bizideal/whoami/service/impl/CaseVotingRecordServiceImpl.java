package com.bizideal.whoami.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.mapper.CaseVotingRecordMapper;
import com.bizideal.whoami.service.CaseVotingRecordService;
import com.bizideal.whoami.vote.entity.CaseVotingRecord;
import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 
 * @ClassName CaseVotingRecordServiceImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Service("caseVotingRecordService")
public class CaseVotingRecordServiceImpl implements CaseVotingRecordService {

	@Autowired
	private CaseVotingRecordMapper caseVotingRecordMapper;

	public int insert(CaseVotingRecord caseVotingRecord) {
		// 添加,使用存储过程同时将投票统计表总数+1
		return caseVotingRecordMapper.insertCaseVotingRecord(caseVotingRecord);
	}

	public int update(CaseVotingRecord caseVotingRecord) {
		// 修改
		return caseVotingRecordMapper.updateByPrimaryKeySelective(caseVotingRecord);
	}

	public int delete(int id) {
		// 删除
		return caseVotingRecordMapper.deleteByPrimaryKey(id);
	}

	public List<CaseVotingRecord> selectCaseVotingRecord(Integer caseId) {
		// 根据案例id查询全部
		return caseVotingRecordMapper.selectCaseVotingRecord(caseId);
	}

	public void deleteByMeetingId(Integer meetingId) {
		// 根据会议id删除
		CaseVotingRecord record = new CaseVotingRecord();
		record.setMeetingId(meetingId);
		caseVotingRecordMapper.delete(record);
	}

	public int deleteByMeetings(List<VoteInfo> list) {
		// 自动删除
		return caseVotingRecordMapper.deleteByMeetings(list);
	}
}
