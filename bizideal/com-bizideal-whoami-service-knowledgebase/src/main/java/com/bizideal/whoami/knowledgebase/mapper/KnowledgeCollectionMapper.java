package com.bizideal.whoami.knowledgebase.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeCollection;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeType;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月22日 下午3:40:42
 * @version 1.0
 */
public interface KnowledgeCollectionMapper extends Mapper<KnowledgeCollection> {

	/* 根据文件id查询type_url */
	@Select("SELECT kt.* FROM knowledge_file kf LEFT JOIN knowledge_type kt ON kf.type_id = kt.id WHERE kf.id = #{fileId}")
	KnowledgeType selectTypeUrlByFileId(int fileId);

	/* 查询我的收藏 */
	List<KnowledgeFile> selectMyCollectionsByUserId(KnowledgeCollection knowledgeCollection);
}
