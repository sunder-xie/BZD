package com.bizideal.whoami.weixin.dto.response;

import java.util.List;
/**
 * 图文素材
 * @author pc
 *
 */
public class NewsMessage extends BaseMessage{
	// 图文消息个数，限制为10 条
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item 为大图
	private List<Article> Articles;
	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}
