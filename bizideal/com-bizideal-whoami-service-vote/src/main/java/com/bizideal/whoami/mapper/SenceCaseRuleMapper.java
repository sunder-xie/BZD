package com.bizideal.whoami.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.sencevote.entity.SenceCaseRule;

/**
 * 现场案例投票规则
 * 
 * @ClassName SenceCaseRuleMapper
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月9日
 */
public interface SenceCaseRuleMapper extends Mapper<SenceCaseRule> {

	/**
	 * 根据会议id查找现场案例投票规则
	 * 
	 * @param meeId
	 * @return
	 */
	List<SenceCaseRule> selectByMeeId(Integer meeId);
}