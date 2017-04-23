package com.bizideal.whoami.facade.meeting.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MeetingHallEnum
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-06 16:18:59
 */
public enum RequestEnum {

	/** 操作成功 **/
	SUCCESS("操作成功", 0),

	/** 操作失败 **/
	UN_SUCCESS("操作失败", 6505),

	/** 系统错误 **/
	SYSTEM_ERROR("系统错误", 6500);

	/** 描述 */
	private String code;

	/** 状态码 */
	private Integer status;

	RequestEnum(String code, Integer status) {
		this.code = code;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static RequestEnum getEnum(Integer status) {
		RequestEnum resultEnum = null;
		RequestEnum[] enumAry = RequestEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getStatus() == status) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		RequestEnum[] ary = RequestEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getStatus()));
			map.put("Status", String.valueOf(ary[num].getStatus()));
			map.put("Code", ary[num].getCode());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	public static Map<String, Map<String, Object>> NametoMap() {
		RequestEnum[] ary = RequestEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();

		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getStatus()));
			map.put("Status", String.valueOf(ary[num].getStatus()));
			map.put("Code", ary[num].getCode());
			enumMap.put(key, map);
		}

		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		RequestEnum[] ary = RequestEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Status", ary[i].toString());
			map.put("Code", ary[i].getCode());
			list.add(map);
		}
		return list;
	}

	public static RequestEnum fromInt(int value) {
		switch (value) {
		case 0:
			return SUCCESS;
		case 1:
			return UN_SUCCESS;
		case 2:
			return SYSTEM_ERROR;
		default:
			throw new IllegalArgumentException("Unkown enum value: " + value);
		}
	}

	public static void main(String[] args) {
		System.out.println(RequestEnum.fromInt(3));
	}
}
