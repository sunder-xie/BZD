package com.bizideal.whoami.sencevote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 现场案例投票记录
 * 
 * @ClassName SenceCaseVotingRecord
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月9日
 */
public class SenceCaseVotingRecord extends BaseEntity {

	private static final long serialVersionUID = -7925221166805675433L;

	/**
	 * 现场案例投票记录id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 现场案例id
	 */
	private Integer senceCaseId;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 投票时间
	 */
	private Long time;

	/**
	 * 会议id
	 */
	private Integer meetingId;

	public SenceCaseVotingRecord() {
		super();
	}

	public SenceCaseVotingRecord(Integer id, Integer senceCaseId, String userId, Long time, Integer meetingId) {
		super();
		this.id = id;
		this.senceCaseId = senceCaseId;
		this.userId = userId;
		this.time = time;
		this.meetingId = meetingId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSenceCaseId() {
		return senceCaseId;
	}

	public void setSenceCaseId(Integer senceCaseId) {
		this.senceCaseId = senceCaseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
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
		return "SenceCaseVotingRecord [id=" + id + ", senceCaseId=" + senceCaseId + ", userId=" + userId + ", time="
				+ time + ", meetingId=" + meetingId + "]";
	}
}