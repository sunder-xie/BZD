package com.bizideal.whoami.weixin.service;

import java.util.List;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.weixin.dto.response.ImageMessage;
import com.bizideal.whoami.weixin.dto.response.NewsMessage;
import com.bizideal.whoami.weixin.dto.response.TextMessage;
import com.bizideal.whoami.weixin.entity.WechatImage;
import com.bizideal.whoami.weixin.entity.WechatNews;
import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;
import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;
import com.bizideal.whoami.weixin.entity.WechatUserLocation;
import com.github.pagehelper.PageInfo;


/**
 * @author zhu_shangjin
 * @version 2016年12月22日 下午12:50:23
 */
public interface WechatResponseMessageNewsImageService {
	
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
	 * @param page  第几页
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
	 * @param page 第几页
	 * @param rows 每页多少行
	 * @return
	 */
	PageInfo<WechatNews> getWechatNews(Integer page, Integer rows);
	/**
	 * 根据关键字获取文本回复消息
	 * @param key 关键字
	 * @return
	 */
	TextMessage getTextMessageByKey(String key);
	
	/**
	 * 根据关键字获取图片回复消息
	 * @param key  关键字
	 * @return
	 */
	ImageMessage getImageMessageByKey(String key);
	
	
	/**
	 * 根据关键字获取图文回复消息
	 * @param key  关键字
	 * @return
	 */
	NewsMessage getNewsMessageByKey(String key);
	
	/**
	 * 新增图片素材
	 * @param wechatImage
	 * @return
	 */
	int insertWechatImage(WechatImage wechatImage) throws Exception;
	/**
	 * 批量新增图片素材
	 * @return
	 */
	int batchInsertWechatImage(List<WechatImage> wechatImages) throws Exception;
	/**
	 * 新增图文素材
	 * @param wechatNews
	 * @return
	 */
	int insertWechatNews(WechatNews wechatNews) throws Exception;
	/**
	 * 批量新增图文素材
	 * @param wechatNews
	 * @return
	 */
	int batchInsertWechatNews(List<WechatNews> wechatNews) throws Exception;
	

}
