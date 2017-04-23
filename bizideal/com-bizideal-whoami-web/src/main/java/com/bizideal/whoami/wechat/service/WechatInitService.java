package com.bizideal.whoami.wechat.service;

import java.util.List;

/**
 * @author zhu_shangjin
 * @version 2017年1月3日 下午3:13:51
 */
public interface WechatInitService {
	/**
	 *  初始化关注微信公众号的总用户
	 * @throws Exception
	 */
	void initWechatTotalUser() throws Exception;
	/**
	 * 初始化关注微信公众号的用户
	 * @param openids
	 * @param token
	 * @throws Exception
	 */
	void initWechatUserInfo(List<String> openids, String token) throws Exception;

}
