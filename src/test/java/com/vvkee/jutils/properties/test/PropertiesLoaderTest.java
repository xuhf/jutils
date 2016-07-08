package com.vvkee.jutils.properties.test;

import org.junit.Assert;
import org.junit.Test;

import com.vvkee.jutils.properties.PropertiesLoader;

/**
 * 读取配置文件
 * 
 * @author xuhf
 *
 */
public class PropertiesLoaderTest {

	@Test
	public void testGetValue() {
		String value = PropertiesLoader.getValue("project.author.blog");
		Assert.assertEquals("http://www.vvkee.com", value);
	}

}
