package com.bizideal.whoami.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.mapper.SenceCaseRuleMapper;
import com.bizideal.whoami.mapper.SenceCaseVotingRecordMapper;
import com.bizideal.whoami.sencevote.entity.SenceCaseRule;
import com.bizideal.whoami.sencevote.entity.SenceCaseVotingRecord;
import com.bizideal.whoami.service.SenceCaseVotingRecordService;
import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 
 * @ClassName SenceCaseVotingRecordServiceImpl
 * @Description TODO(detail)
 * @Author sy
 * @Date 2017年2月9日
 */
@Service("senceCaseVotingRecordService")
public class SenceCaseVotingRecordServiceImpl implements SenceCaseVotingRecordService {

	@Autowired
	private SenceCaseVotingRecordMapper senceCaseVotingRecordMapper;

	@Autowired
	private SenceCaseRuleMapper senceCaseRuleMapper;

	public int insert(SenceCaseVotingRecord senceCaseVotingRecord) {
		// 添加,使用存储过程同时将投票统计表总数+1
		SenceCaseRule scr = senceCaseRuleMapper.selectByMeeId(senceCaseVotingRecord.getMeetingId()).get(0);
		SenceCaseVotingRecord record = new SenceCaseVotingRecord();

		if (null != scr.getTime() && scr.getTime() > 0) {
			record.setSenceCaseId(senceCaseVotingRecord.getSenceCaseId());
			record.setUserId(senceCaseVotingRecord.getUserId());
			if (scr.getUser() > 0 && scr.getUser() <= selectCountByTimeAndCaseAndUser(record, scr.getTime())) {
				// 同一个用户规定天数内对同一案例投票次数超标
				return -1;
			}
			record.setSenceCaseId(null);
			record.setMeetingId(senceCaseVotingRecord.getMeetingId());
			if (scr.getCount() > 0 && scr.getCount() <= selectCountByTimeAndMeetingAndUser(record, scr.getTime())) {
				// 同一个用户规定天数内对同一会议投票次数超标
				return -2;
			}
			return senceCaseVotingRecordMapper.insertSenceCaseVotingRecord(senceCaseVotingRecord);
		} else {
			record.setSenceCaseId(senceCaseVotingRecord.getSenceCaseId());
			record.setUserId(senceCaseVotingRecord.getUserId());
			if (scr.getUser() > 0 && scr.getUser() < selectCount(record)) {
				// 同一个用户对同一案例投票次数超标
				return -3;
			}
			record.setSenceCaseId(null);
			record.setMeetingId(senceCaseVotingRecord.getMeetingId());
			if (scr.getCount() > 0 && scr.getCount() < selectCount(record)) {
				// 同一个用户对同一会议投票次数超标
				return -4;
			}
			return senceCaseVotingRecordMapper.insertSenceCaseVotingRecord(senceCaseVotingRecord);
		}
	}

	public int update(SenceCaseVotingRecord senceCaseVotingRecord) {
		// 修改
		return senceCaseVotingRecordMapper.updateByPrimaryKeySelective(senceCaseVotingRecord);
	}

	public int delete(int id) {
		// 删除
		return senceCaseVotingRecordMapper.deleteByPrimaryKey(id);
	}

	public List<SenceCaseVotingRecord> selectBySenceCaseId(Integer caseId) {
		// 根据案例id查询全部
		return senceCaseVotingRecordMapper.selectBySenceCaseId(caseId);
	}

	public void deleteByMeetingId(Integer meetingId) {
		// 根据会议id删除
		SenceCaseVotingRecord record = new SenceCaseVotingRecord();
		record.setMeetingId(meetingId);
		senceCaseVotingRecordMapper.delete(record);
	}

	public int deleteByMeetings(List<VoteInfo> list) {
		// 自动删除
		return senceCaseVotingRecordMapper.deleteByMeetings(list);
	}

	public int selectCount(SenceCaseVotingRecord record) {

		return senceCaseVotingRecordMapper.selectCount(record);
	}

	public int selectCountByTimeAndCaseAndUser(SenceCaseVotingRecord record, int days) {

		Date date = new Date();
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		long today = date.getTime();
		Example example = new Example(SenceCaseVotingRecord.class);
		example.createCriteria().andCondition("sence_case_id=", record.getSenceCaseId())
				.andCondition("user_id=", record.getUserId())
				.andBetween("time", today - (24 * 60 * 60 * 1000l) * days, today);
		return (senceCaseVotingRecordMapper.selectByExample(example)).size();
	}

	public int selectCountByTimeAndMeetingAndUser(SenceCaseVotingRecord record, int days) {

		Date date = new Date();
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		long today = date.getTime();
		Example example = new Example(SenceCaseVotingRecord.class);
		example.createCriteria().andCondition("meeting_id=", record.getMeetingId())
				.andCondition("user_id=", record.getUserId())
				.andBetween("time", today - (24 * 60 * 60 * 1000l) * days, today);
		return (senceCaseVotingRecordMapper.selectByExample(example)).size();
	}
}
