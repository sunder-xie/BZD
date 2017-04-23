package com.bizideal.whoami.signup.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月15日 上午10:54:21
 * @version 1.0 模块和字段关联表
 */
@Table(name = "signup_field_module_link")
public class SignupFieldModuleLink extends BaseEntity {

	private static final long serialVersionUID = -6306727859142019469L;

	private int moduleId;// 模块id
	@Id
	private int fieldId;// 字段id
	private String dsp;// 注释

	public SignupFieldModuleLink() {
		super();
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp;
	}

	public SignupFieldModuleLink(int moduleId, int fieldId, String dsp) {
		super();
		this.moduleId = moduleId;
		this.fieldId = fieldId;
		this.dsp = dsp;
	}

	@Override
	public String toString() {
		return "SignupFieldModuleLink [moduleId=" + moduleId + ", fieldId="
				+ fieldId + ", dsp=" + dsp + "]";
	}

}
