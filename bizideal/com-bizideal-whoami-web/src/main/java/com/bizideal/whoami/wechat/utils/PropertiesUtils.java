package com.bizideal.whoami.wechat.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取
 * url.properties 
 * 配置文件的工具类 
 * @author zhushangjin
 * 
 */
public class PropertiesUtils {

	private Properties pros = null;

	private static class ConfigurationHolder {
		private static PropertiesUtils configuration = new PropertiesUtils();
	}
   /**
    * 获取url.properties的 对应的配置
    * @return
    */
	public static PropertiesUtils getInstance() {
		return ConfigurationHolder.configuration;
	}
/**
 * 根据url.properties中的key获取对应的值
 * @param key
 * @return
 */
	public String getValue(String key) {
		return pros.getProperty(key);
	}

	private PropertiesUtils() {
		readConfig();
	}

	private void readConfig() {
		pros = new Properties();
		InputStream in = null;
		try {

			in = PropertiesUtils.class.getResourceAsStream("/url.properties");
			pros.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(PropertiesUtils.getInstance().getValue("loginurl"));
	}
}