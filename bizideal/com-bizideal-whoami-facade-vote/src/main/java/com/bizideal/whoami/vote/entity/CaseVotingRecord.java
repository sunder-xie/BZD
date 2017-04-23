package com.bizideal.whoami.vote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 案例投票记录
 * 
 * @ClassName CaseVotingRecord
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public class CaseVotingRecord extends BaseEntity {

	private static final long serialVersionUID = 2406654693917616494L;

	/**
	 * 案例投票记录id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 案例id
	 */
	private Integer caseId;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * IP地址
	 */
	private String ipAddress;

	/**
	 * 时间
	 */
	private Long time;

	/**
	 * 会议id
	 */
	private Integer meetingId;

	/**
	 * 用户姓名
	 */
	@Transient
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public CaseVotingRecord() {
		super();
	}

	public CaseVotingRecord(Integer id, Integer caseId, String userId, String ipAddress, Long time, Integer meetingId) {
		super();
		this.id = id;
		this.caseId = caseId;
		this.userId = userId;
		this.ipAddress = ipAddress;
		this.time = time;
		this.meetingId = meetingId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress == null ? null : ipAddress.trim();
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	@Override
	public String toString() {
		return "CaseVotingRecord [id=" + id + ", caseId=" + caseId + ", userId=" + userId + ", ipAddress=" + ipAddress
				+ ", time=" + time + ", meetingId=" + meetingId + ", userName=" + userName + "]";
	}
}