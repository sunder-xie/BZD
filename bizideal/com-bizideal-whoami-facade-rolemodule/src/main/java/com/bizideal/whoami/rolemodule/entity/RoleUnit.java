package com.bizideal.whoami.rolemodule.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 
 * 
 * @author zhu_shangjin
 * @version 2016年12月13日 上午9:26:52 角色对应的单位报名所需的费用等信息
 */
@Table(name = "role_unit")
public class RoleUnit extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	private Integer roleUnitId; // 单位主键Id
	private Integer roleId; // 角色Id
	private String cost; // 费用
	private Integer peopleNumber; // 单位报名限制人数

	public RoleUnit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleUnit(Integer roleUnitId, Integer roleId, String cost, Integer peopleNumber) {
		super();
		this.roleUnitId = roleUnitId;
		this.roleId = roleId;
		this.cost = cost;
		this.peopleNumber = peopleNumber;
	}

	public Integer getRoleUnitId() {
		return roleUnitId;
	}

	public void setRoleUnitId(Integer roleUnitId) {
		this.roleUnitId = roleUnitId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Integer getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(Integer peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	@Override
	public String toString() {
		return "RoleUnit [roleUnitId=" + roleUnitId + ", roleId=" + roleId + ", cost=" + cost + ", peopleNumber=" + peopleNumber + "]";
	}

}
