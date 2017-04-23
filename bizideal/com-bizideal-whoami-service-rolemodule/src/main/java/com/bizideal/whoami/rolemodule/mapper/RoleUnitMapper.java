package com.bizideal.whoami.rolemodule.mapper;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.rolemodule.entity.RoleUnit;

/**
 * 
 * @author zhu_shangjin
 * @version 2016年12月13日 下午4:23:05
 */
public interface RoleUnitMapper extends Mapper<RoleUnit> {
	int deleteByRoleId(int roleid) throws Exception;

	int updateByRoleId(RoleUnit roleUnit);

}
