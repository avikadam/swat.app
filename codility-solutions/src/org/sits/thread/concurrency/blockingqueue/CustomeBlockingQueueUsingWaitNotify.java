package org.sits.thread.concurrency.blockingqueue;

import java.util.LinkedList;
import java.util.List;

public class CustomeBlockingQueueUsingWaitNotify<E> {
	private List<E> queue = new LinkedList<>();
	private int limit = 10;

	public CustomeBlockingQueueUsingWaitNotify(int limit) {
		this.limit = limit;
	}

	/**
	 * Add element to queue
	 * 
	 * @param item
	 * @throws InterruptedException
	 */
	public synchronized void enqueue(E item) throws InterruptedException {
		while (this.queue.size() == this.limit) {
			wait();
		}
		if (this.queue.size() == 0) {
			notifyAll();
		}
		this.queue.add(item);
	}

	/**
	 * Take element from queue
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized Object dequeue() throws InterruptedException {
		while (this.queue.size() == 0) {
			wait();
		}
		if (this.queue.size() == this.limit) {
			notifyAll();
		}

		return this.queue.remove(0);
	}
}
