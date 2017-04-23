package com.bizideal.whoami.signup.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseEntity;

public class UserSignupSubmeeting extends BaseEntity {

	private static final long serialVersionUID = 6881797802350541633L;
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id; // 自增id
	private String signupId;// 报名表id
	private Integer submeetingId;// 子会议id
	private long createDatatime;// 创建时间
	private String dsp;// 备注

	public UserSignupSubmeeting() {
		super();
	}

	public UserSignupSubmeeting(Integer id, String signupId,
			Integer submeetingId, long createDatatime, String dsp) {
		super();
		this.id = id;
		this.signupId = signupId;
		this.submeetingId = submeetingId;
		this.createDatatime = createDatatime;
		this.dsp = dsp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSignupId() {
		return signupId;
	}

	public void setSignupId(String signupId) {
		this.signupId = signupId;
	}

	public Integer getSubmeetingId() {
		return submeetingId;
	}

	public void setSubmeetingId(Integer submeetingId) {
		this.submeetingId = submeetingId;
	}

	public long getCreateDatatime() {
		return createDatatime;
	}

	public void setCreateDatatime(long createDatatime) {
		this.createDatatime = createDatatime;
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp;
	}

	@Override
	public String toString() {
		return "UserSignupSubmeeting [id=" + id + ", signupId=" + signupId
				+ ", submeetingId=" + submeetingId + ", createDatatime="
				+ createDatatime + ", dsp=" + dsp + "]";
	}

}
