package com.bizideal.whoami.mapper;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.vote.entity.CaseCount;

/**
 * 案例投票总数Mapper
 * 
 * @ClassName CaseCountMapper
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public interface CaseCountMapper extends Mapper<CaseCount> {

	/**
	 * 插入案例投票总数
	 * 
	 * @param caseCount
	 * @return
	 */
	int insertCaseCount(CaseCount caseCount);

}