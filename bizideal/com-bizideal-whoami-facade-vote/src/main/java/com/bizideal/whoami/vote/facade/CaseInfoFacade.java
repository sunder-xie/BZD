package com.bizideal.whoami.vote.facade;

import java.util.List;

import com.bizideal.whoami.vote.entity.CaseInfo;

/**
 * 案例信息Facade
 * 
 * @ClassName CaseInfoFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseInfoFacade {

	/**
	 * 添加案例，主键id返回到原对象中，返回值为插入条数
	 * 
	 * @param caseInfo
	 * @return
	 */
	int insert(CaseInfo caseInfo);

	/**
	 * 根据主键id修改案例，返回值为修改条数
	 * 
	 * @param caseInfo
	 * @return
	 */
	int update(CaseInfo caseInfo);

	/**
	 * 根据主键id逻辑删除,返回值为删除条数
	 * 
	 * @param id
	 * @return
	 */
	int delete(int id);

	/**
	 * 根据主题id查找案例
	 * 
	 * @param caseVoteThemeId
	 * @return
	 */
	List<CaseInfo> selectCaseInfo(int caseVoteThemeId);

	/**
	 * 根据字符串（名称/省市/组织）模糊查找案例
	 * 
	 * @param caseVoteThemeId
	 * @param str
	 * @return
	 */
	List<CaseInfo> selectFuzzyByString(int caseVoteThemeId, String str);

	/**
	 * 根据主键id查找案例
	 * 
	 * @param id
	 * @return
	 */
	CaseInfo selectById(int id);
}
