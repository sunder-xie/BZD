package com.bizideal.whoami.weixin.service;

import java.util.List;

import com.bizideal.whoami.weixin.entity.WechatTotalUser;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 下午1:32:20
 */
public interface WechatTotalUserService {
	/**
	 * 获取数据库存在且集合totalUsers也存在的记录
	 * @param totalUsers
	 * @return
	 */
	List<WechatTotalUser> getTotalusersByOpenids(List<WechatTotalUser> totalUsers);
	/**
	 * 获取数据库存在且集合openids也存在的记录
	 * @param openids
	 * @return
	 */
	List<WechatTotalUser> getTotalByOpenids(List<String> openids);
	/**
	 * 批量新增WechatTotalUser  
	 * @param openids  微信用户openid集合
	 * @return
	 * @throws Exception
	 */
	int batchSaveTotalUser(List<String> openids) throws Exception;
	/**
	 * 新增WechatTotalUser
	 * @param openid  微信用户openid
	 * @return
	 * @throws Exception
	 */
	int insertTotalUser(String openid) throws Exception;
	/**
	 * 查询出集合openids存在且数据库WechatTotalUser也存在的openis的集合
	 * @param openids
	 * @return
	 */
	List<String> getFromDBByOpenids(List<String> openids);

}
