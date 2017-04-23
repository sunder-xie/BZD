package com.bizideal.whoami.weixin.facade;

import java.util.List;





import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.bizideal.whoami.weixin.dto.response.ImageMessage;
import com.bizideal.whoami.weixin.dto.response.NewsMessage;
import com.bizideal.whoami.weixin.dto.response.TextMessage;
import com.bizideal.whoami.weixin.entity.WechatConfig;
import com.bizideal.whoami.weixin.entity.WechatImage;
import com.bizideal.whoami.weixin.entity.WechatNews;
import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;
import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;
import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;
import com.bizideal.whoami.weixin.entity.WechatUserLocation;
import com.github.pagehelper.PageInfo;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 上午9:34:08
 */
public interface WechatUserReadFacade {
	/**
	 * 获取wechat_total_user表中和totalUsers集合中都存在的记录
	 * @param totalUsers
	 * @return
	 */
	List<WechatTotalUser> getTotalusersByOpenids(
			List<WechatTotalUser> totalUsers);
    /**
     * 根据openid的集合totalUsers 获取wechat_user_info表中存在的用户信息
     * @param totalUsers
     * @return
     */
	List<WechatUserInfo> getUserByOpenids(
			List<WechatTotalUser> totalUsers);
/**
 * 根据环境配置获取对应公众号的配置
 * @param profile对应 wechat_config表中的wechat_profile字段
 * @return
 */
WechatConfig getWechatConfig(String profile);
    /**
     * 根据openids集合获取wechat_total_user  存在的记录
     * @param openids
     * @return
     */
	List<WechatTotalUser> getTotalByOpenids(List<String> openids);
/**
 * 根据openids 获取wechat_user_info表中存在的记录
 * @param openids
 * @return
 */
List<WechatUserInfo> getUserinfoByOpenids(List<String> openids);
   /**
    * 获取微信接口调用凭证
    * @return
    */
   WechatAccessToken getWechatAccessToken();
   /**
    * 设置微信接口调用凭证到redis中 缓存2小时
    * @param wechatAccessToken
    * @return
    * @throws Exception
    */
   DubboxResult setWechhatAccessToken(
		   WechatAccessToken wechatAccessToken) throws Exception;

	/**
	 * 根据关键字获取请求规则和文本响应
	 * 
	 * @param reqKeyWords  关键字
	 * @return
	 */
	WechatRequestMessageText getReqAndRespByKey(String reqKeyWords);

	/**
	 * 根据关键字获取请求规则
	 * 
	 * @param reqKeyWords  关键字
	 * @return
	 */
	WechatRequestMessageText getRequestByKey(String reqKeyWords);

	/**
	 * 根据关键字获取文本响应消息
	 * 
	 * @param reqKeyWords  关键字
	 * @return
	 */
	WechatResponseMessageText getResponseByKeyWords(String reqKeyWords);

	/**
	 *  根据unionid 获取用户地理位置信息
	 * @param unionid
	 * @return
	 */
	WechatUserLocation getLocation(String unionid);

	/**
	 * 根据openid获取用户地理位置
	 * 
	 * @param openid  用户openid
	 * @return
	 */
	WechatUserLocation getLocationByOpenid(String openid);

	/**
	 *  获取 已关注且在 wechat_total_user 表中存在的用户opendis
	 * @param openids
	 * @return
	 */

	List<String> getFromDBByOpenids(List<String> openids);

	/**
	 *  获取 已关注且在 wechat_user_info 表中存在的用户opendis
	 * @param openids
	 * @return
	 */
	List<String> getUserFromDBByOpenids(List<String> openids);
	/**
	 * 根据mediaId获取图片素材
	 * @param mediaId
	 * @return
	 */
	WechatImage getWechatImageByMediaId(String mediaId);
	/**
	 * 获取所有图片素材
	 * @return
	 */
	List<WechatImage> getAllWechatImages();
	/**
	 * 分页获取图片素材
	 * @param page 第几页
	 * @param rows  每页多少行
	 * @return
	 */
	PageInfo<WechatImage> getWechatIamage(Integer page, Integer rows);
	
	
	/**
	 * 根据mediaId获取图文素材
	 * @param mediaId  图文素材mediaId
	 * @return
	 */
	WechatNews getWechatNewsByMediaId(String mediaId);
	/**
	 * 获取所有图文素材
	 * @return
	 */
	List<WechatNews> getAllWechatNews();
	/**
	 * 分页获取图文素材
	 * @param page  第几页
	 * @param rows  显示多少行
	 * @return
	 */
	PageInfo<WechatNews> getWechatNews(Integer page, Integer rows);
	/**
	 * 根据关键字获取文本回复消息
	 * @param key  关键字
	 * @return
	 */
	TextMessage getTextMessageByKey(String key);
	
	/**
	 * 根据关键字获取图片回复消息
	 * @param key 关键字
	 * @return
	 */
	ImageMessage getImageMessageByKey(String key);
	
	
	/**
	 * 根据关键字获取图文回复消息
	 * @param key 关键字
	 * @return
	 */
	NewsMessage getNewsMessageByKey(String key);

}
