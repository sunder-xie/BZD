package com.bizideal.whoami.core.im.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName PropertiesUtils
 * @Description TODO(Get properties file attributes value)
 * @Author Zj.Qu
 * @Date 2016-07-27 16:43:20
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
