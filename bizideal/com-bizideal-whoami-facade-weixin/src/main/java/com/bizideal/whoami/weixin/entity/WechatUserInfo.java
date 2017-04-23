package com.bizideal.whoami.weixin.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;
import com.google.gson.annotations.SerializedName;

/**
 * 关注公众号的用户表
 * @author zhu_shangjin
 * @version 2016年12月16日 下午3:57:02
 */
@Table(name = "wechat_user_info")
public class WechatUserInfo extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	private Integer id;
	// 用户是否订阅该公众号标识
	private String subscribe;
	// 用户的标识，对当前公众号唯一
	private String openid;
	// 用户的昵称
	private String nickname;
	// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private int sex;
	// 用户所在城市
	private String city;
	// 用户所在国家
	private String country;
	// 用户所在省份
	private String province;
	// 用户的语言，简体中文为zh_CN
	 @SerializedName(value = "language", alternate = {"language", "motherLanguage"})
	private String motherLanguage;
	// 用户头像，最后一个数值代表正方形头像大小
	private String headimgurl;
	// 用户关注时间，为时间戳。
	private String subscribeTime;
	// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	private String unionid;
	// 公众号运营者对粉丝的备注
	private String remark;
	// 用户所在的分组ID（暂时兼容用户分组旧接口）
	private int groupid;
	//对应到whoami注册用户的userid 需要通过用户手机绑定
	private String userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getMotherLanguage() {
		return motherLanguage;
	}
	public void setMotherLanguage(String motherLanguage) {
		this.motherLanguage = motherLanguage;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
