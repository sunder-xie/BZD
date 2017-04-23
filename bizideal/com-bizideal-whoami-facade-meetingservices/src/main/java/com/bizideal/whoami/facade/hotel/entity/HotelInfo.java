package com.bizideal.whoami.facade.hotel.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 酒店信息表
 * 
 * @ClassName HotelInfo
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
public class HotelInfo implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 2173318471882985653L;

	/**
	 * 酒店id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer hotelId;

	/**
	 * 酒店名称
	 */
	private String hotelName;

	/**
	 * 会议厅id
	 */
	private Integer meetingHallId;

	/**
	 * 会议id
	 */
	private Integer meetingId;

	/**
	 * 酒店地址
	 */
	private String address;

	/**
	 * 负责人
	 */
	private String person;

	/**
	 * 电话号码
	 */
	private String tel;

	/**
	 * 删除标记
	 */
	private String delFlag;

	/**
	 * 备注
	 */
	private String dsp;

	/**
	 * 房间总数
	 */
	@Transient
	private Integer roomSum;

	public HotelInfo() {
		super();
	}

	public HotelInfo(Integer hotelId, String hotelName, Integer meetingHallId, Integer meetingId, String address,
			String person, String tel, String delFlag, String dsp, Integer roomSum) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.meetingHallId = meetingHallId;
		this.meetingId = meetingId;
		this.address = address;
		this.person = person;
		this.tel = tel;
		this.delFlag = delFlag;
		this.dsp = dsp;
		this.roomSum = roomSum;
	}

	public Integer getRoomSum() {
		return roomSum;
	}

	public void setRoomSum(Integer roomSum) {
		this.roomSum = roomSum;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName == null ? null : hotelName.trim();
	}

	public Integer getMeetingHallId() {
		return meetingHallId;
	}

	public void setMeetingHallId(Integer meetingHallId) {
		this.meetingHallId = meetingHallId;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person == null ? null : person.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp == null ? null : dsp.trim();
	}

	@Override
	public String toString() {
		return "HotelInfo [hotelId=" + hotelId + ", hotelName=" + hotelName + ", meetingHallId=" + meetingHallId
				+ ", meetingId=" + meetingId + ", address=" + address + ", person=" + person + ", tel=" + tel
				+ ", delFlag=" + delFlag + ", dsp=" + dsp + ", roomSum=" + roomSum + "]";
	}
}