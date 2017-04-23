package com.bizideal.whoami.croe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName BaseBiz
 * @Description TODO(通用业务层的的实现类)
 * @Author Zj.Qu
 * @Date 2016-12-05 16:06:46
 * @param <T>
 */
public abstract class BaseBizImpl<T> implements BaseBiz<T> {

	@Autowired
	private Mapper<T> mapper;

	@Override
	public T queryById(int id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> queryAll() {
		return this.mapper.select(null);
	}

	@Override
	public T queryOne(T record) {
		return this.mapper.selectOne(record);
	}

	@Override
	public List<T> queryListByWhere(T record) {
		return this.mapper.select(record);
	}

	@Override
	public PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		List<T> list = this.mapper.select(record);
		return new PageInfo<T>(list);
	}

	@Override
	public Integer save(T t) {
		return this.mapper.insert(t);
	}

	@Override
	public Integer saveSelective(T t) {
		return this.mapper.insertSelective(t);
	}

	@Override
	public Integer update(T t) {
		return this.mapper.updateByPrimaryKey(t);
	}

	@Override
	public Integer updateSelective(T t) {
		return this.mapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public Integer deleteById(Integer id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer deleteByIds(Class<T> clazz, String property, List<Object> values) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, values);
		return this.mapper.deleteByExample(example);
	}

	@Override
	public Integer deleteByWhere(T record) {
		return this.mapper.delete(record);
	}

}
