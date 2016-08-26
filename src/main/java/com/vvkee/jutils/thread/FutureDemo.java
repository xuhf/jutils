package com.vvkee.jutils.thread;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

public class FutureDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 120, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.DiscardOldestPolicy());

		List<Future<CallbackModel>> list = Lists.newArrayList();
		for (int i = 0; i < 100; i++) {
			Task t = new Task(i);
			Future<CallbackModel> f = executor.submit(t);
			list.add(f);
		}

		while (executor.getActiveCount() > 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("线程未执行完毕");
		}

		for (Future<CallbackModel> f : list) {
			System.out.println(f.get());
		}

		System.out.println("线程执行完毕");
		executor.shutdown();
	}

	public static class Task implements Callable<CallbackModel> {

		public int i;

		public Task(int i) {
			this.i = i;
		}

		@Override
		public CallbackModel call() throws Exception {
			System.out.println("i is " + i);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			CallbackModel c = new CallbackModel();
			c.setKey("key_" + i);
			c.setValue("Value is " + i);
			return c;
		}
	}

	public static class CallbackModel {
		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "CallbackModel [key=" + key + ", value=" + value + "]";
		}

	}
}
