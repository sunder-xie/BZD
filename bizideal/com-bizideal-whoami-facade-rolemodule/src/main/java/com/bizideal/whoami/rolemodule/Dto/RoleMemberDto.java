package com.bizideal.whoami.rolemodule.Dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;
import com.bizideal.whoami.rolemodule.entity.Module;

@Table(name = "role")
public class RoleMemberDto extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	// 角色Id
	private Integer roleId;
	// 个人或单位Id
	private Integer member_type_id;
	// 个人或单位费用
	private String cost;
	// 单位报名限制人数
	private Integer peopleNumber;
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
	// 用于区分joniner组织和个人的 1个人 0组织
	private Integer type;

	private Integer role_personal_id; // 个人的Id

	private Integer role_unit_id;// 组织的Id

	@Transient
	private List<Module> modules; // 该角色所拥有的权限模块

	@Transient
	private String modulesListString;// 角色模块的模块id

	@Transient
	private List<Integer> moduleIds; // 该角色所拥有的权限模块的id集合

	private String roleStatus; // 角色状态 default为系统默认创建的角色

	public RoleMemberDto() {
		super();
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getModulesListString() {
		return modulesListString;
	}

	public void setModulesListString(String modulesListString) {
		this.modulesListString = modulesListString;
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

	public Integer getRole_personal_id() {
		return role_personal_id;
	}

	public void setRole_personal_id(Integer role_personal_id) {
		this.role_personal_id = role_personal_id;
	}

	public Integer getRole_unit_id() {
		return role_unit_id;
	}

	public void setRole_unit_id(Integer role_unit_id) {
		this.role_unit_id = role_unit_id;
	}

	@Override
	public String toString() {
		return "RoleMemberDto [roleId=" + roleId + ", member_type_id=" + member_type_id + ", cost=" + cost + ", peopleNumber=" + peopleNumber + ", roleName=" + roleName + ", roleType=" + roleType + ", meetHallId=" + meetHallId + ", roleRemark=" + roleRemark + ", meetId=" + meetId + ", type=" + type + ", role_personal_id=" + role_personal_id + ", role_unit_id=" + role_unit_id + ", modules=" + modules + ", modulesListString=" + modulesListString + ", moduleIds=" + moduleIds + ", roleStatus=" + roleStatus + "]";
	}

}
