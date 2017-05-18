package com.vvkee.jutils.queue;

public class Main {

	public static void main(String[] args) {
		OrderIdProducer producer = new OrderIdProducer();
		new Thread(producer).start();
		OrderIdConsomer c = new OrderIdConsomer();
		new Thread(c).start();
		new Thread(c).start();
		new Thread(c).start();
		new Thread(c).start();
		new Thread(c).start();
	}

}
