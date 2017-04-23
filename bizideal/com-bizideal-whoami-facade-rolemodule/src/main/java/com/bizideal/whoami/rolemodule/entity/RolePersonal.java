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
 * @version 2016年12月13日 上午9:21:02 角色对应的个人报名需要的费用等信息
 */
@Table(name = "role_personal")
public class RolePersonal extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	private Integer rolePersonalId; // 个人主键Id
	private Integer roleId; // 角色Id
	private String cost; // 个人会员费用

	public RolePersonal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RolePersonal(Integer rolePersonalId, Integer roleId, String cost) {
		super();
		this.rolePersonalId = rolePersonalId;
		this.roleId = roleId;
		this.cost = cost;
	}

	public Integer getRolePersonalId() {
		return rolePersonalId;
	}

	public void setRolePersonalId(Integer rolePersonalId) {
		this.rolePersonalId = rolePersonalId;
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

	@Override
	public String toString() {
		return "RolePersonal [rolePersonalId=" + rolePersonalId + ", roleId=" + roleId + ", cost=" + cost + "]";
	}

}
