package com.bizideal.whoami.user.entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 上午10:56:11
 * @version 1.0 微信或系统用户基本信息类
 */
@Table(name = "user_weixin_info")
public class UserWeixinInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String userId;// 用户id
	private String unionid;// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	private String openid;// 普通用户的标识，对当前公众号唯一
	private String subscribe;// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String realName;// 真实姓名
	private String nickname;// 昵称
	private String sex; // 性别
	private String email;// 用户默认邮箱
	private String headimgurl;// 头像地址
	private String country; // 国家
	private String province;// 省份
	private String city;// 城市
	private Long subscribeTime;// 注册时间
	private String language;// 语言
	private String remark;// 系统管理员给用户的备注
	private String groupid;// 群组ID
	private String weixin;// 用户微信账号
	@Transient
	private String phone; // 手机号
	@Transient
	private String password; // 登陆密码
	@Transient
	private String msgCode;// 验证码
	@Transient
	private String redirect;// 重定向地址
	@Transient
	private String type;
	@Transient
	private String isFriend;// 是否是好友
	@Transient
	private String loginType;// 登陆类型

	public UserWeixinInfo() {
		super();
	}

	public UserWeixinInfo(String userId, String unionid, String openid,
			String subscribe, String realName, String nickname, String sex,
			String email, String headimgurl, String country, String province,
			String city, Long subscribeTime, String language, String remark,
			String groupid, String weixin, String phone, String password,
			String msgCode, String redirect, String type, String isFriend,
			String loginType) {
		super();
		this.userId = userId;
		this.unionid = unionid;
		this.openid = openid;
		this.subscribe = subscribe;
		this.realName = realName;
		this.nickname = nickname;
		this.sex = sex;
		this.email = email;
		this.headimgurl = headimgurl;
		this.country = country;
		this.province = province;
		this.city = city;
		this.subscribeTime = subscribeTime;
		this.language = language;
		this.remark = remark;
		this.groupid = groupid;
		this.weixin = weixin;
		this.phone = phone;
		this.password = password;
		this.msgCode = msgCode;
		this.redirect = redirect;
		this.type = type;
		this.isFriend = isFriend;
		this.loginType = loginType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(String isFriend) {
		this.isFriend = isFriend;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Override
	public String toString() {
		return "UserWeixinInfo [userId=" + userId + ", unionid=" + unionid
				+ ", openid=" + openid + ", subscribe=" + subscribe
				+ ", realName=" + realName + ", nickname=" + nickname
				+ ", sex=" + sex + ", email=" + email + ", headimgurl="
				+ headimgurl + ", country=" + country + ", province="
				+ province + ", city=" + city + ", subscribeTime="
				+ subscribeTime + ", language=" + language + ", remark="
				+ remark + ", groupid=" + groupid + ", weixin=" + weixin
				+ ", phone=" + phone + ", password=" + password + ", msgCode="
				+ msgCode + ", redirect=" + redirect + ", type=" + type
				+ ", isFriend=" + isFriend + ", loginType=" + loginType + "]";
	}

}
