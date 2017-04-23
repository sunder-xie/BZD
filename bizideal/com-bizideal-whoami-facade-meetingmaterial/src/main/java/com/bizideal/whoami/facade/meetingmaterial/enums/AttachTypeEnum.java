package com.bizideal.whoami.facade.meetingmaterial.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName AttachTypeEnum
 * @Description TODO(会议附件类型)
 * @Author Zj.Qu
 * @Date 2016-12-14 16:40:46
 */
public enum AttachTypeEnum {
	
	/**会议议程**/
	ATTACH_TYPE_AGENDA("会议议程","0"),
	
	/**会议导读**/
	ATTACH_TYPE_GUIDE("会议导读","1"),
	
	/**会议资料**/
	ATTACH_TYPE_MATERIAL("会议资料","2");
	
	/** 枚举值 */
	private String value;

	/** 描述 */
	private String desc;

	
	AttachTypeEnum(String desc, String value) {
		this.value = value;
		this.desc = desc;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}
	

	public static AttachTypeEnum getEnum(String value) {
		AttachTypeEnum resultEnum = null;
		AttachTypeEnum[] enumAry = AttachTypeEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue() == value) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		AttachTypeEnum[] ary = AttachTypeEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", ary[num].getValue());
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		AttachTypeEnum[] ary = AttachTypeEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", ary[i].toString());
			map.put("desc", ary[i].getDesc());
			list.add(map);
		}
		return list;
	}
	
}
