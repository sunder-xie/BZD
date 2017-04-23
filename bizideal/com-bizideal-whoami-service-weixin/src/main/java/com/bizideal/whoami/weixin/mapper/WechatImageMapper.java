package com.bizideal.whoami.weixin.mapper;

import java.util.List;

import com.bizideal.whoami.weixin.entity.WechatImage;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2017年2月14日 下午1:27:28
 */
public interface WechatImageMapper extends Mapper<WechatImage>{
	/**
	 * 批量新增微信图片素材
	 * @param wechatImages
	 * @return
	 */
	int batchsaveImage(List<WechatImage> wechatImages);

}
