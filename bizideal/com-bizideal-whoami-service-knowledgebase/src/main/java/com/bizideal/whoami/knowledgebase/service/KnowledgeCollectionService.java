package com.bizideal.whoami.knowledgebase.service;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeCollection;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月23日 上午10:42:35
 * @version 1.0
 */
public interface KnowledgeCollectionService extends
		BaseBiz<KnowledgeCollection> {

	/**
	 * 收藏接口
	 * 
	 * @param knowledgeCollection
	 * @return 返回大于0表示成功
	 */
	int saveCollection(KnowledgeCollection knowledgeCollection);

	/**
	 * 查询我的收藏，分页展示
	 * 
	 * @param knowledgeCollection
	 * @return
	 */
	PageInfo<KnowledgeFile> selectMyCollectionsByUserId(
			KnowledgeCollection knowledgeCollection);
}
