package com.bizideal.whoami.rolemodule.entity;

import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 会议模块关联表
 * 
 * @author wangjingfei
 *
 */
@Table(name = "hall_module")
public class HallModule extends BaseEntity {
	// 会议厅Id
	private Integer meetingHallId;
	// 主会议Id
	private Integer meetingId;
	// 模块ID
	private String moduleId;

	//

	public HallModule() {
		super();
	}

	public HallModule(Integer meetingHallId, Integer meetingId, String moduleId) {
		super();
		this.meetingHallId = meetingHallId;
		this.meetingId = meetingId;
		this.moduleId = moduleId;
	}

	public Integer getMeetingHallId() {
		return meetingHallId;
	}

	public void setMeetingHallId(Integer meetingHallId) {
		this.meetingHallId = meetingHallId;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Override
	public String toString() {
		return "HallModule [meetingHallId=" + meetingHallId + ", meetingId=" + meetingId + ", moduleId=" + moduleId + "]";
	}

}
