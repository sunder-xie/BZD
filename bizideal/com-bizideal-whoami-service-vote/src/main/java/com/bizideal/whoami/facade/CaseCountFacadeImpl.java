package com.bizideal.whoami.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.service.CaseCountService;
import com.bizideal.whoami.vote.entity.CaseCount;
import com.bizideal.whoami.vote.facade.CaseCountFacade;

/**
 * 
 * @ClassName CaseCountFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Component("caseCountFacade")
public class CaseCountFacadeImpl implements CaseCountFacade {

	@Autowired
	private CaseCountService caseCountService;

	@Override
	public CaseCount selectCaseCount(int caseId) {
		return caseCountService.selectCaseCount(caseId);
	}

}
