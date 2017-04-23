package com.bizideal.whoami.knowledgebase.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.bizideal.whoami.knowledgebase.facade.KnowledgeFileFacade;
import com.bizideal.whoami.knowledgebase.service.KnowledgeFileService;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月23日 上午9:26:13
 * @version 1.0
 */
@Component("knowledgeFileFacade")
public class KnowledgeFileFacadeImpl implements KnowledgeFileFacade {

	@Autowired
	private KnowledgeFileService knowledgeFileService;

	@Override
	public int saveSelective(KnowledgeFile knowledgeFile) {
		return knowledgeFileService.saveSelective(knowledgeFile);
	}

	@Override
	public int updateSelective(KnowledgeFile knowledgeFile) {
		return knowledgeFileService.updateSelective(knowledgeFile);
	}

	@Override
	public List<KnowledgeFile> selectFilesNotSign(int meeId) {
		return knowledgeFileService.selectFilesNotSign(meeId);
	}

	@Override
	public PageInfo<KnowledgeFile> selectFilesByTypeId(String userId, int typeId, int pageNum, int pageSize) {
		return knowledgeFileService.selectFilesByTypeId(userId, typeId, pageNum, pageSize);
	}

	@Override
	public int saveFileOrUrl(KnowledgeFile knowledgeFile) {
		return knowledgeFileService.saveFileOrUrl(knowledgeFile);
	}

	@Override
	public KnowledgeFile selectById(Integer fileId) {
		return knowledgeFileService.selectById(fileId);
	}

	@Override
	public int delete(Integer id, String userId) {
		return knowledgeFileService.delete(id, userId);
	}

}
