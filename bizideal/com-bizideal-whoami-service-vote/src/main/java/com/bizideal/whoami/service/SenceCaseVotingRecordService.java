package com.bizideal.whoami.service;

import java.util.List;

import com.bizideal.whoami.sencevote.entity.SenceCaseVotingRecord;
import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 现场案例投票记录Service
 * 
 * @author sy
 * @date 2017-2-21 10:38:02
 */
public interface SenceCaseVotingRecordService {

	/**
	 * 添加,使用存储过程同时将投票统计表总数+1
	 * 
	 * @param senceCaseVotingRecord
	 * @return 1代表成功，其他代表失败 0 常见于count表中的对应记录不存在 -1 同一个用户规定天数内对同一案例投票次数超标 -2
	 *         同一个用户规定天数内对同一会议投票次数超标 -3 同一个用户对同一案例投票次数超标 -4 同一个用户对同一会议投票次数超标
	 */
	int insert(SenceCaseVotingRecord senceCaseVotingRecord);

	/**
	 * 更新现场案例投票记录
	 * 
	 * @param senceCaseVotingRecord
	 * @return
	 */
	int update(SenceCaseVotingRecord senceCaseVotingRecord);

	/**
	 * 根据现场案例投票记录id删除现场案例投票记录
	 * 
	 * @param id
	 * @return
	 */
	int delete(int id);

	/**
	 * 根据案例id查询全部
	 * 
	 * @param caseId
	 * @return
	 */
	List<SenceCaseVotingRecord> selectBySenceCaseId(Integer caseId);

	/**
	 * 根据会议id删除现场案例投票记录
	 * 
	 * @param meetingId
	 */
	void deleteByMeetingId(Integer meetingId);

	/**
	 * 根据投票信息删除现场案例投票记录
	 * 
	 * @param list
	 * @return
	 */
	int deleteByMeetings(List<VoteInfo> list);

	/**
	 * 根据参数的属性（除时间）查找现场案例投票记录总数
	 * 
	 * @param record
	 * @return
	 */
	int selectCount(SenceCaseVotingRecord record);

	/**
	 * 根据时间规则、案例规则、用户规则查询现场案例投票记录总数
	 * 
	 * @param record
	 * @param days
	 * @return
	 */
	int selectCountByTimeAndCaseAndUser(SenceCaseVotingRecord record, int days);

	/**
	 * 根据时间规则、会议规则、用户规则查询现场案例投票记录总数
	 * 
	 * @param record
	 * @param days
	 * @return
	 */
	int selectCountByTimeAndMeetingAndUser(SenceCaseVotingRecord record, int days);
}
