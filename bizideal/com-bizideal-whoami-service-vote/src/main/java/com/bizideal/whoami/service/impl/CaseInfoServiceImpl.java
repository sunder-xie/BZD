package com.bizideal.whoami.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.mapper.CaseInfoMapper;
import com.bizideal.whoami.service.CaseCountService;
import com.bizideal.whoami.service.CaseInfoService;
import com.bizideal.whoami.vote.entity.CaseCount;
import com.bizideal.whoami.vote.entity.CaseInfo;

/**
 * 
 * @ClassName CaseInfoService
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Service("caseInfoService")
public class CaseInfoServiceImpl implements CaseInfoService {

	@Autowired
	private CaseInfoMapper caseInfoMapper;
	@Autowired
	private CaseCountService caseCountService;

	public int insert(CaseInfo caseInfo) {
		// 添加,同时添加投票统计
		int insertCaseInfo = caseInfoMapper.insertCaseInfo(caseInfo);
		CaseCount caseCount = new CaseCount();
		caseCount.setCaseId(caseInfo.getId());
		caseCount.setUpdateTime((new Date()).getTime());
		caseCountService.insert(caseCount);
		return insertCaseInfo;
	}

	public int update(CaseInfo caseInfo) {
		// 修改
		return caseInfoMapper.updateByPrimaryKeySelective(caseInfo);
	}

	public int delete(int id) {
		// 逻辑删除 删除标记改为1
		CaseInfo caseInfo = new CaseInfo();
		caseInfo.setId(id);
		caseInfo.setDelFlag((byte) 1);
		return caseInfoMapper.updateByPrimaryKeySelective(caseInfo);
	}

	public List<CaseInfo> selectCaseInfo(int caseVoteThemeId) {
		// 根据主题id查询全部
		List<CaseInfo> list = caseInfoMapper.selectCaseInfo(caseVoteThemeId);
		return list;
	}

	public List<CaseInfo> selectFuzzyByString(int caseVoteThemeId, String str) {
		// 根据字段模糊查找
		return caseInfoMapper.selectFuzzyByString(caseVoteThemeId, str);
	}

	public CaseInfo selectById(int id) {
		// 根据主键id查找
		CaseInfo record = new CaseInfo();
		record.setId(id);
		return caseInfoMapper.selectOne(record);
	}
}
