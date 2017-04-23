package com.bizideal.whoami.rolemodule.mapper;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.rolemodule.entity.RolePersonal;

/**
 * 
 * @author zhu_shangjin
 * @version 2016年12月13日 下午4:23:05
 */
public interface RolePersonalMapper extends Mapper<RolePersonal> {

	int deleteByRoleId(int roleid) throws Exception;

	int updateByRoleId(RolePersonal polePersonal);

}
