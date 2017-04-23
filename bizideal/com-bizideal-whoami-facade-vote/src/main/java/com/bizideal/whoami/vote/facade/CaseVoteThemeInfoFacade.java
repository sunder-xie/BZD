package com.bizideal.whoami.vote.facade;

import java.util.List;

import com.bizideal.whoami.vote.entity.CaseVoteThemeInfo;

/**
 * 案例投票主题Facade
 * 
 * @ClassName CaseVoteThemeInfoFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseVoteThemeInfoFacade {

	/**
	 * 添加案例主题，主键id返回到原对象中，返回值为插入条数
	 * 
	 * @param caseVoteThemeInfo
	 * @return
	 */
	int insert(CaseVoteThemeInfo caseVoteThemeInfo);

	/**
	 * 根据主键id修改案例主题，返回值为修改条数
	 * 
	 * @param caseVoteThemeInfo
	 * @return
	 */
	int update(CaseVoteThemeInfo caseVoteThemeInfo);

	/**
	 * 根据主键id删除案例主题，返回值为删除条数
	 * 
	 * @param id
	 * @return
	 */
	int delete(int id);

	/**
	 * 根据投票id查找案例主题
	 * 
	 * @param voteId
	 * @return
	 */
	List<CaseVoteThemeInfo> selectCaseVoteThemeInfo(Integer voteId, Integer type);

	/**
	 * 根据名称字符串模糊查找案例主题
	 * 
	 * @param voteId
	 * @param name
	 * @return
	 */
	List<CaseVoteThemeInfo> selectFuzzyByName(int voteId, String name);

	/**
	 * 根据主键id查找案例主题
	 * 
	 * @param id
	 * @return
	 */
	CaseVoteThemeInfo selectById(int id);
}
