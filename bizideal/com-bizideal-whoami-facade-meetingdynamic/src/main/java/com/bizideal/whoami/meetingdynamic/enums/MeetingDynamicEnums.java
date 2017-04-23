package com.bizideal.whoami.meetingdynamic.enums;

/**
 * @ClassName MeetingDynamicEnums
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-18 15:22:26
 */
import java.util.HashMap;
import java.util.Map;

public enum MeetingDynamicEnums {

	MEETINGDYNAMIC_INSERT_SUCCESS("0", "添加会议动态成功。"),
	MEETINGDYNAMIC_INSERT_EXCEPTION("6404","添加会议动态异常。"), 
	MEETINGDYNAMIC_UPDATE_SUCCESS("6405", "修改会议动态成功。"), 
	MEETINGDYNAMIC_UPDATE_EXCEPTION("6406","修改会议动态异常。"),
	MEETINGDYNAMIC_DELETE_SUCCESS("6407", "删除会议动态成功。"),
	MEETINGDYNAMIC_DELETE_EXCEPTION("6408", "删除会议动态异常。"),
	MEETINGDYNAMIC_SELECT_SUCCESS("6409","查询所有会议动态成功。"),
	MEETINGDYNAMIC_SELECT_EXCEPTION("6410","查询所有会议动态异常。"), 
	MEETINGDYNAMIC_SELECT_ONE_SUCCESS("6411","查询所有会议动态成功。"), 
	MEETINGDYNAMIC_SELECT_ONE_EXCEPTION("6412","查询所有会议动态异常。"), 
	MEETINGDYNAMIC_SYSTEM_EXCEPTION("6413","系统异常，请稍后。");

	private String code;
	private String msg;

	MeetingDynamicEnums(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public MeetingDynamicEnums getEnum(String value) {
		MeetingDynamicEnums meetingDynamicEnums = null;
		MeetingDynamicEnums[] ary = MeetingDynamicEnums.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getCode()));
			map.put("code", ary[num].getCode());
			map.put("msg", ary[num].getMsg());
			enumMap.put(key, map);
		}
		return meetingDynamicEnums;
	}

	public String getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}

}
