package com.vvkee.jutils.security;

public class Base64 {

	public static String encodeStr(String plainText) {
		byte[] b = plainText.getBytes();
		org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
		b = base64.encode(b);
		String s = new String(b);
		return s;
	}

	public static String decodeStr(String encodeStr) {
		byte[] b = encodeStr.getBytes();
		org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
		b = base64.decode(b);
		String s = new String(b);
		return s;
	}
}
