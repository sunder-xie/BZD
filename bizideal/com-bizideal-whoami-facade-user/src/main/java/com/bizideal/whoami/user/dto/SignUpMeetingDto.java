package com.bizideal.whoami.user.dto;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月17日 下午4:12:58
 * @version 1.0 查找用户报名信息时接收请求参数
 */
public class SignUpMeetingDto extends BaseEntity {

	private static final long serialVersionUID = -1376375206428019840L;
	private Integer meeId; // 会议id
	private String name; // 模糊查询字段，代表真实姓名或者手机号

	public SignUpMeetingDto() {
		super();
	}

	public SignUpMeetingDto(Integer meeId, String name) {
		super();
		this.meeId = meeId;
		this.name = name;
	}

	public Integer getMeeId() {
		return meeId;
	}

	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SignUpMeetingDto [meeId=" + meeId + ", name=" + name + "]";
	}

}
