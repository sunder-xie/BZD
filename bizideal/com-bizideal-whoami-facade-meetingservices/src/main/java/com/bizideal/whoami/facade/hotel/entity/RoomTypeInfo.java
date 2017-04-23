package com.bizideal.whoami.facade.hotel.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 房间类型信息（废用）
 * 
 * @ClassName RoomTypeInfo
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
public class RoomTypeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2298878226563264618L;

	/**
	 * 房间类型id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer roomTypeId;

	/**
	 * 房间类型名称
	 */
	private String roomTypeName;

	/**
	 * 备注
	 */
	private String dsp;

	/**
	 * 删除标记
	 */
	private String delFlag;

	@Override
	public String toString() {
		return "RoomTypeInfo [roomTypeId=" + roomTypeId + ", delFlag=" + delFlag + ", roomTypeName=" + roomTypeName
				+ ", dsp=" + dsp + "]";
	}

	public RoomTypeInfo() {
		super();
	}

	public RoomTypeInfo(Integer roomTypeId, String roomTypeName, String dsp, String delFlag) {
		super();
		this.roomTypeId = roomTypeId;
		this.roomTypeName = roomTypeName;
		this.dsp = dsp;
		this.delFlag = delFlag;
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName == null ? null : roomTypeName.trim();
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp == null ? null : dsp.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}