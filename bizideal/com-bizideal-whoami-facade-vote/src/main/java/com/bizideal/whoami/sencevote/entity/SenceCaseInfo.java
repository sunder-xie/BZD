package com.bizideal.whoami.sencevote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 现场案例信息
 * 
 * @ClassName SenceCaseInfo
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月9日
 */
public class SenceCaseInfo extends BaseEntity {

	private static final long serialVersionUID = 6430462449648088347L;

	/**
	 * 现场案例id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 投票id
	 */
	private Integer voteId;

	/**
	 * 会议id
	 */
	@Transient
	private Integer meeId;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 组织名称
	 */
	private String unit;

	/**
	 * 案例名称
	 */
	private String name;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 删除标记
	 */
	private Byte delFlag;

	/**
	 * 投票总数
	 */
	@Transient
	private Integer number;

	public SenceCaseInfo() {
		super();
	}

	public SenceCaseInfo(Integer id, Integer voteId, Integer meeId, String province, String unit, String name,
			String userId, Byte delFlag, Integer number) {
		super();
		this.id = id;
		this.voteId = voteId;
		this.meeId = meeId;
		this.province = province;
		this.unit = unit;
		this.name = name;
		this.userId = userId;
		this.delFlag = delFlag;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
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

	public Integer getMeeId() {
		return meeId;
	}

	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "SenceCaseInfo [id=" + id + ", voteId=" + voteId + ",meeId=" + meeId + ", province=" + province
				+ ", unit=" + unit + ", name=" + name + ", userId=" + userId + ", delFlag=" + delFlag + ", number="
				+ number + "]";
	}
}