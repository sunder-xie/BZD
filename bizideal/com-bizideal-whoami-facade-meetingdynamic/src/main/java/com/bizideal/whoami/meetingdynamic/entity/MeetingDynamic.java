package com.bizideal.whoami.meetingdynamic.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseOpEntity;

/**
 * @ClassName MeetingDynamic
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 13:17:27
 */
@Table(name = "meeting_dynamic") 
public class MeetingDynamic extends BaseOpEntity{

	private static final long serialVersionUID = -656528184589864691L;
	
	@Id
	@GeneratedValue(generator = "JDBC")
	//动态id
	private Integer dynamicId;
	//会议id
	private Integer meetingId;
	//会议厅id
	private Integer meetHallId;
	//用户id
	private String userId;
	//动态标题
	private String dynamicTitle;
	//动态图片url
	private String dynamicUrl;
	//动态类型
	private Integer type;
	
	@Transient
	private Integer contentId;
	@Transient
	private String content;
	@Transient
	private String imageUrl;
	
	public MeetingDynamic() {
		
	}
		
	public MeetingDynamic(Integer dynamicId, Integer meetingId,
			Integer meetHallId, String userId, String dynamicTitle,
			String dynamicUrl, Integer type, Integer contentId, String content,
			String imageUrl) {
		super();
		this.dynamicId = dynamicId;
		this.meetingId = meetingId;
		this.meetHallId = meetHallId;
		this.userId = userId;
		this.dynamicTitle = dynamicTitle;
		this.dynamicUrl = dynamicUrl;
		this.type = type;
		this.contentId = contentId;
		this.content = content;
		this.imageUrl = imageUrl;
	}

	public Integer getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
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

	public String getDynamicTitle() {
		return dynamicTitle;
	}

	public void setDynamicTitle(String dynamicTitle) {
		this.dynamicTitle = dynamicTitle;
	}

	public String getDynamicUrl() {
		return dynamicUrl;
	}

	public void setDynamicUrl(String dynamicUrl) {
		this.dynamicUrl = dynamicUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MeetingDynamic [dynamicId=" + dynamicId + ", meetingId="
				+ meetingId + ", meetHallId=" + meetHallId + ", userId="
				+ userId + ", dynamicTitle=" + dynamicTitle + ", dynamicUrl="
				+ dynamicUrl + ", type=" + type + ", contentId=" + contentId
				+ ", content=" + content + ", imageUrl=" + imageUrl + "]";
	}
}
