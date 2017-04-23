package com.bizideal.whoami.signup.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月15日 上午10:45:04
 * @version 1.0 报名时系统所有的可靠字段表
 */
@Table(name = "signup_field")
public class SignupField extends BaseEntity {

	private static final long serialVersionUID = -2146272838776355093L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fieldId;// 自增id
	private String fieldName; // 属性名称
	private boolean fieldBasics; // 是否是基础字段
	private String fieldDsp; // 字段名称

	public SignupField() {
		super();
	}

	public SignupField(int fieldId, String fieldName, boolean fieldBasics,
			String fieldDsp) {
		super();
		this.fieldId = fieldId;
		this.fieldName = fieldName;
		this.fieldBasics = fieldBasics;
		this.fieldDsp = fieldDsp;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isFieldBasics() {
		return fieldBasics;
	}

	public void setFieldBasics(boolean fieldBasics) {
		this.fieldBasics = fieldBasics;
	}

	public String getFieldDsp() {
		return fieldDsp;
	}

	public void setFieldDsp(String fieldDsp) {
		this.fieldDsp = fieldDsp;
	}

	@Override
	public String toString() {
		return "SignupField [fieldId=" + fieldId + ", fieldName=" + fieldName
				+ ", fieldBasics=" + fieldBasics + ", fieldDsp=" + fieldDsp
				+ "]";
	}

}
