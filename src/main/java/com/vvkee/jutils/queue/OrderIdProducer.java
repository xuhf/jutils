package com.vvkee.jutils.queue;

import java.util.UUID;

public class OrderIdProducer implements Runnable {

	public void produce() throws InterruptedException {
		int size = OrderIdQueue.getQueueSize();
		System.out.println("队列长度 ： " + size);
		if (size <= OrderIdQueue.QUEUE_MIN_SIZE) {
			int needProductSize = OrderIdQueue.QUEUE_MAX_SIZE - OrderIdQueue.QUEUE_MIN_SIZE;
			for (int i = 0; i < needProductSize; i++) {
				String uuid = UUID.randomUUID().toString().replace("-", "");
				OrderIdQueue.put(uuid);
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				produce();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
