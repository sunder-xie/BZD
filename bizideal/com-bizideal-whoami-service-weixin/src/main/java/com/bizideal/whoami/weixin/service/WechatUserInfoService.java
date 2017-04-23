package com.bizideal.whoami.weixin.service;

import java.util.List;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 下午1:32:44
 */
public interface WechatUserInfoService {
	/**
	 * 根据集合totalUsers中的openid集合获取数据库中WechatUserInfo也存在的用户信息
	 * @param totalUsers
	 * @return
	 */
	List<WechatUserInfo> getUserByOpenids(
			List<WechatTotalUser> totalUsers);
    /**
     * 根据集合openids中的openid集合获取数据库中WechatUserInfo也存在的用户信息
     * @param openids
     * @return
     */
	List<WechatUserInfo> getUserinfoByOpenids(List<String> openids);
    /**
     * 根据微信用户openid 删除用户信息
     * @param openid 微信用户openid
     * @return
     * @throws Exception
     */
	int deleteUserAndTotalUser(String openid) throws Exception;
    /**
     *  保存关注的微信用户信息
     * @param wechatUserInfo
     * @return
     * @throws Exception
     */
	int insertUser(WechatUserInfo wechatUserInfo) throws Exception;
/**
 * 批量新增微信用户信息
 * @param wechatUserInfos
 * @return
 * @throws Exception
 */
int batchSaveUser(List<WechatUserInfo> wechatUserInfos)
			throws Exception;
/**
 *    获取微信接口调用凭证的accesstoken
 * @return
 */
WechatAccessToken getWechatAccessToken();
    /**
     * 设置微信接口调用凭证accesstoken到redis缓存中
     * @param wechatAccessToken
     * @return
     * @throws Exception
     */
	String setWechhatAccessToken(WechatAccessToken wechatAccessToken)
			throws Exception;

	/**
	 *  微信用户关注后执行新增用户和总用户表
	 * @param wechatUserInfo
	 * @return
	 * @throws Exception
	 */
	int insertSubscribe(WechatUserInfo wechatUserInfo) throws Exception;

	/**
	 *  微信用户取消关注 删除用户 ,总用户表 和用户地理位置信息
	 * @param wechatUserInfo
	 * @return
	 * @throws Exception
	 */
	int deleteUnSubscribe(WechatUserInfo wechatUserInfo)
			throws Exception;
	/**
	 * 根据传入的openid集合获取数据库中也存在的用户的openid集合
	 * @param openids
	 * @return
	 */
	List<String> getUserFromDBByOpenids(List<String> openids);

}
