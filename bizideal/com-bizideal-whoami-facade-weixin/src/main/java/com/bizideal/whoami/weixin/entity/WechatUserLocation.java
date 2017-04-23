package com.bizideal.whoami.weixin.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 用户地理位置信息
 * @author zhu_shangjin
 * @version 2016年12月16日 下午4:14:29
 */
@Table(name="wechat_user_location")
public class WechatUserLocation extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	//主键
    private Integer id;
	//用户unionid
	private String unionid;
	//用户 openid
	private String openid;
	// 创建时间
	private String createTime;
	//纬度
	private String latitude;
//	经度
	private String longitude;
	//精确度
	private String precisions;
	
	
	public WechatUserLocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPrecisions() {
		return precisions;
	}
	public void setPrecisions(String precisions) {
		this.precisions = precisions;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	

}
