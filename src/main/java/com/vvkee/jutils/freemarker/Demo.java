package com.vvkee.jutils.freemarker;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 使用Freemarker作为模板
 * 
 * @author xuhf
 *
 */
public class Demo {

	public static void main(String[] args) throws Exception {
		String result = getEmailContext("xuhf");
		System.out.println(result);
	}

	private static String getEmailContext(String username) throws Exception {
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(Demo.class, "/");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.CHINA);
		Template temp = cfg.getTemplate("email.ftl");
		StringWriter out = new StringWriter();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("username", username);
		temp.process(root, out);
		return out.toString();
	}
}
