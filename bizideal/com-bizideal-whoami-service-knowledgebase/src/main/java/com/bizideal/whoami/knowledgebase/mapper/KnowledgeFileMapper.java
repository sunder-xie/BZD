package com.bizideal.whoami.knowledgebase.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月22日 下午3:41:45
 * @version 1.0
 */
public interface KnowledgeFileMapper extends Mapper<KnowledgeFile> {

	/* 查询未分配的知识库资料 */
	@Select("SELECT * FROM knowledge_file kf WHERE kf.del_flag = 0 AND kf.mee_id = #{meeId} AND kf.type_id IS NULL")
	List<KnowledgeFile> selectFilesNotSign(int meeId);

	@Select("SELECT kf.*,CASE WHEN kc.user_id IS NOT NULL THEN 1 ELSE 0 END AS isCollected FROM knowledge_file kf LEFT JOIN knowledge_collection kc "
			+ " ON (kf.id = kc.file_id AND kc.user_id = #{userId}) WHERE kf.type_id = #{typeId} AND kf.del_flag = 0")
	List<KnowledgeFile> selectFilesByTypeId(
			@Param("userId") String userId, @Param("typeId") int typeId);
}
