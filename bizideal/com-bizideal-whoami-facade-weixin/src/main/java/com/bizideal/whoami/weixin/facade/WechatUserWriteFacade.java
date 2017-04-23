package com.bizideal.whoami.weixin.facade;

import java.util.List;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.weixin.entity.WechatImage;
import com.bizideal.whoami.weixin.entity.WechatNews;
import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;
import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;
import com.bizideal.whoami.weixin.entity.WechatUserLocation;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 上午9:33:18
 */
public interface WechatUserWriteFacade {
	/**
	 * 批量新增WechatTotalUser表
	 * @param totalUsers
	 * @return
	 */
	DubboxResult batchSaveTotalUser(List<WechatTotalUser> totalUsers);
	/**
	 * 取消关注后 删除该用户
	 * @param wechatUserInfo
	 * @return
	 */
	DubboxResult deleteWechatUser(WechatUserInfo wechatUserInfo);
	/**
	 * 批量保存用户信息
	 * @param wechatUserInfos
	 * @return
	 */
	DubboxResult batchSaveUser(List<WechatUserInfo> wechatUserInfos);
	/**
	 * 用户关注后保存用户信息
	 * @param wechatUserInfos
	 * @return
	 */
	DubboxResult saveUser(WechatUserInfo wechatUserInfos);
	
	/**
	 * 批量新增WechatTotalUser表
	 * @param openids
	 * @return
	 */
	DubboxResult batchInsertTotalUser(List<String> openids);
	/**
	 * 根据openid删除关注用户
	 * @param openid
	 * @return
	 */
	DubboxResult deleteWechatUserByOpenid(String openid);
	/**
	 * 保存自动回复的请求和响应
	 * @param wechatRequestMessageText
	 * @return
	 * @throws Exception
	 */
	DubboxResult insertReqAndResp(WechatRequestMessageText wechatRequestMessageText) throws Exception;
	/**
	 * 根据关键字删除请求和响应的规则
	 * @param keyWords  关键字
	 * @return
	 * @throws Exception
	 */
	DubboxResult deleteReqAndRespByKey(String keyWords) throws Exception;
	/**
	 * 更新自动回复的请求和响应规则
	 * @param wechatRequestMessageText
	 * @return
	 * @throws Exception
	 */
	DubboxResult updateReqAndResp(WechatRequestMessageText wechatRequestMessageText) throws Exception;
	/**
	 * 微信用户关注后执行新增用户和total
	 * @param wechatUserInfo
	 * @return
	 * @throws Exception
	 */
	DubboxResult insertSubscribe(WechatUserInfo wechatUserInfo) throws Exception;
	/**
	 * 微信用户取消关注 删除用户 total  和location
	 * @param wechatUserInfo
	 * @return
	 * @throws Exception
	 */
	DubboxResult deleteUnSubscribe(WechatUserInfo wechatUserInfo) throws Exception;
	
	/**
	 * 新增或更新用户地理位置信息
	 * @param wechatUserLocation
	 * @return
	 * @throws Exception
	 */
	DubboxResult insertOrUpdateLocation(WechatUserLocation wechatUserLocation) throws Exception;
	/**
	 * 新增图片素材
	 * @param wechatImage
	 * @return
	 * @throws Exception 
	 */
	DubboxResult insertWechatImage(WechatImage wechatImage) throws Exception;
	/**
	 * 批量新增图片素材
	 * @return
	 * @throws Exception 
	 */
	DubboxResult batchInsertWechatImage(List<WechatImage> wechatImages) throws Exception;
	/**
	 * 新增图文素材
	 * @param wechatNews
	 * @return
	 * @throws Exception 
	 */
	DubboxResult insertWechatNews(WechatNews wechatNews) throws Exception;
	/**
	 * 批量新增图文素材
	 * @param wechatNews
	 * @return
	 * @throws Exception 
	 */
	DubboxResult batchInsertWechatNews(List<WechatNews> wechatNews) throws Exception;
	

}
