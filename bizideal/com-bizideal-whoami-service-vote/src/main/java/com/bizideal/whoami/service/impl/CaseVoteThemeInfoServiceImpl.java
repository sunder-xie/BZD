package com.bizideal.whoami.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.mapper.CaseVoteThemeInfoMapper;
import com.bizideal.whoami.service.CaseVoteThemeInfoService;
import com.bizideal.whoami.vote.entity.CaseVoteThemeInfo;

/**
 * 
 * @ClassName CaseVoteThemeInfoServiceImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Service("caseVoteThemeInfoService")
public class CaseVoteThemeInfoServiceImpl implements CaseVoteThemeInfoService {

	@Autowired
	private CaseVoteThemeInfoMapper caseVoteThemeInfoMapper;

	public int insert(CaseVoteThemeInfo caseVoteThemeInfo) {
		// 添加
		return caseVoteThemeInfoMapper.insertCaseVoteThemeInfo(caseVoteThemeInfo);
	}

	public int update(CaseVoteThemeInfo caseVoteThemeInfo) {
		// 修改
		return caseVoteThemeInfoMapper.updateByPrimaryKeySelective(caseVoteThemeInfo);
	}

	public int delete(int id) {
		// 删除
		return caseVoteThemeInfoMapper.deleteByPrimaryKey(id);
	}

	public List<CaseVoteThemeInfo> selectCaseVoteThemeInfo(Integer voteId, Integer type) {
		// 根据投票id查询全部
		CaseVoteThemeInfo record = new CaseVoteThemeInfo();
		record.setVoteId(voteId);
		record.setType(type);
		List<CaseVoteThemeInfo> list = caseVoteThemeInfoMapper.select(record);
		return list;
	}

	public List<CaseVoteThemeInfo> selectFuzzyByName(int voteId, String name) {
		// 根据name模糊查询
		return caseVoteThemeInfoMapper.selectFuzzyByName(voteId, name);
	}

	public CaseVoteThemeInfo selectById(int id) {
		// 根据主键id查询
		CaseVoteThemeInfo record = new CaseVoteThemeInfo();
		record.setId(id);
		return caseVoteThemeInfoMapper.selectOne(record);
	}
}
