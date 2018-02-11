package org.sits.thread.concurrency.threadpool;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
	private Queue<T> queue = new LinkedList<>();
	private int MAX_QUEUE_SIZE = -1;

	public BlockingQueue(int size) {
		MAX_QUEUE_SIZE = size;
	}

	public synchronized void enqueue(T task) throws InterruptedException {
		while (MAX_QUEUE_SIZE == queue.size()) {
			wait();
		}
		if (queue.isEmpty()) {
			notifyAll();
		}
		queue.offer(task);

	}

	public synchronized T dequeue() throws InterruptedException {
		while (queue.isEmpty()) {
			wait();
		}
		if (MAX_QUEUE_SIZE == queue.size()) {
			notifyAll();
		}
		return queue.poll();
	}

	public synchronized void clear() {
		if (!queue.isEmpty()) {
			queue.clear();
		}
	}

}
