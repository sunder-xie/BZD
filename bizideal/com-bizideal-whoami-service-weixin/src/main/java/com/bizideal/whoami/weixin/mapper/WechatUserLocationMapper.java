package com.bizideal.whoami.weixin.mapper;


import com.bizideal.whoami.weixin.entity.WechatUserLocation;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午4:23:05
 */
public interface WechatUserLocationMapper extends Mapper<WechatUserLocation>{
	/**
	 * 根据unionid 查询出数据库中是否存在记录
	 * @param unionid
	 * @return
	 */
	Long countLocationByUnionid(String unionid);
	

}
