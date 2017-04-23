package com.bizideal.whoami.service;

import com.bizideal.whoami.sencevote.entity.SenceCaseCount;

/**
 * 现场案例投票总数Service
 * 
 * @author sy
 *
 */
public interface SenceCaseCountService {

	/**
	 * 插入现场案例投票总数
	 * 
	 * @param senceCaseCount
	 * @return
	 */
	int insert(SenceCaseCount senceCaseCount);

	/**
	 * 根据案例id查询票数总数
	 * 
	 * @param senceCaseId
	 * @return
	 */
	SenceCaseCount selectSenceCaseCount(int senceCaseId);
}
