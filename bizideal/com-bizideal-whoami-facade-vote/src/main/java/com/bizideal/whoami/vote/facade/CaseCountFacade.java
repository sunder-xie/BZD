package com.bizideal.whoami.vote.facade;

import com.bizideal.whoami.vote.entity.CaseCount;

/**
 * 案例总数Facade
 * 
 * @ClassName CaseCountFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseCountFacade {

	/**
	 * 根据案例id查询案例投票总数
	 * 
	 * @param caseId
	 * @return
	 */
	CaseCount selectCaseCount(int caseId);
}
