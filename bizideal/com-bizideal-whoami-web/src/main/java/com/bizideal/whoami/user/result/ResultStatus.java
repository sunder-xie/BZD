package com.bizideal.whoami.user.result;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName ResultStatus
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-05 10:36:32
 */
public class ResultStatus {

	private static Map<String, Object> map = new HashMap<String, Object>();
	//private static ObjectMapper objectMapper = new ObjectMapper();

	public static Map<String, Object> signUpSuccess() {
		
		map.put("status", 0);
		map.put("msg", "报名成功");
		return map;
	}

	public static Map<String, Object> serviceError() {
		
		map.put("status", 6401);
		map.put("msg", "服务器不可用");
		return map;
	}

	public static Map<String, Object> signUpFail() {
		
		map.put("status", 6400);
		map.put("msg", "报名失败");
		return map;
	}
}
