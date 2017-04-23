package com.bizideal.whoami.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @ClassName UserSignUpInfoDto
 * @Description 修改报名信息传输类
 * @Author li.peng
 * @Date 2016-12-06 12:53:27
 */
public class SignUpInfoEditDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String type = "SignUpEdit";
	private String signUpId; // 报名主业务id
	private Integer meethallId; // 会议厅id
	private Integer meetingId; // 主会议id
	private Integer[] submeetingId; // 子会议id
	private String email;//邮箱
	private String disp; // 备注
	private String userId;// 用户id
	private Integer dietId; // 饮食id，关联diet_info表
	private String realName;  //真实姓名
	private String sex; // 性别，男或女
	private String phone;  // 手机号
	private Integer hotelId; // 酒店id
	private Integer roomId; // 房间id
	private Long createDatatime; // 报名时间
	private Integer unitId;// 单位id
	private Integer identityId; // 身份id
	private String post; // 职位
	private String messageIds; // 消息id,可能有多个	
	private String isPend; // 是否审核通过,0表示待审核，1表示审核通过，2表示审核被拒
	
	//刘立庆 20170320 
	private String messageId; // 消息id，业务执行成功后删除消息用
	private String rejectReason; // 管理员拒绝审批理由
	
	

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public SignUpInfoEditDto(String signUpId, Integer meethallId,
			Integer meetingId, Integer[] submeetingId, String email,
			String disp, String userId, Integer dietId, String realName,
			String sex, String phone, Integer hotelId, Integer roomId,
			Long createDatatime, Integer unitId, Integer identityId,
			String post, String messageIds, String isPend) {
		super();
		this.signUpId = signUpId;
		this.meethallId = meethallId;
		this.meetingId = meetingId;
		this.submeetingId = submeetingId;
		this.email = email;
		this.disp = disp;
		this.userId = userId;
		this.dietId = dietId;
		this.realName = realName;
		this.sex = sex;
		this.phone = phone;
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.createDatatime = createDatatime;
		this.unitId = unitId;
		this.identityId = identityId;
		this.post = post;
		this.messageIds = messageIds;
		this.isPend = isPend;
	}

	public SignUpInfoEditDto() {

	}
	
	public String getType() {
		return type;
	}
	public String getSignUpId() {
		return signUpId;
	}

	public void setSignUpId(String signUpId) {
		this.signUpId = signUpId;
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

	public Integer[] getSubmeetingId() {
		return submeetingId;
	}

	public void setSubmeetingId(Integer[] submeetingId) {
		this.submeetingId = submeetingId;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getCreateDatatime() {
		return createDatatime;
	}

	public void setCreateDatatime(Long createDatatime) {
		this.createDatatime = createDatatime;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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
	
	@Override
	public String toString() {
		return "SignUpInfoEditDto [type=" + type + ", signUpId=" + signUpId
				+ ", meethallId=" + meethallId + ", meetingId=" + meetingId
				+ ", submeetingId=" + Arrays.toString(submeetingId)
				+ ", email=" + email + ", disp=" + disp + ", userId=" + userId
				+ ", dietId=" + dietId + ", realName=" + realName + ", sex="
				+ sex + ", phone=" + phone + ", hotelId=" + hotelId
				+ ", roomId=" + roomId + ", createDatatime=" + createDatatime
				+ ", unitId=" + unitId + ", identityId=" + identityId
				+ ", post=" + post + ", messageIds=" + messageIds + ", isPend="
				+ isPend + "]";
	}

	
}
