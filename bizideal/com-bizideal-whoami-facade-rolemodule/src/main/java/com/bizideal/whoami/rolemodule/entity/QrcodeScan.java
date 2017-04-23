package com.bizideal.whoami.rolemodule.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 记录二维码的信息
 * 
 * @author zhu_shangjin
 * @version 2017年1月10日 下午1:26:16
 */
@Table(name = "qrcode_scan")
public class QrcodeScan extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	private Integer codeId;// 主键 二维码id
	private String redirect; // 扫码成功后重定向的url
	private String hasScan;// 该二维码是否已经被扫过
	private String scanType;// 扫码的途径 app 或微信
	private String scanUserId;// 扫描该二维码的用户id

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getHasScan() {
		return hasScan;
	}

	public void setHasScan(String hasScan) {
		this.hasScan = hasScan;
	}

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public String getScanUserId() {
		return scanUserId;
	}

	public void setScanUserId(String scanUserId) {
		this.scanUserId = scanUserId;
	}

	@Override
	public String toString() {
		return "QrcodeScan [codeId=" + codeId + ", redirect=" + redirect + ", hasScan=" + hasScan + ", scanType=" + scanType + ", scanUserId=" + scanUserId + "]";
	}

}
