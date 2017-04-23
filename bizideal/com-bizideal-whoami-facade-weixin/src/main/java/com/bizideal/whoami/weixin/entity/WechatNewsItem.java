package com.bizideal.whoami.weixin.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 图文素材的组成
 * @author zhu_shangjin
 * @version 2017年2月13日 上午11:22:03
 */
@Table(name = "wechat_news_item")
public class WechatNewsItem extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	//主键 
	private int itemId;
	//标题
	private String title;
	//作者
	private String author;
	// 描述
	private String digest;
	//内容
	private String content;
	//原文链接
	private String contentSourceUrl;
	//缩略图mediaid
	private String thumbMediaId;
	// 是否显示图片
	private int showCoverPic;
	//图片url
	private String url;
	// 缩略图url
	private String thumbUrl;
	//素材所在图文素材的位置
	private int indexs;
	//图文素材的MediaId
	private String newsMediaId;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentSourceUrl() {
		return contentSourceUrl;
	}
	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public int getShowCoverPic() {
		return showCoverPic;
	}
	public void setShowCoverPic(int showCoverPic) {
		this.showCoverPic = showCoverPic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	public int getIndexs() {
		return indexs;
	}
	public void setIndexs(int indexs) {
		this.indexs = indexs;
	}
	public String getNewsMediaId() {
		return newsMediaId;
	}
	public void setNewsMediaId(String newsMediaId) {
		this.newsMediaId = newsMediaId;
	}
	

}
