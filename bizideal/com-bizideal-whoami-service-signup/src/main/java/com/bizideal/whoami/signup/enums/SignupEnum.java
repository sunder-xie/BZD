package com.bizideal.whoami.signup.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月4日 下午12:55:41
 * @version 1.0 用户模块所有异常定义
 */
public enum SignupEnum {
	SIGNUP_OK(0, "success"), 
	SIGNUP_EXCEPTION(6201, "发生系统异常"), 
	SIGNUP_DIET_UPDATE(6202, "饮食更新失败"), 
	SIGNUP_DIET_SET(6203, "饮食添加失败"), 
	SIGNUP_DIET_DELETE(6204, "饮食删除失败");
	
	private int errcode;
	private String errmsg;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	SignupEnum(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public static SignupEnum getEnum(int errcode) {
		SignupEnum resultEnum = null;
		SignupEnum[] enumAry = SignupEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getErrcode() == errcode) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		SignupEnum[] ary = SignupEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getErrcode()));
			map.put("errcode", String.valueOf(ary[num].getErrcode()));
			map.put("errmsg", ary[num].getErrmsg());
			enumMap.put(key, map);
		}
		return enumMap;
	}

}
