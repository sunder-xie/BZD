package com.bizideal.whoami.weixin.mapper;


import java.util.List;

import com.bizideal.whoami.weixin.entity.WechatTotalUser;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午4:23:05
 */
public interface WechatTotalUserMapper extends Mapper<WechatTotalUser>{
	/**
	 * 获取所有totalUsers 存在且数据库存在的总用户
	 * @param totalUsers
	 * @return
	 */
	List<WechatTotalUser> getTotalusersByOpenids(List<WechatTotalUser> totalUsers);
	/**
	 * 批量新增总用户
	 * @param openids
	 * @return
	 * @throws Exception
	 */
	int batchSaveTotalUser(List<String> openids) throws Exception;
	/**
	 * 获取所有openids存在且数据库也存在的总用户
	 * @param openids
	 * @return
	 */
	List<WechatTotalUser> getTotalByOpenids(List<String> openids);
	/**
	 * 根据openid删除总用户表记录
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	int deleteTotalUser(String openid) throws Exception;
	/**
	 * 根据openids 获取数据库存在的用户openid的集合
	 * @param openids
	 * @return
	 */
	List<String> getFromDBByOpenids(List<String> openids);
	

}
