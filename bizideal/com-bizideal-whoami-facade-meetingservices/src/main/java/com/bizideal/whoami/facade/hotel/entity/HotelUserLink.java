package com.bizideal.whoami.facade.hotel.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 酒店用户关联信息
 * 
 * @ClassName HotelUserLink
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
public class HotelUserLink implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5611750257936438856L;

	/**
	 * 酒店用户入住id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer hUId;

	/**
	 * 酒店id
	 */
	private Integer hotelId;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 会议id
	 */
	private Integer meetingId;

	/**
	 * 会议厅id
	 */
	private Integer meetingHallId;

	/**
	 * 房间id
	 */
	private Integer roomId;

	/**
	 * 备注
	 */
	private String dsp;

	/**
	 * 用户姓名
	 */
	@Transient
	private String userName;

	/**
	 * 组织名称
	 */
	@Transient
	private String unitName;

	/**
	 * 性别
	 */
	@Transient
	private String sex;

	/**
	 * 电话
	 */
	@Transient
	private String phone;

	/**
	 * 房间类型
	 */
	@Transient
	private String roomType;

	/**
	 * 酒店名称
	 */
	@Transient
	private String hotelName;

	@Override
	public String toString() {
		return "HotelUserLink [hUId=" + hUId + ", hotelId=" + hotelId + ", userId=" + userId + ", meetingId="
				+ meetingId + ", meetingHallId=" + meetingHallId + ", roomId=" + roomId + ", dsp=" + dsp
				+ ", userName=" + userName + ", unitName=" + unitName + ", sex=" + sex + ", phone=" + phone
				+ ", roomType=" + roomType + ", hotelName=" + hotelName + "]";
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public HotelUserLink(Integer hUId, Integer hotelId, String userId, Integer meetingId, Integer meetingHallId,
			Integer roomId, String dsp, String userName, String unitName, String sex, String phone, String roomType,
			String hotelName) {
		super();
		this.hUId = hUId;
		this.hotelId = hotelId;
		this.userId = userId;
		this.meetingId = meetingId;
		this.meetingHallId = meetingHallId;
		this.roomId = roomId;
		this.dsp = dsp;
		this.userName = userName;
		this.unitName = unitName;
		this.sex = sex;
		this.phone = phone;
		this.roomType = roomType;
		this.hotelName = hotelName;
	}

	public HotelUserLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer gethUId() {
		return hUId;
	}

	public void sethUId(Integer hUId) {
		this.hUId = hUId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Integer getMeetingHallId() {
		return meetingHallId;
	}

	public void setMeetingHallId(Integer meetingHallId) {
		this.meetingHallId = meetingHallId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp == null ? null : dsp.trim();
	}
}