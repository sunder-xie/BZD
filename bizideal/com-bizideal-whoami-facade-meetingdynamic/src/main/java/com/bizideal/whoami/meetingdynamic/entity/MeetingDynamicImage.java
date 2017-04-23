package com.bizideal.whoami.meetingdynamic.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @ClassName MeetingDynamicImage
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 13:37:10
 */
@Table(name = "meeting_dynamic_image")
public class MeetingDynamicImage extends BaseEntity{

	private static final long serialVersionUID = 7918137657631013147L;
	
	@Id
	@GeneratedValue(generator = "JDBC")
	//会议动态图片id
	private Integer imageId;
	//会议动态id
	private Integer dynamicId;
	//会议动态图片url
	private String imageUrl;
	 
	public MeetingDynamicImage() {
		
	}

	public MeetingDynamicImage(Integer imageId, Integer dynamicId, String imageUrl) {
		super();
		this.imageId = imageId;
		this.dynamicId = dynamicId;
		this.imageUrl = imageUrl;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Integer getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "MeetingDynamicImage [imageId=" + imageId + ", dynamicId=" + dynamicId + ", imageUrl=" + imageUrl + "]";
	}
	
}
