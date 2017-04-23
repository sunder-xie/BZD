package com.bizideal.whoami.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.mapper.CaseCountMapper;
import com.bizideal.whoami.service.CaseCountService;
import com.bizideal.whoami.vote.entity.CaseCount;

/**
 * 
 * @ClassName CaseCountService
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Service("caseCountService")
public class CaseCountServiceImpl implements CaseCountService {

	@Autowired
	private CaseCountMapper caseCountMapper;

	public int insert(CaseCount caseCount) {
		// 添加
		return caseCountMapper.insertCaseCount(caseCount);
	}

	public int update(CaseCount caseCount) {
		// 修改
		return caseCountMapper.updateByPrimaryKeySelective(caseCount);
	}

	public int delete(int id) {
		// 删除
		return caseCountMapper.deleteByPrimaryKey(id);
	}

	public CaseCount selectCaseCount(int caseId) {
		// 根据案例id查询
		CaseCount record = new CaseCount();
		record.setCaseId(caseId);
		CaseCount caseCount = caseCountMapper.selectOne(record);
		return caseCount;
	}
}
