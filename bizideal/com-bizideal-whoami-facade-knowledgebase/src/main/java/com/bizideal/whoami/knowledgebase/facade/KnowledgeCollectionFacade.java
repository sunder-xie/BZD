package com.bizideal.whoami.knowledgebase.facade;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeCollection;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月23日 上午10:58:36
 * @version 1.0
 */
public interface KnowledgeCollectionFacade {

	/**
	 * 收藏接口
	 * 
	 * @param knowledgeCollection
	 * @return 返回大于0表示成功
	 */
	int saveCollection(KnowledgeCollection knowledgeCollection);

	/**
	 * 取消收藏接口
	 * 
	 * @param KnowledgeCollection
	 *            收藏表参数条件
	 * @return 返回大于0表示成功
	 */
	int deleteCollection(KnowledgeCollection record);

	/**
	 * 查询我的收藏，分页展示
	 * 
	 * @param knowledgeCollection
	 * @return
	 */
	PageInfo<KnowledgeFile> selectMyCollectionsByUserId(KnowledgeCollection knowledgeCollection);
}
