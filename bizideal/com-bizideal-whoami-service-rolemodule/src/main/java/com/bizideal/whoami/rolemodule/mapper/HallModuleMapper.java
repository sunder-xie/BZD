package com.bizideal.whoami.rolemodule.mapper;

import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.rolemodule.entity.HallModule;

public interface HallModuleMapper extends Mapper<HallModule> {

	// 插入会议模块关联中间表
	int insertHallModule(Map<String, Object> map);

	// 更新会议模块关联中间表
	int updateHallModule(Map<String, Object> map);
}
