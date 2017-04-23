package com.bizideal.whoami.knowledgebase.service;

import java.util.List;

import com.bizideal.whoami.croe.service.BaseOpBiz;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月22日 下午5:30:47
 * @version 1.0
 */
public interface KnowledgeFileService extends BaseOpBiz<KnowledgeFile> {

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

	int delete(Integer id, String userId);
}
