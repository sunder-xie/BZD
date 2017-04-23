package com.bizideal.whoami.wechat.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.bizideal.whoami.utils.HttpsX509TrustManager;
import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 公众平台通用接口工具类
 * 
 * @author Herman.Xiong
 * @date 2016年1月15日16:16:35
 */
public class WeChatUtil {
	// 获取access_token的接口地址（GET） 限200（次/天）

	public static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static WechatAccessToken getAccessToken(String appid,
			String appsecret) {
		WechatAccessToken accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JsonObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			accessToken = new WechatAccessToken();
			accessToken.setToken(jsonObject.get("access_token").getAsString());
			accessToken.setExpiresIn(jsonObject.get("expires_in").getAsInt());
		}
		return accessToken;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return
	 */
	public static JsonObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JsonObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new HttpsX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = (JsonObject) new JsonParser().parse(buffer.toString());
		} catch (ConnectException ce) {
		} catch (Exception e) {
		}
		return jsonObject;
	}
   /**
    * 根据appid和appsecret 获取微信WechatAccessToken
    * @param appid
    * @param appsecret
    * @return
    */
	public static WechatAccessToken getWechatAccessToken(String appid,
			String appsecret) {
		String requestUrl = PropertiesUtils.getInstance().getValue(
				"access_token_url");
		requestUrl = requestUrl.replace("APPID", appid).replace("APPSECRET",
				appsecret);
		JsonObject jsonObject = null;
		try {
			jsonObject = WeiXinInterfaceUtil.getHttpRequest(requestUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果请求成功
		WechatAccessToken accessToken = null;
		if (null != jsonObject) {

			accessToken = new WechatAccessToken();
			accessToken.setToken(jsonObject.get("access_token").getAsString());
			accessToken.setExpiresIn(jsonObject.get("expires_in").getAsInt());
			accessToken.setGenerateTime(new Date().getTime());
		}
		return accessToken;
	}

}
