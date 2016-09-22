package com.vvkee.jutils.quartz;

import java.util.List;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class Main {

	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			TaskLoader loader = new TaskLoader();
			List<Task> tasks = loader.load();
			for (Task task : tasks) {
				System.out.println(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
