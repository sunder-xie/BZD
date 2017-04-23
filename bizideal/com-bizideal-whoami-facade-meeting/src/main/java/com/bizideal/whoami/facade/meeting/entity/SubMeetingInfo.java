package com.bizideal.whoami.facade.meeting.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseOpEntity;

/**
 * @ClassName MeetingInfo
 * @Description TODO(会议信息实体)
 * @Author Zj.Qu
 * @Date 2016-12-02 17:29:58
 */
@Table(name = "sub_meeting_info")
public class SubMeetingInfo extends BaseOpEntity {

	private static final long serialVersionUID = 6318939520465241010L;

	@Id
	@GeneratedValue(generator="JDBC")
	private Integer meeId;

	private Integer hallId;

	private String meeName;

	private Integer meeParentId;

	private String meeIntro;

	private String meeAddress;

	private String meeCover;

	private Integer meeWillnum;

	private Integer meeScale;

	private Long meeStartTime;

	private Long meeEndTime;

	private String meeContactName;

	private String meeContactNumber;

	private String meeContactEmail;

	private String meeType;

	private String meeTheme;

	public SubMeetingInfo() {

	}

	public SubMeetingInfo(Integer meeId, Integer hallId, String meeName, Integer meeParentId, String meeIntro,
			String meeAddress, String meeCover, Integer meeWillnum, Integer meeScale, Long meeStartTime,
			Long meeEndTime, String meeContactName, String meeContactNumber, String meeContactEmail, String meeType,
			String meeTheme) {
		this.meeId = meeId;
		this.hallId = hallId;
		this.meeName = meeName;
		this.meeParentId = meeParentId;
		this.meeIntro = meeIntro;
		this.meeAddress = meeAddress;
		this.meeCover = meeCover;
		this.meeWillnum = meeWillnum;
		this.meeScale = meeScale;
		this.meeStartTime = meeStartTime;
		this.meeEndTime = meeEndTime;
		this.meeContactName = meeContactName;
		this.meeContactNumber = meeContactNumber;
		this.meeContactEmail = meeContactEmail;
		this.meeType = meeType;
		this.meeTheme = meeTheme;
	}

	public Integer getMeeId() {
		return meeId;
	}

	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

	public Integer getHallId() {
		return hallId;
	}

	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}

	public String getMeeName() {
		return meeName;
	}

	public void setMeeName(String meeName) {
		this.meeName = meeName == null ? null : meeName.trim();
	}

	public Integer getMeeParentId() {
		return meeParentId;
	}

	public void setMeeParentId(Integer meeParentId) {
		this.meeParentId = meeParentId;
	}

	public String getMeeIntro() {
		return meeIntro;
	}

	public void setMeeIntro(String meeIntro) {
		this.meeIntro = meeIntro == null ? null : meeIntro.trim();
	}

	public String getMeeAddress() {
		return meeAddress;
	}

	public void setMeeAddress(String meeAddress) {
		this.meeAddress = meeAddress == null ? null : meeAddress.trim();
	}

	public String getMeeCover() {
		return meeCover;
	}

	public void setMeeCover(String meeCover) {
		this.meeCover = meeCover == null ? null : meeCover.trim();
	}

	public Integer getMeeWillnum() {
		return meeWillnum;
	}

	public void setMeeWillnum(Integer meeWillnum) {
		this.meeWillnum = meeWillnum;
	}

	public Integer getMeeScale() {
		return meeScale;
	}

	public void setMeeScale(Integer meeScale) {
		this.meeScale = meeScale;
	}

	public Long getMeeStartTime() {
		return meeStartTime;
	}

	public void setMeeStartTime(Long meeStartTime) {
		this.meeStartTime = meeStartTime;
	}

	public Long getMeeEndTime() {
		return meeEndTime;
	}

	public void setMeeEndTime(Long meeEndTime) {
		this.meeEndTime = meeEndTime;
	}

	public String getMeeContactName() {
		return meeContactName;
	}

	public void setMeeContactName(String meeContactName) {
		this.meeContactName = meeContactName == null ? null : meeContactName.trim();
	}

	public String getMeeContactNumber() {
		return meeContactNumber;
	}

	public void setMeeContactNumber(String meeContactNumber) {
		this.meeContactNumber = meeContactNumber == null ? null : meeContactNumber.trim();
	}

	public String getMeeContactEmail() {
		return meeContactEmail;
	}

	public void setMeeContactEmail(String meeContactEmail) {
		this.meeContactEmail = meeContactEmail == null ? null : meeContactEmail.trim();
	}

	public String getMeeType() {
		return meeType;
	}

	public void setMeeType(String meeType) {
		this.meeType = meeType == null ? null : meeType.trim();
	}

	public String getMeeTheme() {
		return meeTheme;
	}

	public void setMeeTheme(String meeTheme) {
		this.meeTheme = meeTheme == null ? null : meeTheme.trim();
	}

	@Override
	public String toString() {
		return "SubMeetingInfo [meeId=" + meeId + ", hallId=" + hallId
				+ ", meeName=" + meeName + ", meeParentId=" + meeParentId
				+ ", meeIntro=" + meeIntro + ", meeAddress=" + meeAddress
				+ ", meeCover=" + meeCover + ", meeWillnum=" + meeWillnum
				+ ", meeScale=" + meeScale + ", meeStartTime=" + meeStartTime
				+ ", meeEndTime=" + meeEndTime + ", meeContactName="
				+ meeContactName + ", meeContactNumber=" + meeContactNumber
				+ ", meeContactEmail=" + meeContactEmail + ", meeType="
				+ meeType + ", meeTheme=" + meeTheme + "]";
	}

}