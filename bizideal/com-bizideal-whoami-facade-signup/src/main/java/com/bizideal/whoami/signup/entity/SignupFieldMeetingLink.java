package com.bizideal.whoami.signup.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月15日 上午10:51:34
 * @version 1.0 报名字段和会议关联表
 */
@Table(name = "signup_field_meeting_link")
public class SignupFieldMeetingLink extends BaseEntity {

	private static final long serialVersionUID = 6110385387702207450L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 自增id
	private int meeId; // 主会议id
	private String fieldIds; // 该主会议选择的所有报名字段

	public SignupFieldMeetingLink() {
		super();
	}

	public SignupFieldMeetingLink(int id, int meeId, String fieldIds) {
		super();
		this.id = id;
		this.meeId = meeId;
		this.fieldIds = fieldIds;
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

	public String getFieldIds() {
		return fieldIds;
	}

	public void setFieldIds(String fieldIds) {
		this.fieldIds = fieldIds;
	}

	@Override
	public String toString() {
		return "SignupFieldMeetingLink [id=" + id + ", meeId=" + meeId
				+ ", fieldIds=" + fieldIds + "]";
	}

}
