package com.bizideal.whoami.exception.enums;

import java.util.HashMap;
import java.util.Map;

import com.bizideal.whoami.enums.exception.ExceptionModelEums;

/**
 * 
 * 异常枚举
 * @author ct
 * 2017年3月14日
 * 结构形式      类名_方法名_编号_error  其中编号00为服务异常       
 * 例如 1100:代表第一个类 类名1 第一个方法方法名1 参数路由00
 */
public enum ExceptionFacadeEnums {
	
	//ExceptionLogServiceImpl类  
	ExceptionLogServiceImpl_insertExceptionLog_00_ERROR                  ("10100", "异常模块,新增操作,服务异常"),
	
	ExceptionLogServiceImpl_queryExceptionLogs_00_ERROR                  ("10100", "异常模块,查询异常列表,服务异常"),
	ExceptionLogServiceImpl_queryExceptionLogs_01_ERROR                  ("10101", "异常模块,查询异常列表,页码为必输项"),	
	ExceptionLogServiceImpl_queryExceptionLogs_02_ERROR                  ("10102", "异常模块,查询异常列表,每页大小为必输项"),	
	
	ExceptionLogServiceImpl_queryExceptionLogById_00_ERROR               ("10100", "异常模块,查询单个异常,服务异常"),
	ExceptionLogServiceImpl_queryExceptionLogById_01_ERROR               ("10101", "异常模块,查询单个异常,异常ID为必输项"),	
	
	ExceptionLogServiceImpl_delExceptionLogById_00_ERROR                 ("10100", "异常模块,单个删除操作,服务异常"),
	ExceptionLogServiceImpl_delExceptionLogById_01_ERROR                 ("10101", "异常模块,单个删除操作,异常ID为必输项"),
	
	ExceptionLogServiceImpl_delExceptionLogs_00_ERROR              ("10100", "异常模块,批量删除操作,服务异常"),
	ExceptionLogServiceImpl_delExceptionLogs_01_ERROR              ("10101", "异常模块,批量删除操作,参数不能为null"),
	
	
	;
	private String code;
	private String msg;

	

	public String getCode() {
		return ExceptionModelEums.exception+code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	

	ExceptionFacadeEnums(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static ExceptionFacadeEnums getEnum(String code) {
		ExceptionFacadeEnums resultEnum = null;
		ExceptionFacadeEnums[] enumAry = ExceptionFacadeEnums.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getCode() == code) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		ExceptionFacadeEnums[] ary = ExceptionFacadeEnums.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getCode()));
			map.put("code", String.valueOf(ary[num].getCode()));
			map.put("msg", ary[num].getMsg());
			enumMap.put(key, map);
		}
		return enumMap;
	}

//	public static void main(String[] args) {
//		System.out.println(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.errcode);
//		System.out.println(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.errmsg);
//		RoleAndMqEnums r = RoleAndMqEnums.getEnum(6101);
//		System.out.println(RoleAndMqEnums.getEnum(6101).errmsg);
//		System.out.println(toMap());
//	}

}
