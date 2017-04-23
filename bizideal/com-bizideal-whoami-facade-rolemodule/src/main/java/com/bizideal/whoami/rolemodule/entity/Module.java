package com.bizideal.whoami.rolemodule.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

@Table(name = "module")
/**
 * 所有的总模块
 * @author pc
 *
 */
public class Module extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	private Integer moduleId; // 模块id
	private String moduleName;// 模块名称
	private String moduleRemark;// 模块备注
	private String moduleType;// 模块类型 default 为已报名角色可以查看的
	private String moduleUrl;// 模块的url
	private String moduleStatus; // 模块的状态 default 为未报名可查看的
	private String moduleIconSrc;// 模块的图标

	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleRemark() {
		return moduleRemark;
	}

	public void setModuleRemark(String moduleRemark) {
		this.moduleRemark = moduleRemark;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getModuleStatus() {
		return moduleStatus;
	}

	public void setModuleStatus(String moduleStatus) {
		this.moduleStatus = moduleStatus;
	}

	public String getModuleIconSrc() {
		return moduleIconSrc;
	}

	public void setModuleIconSrc(String moduleIconSrc) {
		this.moduleIconSrc = moduleIconSrc;
	}

	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleRemark=" + moduleRemark + ", moduleType=" + moduleType + ", moduleUrl=" + moduleUrl + ", moduleStatus=" + moduleStatus + "]";
	}

}
