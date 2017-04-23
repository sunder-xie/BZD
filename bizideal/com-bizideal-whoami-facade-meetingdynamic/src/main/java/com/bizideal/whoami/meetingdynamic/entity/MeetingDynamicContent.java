package com.bizideal.whoami.meetingdynamic.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @ClassName MeetingDynamicContent
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 13:34:31
 */
@Table(name = "meeting_dynamic_content") 
public class MeetingDynamicContent extends BaseEntity{

	private static final long serialVersionUID = -6621327346414100402L;
	
	@Id
	@GeneratedValue(generator = "JDBC")
	//动态内容id
	private Integer contentId;
	//动态id
	private Integer dynamicId;
	//动态内容
	private String content;
	//会议id
	private Integer meetingId;
	//会议厅id
	private Integer meetHallId;
	
	public MeetingDynamicContent() {
		super();
	}
	public MeetingDynamicContent(Integer contentId, Integer dynamicId, String content, Integer meetingId,
			Integer meetHallId) {
		super(); 
		this.contentId = contentId;
		this.dynamicId = dynamicId;
		this.content = content;
		this.meetingId = meetingId;
		this.meetHallId = meetHallId;
	}
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public Integer getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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


	@Override
	public String toString() {
		return "MeetingDynamicContent [contentId=" + contentId + ", dynamicId=" + dynamicId + ", content=" + content
				+ ", meetingId=" + meetingId + ", meetHallId=" + meetHallId + "]";
	}
	
	
}
