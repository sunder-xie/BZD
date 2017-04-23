package com.bizideal.whoami.rolemodule.entity;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者zhushangjin: 会议厅角色表
 * @date 创建时间：2016年12月5日 下午1:44:47
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Table(name = "hall_role")
public class HallRole extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	// 会议厅角色id
	private Integer roleId;
	// 会议厅角色名称
	private String roleName;
	// 会议厅角色类型 joiner manager
	private String roleType;
	// 会议厅id
	private Integer meetHallId;
	// 会议厅角色备注
	private String roleRemark;
	// 会议厅角色状态 default为系统默认创建的角色
	private String roleStatus;
	// 该角色可使用的功能模块

	@Transient
	private String functionsString; // 功能模块的字符串

	@Transient
	private List<HallFunction> functions;
	// 该角色可使用功能模块的id集合
	@Transient
	private List<Integer> functionIds;

	public HallRole() {
		super();

	}

	public String getFunctionsString() {
		return functionsString;
	}

	public void setFunctionsString(String functionsString) {
		this.functionsString = functionsString;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Integer getMeetHallId() {
		return meetHallId;
	}

	public void setMeetHallId(Integer meetHallId) {
		this.meetHallId = meetHallId;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public List<HallFunction> getFunctions() {
		return functions;
	}

	public void setFunctions(List<HallFunction> functions) {
		this.functions = functions;
	}

	public List<Integer> getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(List<Integer> functionIds) {
		this.functionIds = functionIds;
	}

	@Override
	public String toString() {
		return "HallRole [roleId=" + roleId + ", roleName=" + roleName + ", roleType=" + roleType + ", meetHallId=" + meetHallId + ", roleRemark=" + roleRemark + ", roleStatus=" + roleStatus + ", functionsString=" + functionsString + ", functions=" + functions + ", functionIds=" + functionIds + "]";
	}

}
