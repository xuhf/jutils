package com.vvkee.jutils.sign;

import java.util.UUID;

public class ApiCore {

	public static final String CHARSET = "utf-8";

	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		System.out.println(uuid);
		String uuid2 = UUID.randomUUID().toString().replace("-", "");
		System.out.println(uuid2);
	}
}
