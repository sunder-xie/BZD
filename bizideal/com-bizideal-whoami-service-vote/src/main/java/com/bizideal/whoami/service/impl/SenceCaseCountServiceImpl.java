package com.bizideal.whoami.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.mapper.SenceCaseCountMapper;
import com.bizideal.whoami.sencevote.entity.SenceCaseCount;
import com.bizideal.whoami.service.SenceCaseCountService;

/**
 * 
 * @ClassName CaseCountService
 * @Description TODO(detail)
 * @Author sy
 * @Date 2017年2月9日
 */
@Service("senceCaseCountService")
public class SenceCaseCountServiceImpl implements SenceCaseCountService {

	@Autowired
	private SenceCaseCountMapper senceCaseCountMapper;

	public int insert(SenceCaseCount senceCaseCount) {
		// 添加
		return senceCaseCountMapper.insertSelective(senceCaseCount);
	}

	public SenceCaseCount selectSenceCaseCount(int senceCaseId) {
		// 根据案例id查询
		SenceCaseCount record = new SenceCaseCount();
		record.setSenceCaseId(senceCaseId);
		SenceCaseCount senceCaseCount = senceCaseCountMapper.selectOne(record);
		return senceCaseCount;
	}
}
