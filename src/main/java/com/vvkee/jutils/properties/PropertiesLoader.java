package com.vvkee.jutils.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的例子，主要有以下几种方式 <br/>
 * 
 * @author xuhf
 *
 */
public class PropertiesLoader {

	private static Properties p = new Properties();

	static {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jutils.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这个是第一种方式
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String value = p.getProperty(key);
		return value;
	}

	// 使用Spring的 PropertiesLoaderUtils
	public static String getValueBySpring(String key) {
		// Properties p = PropertiesLoaderUtils.loadAllProperties("platform.properties");
		return null;
	}

}
