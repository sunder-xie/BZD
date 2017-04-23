package com.bizideal.whoami.knowledgebase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.service.impl.BaseOpBizImpl;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeType;
import com.bizideal.whoami.knowledgebase.mapper.KnowledgeFileMapper;
import com.bizideal.whoami.knowledgebase.mapper.KnowledgeTypeMapper;
import com.bizideal.whoami.knowledgebase.service.KnowledgeFileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月23日 上午9:26:43
 * @version 1.0
 */
@Service("knowledgeFileService")
public class KnowledgeFileServiceImpl extends BaseOpBizImpl<KnowledgeFile> implements KnowledgeFileService {

	@Autowired
	private KnowledgeFileMapper knowledgeFileMapper;
	@Autowired
	private KnowledgeTypeMapper knowledgeTypeMapper;

	@Override
	public List<KnowledgeFile> selectFilesNotSign(int meeId) {
		return knowledgeFileMapper.selectFilesNotSign(meeId);
	}

	@Override
	public PageInfo<KnowledgeFile> selectFilesByTypeId(String userId, int typeId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<KnowledgeFile> files = knowledgeFileMapper.selectFilesByTypeId(userId, typeId);
		return new PageInfo<KnowledgeFile>(files);
	}

	@Override
	public int saveFileOrUrl(KnowledgeFile knowledgeFile) {
		KnowledgeType type = knowledgeTypeMapper.selectByPrimaryKey(knowledgeFile.getTypeId());
		if (null == type) {
			return 0;
		}
		if (!"2".equals(type.getIsType())) {
			// 改变type节点的类型值，2表示是资
			KnowledgeType t = new KnowledgeType();
			t.setId(type.getId());
			t.setIsType("2");
			int result = knowledgeTypeMapper.updateByPrimaryKeySelective(t);
			if (result == 0) {
				return 0;
			}
		}
		knowledgeFile.setCreateTime(System.currentTimeMillis());
		return knowledgeFileMapper.insertSelective(knowledgeFile);
	}

	@Override
	public KnowledgeFile selectById(Integer fileId) {
		return knowledgeFileMapper.selectByPrimaryKey(fileId);
	}

	@Override
	public int delete(Integer id, String userId) {
		KnowledgeFile file = knowledgeFileMapper.selectByPrimaryKey(id);
		int deleteByPrimaryKey = knowledgeFileMapper.deleteByPrimaryKey(id);
		if (1 == deleteByPrimaryKey) {
			PageInfo<KnowledgeFile> selectFilesByTypeId = selectFilesByTypeId(userId, file.getTypeId(), 1, 1000);
			if (0 == selectFilesByTypeId.getList().size()) {
				KnowledgeType type = new KnowledgeType();
				type.setId(file.getTypeId());
				type.setIsType("0");
				knowledgeTypeMapper.updateByPrimaryKeySelective(type);
			}
		}
		return deleteByPrimaryKey;
	}
}
