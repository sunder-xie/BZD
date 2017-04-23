package com.bizideal.whoami.member.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 个人会员信息
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:05:25
 * @version 1.0
 */
public class MemberPersonInfo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1707371010021915764L;

	/**
	 * 个人会员id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 会议厅id
	 */
	private Integer hallId;

	/**
	 * 个人名称
	 */
	private String personName;

	/**
	 * 个人电话
	 */
	private String phone;

	/**
	 * 会员分类id
	 */
	private Integer memberTypeId;

	/**
	 * 备注
	 */
	private String dsp;

	/**
	 * 会员角色名称
	 */
	@Transient
	private String typeName;

	public MemberPersonInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberPersonInfo(Integer id, Integer hallId, String personName, String phone, Integer memberTypeId,
			String dsp, String typeName) {
		super();
		this.id = id;
		this.hallId = hallId;
		this.personName = personName;
		this.phone = phone;
		this.memberTypeId = memberTypeId;
		this.dsp = dsp;
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHallId() {
		return hallId;
	}

	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName == null ? null : personName.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getMemberTypeId() {
		return memberTypeId;
	}

	public void setMemberTypeId(Integer memberTypeId) {
		this.memberTypeId = memberTypeId;
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp == null ? null : dsp.trim();
	}

	@Override
	public String toString() {
		return "MemberPersonInfo [id=" + id + ", hallId=" + hallId + ", personName=" + personName + ", phone=" + phone
				+ ", memberTypeId=" + memberTypeId + ", dsp=" + dsp + ", typeName=" + typeName + "]";
	}
}