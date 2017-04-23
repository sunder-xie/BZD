package com.bizideal.whoami.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.mapper.SenceCaseRuleMapper;
import com.bizideal.whoami.sencevote.entity.SenceCaseRule;
import com.bizideal.whoami.service.SenceCaseRuleService;

/**
 * 
 * @ClassName SenceCaseRuleServiceImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
@Service("senceCaseRuleService")
public class SenceCaseRuleServiceImpl implements SenceCaseRuleService{

	@Autowired
	private SenceCaseRuleMapper senceCaseRuleMapper;
	@Override
	public int insert(SenceCaseRule senceCaseRule) {
		return senceCaseRuleMapper.insertSelective(senceCaseRule);
	}

	@Override
	public int update(SenceCaseRule senceCaseRule) {
		return senceCaseRuleMapper.updateByPrimaryKeySelective(senceCaseRule);
	}

	@Override
	public int delete(SenceCaseRule senceCaseRule) {
		return senceCaseRuleMapper.deleteByPrimaryKey(senceCaseRule.getId());
	}

	@Override
	public SenceCaseRule selectById(Integer id) {
		return senceCaseRuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public SenceCaseRule selectByMeeId(Integer meeId) {
		List<SenceCaseRule> list=senceCaseRuleMapper.selectByMeeId(meeId);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
