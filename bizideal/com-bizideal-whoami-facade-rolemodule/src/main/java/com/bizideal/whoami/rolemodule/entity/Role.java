package com.bizideal.whoami.rolemodule.entity;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者zhushangjin: 会议角色表
 * @date 创建时间：2016年12月5日 下午1:44:47
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Table(name = "role")
public class Role extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	// 角色id
	private Integer roleId;
	// 角色名称
	private String roleName;
	// 角色类型 joiner manager
	private String roleType;
	// 会议厅id
	private Integer meetHallId;
	// 角色备注
	private String roleRemark;
	// 主会议id
	private Integer meetId;
	// 组织和个人的Id
	private Integer member_type_id;
	// type用于区分组织和个人
	private Integer type;

	@Transient
	private List<Module> modules; // 该角色所拥有的权限模块

	@Transient
	private String modulesListString;// 角色模块的模块id

	@Transient
	private List<Integer> moduleIds; // 该角色所拥有的权限模块的id集合

	private String roleStatus; // 角色状态 default为系统默认创建的角色

	public Role() {
		super();

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

	public Integer getMeetId() {
		return meetId;
	}

	public void setMeetId(Integer meetId) {
		this.meetId = meetId;
	}

	public String getModulesListString() {
		return modulesListString;
	}

	public void setModulesListString(String modulesListString) {
		this.modulesListString = modulesListString;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Integer> getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(List<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Integer getMember_type_id() {
		return member_type_id;
	}

	public void setMember_type_id(Integer member_type_id) {
		this.member_type_id = member_type_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleType=" + roleType + ", meetHallId=" + meetHallId + ", roleRemark=" + roleRemark + ", meetId=" + meetId + ", member_type_id=" + member_type_id + ", type=" + type + ", modules=" + modules + ", modulesListString=" + modulesListString + ", moduleIds=" + moduleIds + ", roleStatus=" + roleStatus + "]";
	}

}
