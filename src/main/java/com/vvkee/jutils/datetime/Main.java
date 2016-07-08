package com.vvkee.jutils.datetime;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

public class Main {
	
	public static void main(String[] args) {
		testAdd();
	}
	
	public static void testAdd() {
		Date d = new Date();
		Date yesterday = DateUtils.addDays(d, -1);
		System.out.println(DateFormatUtils.format(yesterday, "yyyy-MM-dd HH:mm:ss"));
	}
}
