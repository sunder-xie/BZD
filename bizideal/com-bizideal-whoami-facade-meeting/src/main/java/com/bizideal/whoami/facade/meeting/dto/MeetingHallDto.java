package com.bizideal.whoami.facade.meeting.dto;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseOpEntity;

/**
 * @ClassName MeetingHall
 * @Description TODO(会议厅实体类)
 * @Author Zj.Qu
 * @Date 2016-12-02 17:29:51
 */
public class MeetingHallDto extends BaseOpEntity {

	private static final long serialVersionUID = 4260759860625535843L;

	@Id
	private Integer hallId;

	/** 名称 **/
	private String hallName;

	/** 介绍 **/
	private String hallIntro;

	/** 封面头像 **/
	private String hallCover;

	/** 背景图**/
	private String hallBackImage;
	
	/** 主题 **/
	private String hallTheme;

	/** 类型(0:个人,1:组织) **/
	private String hallType;

	/** 用户 **/
	private String userId;

	/** 离开时间 **/
	private Long hallLeaveTime;

	/** 访问量 **/
	private Integer hallVisits;
	
	/** 会员类型:0个人,1是组织 **/
	private String hallVipType;

	/** 是否关注 **/
	@Transient
	private String isFollow;
	
	public MeetingHallDto() {

	}

	public MeetingHallDto(Integer hallId, String hallName, String hallIntro, String hallCover, String hallBackImage,
			String hallTheme, String hallType, String userId, Long hallLeaveTime, Integer hallVisits,
			String hallVipType) {
		super();
		this.hallId = hallId;
		this.hallName = hallName;
		this.hallIntro = hallIntro;
		this.hallCover = hallCover;
		this.hallBackImage = hallBackImage;
		this.hallTheme = hallTheme;
		this.hallType = hallType;
		this.userId = userId;
		this.hallLeaveTime = hallLeaveTime;
		this.hallVisits = hallVisits;
		this.hallVipType = hallVipType;
	}

	public Integer getHallId() {
		return hallId;
	}

	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName == null ? null : hallName.trim();
	}

	public String getHallIntro() {
		return hallIntro;
	}

	public void setHallIntro(String hallIntro) {
		this.hallIntro = hallIntro == null ? null : hallIntro.trim();
	}

	public String getHallBackImage() {
		return hallBackImage;
	}

	public void setHallBackImage(String hallBackImage) {
		this.hallBackImage = hallBackImage;
	}

	public String getHallCover() {
		return hallCover;
	}

	public void setHallCover(String hallCover) {
		this.hallCover = hallCover == null ? null : hallCover.trim();
	}

	public String getHallTheme() {
		return hallTheme;
	}

	public void setHallTheme(String hallTheme) {
		this.hallTheme = hallTheme == null ? null : hallTheme.trim();
	}

	public String getHallType() {
		return hallType;
	}

	public void setHallType(String hallType) {
		this.hallType = hallType == null ? null : hallType.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Long getHallLeaveTime() {
		return hallLeaveTime;
	}

	public void setHallLeaveTime(Long hallLeaveTime) {
		this.hallLeaveTime = hallLeaveTime;
	}

	public Integer getHallVisits() {
		return hallVisits;
	}

	public void setHallVisits(Integer hallVisits) {
		this.hallVisits = hallVisits;
	}

	public String getHallVipType() {
		return hallVipType;
	}

	public void setHallVipType(String hallVipType) {
		this.hallVipType = hallVipType;
	}

	public String getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(String isFollow) {
		this.isFollow = isFollow;
	}

	@Override
	public String toString() {
		return "MeetingHallDto [hallId=" + hallId + ", hallName=" + hallName
				+ ", hallIntro=" + hallIntro + ", hallCover=" + hallCover
				+ ", hallBackImage=" + hallBackImage + ", hallTheme="
				+ hallTheme + ", hallType=" + hallType + ", userId=" + userId
				+ ", hallLeaveTime=" + hallLeaveTime + ", hallVisits="
				+ hallVisits + ", hallVipType=" + hallVipType + ", isFollow="
				+ isFollow + "]";
	}
	
	
}