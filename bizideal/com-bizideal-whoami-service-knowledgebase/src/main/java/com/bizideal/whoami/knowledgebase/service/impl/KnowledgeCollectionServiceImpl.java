package com.bizideal.whoami.knowledgebase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeCollection;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeType;
import com.bizideal.whoami.knowledgebase.mapper.KnowledgeCollectionMapper;
import com.bizideal.whoami.knowledgebase.mapper.KnowledgeTypeMapper;
import com.bizideal.whoami.knowledgebase.service.KnowledgeCollectionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月23日 上午10:43:10
 * @version 1.0
 */
@Service("knowledgeCollectionService")
public class KnowledgeCollectionServiceImpl extends BaseBizImpl<KnowledgeCollection> implements
		KnowledgeCollectionService {

	@Autowired
	private KnowledgeCollectionMapper knowledgeCollectionMapper;
	@Autowired
	private KnowledgeTypeMapper knowledgeTypeMapper;

	@Override
	public int saveCollection(KnowledgeCollection knowledgeCollection) {
		KnowledgeType selectTypeUrlByFileId = knowledgeCollectionMapper.selectTypeUrlByFileId(knowledgeCollection
				.getFileId());
		if ("".equals(selectTypeUrlByFileId.getTypeUrl())) {
			knowledgeCollection.setTypeUrl(knowledgeCollection.getTypeUrl() + "://" + selectTypeUrlByFileId.getType());
		} else {
			knowledgeCollection.setTypeUrl(knowledgeCollection.getTypeUrl() + "://"
					+ selectTypeUrlByFileId.getTypeUrl() + "/" + selectTypeUrlByFileId.getType());
		}
		knowledgeCollection.setCollectionTime(System.currentTimeMillis());
		return knowledgeCollectionMapper.insertSelective(knowledgeCollection);
	}

	@Override
	public PageInfo<KnowledgeFile> selectMyCollectionsByUserId(KnowledgeCollection knowledgeCollection) {
		PageHelper.startPage(knowledgeCollection.getPageNum(), knowledgeCollection.getPageSize());
		List<KnowledgeFile> list = knowledgeCollectionMapper.selectMyCollectionsByUserId(knowledgeCollection);
		return new PageInfo<KnowledgeFile>(list);
	}
}
