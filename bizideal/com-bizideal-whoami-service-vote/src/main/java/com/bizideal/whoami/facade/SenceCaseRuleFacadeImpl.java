package com.bizideal.whoami.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.sencevote.entity.SenceCaseRule;
import com.bizideal.whoami.sencevote.facade.SenceCaseRuleFacade;
import com.bizideal.whoami.service.SenceCaseRuleService;

/**
 * 
 * @ClassName SenceCaseRuleFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
@Component("senceCaseRuleFacade")
public class SenceCaseRuleFacadeImpl implements SenceCaseRuleFacade{

	@Autowired
	SenceCaseRuleService senceCaseRuleService;
	
	@Override
	public int insert(SenceCaseRule senceCaseRule) {
		return senceCaseRuleService.insert(senceCaseRule);
	}

	@Override
	public int update(SenceCaseRule senceCaseRule) {
		return senceCaseRuleService.update(senceCaseRule);
	}

	@Override
	public int delete(SenceCaseRule senceCaseRule) {
		return senceCaseRuleService.delete(senceCaseRule);
	}

	@Override
	public SenceCaseRule selectById(Integer id) {
		return senceCaseRuleService.selectById(id);
	}

	@Override
	public SenceCaseRule selectByMeeId(Integer meeId) {
		return senceCaseRuleService.selectByMeeId(meeId);
	}

}
