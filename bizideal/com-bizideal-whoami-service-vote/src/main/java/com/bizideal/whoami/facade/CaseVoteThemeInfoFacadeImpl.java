package com.bizideal.whoami.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.service.CaseVoteThemeInfoService;
import com.bizideal.whoami.vote.entity.CaseVoteThemeInfo;
import com.bizideal.whoami.vote.facade.CaseVoteThemeInfoFacade;

/**
 * 
 * @ClassName CaseVoteThemeInfoFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Component("caseVoteThemeInfoFacade")
public class CaseVoteThemeInfoFacadeImpl implements CaseVoteThemeInfoFacade {

	@Autowired
	private CaseVoteThemeInfoService caseVoteThemeInfoService;

	@Override
	public int insert(CaseVoteThemeInfo caseVoteThemeInfo) {
		return caseVoteThemeInfoService.insert(caseVoteThemeInfo);
	}

	@Override
	public int update(CaseVoteThemeInfo caseVoteThemeInfo) {
		return caseVoteThemeInfoService.update(caseVoteThemeInfo);
	}

	@Override
	public int delete(int id) {
		return caseVoteThemeInfoService.delete(id);
	}

	@Override
	public List<CaseVoteThemeInfo> selectCaseVoteThemeInfo(Integer voteId, Integer type) {
		return caseVoteThemeInfoService.selectCaseVoteThemeInfo(voteId, type);
	}

	@Override
	public List<CaseVoteThemeInfo> selectFuzzyByName(int voteId, String name) {
		return caseVoteThemeInfoService.selectFuzzyByName(voteId, name);
	}

	@Override
	public CaseVoteThemeInfo selectById(int id) {
		return caseVoteThemeInfoService.selectById(id);
	}

}
