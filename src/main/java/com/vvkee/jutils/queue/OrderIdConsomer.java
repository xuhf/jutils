package com.vvkee.jutils.queue;

public class OrderIdConsomer implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				// 获取生成的订单ID
				OrderIdQueue.pop();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
