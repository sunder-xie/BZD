package com.bizideal.whoami.meetingdynamic.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 
 * @ClassName MeetingDynamicCrawler
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月11日
 */
@Table(name = "meeting_dynamic_crawler")
public class MeetingDynamicCrawler extends BaseEntity{

	private static final long serialVersionUID = -6312085439469205664L;
	
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer crawlerId;
	private Integer meetingId;
	private Integer meetHallId;
	private String userId;
	private String url;
	private String articleUrl;
	private Integer crawlerFlag;
	
	public MeetingDynamicCrawler() {
		
	}

	public MeetingDynamicCrawler(Integer crawlerId, Integer meetingId, Integer meetHallId, String userId, String url,
			String articleUrl,Integer crawlerFlag) {
		super();
		this.crawlerId = crawlerId;
		this.meetingId = meetingId;
		this.meetHallId = meetHallId;
		this.userId = userId;
		this.url = url;
		this.articleUrl=articleUrl;
		this.crawlerFlag = crawlerFlag;
	}

	public Integer getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(Integer crawlerId) {
		this.crawlerId = crawlerId;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Integer getMeetHallId() {
		return meetHallId;
	}

	public void setMeetHallId(Integer meetHallId) {
		this.meetHallId = meetHallId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCrawlerFlag() {
		return crawlerFlag;
	}

	public void setCrawlerFlag(Integer crawlerFlag) {
		this.crawlerFlag = crawlerFlag;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	@Override
	public String toString() {
		return "MeetingDynamicCrawler [crawlerId=" + crawlerId + ", meetingId="
				+ meetingId + ", meetHallId=" + meetHallId + ", userId="
				+ userId + ", url=" + url + ", articleUrl=" + articleUrl
				+ ", crawlerFlag=" + crawlerFlag + "]";
	}
	
}
