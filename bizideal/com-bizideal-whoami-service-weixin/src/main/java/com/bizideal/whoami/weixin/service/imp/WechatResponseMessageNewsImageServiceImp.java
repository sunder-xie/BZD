package com.bizideal.whoami.weixin.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.weixin.dto.response.Article;
import com.bizideal.whoami.weixin.dto.response.ImageMessage;
import com.bizideal.whoami.weixin.dto.response.MediaId;
import com.bizideal.whoami.weixin.dto.response.NewsMessage;
import com.bizideal.whoami.weixin.dto.response.TextMessage;
import com.bizideal.whoami.weixin.entity.WechatImage;
import com.bizideal.whoami.weixin.entity.WechatNews;
import com.bizideal.whoami.weixin.entity.WechatNewsItem;
import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;
import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;
import com.bizideal.whoami.weixin.entity.WechatUserLocation;
import com.bizideal.whoami.weixin.mapper.WechatImageMapper;
import com.bizideal.whoami.weixin.mapper.WechatNewsItemMapper;
import com.bizideal.whoami.weixin.mapper.WechatNewsMapper;
import com.bizideal.whoami.weixin.mapper.WechatRequestMessageTextMapper;
import com.bizideal.whoami.weixin.mapper.WechatResponseMessageTextMapper;
import com.bizideal.whoami.weixin.service.WechatResponseMessageNewsImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author zhu_shangjin
 * @version 2017年2月14日 下午1:44:41
 */
@Service
public class WechatResponseMessageNewsImageServiceImp implements
		WechatResponseMessageNewsImageService {
	@Autowired
	WechatImageMapper wechatImageMapper;
	@Autowired
	WechatNewsItemMapper wechatNewsItemMapper;
	@Autowired
	WechatNewsMapper wechatNewsMapper;
	@Autowired
	WechatResponseMessageTextMapper wechatResponseMessageTextMapper;
	@Override
	public WechatImage getWechatImageByMediaId(String mediaId) {
		
		return wechatImageMapper.selectByPrimaryKey(mediaId);
	}

	@Override
	public List<WechatImage> getAllWechatImages() {
		
		return wechatImageMapper.selectAll();
	}

	@Override
	public PageInfo<WechatImage> getWechatIamage(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<WechatImage> list = wechatImageMapper.selectAll();
		PageInfo<WechatImage> pageInfo = new PageInfo<WechatImage>(list);
		return pageInfo;
	}

	@Override
	public WechatNews getWechatNewsByMediaId(String mediaId) {
		
		return wechatNewsMapper.getWechatNewsByMediaId(mediaId);
	}

	@Override
	public List<WechatNews> getAllWechatNews() {
		
		return wechatNewsMapper.getAllWechatNews();
	}

	@Override
	public PageInfo<WechatNews> getWechatNews(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<WechatNews> allnews = wechatNewsMapper.getNewsByPage();
		
		
		PageInfo<WechatNews> pageInfo = new PageInfo<WechatNews>(allnews);
		return pageInfo;
	}

	@Override
	public TextMessage getTextMessageByKey(String key) {
		WechatResponseMessageText responsetext = wechatResponseMessageTextMapper.getResponseByKeyWords(key);
		TextMessage textMessage = new TextMessage();
		textMessage.setContent(responsetext.getRespContent());
		return textMessage;
	}

	@Override
	public ImageMessage getImageMessageByKey(String key) {
		WechatImage wechatImage = wechatImageMapper.selectByPrimaryKey(key);
		MediaId mediaId = new MediaId();
		mediaId.setMediaId(wechatImage.getMediaId());
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setImage(mediaId);
		return imageMessage;
	}

	@Override
	public NewsMessage getNewsMessageByKey(String key) {
		WechatNews news = wechatNewsMapper.getWechatNewsByMediaId(key);
		List<WechatNewsItem> newsItems = news.getNewsItems();
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setArticleCount(newsItems.size());
		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		for (WechatNewsItem wechatNewsItem : newsItems) {
			article = new Article();
			article.setDescription(wechatNewsItem.getDigest());
			article.setTitle(wechatNewsItem.getTitle());
			article.setUrl(wechatNewsItem.getUrl());
			article.setPicUrl(wechatNewsItem.getThumbUrl());
			articles.add(article);
		}
		newsMessage.setArticles(articles);
		return newsMessage;
	}

	@Override
	public int insertWechatImage(WechatImage wechatImage) {
		int i = wechatImageMapper.insert(wechatImage);
		return i;
	}

	@Override
	public int batchInsertWechatImage(List<WechatImage> wechatImages) {
		int i = wechatImageMapper.batchsaveImage(wechatImages);
		return i;
	}

	@Override
	public int insertWechatNews(WechatNews wechatNews) {
		int i = wechatNewsMapper.insert(wechatNews);
		List<WechatNewsItem> wechatNewsItems = wechatNews.getNewsItems();
		i = wechatNewsItemMapper.batchInsertNewsItem(wechatNewsItems);
		return i;
	}

	@Override
	public int batchInsertWechatNews(List<WechatNews> wechatNews) {
		int i = wechatNewsMapper.batchInsertNews(wechatNews);
		List<WechatNewsItem> wechatNewsItems = new ArrayList<WechatNewsItem>();
		for (WechatNews wechatNews2 : wechatNews) {
			wechatNewsItems.addAll(wechatNews2.getNewsItems());
		}
		i = wechatNewsItemMapper.batchInsertNewsItem(wechatNewsItems);
		return i;
	}

}
