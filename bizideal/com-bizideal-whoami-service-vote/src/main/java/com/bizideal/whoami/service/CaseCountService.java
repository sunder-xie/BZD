package com.bizideal.whoami.service;

import com.bizideal.whoami.vote.entity.CaseCount;

/**
 * 案例投票总树Service
 * 
 * @ClassName CaseCountService
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseCountService {

	/**
	 * 插入案例投票总数
	 * 
	 * @param caseCount
	 * @return
	 */
	int insert(CaseCount caseCount);

	/**
	 * 更新案例投票总数
	 * 
	 * @param caseCount
	 * @return
	 */
	int update(CaseCount caseCount);

	/**
	 * 删除案例投票总数
	 * 
	 * @param id
	 * @return
	 */
	int delete(int id);

	/**
	 * 根据案例id查询案例投票总数
	 * 
	 * @param caseId
	 * @return
	 */
	CaseCount selectCaseCount(int caseId);
}
