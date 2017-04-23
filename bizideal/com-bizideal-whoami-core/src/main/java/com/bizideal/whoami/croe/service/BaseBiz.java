package com.bizideal.whoami.croe.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * @ClassName BaseBiz
 * @Description TODO(通用业务层的接口)
 * @Author Zj.Qu
 * @Date 2016-12-05 16:29:41
 */
public interface BaseBiz<T> {

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	T queryById(int id);

	/**
	 * 查询所有数据
	 * @return
	 */
	List<T> queryAll();

	/**
	 * 根据条件查询一条数据
	 * @param record
	 * @return
	 */
	T queryOne(T record);

	/**
	 * 根据条件查询数据列表
	 * @param record
	 * @return
	 */
	List<T> queryListByWhere(T record);

	/**
	 * 分页查询数据列表
	 * 
	 * @param page
	 * @param rows
	 * @param record
	 * @return
	 */
	PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record);

	/**
	 * 新增数据
	 * 
	 * @param t
	 * @return
	 */
	Integer save(T t);

	/**
	 * 有选择的保存，null的属性不会保存，会使用数据库默认值
	 * @param t
	 * @return
	 */
	Integer saveSelective(T t);

	/**
	 * 更新数据
	 * @param t
	 * @return
	 */
	Integer update(T t);

	/**
	 * 有选择的更新，选择不为null的字段作为插入字段
	 * @param t
	 * @return
	 */
	Integer updateSelective(T t);

	/**
	 * 根据id删除数据
	 * @param id
	 * @return
	 */
	Integer deleteById(Integer id);

	/**
	 * 批量删除
	 * @param clazz
	 * @param property
	 * @param values
	 * @return
	 */
	Integer deleteByIds(Class<T> clazz, String property, List<Object> values);

	/**
	 * 根据条件删除数据
	 * @param record
	 * @return
	 */
	Integer deleteByWhere(T record);

}