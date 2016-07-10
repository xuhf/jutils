package com.vvkee.jutils.lang;

import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;

public class Strings {

	public static void main(String[] args) {
		String source = "*.中国.*,爱情、婚姻、保卫,中国,爱情,习惯,*.软件.*";
		source = "*.中国.*";
		source = "";
		
		Set<String> set = removeRepeat(source);
		System.out.println(Joiner.on(",").join(set));
	}

	/**
	 * 关键词去重
	 * 
	 * 如果关键词b中包含了关键词
	 * 
	 * 那么就只保留b
	 * 
	 * @param source
	 * @return
	 */
	private static Set<String> removeRepeat(String source) {
		// 第一次去重
		Set<String> set = Sets.newHashSet();
		String[] array = source.split(",");
		for (String s : array) {
			set.add(s);
		}
		Set<String> finalSet = Sets.newHashSet();
		// 第二次去重
		// 最大循环次数 set.size() * set.size()
		for (String s : set) {
			boolean containes = false;
			for (String a : set) {
				if (hasContains(s, a)) {
					containes = true;
					break;
				}
			}
			if (!containes) {
				finalSet.add(s);
			}
		}
		return finalSet;
	}

	// a字符串中是否包含
	private static boolean hasContains(String b, String a) {
		if (a.equals(b)) {
			return false;
		}
		if (a.contains(b)) {
			return true;
		}
		return false;
	}
}
