package com.bizideal.whoami.signup.entity;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @ClassName UserSignUpInfo
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-02 15:10:06
 */
@Table(name = "user_signup_info")
public class SignUpInfo extends BaseEntity {

	private static final long serialVersionUID = 6716428941346083205L;
	@Id
	private String id;// 自增id
	private Integer meethallId; // 会议厅id
	private Integer meetingId;// 主会议id
	private String email;// 邮箱
	private String disp;// 备注
	private String userId;// 用户id
	private Integer dietId;// 饮食表id
	private Long createDatatime;// 报名时间
	private Integer unitId;// 单位id
	private Integer identityId; // 身份id
	private String post; // 职位
	private String messageIds; // 消息id,可能有多个
	private String isPend; // 是否审核通过,0表示待审核，1表示审核通过，2表示审核被拒
	private String rejectReason; // 管理员拒绝审批理由

	@Transient
	private List<UserSignupSubmeeting> subMeetings;

	public SignUpInfo() {
		super();
	}

	public SignUpInfo(String id, Integer meethallId, Integer meetingId, String email, String disp, String userId, Integer dietId,
			Long createDatatime, Integer unitId, Integer identityId, String post, String messageIds, String isPend, String rejectReason,
			List<UserSignupSubmeeting> subMeetings) {
		super();
		this.id = id;
		this.meethallId = meethallId;
		this.meetingId = meetingId;
		this.email = email;
		this.disp = disp;
		this.userId = userId;
		this.dietId = dietId;
		this.createDatatime = createDatatime;
		this.unitId = unitId;
		this.identityId = identityId;
		this.post = post;
		this.messageIds = messageIds;
		this.isPend = isPend;
		this.rejectReason = rejectReason;
		this.subMeetings = subMeetings;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getMeethallId() {
		return meethallId;
	}

	public void setMeethallId(Integer meethallId) {
		this.meethallId = meethallId;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisp() {
		return disp;
	}

	public void setDisp(String disp) {
		this.disp = disp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getDietId() {
		return dietId;
	}

	public void setDietId(Integer dietId) {
		this.dietId = dietId;
	}

	public Long getCreateDatatime() {
		return createDatatime;
	}

	public void setCreateDatatime(Long createDatatime) {
		this.createDatatime = createDatatime;
	}

	public List<UserSignupSubmeeting> getSubMeetings() {
		return subMeetings;
	}

	public void setSubMeetings(List<UserSignupSubmeeting> subMeetings) {
		this.subMeetings = subMeetings;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getIdentityId() {
		return identityId;
	}

	public void setIdentityId(Integer identityId) {
		this.identityId = identityId;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getMessageIds() {
		return messageIds;
	}

	public void setMessageIds(String messageIds) {
		this.messageIds = messageIds;
	}

	public String getIsPend() {
		return isPend;
	}

	public void setIsPend(String isPend) {
		this.isPend = isPend;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Override
	public String toString() {
		return "SignUpInfo [id=" + id + ", meethallId=" + meethallId + ", meetingId=" + meetingId + ", email=" + email + ", disp=" + disp
				+ ", userId=" + userId + ", dietId=" + dietId + ", createDatatime=" + createDatatime + ", unitId=" + unitId + ", identityId="
				+ identityId + ", post=" + post + ", messageIds=" + messageIds + ", isPend=" + isPend + ", rejectReason=" + rejectReason
				+ ", subMeetings=" + subMeetings + "]";
	}

}
