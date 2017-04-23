package com.bizideal.whoami.facade.meeting.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseOpEntity;

/**
 * @ClassName OrganizerInfo
 * @Description TODO(组织信息实体)
 * @Author Zj.Qu
 * @Date 2016-12-02 17:30:12
 */
@Table(name = "organizer_info")
public class OrganizerInfo extends BaseOpEntity {

	private static final long serialVersionUID = -1714070615012629306L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer orgId;

	/** 会议厅 **/
	private Integer hallId;

	/** 组织代码 **/
	private String orgCode;

	/** 组织名称 **/
	private String orgName;

	/** 运营者姓名 **/
	private String orgOperatorName;

	/** 运营者手机号 **/
	private String orgOperatorPhone;

	/** 身份证正面照片 **/
	private String orgIdcardPositive;

	/** 身份证反面照片 **/
	private String orgIdcardNegative;

	/** 组织授权书 **/
	private String orgAuthorization;

	/** 是否通过审核(1:是,0:否) **/
	private String orgIsPass;

	/** 备注 **/
	private String orgRemark;

	public OrganizerInfo() {

	}

	public OrganizerInfo(Integer orgId, Integer hallId, String orgCode, String orgName, String orgOperatorName,
			String orgOperatorPhone, String orgIdcardPositive, String orgIdcardNegative, String orgAuthorization,
			String orgIsPass, String orgRemark) {
		this.orgId = orgId;
		this.hallId = hallId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgOperatorName = orgOperatorName;
		this.orgOperatorPhone = orgOperatorPhone;
		this.orgIdcardPositive = orgIdcardPositive;
		this.orgIdcardNegative = orgIdcardNegative;
		this.orgAuthorization = orgAuthorization;
		this.orgIsPass = orgIsPass;
		this.orgRemark = orgRemark;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getHallId() {
		return hallId;
	}

	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName == null ? null : orgName.trim();
	}

	public String getOrgOperatorName() {
		return orgOperatorName;
	}

	public void setOrgOperatorName(String orgOperatorName) {
		this.orgOperatorName = orgOperatorName == null ? null : orgOperatorName.trim();
	}

	public String getOrgOperatorPhone() {
		return orgOperatorPhone;
	}

	public void setOrgOperatorPhone(String orgOperatorPhone) {
		this.orgOperatorPhone = orgOperatorPhone == null ? null : orgOperatorPhone.trim();
	}

	public String getOrgIdcardPositive() {
		return orgIdcardPositive;
	}

	public void setOrgIdcardPositive(String orgIdcardPositive) {
		this.orgIdcardPositive = orgIdcardPositive == null ? null : orgIdcardPositive.trim();
	}

	public String getOrgIdcardNegative() {
		return orgIdcardNegative;
	}

	public void setOrgIdcardNegative(String orgIdcardNegative) {
		this.orgIdcardNegative = orgIdcardNegative == null ? null : orgIdcardNegative.trim();
	}

	public String getOrgAuthorization() {
		return orgAuthorization;
	}

	public void setOrgAuthorization(String orgAuthorization) {
		this.orgAuthorization = orgAuthorization == null ? null : orgAuthorization.trim();
	}

	public String getOrgIsPass() {
		return orgIsPass;
	}

	public void setOrgIsPass(String orgIsPass) {
		this.orgIsPass = orgIsPass == null ? null : orgIsPass.trim();
	}

	public String getOrgRemark() {
		return orgRemark;
	}

	public void setOrgRemark(String orgRemark) {
		this.orgRemark = orgRemark == null ? null : orgRemark.trim();
	}

	@Override
	public String toString() {
		return "OrganizerInfo [orgId=" + orgId + ", hallId=" + hallId
				+ ", orgCode=" + orgCode + ", orgName=" + orgName
				+ ", orgOperatorName=" + orgOperatorName
				+ ", orgOperatorPhone=" + orgOperatorPhone
				+ ", orgIdcardPositive=" + orgIdcardPositive
				+ ", orgIdcardNegative=" + orgIdcardNegative
				+ ", orgAuthorization=" + orgAuthorization + ", orgIsPass="
				+ orgIsPass + ", orgRemark=" + orgRemark + "]";
	}

}