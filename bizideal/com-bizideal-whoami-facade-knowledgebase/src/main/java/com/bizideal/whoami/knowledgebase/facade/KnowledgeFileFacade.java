package com.bizideal.whoami.knowledgebase.facade;

import java.util.List;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月23日 上午9:24:48
 * @version 1.0
 */
public interface KnowledgeFileFacade {

	/**
	 * 选择性保存，为null不保存
	 * 
	 * @param knowledgeFile
	 * @return 返回大于0表示成功
	 */
	int saveSelective(KnowledgeFile knowledgeFile);

	/**
	 * 选择性更新，为null不更新
	 * 
	 * @param knowledgeFile
	 * @return 返回大于0表示成功
	 */
	int updateSelective(KnowledgeFile knowledgeFile);

	/**
	 * 查询会议下未分配的资料
	 * 
	 * @param meeId
	 *            主会议id
	 * @return
	 */
	List<KnowledgeFile> selectFilesNotSign(int meeId);

	/**
	 * 查询分类下的资料列表
	 * 
	 * @param userId
	 *            当前登陆用户
	 * @param typeId
	 *            分类id
	 * @param pageNum
	 *            当前第几页
	 * @param pageSize
	 *            每页多少条数据
	 * @return
	 */
	PageInfo<KnowledgeFile> selectFilesByTypeId(String userId, int typeId, int pageNum, int pageSize);

	/**
	 * 保存文件或url
	 * 
	 * @param knowledgeFile
	 * @return 返回大于0表示成功
	 */
	int saveFileOrUrl(KnowledgeFile knowledgeFile);

	/**
	 * 根据id查找文件
	 * 
	 * @param fileId
	 * @return
	 */
	KnowledgeFile selectById(Integer fileId);

	/**
	 * 根据id删除文件
	 * 
	 * @param id
	 * @return
	 */
	int delete(Integer id, String userId);
}
