package com.bizideal.whoami.rolemodule.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.rolemodule.entity.HallFunction;

/**
 * @author 作者 zhushangjin:
 * @date 创建时间：2016年11月29日 下午2:29:53
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface HallFunctionMapper extends Mapper<HallFunction> {
	/**
	 * 获取所有功能模块的主键
	 * 
	 * @return
	 */
	List<Integer> getAllFunctionIds();

	List<HallFunction> selectfunction(List<Integer> funcitons);

}
