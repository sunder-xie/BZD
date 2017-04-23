package com.bizideal.whoami.vote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 案例信息
 * 
 * @ClassName CaseInfo
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public class CaseInfo extends BaseEntity {

	private static final long serialVersionUID = -2679809011945421687L;

	/**
	 * 案例id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 投票案例主题id
	 */
	private Integer caseVoteThemeId;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 投票数量
	 */
	@Transient
	private Integer number;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public CaseInfo() {
		super();
	}

	public CaseInfo(Integer id, Integer caseVoteThemeId, String province, String unit, String name, String url,
			String thumbImgUrl, Integer caseType, String userId, Byte delFlag) {
		super();
		this.id = id;
		this.caseVoteThemeId = caseVoteThemeId;
		this.province = province;
		this.unit = unit;
		this.name = name;
		this.url = url;
		this.thumbImgUrl = thumbImgUrl;
		this.caseType = caseType;
		this.userId = userId;
		this.delFlag = delFlag;
	}

	private String unit;

	private String name;

	private String url;

	private String thumbImgUrl;

	private Integer caseType;

	private String userId;

	private Byte delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseVoteThemeId() {
		return caseVoteThemeId;
	}

	public void setCaseVoteThemeId(Integer caseVoteThemeId) {
		this.caseVoteThemeId = caseVoteThemeId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getThumbImgUrl() {
		return thumbImgUrl;
	}

	public void setThumbImgUrl(String thumbImgUrl) {
		this.thumbImgUrl = thumbImgUrl == null ? null : thumbImgUrl.trim();
	}

	public Integer getCaseType() {
		return caseType;
	}

	public void setCaseType(Integer caseType) {
		this.caseType = caseType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "CaseInfo [id=" + id + ", caseVoteThemeId=" + caseVoteThemeId + ", province=" + province + ", number="
				+ number + ", unit=" + unit + ", name=" + name + ", url=" + url + ", thumbImgUrl=" + thumbImgUrl
				+ ", caseType=" + caseType + ", userId=" + userId + ", delFlag=" + delFlag + "]";
	}
}