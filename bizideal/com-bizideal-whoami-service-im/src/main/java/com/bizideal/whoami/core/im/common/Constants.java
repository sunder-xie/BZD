package com.bizideal.whoami.core.im.common;


/**
 * @ClassName Constants
 * @Description TODO(读取配置信息)
 * @Author Zj.Qu
 * @Date 2016-08-02 16:17:39
 */
public interface Constants {
//	API_SERVER_HOST = a1.easemob.com
//
//			#Appkey, just for test
//			APP_KEY = sephiroth#meeting
//
//			#App client id of the app above
//			APP_CLIENT_ID = YXA64VprUE1fEeaME1G5HkEXUw
//
//			#App client secret of the app above
//			APP_CLIENT_SECRET = YXA6vDWESaY5lxd-PGKi5ICr85V5rJU
	
	// API_HTTP_SCHEMA
//	public static String API_HTTP_SCHEMA = "https";
//	// API_SERVER_HOST
//	public static String API_SERVER_HOST = "a1.easemob.com";
//	// APPKEY
//	public static String APP_KEY = "qsmxxl#easemob-chat";
//	//ORG_NAME
//	public static String ORG_NAME="qsmxxl";
//	//APP_NAME
//	public static String APP_NAME="easemob-chat";
//	// APP_CLIENT_ID
//	public static String APP_CLIENT_ID = "YXA6jfgYME7mEeanh4sI9hi48Q";
//	// APP_CLIENT_SECRET
//	public static String APP_CLIENT_SECRET = "YXA6HMH3vAVoHdYDlixWqzwa9RbvYSI";
//	// DEFAULT_PASSWORD
//	public static String DEFAULT_PASSWORD = "abc12360";


	// API_HTTP_SCHEMA
	String API_HTTP_SCHEMA = "https";
	// API_SERVER_HOST
	String API_SERVER_HOST = PropertiesUtils.getProperties().getProperty("API_SERVER_HOST");
	// APPKEY
	String APP_KEY = PropertiesUtils.getProperties().getProperty("APP_KEY");
	//ORG_NAME
	String ORG_NAME=APP_KEY.split("#")[0];
	//APP_NAME
	String APP_NAME=APP_KEY.split("#")[1];
	// APP_CLIENT_ID
	String APP_CLIENT_ID = PropertiesUtils.getProperties().getProperty("APP_CLIENT_ID");
	// APP_CLIENT_SECRET
	String APP_CLIENT_SECRET = PropertiesUtils.getProperties().getProperty("APP_CLIENT_SECRET");
	// DEFAULT_PASSWORD
	String DEFAULT_PASSWORD = "123456";

}
