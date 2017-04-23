package com.bizideal.whoami.weixin.mapper;

import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2016年12月22日 上午10:18:58
 */
public interface WechatRequestMessageTextMapper extends Mapper<WechatRequestMessageText>{
	/**
	 * 根据关键字获取自动回复请求和响应
	 * @param reqKeyWords  关键字
	 * @return
	 */
	WechatRequestMessageText getReqAndRespByKey(String reqKeyWords);
	/**
	 * 根据关键字获取请求和文本响应
	 * @param reqKeyWords  关键字
	 * @return
	 */
	WechatRequestMessageText getRequestByKey(String reqKeyWords);
	/**
	 * 根据关键字删除请求和响应
	 * @param keywords  关键字
	 * @return
	 * @throws Exception
	 */
	int deleteReqByKey(String keywords) throws Exception;

}
