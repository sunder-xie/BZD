package com.bizideal.whoami.weixin.mapper;

import java.util.List;

import com.bizideal.whoami.weixin.entity.WechatNewsItem;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2017年2月14日 下午1:28:02
 */
public interface WechatNewsItemMapper extends Mapper<WechatNewsItem>{
     /**
      * 批量新增微信图文素材
      * @param wechatNewsItems
      * @return
      */
	int batchInsertNewsItem(List<WechatNewsItem> wechatNewsItems);

}
