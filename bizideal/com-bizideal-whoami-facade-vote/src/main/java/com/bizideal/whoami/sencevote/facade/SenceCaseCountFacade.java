package com.bizideal.whoami.sencevote.facade;

import com.bizideal.whoami.sencevote.entity.SenceCaseCount;

/**
 * 现场投票案例票数总数Facade
 * 
 * @ClassName SenceCaseCountFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
public interface SenceCaseCountFacade {

	/**
	 * 根据案例id查询票数总数
	 * 
	 * @param senceCaseId
	 * @return
	 */
	SenceCaseCount selectSenceCaseCount(int senceCaseId);
}
