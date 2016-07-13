package com.vvkee.jutils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 简单的介绍四种线程池的使用
 * 
 * @author xuhf
 *
 */
public class Main {

	public static void main(String[] args) {
		singlePool();
	}

	public static void cachedPool() {
		// 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			DemoTask task = new DemoTask();
			cachedThreadPool.execute(task);
		}
	}

	public static void fixedPool() {
		// 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
		// 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 100; i++) {
			DemoTask task = new DemoTask();
			fixedThreadPool.execute(task);
		}
	}

	public static void scheduledPool() {
		// 创建一个定长线程池，支持定时及周期性任务执行
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		// 表示延迟1秒后每3秒执行一次。
		scheduledThreadPool.scheduleAtFixedRate(new DemoTask(), 1, 3, TimeUnit.SECONDS);
	}

	public static void singlePool() {
		// 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			// 结果依次输出，相当于顺序执行各个任务
			singleThreadExecutor.execute(new DemoTask(i));
		}
		singleThreadExecutor.shutdown();
	}

	public static class DemoTask implements Runnable {

		private int i;

		public DemoTask() {
		}

		public DemoTask(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println("this i is " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
