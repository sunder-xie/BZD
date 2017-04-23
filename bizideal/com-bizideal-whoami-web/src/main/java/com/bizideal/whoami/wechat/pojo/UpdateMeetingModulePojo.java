package com.bizideal.whoami.wechat.pojo;

import java.util.List;

public class UpdateMeetingModulePojo {
	Integer meet_hall_id;
	Integer meet_id;
	List<String> module_id;

	public UpdateMeetingModulePojo() {
		super();
	}

	public UpdateMeetingModulePojo(Integer meet_hall_id, Integer meet_id, List<String> module_id) {
		super();
		this.meet_hall_id = meet_hall_id;
		this.meet_id = meet_id;
		this.module_id = module_id;
	}

	public Integer getMeet_hall_id() {
		return meet_hall_id;
	}

	public void setMeet_hall_id(Integer meet_hall_id) {
		this.meet_hall_id = meet_hall_id;
	}

	public Integer getMeet_id() {
		return meet_id;
	}

	public void setMeet_id(Integer meet_id) {
		this.meet_id = meet_id;
	}

	public List<String> getModule_id() {
		return module_id;
	}

	public void setModule_id(List<String> module_id) {
		this.module_id = module_id;
	}

	@Override
	public String toString() {
		return "UpdateMeetingModulePojo [meet_hall_id=" + meet_hall_id + ", meet_id=" + meet_id + ", module_id=" + module_id + "]";
	}

}