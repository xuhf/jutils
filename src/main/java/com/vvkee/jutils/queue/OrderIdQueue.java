package com.vvkee.jutils.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderIdQueue {

	public static final int QUEUE_MAX_SIZE = 500;

	public static final int QUEUE_MIN_SIZE = 100;

	private static final BlockingQueue<String> ORDER_ID_QUEUE = new LinkedBlockingQueue<String>(QUEUE_MAX_SIZE);

	public static int getQueueSize() {
		return ORDER_ID_QUEUE.size();
	}

	public static String pop() throws InterruptedException {
		return ORDER_ID_QUEUE.take();
	}

	public static void put(String value) throws InterruptedException {
		ORDER_ID_QUEUE.put(value);
	}

}
