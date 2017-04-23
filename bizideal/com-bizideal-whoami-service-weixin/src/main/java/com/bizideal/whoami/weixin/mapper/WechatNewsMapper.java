package com.bizideal.whoami.weixin.mapper;

import java.util.List;

import com.bizideal.whoami.weixin.entity.WechatNews;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2017年2月14日 下午1:27:47
 */
public interface WechatNewsMapper extends Mapper<WechatNews>{
	/**
	 * 根据mediaId 获取微信图文素材
	 * @param mediaId  图文素材mediaId
	 * @return
	 */
	WechatNews getWechatNewsByMediaId(String mediaId);
    /**
     * 获取所有微信图文素材
     * @return
     */
	List<WechatNews> getAllWechatNews();
	/**
	 * 分页查询微信图文素材
	 * @return
	 */
	List<WechatNews>  getNewsByPage();
    /**
     * 批量新增图文素材
     * @param wechatNews
     * @return
     */
	int batchInsertNews(List<WechatNews> wechatNews);

}
