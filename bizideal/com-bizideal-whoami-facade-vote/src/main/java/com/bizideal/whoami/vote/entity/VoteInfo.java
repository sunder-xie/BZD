package com.bizideal.whoami.vote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 投票信息
 * 
 * @ClassName VoteInfo
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public class VoteInfo extends BaseEntity {

	private static final long serialVersionUID = -6169035508660640362L;

	/**
	 * 投票信息id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 会议id
	 */
	private Integer meetingId;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 备注
	 */
	private String dsp;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 开始时间
	 */
	private Long startTime;

	/**
	 * 结束时间
	 */
	private Long endTime;

	/**
	 * 创建用户id
	 */
	private String createUserId;

	/**
	 * 删除标记
	 */
	private Byte delFlag;

	/**
	 * 清空标记
	 */
	private Byte emptyFlag;

	public VoteInfo() {
		super();
	}

	public VoteInfo(Integer id, Integer meetingId, Integer type, String dsp, Long createTime, Long startTime,
			Long endTime, String createUserId, Byte delFlag, Byte emptyFlag) {
		super();
		this.id = id;
		this.meetingId = meetingId;
		this.type = type;
		this.dsp = dsp;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createUserId = createUserId;
		this.delFlag = delFlag;
		this.emptyFlag = emptyFlag;
	}

	public Byte getEmptyFlag() {
		return emptyFlag;
	}

	public void setEmptyFlag(Byte emptyFlag) {
		this.emptyFlag = emptyFlag;
	}

	public Integer getId() {
		return id;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp == null ? null : dsp.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long entTime) {
		this.endTime = entTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId == null ? null : createUserId.trim();
	}

	public Byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "VoteInfo [id=" + id + ", meetingId=" + meetingId + ", type=" + type + ", dsp=" + dsp + ", createTime="
				+ createTime + ", startTime=" + startTime + ", endTime=" + endTime + ", createUserId=" + createUserId
				+ ", delFlag=" + delFlag + ", emptyFlag=" + emptyFlag + "]";
	}
}