package com.bizideal.whoami.facade.meetingmaterial.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseOpEntity;

/**
 * @ClassName MeetingAttach
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-14 16:38:53
 */
public class MeetingAttach extends BaseOpEntity {

	private static final long serialVersionUID = -5567401954308207277L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer attachId;

	/** 会议厅 **/
	private Integer hallId;

	/** 分会议 **/
	private Integer submeeId;

	/** 主会议 **/
	private Integer parmeeId;

	/** 附件名称 **/
	private String attachName;

	/** 附件文件后缀 **/
	private String attachSuffix;

	/** 附件文件地址 **/
	private String attachUrl;

	/** 附件类型:0:会议议程,1:会议导读,2:会议资料 **/
	private String attachType;

	/** 是否下载 默认0:打开 1:下载 **/
	private String attachIsDown;

	/** 备注 **/
	private String remarks;

	public MeetingAttach() {

	}

	public MeetingAttach(Integer attachId, Integer hallId, Integer submeeId, Integer parmeeId, String attachName,
			String attachSuffix, String attachUrl, String attachType, String remarks, String attachIsDown) {
		super();
		this.attachId = attachId;
		this.hallId = hallId;
		this.submeeId = submeeId;
		this.parmeeId = parmeeId;
		this.attachName = attachName;
		this.attachSuffix = attachSuffix;
		this.attachUrl = attachUrl;
		this.attachType = attachType;
		this.attachIsDown = attachIsDown;
		this.remarks = remarks;
	}

	public Integer getAttachId() {
		return attachId;
	}

	public void setAttachId(Integer attachId) {
		this.attachId = attachId;
	}

	public Integer getHallId() {
		return hallId;
	}

	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}

	public Integer getSubmeeId() {
		return submeeId;
	}

	public void setSubmeeId(Integer submeeId) {
		this.submeeId = submeeId;
	}

	public Integer getParmeeId() {
		return parmeeId;
	}

	public void setParmeeId(Integer parmeeId) {
		this.parmeeId = parmeeId;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName == null ? null : attachName.trim();
	}

	public String getAttachSuffix() {
		return attachSuffix;
	}

	public void setAttachSuffix(String attachSuffix) {
		this.attachSuffix = attachSuffix == null ? null : attachSuffix.trim();
	}

	public String getAttachUrl() {
		return attachUrl;
	}

	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl == null ? null : attachUrl.trim();
	}

	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType == null ? null : attachType.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getAttachIsDown() {
		return attachIsDown;
	}

	public void setAttachIsDown(String attachIsDown) {
		this.attachIsDown = attachIsDown;
	}

}