package org.sits.thread.concurrency.threadpool;

import java.util.concurrent.atomic.AtomicBoolean;

public class TaskExecutor<T> extends Thread {
	BlockingQueue<T> queue;
	private AtomicBoolean execute;

	public TaskExecutor(AtomicBoolean execute, BlockingQueue<T> queue) {
		this.execute = execute;
		this.queue = queue;
	}

	@Override
	public void run() {
		while (execute.get()) {
			try {
				Runnable t = (Runnable) queue.dequeue();
				t.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
