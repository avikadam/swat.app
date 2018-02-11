package org.sits.thread.concurrency.forkjoin;

public interface ThreadPool {
	void submitTask(Runnable task);
}
