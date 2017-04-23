package com.bizideal.whoami.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.vote.entity.CaseInfo;

/**
 * 案例信息Mapper
 * 
 * @ClassName CaseInfoMapper
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseInfoMapper extends Mapper<CaseInfo> {

	/**
	 * 插入案例信息
	 * 
	 * @param caseInfo
	 * @return
	 */
	int insertCaseInfo(CaseInfo caseInfo);

	/**
	 * 根据主题id和关键字模糊查找案例信息
	 * 
	 * @param caseVoteThemeId
	 * @param str
	 * @return
	 */
	List<CaseInfo> selectFuzzyByString(@Param("caseVoteThemeId") int caseVoteThemeId, @Param("str") String str);

	/**
	 * 根据主题id查找案例信息
	 * 
	 * @param caseVoteThemeId
	 * @return
	 */
	List<CaseInfo> selectCaseInfo(@Param("caseVoteThemeId") int caseVoteThemeId);
}