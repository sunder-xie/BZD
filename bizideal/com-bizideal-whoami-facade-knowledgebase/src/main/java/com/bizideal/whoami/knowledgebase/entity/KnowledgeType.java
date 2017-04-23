package com.bizideal.whoami.knowledgebase.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

@Table(name = "knowledge_type")
public class KnowledgeType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8649296126261561504L;

	/**
	 * 知识库分类id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 分类名称
	 */
	private String type;

	/**
	 * 分类路径
	 */
	private String typeUrl;

	/**
	 * 区分子类/资料(0-未分类，1-子类，2-资料)
	 */
	private String isType;

	/**
	 * 会议id
	 */
	private Integer meeId;

	/**
	 * 父id
	 */
	private Integer parentId;

	/**
	 * 备注
	 */
	private String dsp;

	public KnowledgeType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KnowledgeType(Integer id, String type, String typeUrl, String isType, Integer meeId, Integer parentId,
			String dsp) {
		super();
		this.id = id;
		this.type = type;
		this.typeUrl = typeUrl;
		this.isType = isType;
		this.meeId = meeId;
		this.parentId = parentId;
		this.dsp = dsp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeUrl() {
		return typeUrl;
	}

	public void setTypeUrl(String typeUrl) {
		this.typeUrl = typeUrl;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType == null ? null : isType.trim();
	}

	public Integer getMeeId() {
		return meeId;
	}

	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", type=").append(type);
		sb.append(", typeUrl=").append(typeUrl);
		sb.append(", isType=").append(isType);
		sb.append(", meeId=").append(meeId);
		sb.append(", parentId=").append(parentId);
		sb.append(", dsp=").append(dsp);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}