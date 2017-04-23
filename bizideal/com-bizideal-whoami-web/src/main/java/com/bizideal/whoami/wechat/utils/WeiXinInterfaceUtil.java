package com.bizideal.whoami.wechat.utils;

import java.io.File;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

/**
 * 公众平台通用接口工具类
 * 
 */
public class WeiXinInterfaceUtil {
	 /**
	  * post  请求
	  * @param requestUrl
	  * @param jsonString
	  * @return
	  * @throws Exception
	  */
	public static JsonObject postHttpRequest(String requestUrl, String jsonString) throws Exception{  
	    JsonObject jsonObject = WeiXingInterfaceHttpRequest.httpRequest(requestUrl, "POST", jsonString);  
	    return jsonObject;  
	}
	/**
	 * get  请求
	 * @param requestUrl
	 * @return
	 * @throws Exception
	 */
	public static JsonObject getHttpRequest(String requestUrl) throws Exception{  
	    JsonObject jsonObject = WeiXingInterfaceHttpRequest.httpRequest(requestUrl, "GET", null);
	    return jsonObject;  
	}
	/**
	 * form 表单
	 * @param requestUrl
	 * @param file
	 * @param Parameters
	 * @return
	 * @throws Exception
	 */
	public static JsonObject postFormHttpRequest(String requestUrl, File file, JsonObject Parameters) throws Exception{  
	    JsonObject jsonObject = WeiXingInterfaceHttpRequest.httpRequestPostForm(requestUrl, file, Parameters);
	    return jsonObject;  
	}	
}