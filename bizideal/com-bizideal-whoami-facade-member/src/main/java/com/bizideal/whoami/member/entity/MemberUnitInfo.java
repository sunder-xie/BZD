package com.bizideal.whoami.member.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 组织会员信息
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:06:05
 * @version 1.0
 */
public class MemberUnitInfo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1665280044572152171L;

	/**
	 * 组织会员id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 会议厅id
	 */
	private Integer hallId;

	/**
	 * 组织名称
	 */
	private String unitName;

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

	public MemberUnitInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberUnitInfo(Integer id, Integer hallId, String unitName, Integer memberTypeId, String dsp, String typeName) {
		super();
		this.id = id;
		this.hallId = hallId;
		this.unitName = unitName;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName == null ? null : unitName.trim();
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
		return "MemberUnitInfo [id=" + id + ", hallId=" + hallId + ", unitName=" + unitName + ", memberTypeId="
				+ memberTypeId + ", dsp=" + dsp + ", typeName=" + typeName + "]";
	}
}