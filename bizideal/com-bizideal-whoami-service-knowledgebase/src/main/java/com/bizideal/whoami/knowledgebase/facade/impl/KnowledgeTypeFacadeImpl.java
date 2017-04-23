package com.bizideal.whoami.knowledgebase.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeType;
import com.bizideal.whoami.knowledgebase.facade.KnowledgeTypeFacade;
import com.bizideal.whoami.knowledgebase.service.KnowledgeTypeService;

/**
 * 知识库分类接口
 * 
 * @author sy
 * @date 2017-2-23 14:34:10
 * @version 1.0
 */
@Component("knowledgeTypeFacade")
public class KnowledgeTypeFacadeImpl implements KnowledgeTypeFacade {

	@Autowired
	private KnowledgeTypeService knowledgeTypeService;

	@Override
	public int insert(KnowledgeType knowledgeType) {
		// TODO Auto-generated method stub
		return knowledgeTypeService.insert(knowledgeType);
	}

	@Override
	public int update(KnowledgeType knowledgeType) {
		// TODO Auto-generated method stub
		return knowledgeTypeService.updateSelective(knowledgeType);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return knowledgeTypeService.delete(id);
	}

	@Override
	public List<KnowledgeType> selectByMeetingId(Integer meeId, Integer pId) {
		// TODO Auto-generated method stub
		return knowledgeTypeService.selectByMeetingId(meeId, pId);
	}

	@Override
	public KnowledgeType selectById(Integer id) {
		// TODO Auto-generated method stub
		return knowledgeTypeService.selectById(id);
	}

}
