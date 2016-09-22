package com.vvkee.jutils.security;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

	public static String md5(String source) {
		return DigestUtils.md5Hex(source);
	}
	
	public static void main(String[] args) {
		System.out.println(md5("123456"));
	}

}
