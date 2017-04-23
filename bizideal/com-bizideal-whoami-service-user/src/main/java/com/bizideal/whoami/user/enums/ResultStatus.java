package com.bizideal.whoami.user.enums;

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

	public static Map<String, Object>  selectSuccess(String msg) {

			map.put("status", 0);
			map.put("msg", "查询成功");
			map.put("userinfo", msg);
			return map;
	}

	public static Map<String, Object>  selectFail() {

			map.put("status", 6402);
			map.put("msg", "查询失败");
			return map;
	}

	public static Map<String, Object>  selectNull() {

			map.put("status", 6403);
			map.put("msg", "没有此用户信息");
			return map;

	}
}
