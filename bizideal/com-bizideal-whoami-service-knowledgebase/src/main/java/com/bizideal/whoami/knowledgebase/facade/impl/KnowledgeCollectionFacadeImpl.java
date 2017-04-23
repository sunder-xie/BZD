package com.bizideal.whoami.knowledgebase.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeCollection;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.bizideal.whoami.knowledgebase.facade.KnowledgeCollectionFacade;
import com.bizideal.whoami.knowledgebase.service.KnowledgeCollectionService;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月23日 上午10:59:12
 * @version 1.0
 */
@Component("knowledgeCollectionFacade")
public class KnowledgeCollectionFacadeImpl implements KnowledgeCollectionFacade {

	@Autowired
	private KnowledgeCollectionService knowledgeCollectionService;

	@Override
	public int saveCollection(KnowledgeCollection knowledgeCollection) {
		return knowledgeCollectionService.saveCollection(knowledgeCollection);
	}

	@Override
	public int deleteCollection(KnowledgeCollection record) {
		return knowledgeCollectionService.deleteByWhere(record);
	}

	@Override
	public PageInfo<KnowledgeFile> selectMyCollectionsByUserId(
			KnowledgeCollection knowledgeCollection) {
		return knowledgeCollectionService
				.selectMyCollectionsByUserId(knowledgeCollection);
	}
}
