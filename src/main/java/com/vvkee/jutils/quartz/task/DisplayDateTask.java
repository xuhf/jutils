package com.vvkee.jutils.quartz.task;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class DisplayDateTask {

	public static void main(String[] args) {
		DisplayDateTask task = new DisplayDateTask();
		task.run();
	}

	public void run() {
		Date d = new Date();
		System.out.println(DateFormatUtils.format(d, "yyyy-MM-dd HH:mm:ss"));
	}
}
