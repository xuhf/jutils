package com.vvkee.jutils.quartz.task;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class DisplayNameAndDateTask {

	public void run() {
		Date d = new Date();
		System.out.println("xuhf_" + DateFormatUtils.format(d, "yyyy-MM-dd HH:mm:ss"));
	}
}
