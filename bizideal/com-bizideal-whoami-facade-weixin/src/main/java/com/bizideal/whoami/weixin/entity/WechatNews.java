package com.bizideal.whoami.weixin.entity;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 微信图文素材
 * @author zhu_shangjin
 * @version 2017年2月13日 上午11:22:03
 */
@Table(name = "wechat_news_item")
public class WechatNews extends BaseEntity {
	@Id
	// 图文素材 mediaId
	private String mediaId;
	//更新时间
	private long updateTime;
	
	@Transient
	// 图文素材的组成
	private List<WechatNewsItem> newsItems;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public List<WechatNewsItem> getNewsItems() {
		return newsItems;
	}
	public void setNewsItems(List<WechatNewsItem> newsItems) {
		this.newsItems = newsItems;
	}
	
}
