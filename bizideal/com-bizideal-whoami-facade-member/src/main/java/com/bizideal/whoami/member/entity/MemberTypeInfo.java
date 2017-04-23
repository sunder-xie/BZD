package com.bizideal.whoami.member.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 会员分类信息
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:05:47
 * @version 1.0
 */
public class MemberTypeInfo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1277702981145162511L;

	/**
	 * 会员分类id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 会议厅id
	 */
	private Integer hallId;

	/**
	 * 分类名称
	 */
	private String name;

	/**
	 * 分类(0-组织，1-个人)
	 */
	private Integer type;

	public MemberTypeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberTypeInfo(Integer id, Integer hallId, String name, Integer type) {
		super();
		this.id = id;
		this.hallId = hallId;
		this.name = name;
		this.type = type;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 
	 * @return 0-组织，1-个人
	 */
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", hallId=").append(hallId);
		sb.append(", name=").append(name);
		sb.append(", type=").append(type);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}