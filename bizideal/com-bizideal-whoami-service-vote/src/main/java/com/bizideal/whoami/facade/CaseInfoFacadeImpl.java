package com.bizideal.whoami.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.service.CaseInfoService;
import com.bizideal.whoami.vote.entity.CaseInfo;
import com.bizideal.whoami.vote.facade.CaseInfoFacade;

/**
 * 
 * @ClassName CaseInfoFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Component("caseInfoFacade")
public class CaseInfoFacadeImpl implements CaseInfoFacade {

	@Autowired
	private CaseInfoService caseInfoService;

	@Override
	public int insert(CaseInfo caseInfo) {
		return caseInfoService.insert(caseInfo);
	}

	@Override
	public int update(CaseInfo caseInfo) {
		return caseInfoService.update(caseInfo);
	}

	@Override
	public int delete(int id) {
		return caseInfoService.delete(id);
	}

	@Override
	public List<CaseInfo> selectCaseInfo(int caseVoteThemeId) {
		return caseInfoService.selectCaseInfo(caseVoteThemeId);
	}

	@Override
	public List<CaseInfo> selectFuzzyByString(int caseVoteThemeId, String str) {
		return caseInfoService.selectFuzzyByString(caseVoteThemeId, str);
	}

	@Override
	public CaseInfo selectById(int id) {
		return caseInfoService.selectById(id);
	}

}
