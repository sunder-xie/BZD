package com.bizideal.whoami.rolemodule.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 会议厅的所有功能
 * 
 * @author pc
 *
 */
@Table(name = "hall_function")
public class HallFunction extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	private Integer functionId; // 主键
	private String functionName; // 功能名称
	private String functionRemark; // 功能备注
	private String functionType; // 功能类型 预留字段
	private String functionStatus; // 功能状态 预留字段

	public HallFunction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionRemark() {
		return functionRemark;
	}

	public void setFunctionRemark(String functionRemark) {
		this.functionRemark = functionRemark;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getFunctionStatus() {
		return functionStatus;
	}

	public void setFunctionStatus(String functionStatus) {
		this.functionStatus = functionStatus;
	}

	@Override
	public String toString() {
		return "HallFunction [functionId=" + functionId + ", functionName=" + functionName + ", functionRemark=" + functionRemark + ", functionType=" + functionType + ", functionStatus=" + functionStatus + "]";
	}

}
