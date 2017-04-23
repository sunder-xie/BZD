package com.bizideal.whoami.knowledgebase.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeType;
import com.bizideal.whoami.knowledgebase.mapper.KnowledgeTypeMapper;
import com.bizideal.whoami.knowledgebase.service.KnowledgeTypeService;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月22日 下午4:06:12
 * @version 1.0
 */
@Service("knowledgeTypeService")
public class KnowledgeTypeServiceImpl extends BaseBizImpl<KnowledgeType> implements KnowledgeTypeService {

	@Autowired
	private KnowledgeTypeMapper knowledgeTypeMapper;

	public int insert(KnowledgeType knowledgeType) {
		String u = "";
		if (knowledgeType.getParentId() != null && knowledgeType.getParentId() != 0) {
			KnowledgeType selectByPrimaryKey = knowledgeTypeMapper.selectByPrimaryKey(knowledgeType.getParentId());

			if ("2".equals(selectByPrimaryKey.getIsType())) {
				return -2;// 父类已经保存了资料
			}

			String[] split = selectByPrimaryKey.getTypeUrl().split("/");
			if (split.length >= 3) {
				return -1;// 不能超过4层分类
			}
			if (split.length == 1 && "".equals(selectByPrimaryKey.getTypeUrl())) {
				u = selectByPrimaryKey.getType();
			} else {
				u = selectByPrimaryKey.getTypeUrl() + "/" + selectByPrimaryKey.getType();
			}

			// 分类为其他分类子类时更改父类的类别为子类1
			if (!"1".equals(selectByPrimaryKey.getIsType())) {
				selectByPrimaryKey.setIsType("1");
				knowledgeTypeMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
			}
		}
		knowledgeType.setTypeUrl(u);
		// 插入知识库分类
		int insertSelective = knowledgeTypeMapper.insertSelective(knowledgeType);
		return insertSelective;
	}

	public int delete(Integer id) {
		// 搜索分类信息
		KnowledgeType selectByPrimaryKey = knowledgeTypeMapper.selectByPrimaryKey(id);
		// 删除分类
		int deleteByPrimaryKey = knowledgeTypeMapper.deleteByPrimaryKey(id);

		List<KnowledgeType> pIds = new ArrayList<KnowledgeType>();
		KnowledgeType e = new KnowledgeType();
		e.setId(id);
		pIds.add(e);
		// 删除子类
		deleteChild(pIds);

		KnowledgeType record = new KnowledgeType();
		record.setParentId(selectByPrimaryKey.getParentId());
		// 搜索兄弟类
		List<KnowledgeType> select = knowledgeTypeMapper.select(record);
		if ((select.size() == 0 || select == null) && selectByPrimaryKey.getParentId() != 0) {
			// 没有兄弟类时更改父类类别为未分类0
			KnowledgeType p = knowledgeTypeMapper.selectByPrimaryKey(selectByPrimaryKey.getParentId());
			p.setIsType("0");
			knowledgeTypeMapper.updateByPrimaryKey(p);
		}
		return deleteByPrimaryKey;
	}

	// 删除全部子类
	public void deleteChild(List<KnowledgeType> pIds) {
		// 设置条件
		Example example = new Example(KnowledgeType.class);
		StringBuilder v = new StringBuilder();
		v.append("(");
		for (int i = 0; i < pIds.size(); i++) {
			KnowledgeType pId = pIds.get(i);
			v.append(pId.getId());
			if (pIds.size() != i + 1) {
				v.append(",");
			}
		}
		v.append(")");
		example.createCriteria().andCondition("parent_id in " + v.toString());
		// 查找子类的子类集合
		List<KnowledgeType> selectByExample = knowledgeTypeMapper.selectByExample(example);
		// 删除子类
		knowledgeTypeMapper.deleteByExample(example);
		// 删除子类的子类
		if (selectByExample.size() != 0 && selectByExample != null) {
			deleteChild(selectByExample);
		}
	}

	public List<KnowledgeType> selectByMeetingId(Integer meeId, Integer pId) {
		KnowledgeType record = new KnowledgeType();
		record.setMeeId(meeId);
		record.setParentId(pId);
		return knowledgeTypeMapper.select(record);
	}

	@Override
	public KnowledgeType selectById(Integer id) {
		return knowledgeTypeMapper.selectByPrimaryKey(id);
	}
}
