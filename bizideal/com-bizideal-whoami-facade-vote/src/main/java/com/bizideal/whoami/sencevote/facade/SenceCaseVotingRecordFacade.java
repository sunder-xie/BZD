package com.bizideal.whoami.sencevote.facade;

import java.util.List;

import com.bizideal.whoami.sencevote.entity.SenceCaseVotingRecord;

/**
 * 现场案例投票记录Facade
 * @ClassName SenceCaseVotingRecordFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
public interface SenceCaseVotingRecordFacade {

	/**
	 * 添加,使用存储过程同时将投票统计表总数+1
	 * @param senceCaseVotingRecord
	 * @return 	1代表成功，其他代表失败
	 * 			0 常见于count表中的对应记录不存在
	 * 			-1 同一个用户规定天数内对同一案例投票次数超标
	 * 			-2 同一个用户规定天数内对同一会议投票次数超标
	 * 			-3 同一个用户对同一案例投票次数超标
	 * 			-4 同一个用户对同一会议投票次数超标
	 */
	int insert(SenceCaseVotingRecord senceCaseVotingRecord);

	/**
	 * 根据案例id查询全部
	 * @param caseId
	 * @return
	 */
	List<SenceCaseVotingRecord> selectBySenceCaseId(Integer caseId);

}
