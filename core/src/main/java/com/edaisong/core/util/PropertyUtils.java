package com.edaisong.core.util;

import java.io.InputStream;
import java.util.Properties;

import com.edaisong.core.common.ConfigHelper;

public class PropertyUtils {
	private static final Properties prop = new Properties();

	static {
		InputStream inputStream = ConfigHelper.class.getClassLoader().getResourceAsStream("conf.properties");
		try {
			prop.load(inputStream);
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
	
	public static String getProperty(String key, String defaultValue){
		return prop.getProperty(key, defaultValue);
	}
}
