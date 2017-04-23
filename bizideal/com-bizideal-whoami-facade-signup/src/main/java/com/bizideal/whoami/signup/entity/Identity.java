package com.bizideal.whoami.signup.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年3月15日 下午5:51:32
 * @version 1.0
 * @description 身份实体类
 */
@Table(name = "identity")
public class Identity extends BaseEntity {

	private static final long serialVersionUID = -8494157383522442178L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/* 主会议id */
	private int meeId;

	/* 身份名称 */
	private String identity;

	public Identity() {
		super();
	}

	public Identity(int id, int meeId, String identity) {
		super();
		this.id = id;
		this.meeId = meeId;
		this.identity = identity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMeeId() {
		return meeId;
	}

	public void setMeeId(int meeId) {
		this.meeId = meeId;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "Identity [id=" + id + ", meeId=" + meeId + ", identity=" + identity + "]";
	}

}
