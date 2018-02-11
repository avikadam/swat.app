package org.sits.thread.concurrency.threadpool;

public class TestTask implements Runnable {
	int sample = 0;

	public TestTask(int sample) {
		this.sample = sample;
	}

	@Override
	public void run() {
		System.out.println("Task number" + this.sample);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
