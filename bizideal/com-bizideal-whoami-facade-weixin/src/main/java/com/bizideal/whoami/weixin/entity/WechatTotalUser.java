package com.bizideal.whoami.weixin.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 *记录 关注公众号的用户openid的表
 * @author zhu_shangjin
 * @version 2016年12月16日 下午4:09:32
 */
@Table(name="wechat_total_user")
public class WechatTotalUser extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
	//主键
	private  Integer id;
	//用户openid
	private String openid;
	

	public WechatTotalUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WechatTotalUser(Integer id, String openid) {
		super();
		this.id = id;
		this.openid = openid;
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
	
	

}
