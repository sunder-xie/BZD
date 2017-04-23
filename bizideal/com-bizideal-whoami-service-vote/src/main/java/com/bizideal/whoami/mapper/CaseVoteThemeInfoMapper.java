package com.bizideal.whoami.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.vote.entity.CaseVoteThemeInfo;

/**
 * 案例投票主题信息Mapper
 * 
 * @ClassName CaseVoteThemeInfoMapper
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseVoteThemeInfoMapper extends Mapper<CaseVoteThemeInfo> {

	/**
	 * 插入
	 * 
	 * @param caseVoteThemeInfo
	 * @return
	 */
	int insertCaseVoteThemeInfo(CaseVoteThemeInfo caseVoteThemeInfo);

	/**
	 * 根据投票id和关键字模糊查找案例投票主题信息
	 * 
	 * @param voteId
	 * @param name
	 * @return
	 */
	List<CaseVoteThemeInfo> selectFuzzyByName(@Param("voteId") int voteId, @Param("name") String name);
}