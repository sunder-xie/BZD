package com.bizideal.whoami.facade.hotel.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 酒店房间信息
 * 
 * @ClassName RoomInfo
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
public class RoomInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 865563577995746497L;

	/**
	 * 房间id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer roomId;
	/**
	 * 酒店id
	 */
	private Integer hotelId;

	/**
	 * 房间类型
	 */
	private String roomType;

	/**
	 * 房间数量
	 */
	private Integer roomNumber;

	/**
	 * 房间预留
	 */
	private Integer roomSpare;

	/**
	 * 房间预订数
	 */
	private Integer normalReservedNum;

	/**
	 * 预留房间预订数
	 */
	private Integer spareReservedNum;

	/**
	 * 费用
	 */
	private Double cost;

	/**
	 * 备注
	 */
	private String dsp;

	/**
	 * 删除标记
	 */
	private String delFlag;

	public RoomInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomInfo(Integer roomId, Integer hotelId, String roomType, Integer roomNumber, Integer roomSpare,
			Integer normalReservedNum, Integer spareReservedNum, Double cost, String dsp, String delFlag) {
		super();
		this.roomId = roomId;
		this.hotelId = hotelId;
		this.roomType = roomType;
		this.roomNumber = roomNumber;
		this.roomSpare = roomSpare;
		this.normalReservedNum = normalReservedNum;
		this.spareReservedNum = spareReservedNum;
		this.cost = cost;
		this.dsp = dsp;
		this.delFlag = delFlag;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getRoomSpare() {
		return roomSpare;
	}

	public void setRoomSpare(Integer roomSpare) {
		this.roomSpare = roomSpare;
	}

	public Integer getNormalReservedNum() {
		return normalReservedNum;
	}

	public void setNormalReservedNum(Integer normalReservedNum) {
		this.normalReservedNum = normalReservedNum;
	}

	public Integer getSpareReservedNum() {
		return spareReservedNum;
	}

	public void setSpareReservedNum(Integer spareReservedNum) {
		this.spareReservedNum = spareReservedNum;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RoomInfo [roomId=" + roomId + ", hotelId=" + hotelId + ", roomType=" + roomType + ", roomNumber="
				+ roomNumber + ", roomSpare=" + roomSpare + ", normalReservedNum=" + normalReservedNum
				+ ", spareReservedNum=" + spareReservedNum + ", cost=" + cost + ", dsp=" + dsp + ", delFlag=" + delFlag
				+ "]";
	}

}