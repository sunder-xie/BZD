package com.bizideal.whoami.knowledgebase.service;

import java.util.List;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeType;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月22日 下午4:05:52
 * @version 1.0
 */
public interface KnowledgeTypeService extends BaseBiz<KnowledgeType> {

	/**
	 * 插入知识库分类
	 * 
	 * @param knowledgeType
	 * @return 返回1-插入成功； 返回0-插入失败； 返回-1-分类不能超过4层； 返回-2-父类已保存资料
	 */
	int insert(KnowledgeType knowledgeType);

	/**
	 * 删除知识库分类，自动删除子类
	 * 
	 * @param id
	 * @return 返回1-删除成功； 返回0-删除失败
	 */
	int delete(Integer id);

	/**
	 * 根据会议id和父类id查询知识库分类集合
	 * 
	 * @param meeId
	 * @param pId
	 * @return 知识库分类List集合
	 */
	List<KnowledgeType> selectByMeetingId(Integer meeId, Integer pId);

	/**
	 * 根据知识库分类id查找知识库分类
	 * 
	 * @param id
	 * @return
	 */
	KnowledgeType selectById(Integer id);
}
