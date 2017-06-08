package com.vvkee.jutils.thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 30; i++) {
			ShutdownThread t = new ShutdownThread();
			service.execute(t);
		}

		service.shutdownNow();
	}

	public static class ShutdownThread implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(new Date());
		}

	}

}
