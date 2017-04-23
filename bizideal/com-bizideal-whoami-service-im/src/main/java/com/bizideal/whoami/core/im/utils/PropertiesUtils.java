package com.bizideal.whoami.core.im.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName PropertiesUtils
 * @Description TODO(Get properties file attributes value)
 * @Author Zj.Qu
 * @Date 2017-01-04 16:44:51
 */
public class PropertiesUtils {

	public static Properties getProperties() {

		Properties p = new Properties();

		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader()
					.getResourceAsStream("RestAPIConfig.properties");

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}

}
