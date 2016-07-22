package com.vvkee.jutils.datetime;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * 使用 joda time <br>
 * 
 * http://ylq365.iteye.com/blog/1769680
 * 
 * @author xuhf
 *
 */
public class DateTimeDemo {

	public static void main(String[] args) {
		// 当前时间
		DateTime now = DateTime.now();
		System.out.println(now);
		// 当前时间的开始
		now = now.withTimeAtStartOfDay();
		System.out.println(now);
		DateTime yesterday = now.minusDays(1);
		System.out.println(yesterday);
		int i = Days.daysBetween(yesterday, now).getDays();
		System.out.println(i);
		now = new DateTime(new Date());
		System.out.println(now.toString("yyyy-MM-dd HH:mm:ss"));
	}
}
