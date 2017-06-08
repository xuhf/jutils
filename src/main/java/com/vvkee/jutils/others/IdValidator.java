package com.vvkee.jutils.others;

import org.apache.commons.lang.StringUtils;

public class IdValidator {

	private static final int DEFAULT_ID_LENGTH = 18;

	private final static char[] VERIFY_CODE = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // 18位身份证中最后一位校验码

	private final static int[] VERIFY_CODE_WEIGHT = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };// 18位身份证中，各个数字的生成校验码时的权值

	public static boolean isLegal(String id) {
		if (StringUtils.isBlank(id)) {
			return false;
		}
		// 验证长度
		if (id.length() != 18) {
			return false;
		}
		// 验证是是否为数字
		if (!isNumber(id)) {
			return false;
		}
		// 验证校验码是否正确
		if (calculateVerifyCode(id) != id.charAt(DEFAULT_ID_LENGTH - 1)) {
			return false;
		}
		return true;
	}

	private static boolean isNumber(String id) {
		boolean result = true;
		for (int i = 0; i < DEFAULT_ID_LENGTH - 1; i++) {
			char ch = id.charAt(i);
			result = result && ch >= '0' && ch <= '9';
		}
		return result;
	}

	private static char calculateVerifyCode(String id) {
		int sum = 0;
		for (int i = 0; i < DEFAULT_ID_LENGTH - 1; i++) {
			char ch = id.charAt(i);
			sum += ((int) (ch - '0')) * VERIFY_CODE_WEIGHT[i];
		}
		return VERIFY_CODE[sum % 11];
	}
}
