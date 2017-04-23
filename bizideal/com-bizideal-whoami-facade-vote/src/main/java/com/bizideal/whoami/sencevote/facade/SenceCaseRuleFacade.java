package com.bizideal.whoami.sencevote.facade;

import com.bizideal.whoami.sencevote.entity.SenceCaseRule;

/**
 * 现场案例投票规则Facade
 * 
 * @ClassName SenceCaseRuleFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
public interface SenceCaseRuleFacade {

	/**
	 * 插入一条现场投票的规则信息
	 * 
	 * @param senceCaseRule
	 * @return
	 */
	int insert(SenceCaseRule senceCaseRule);

	/**
	 * 更新一条现场投票的规则信息
	 * 
	 * @param senceCaseRule
	 * @return
	 */
	int update(SenceCaseRule senceCaseRule);

	/**
	 * 删除一条现场投票的规则信息
	 * 
	 * @param senceCaseRule
	 * @return
	 */
	int delete(SenceCaseRule senceCaseRule);

	/**
	 * 根据ID查询一条现场投票的规则信息
	 * 
	 * @param id
	 * @return
	 */
	SenceCaseRule selectById(Integer id);

	/**
	 * 根据会议ID查询现场投票的规则信息（一个会议只能有一条现场投票的信息，一条投票信息只能有一个投票规则）
	 * 
	 * @param meeId
	 * @return
	 */
	SenceCaseRule selectByMeeId(Integer meeId);

}
