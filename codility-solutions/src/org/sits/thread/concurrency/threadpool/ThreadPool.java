package org.sits.thread.concurrency.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
	// Count of threadpools created
	private static AtomicInteger poolCount = new AtomicInteger(0);
	// Queue of runnables
	private BlockingQueue<Runnable> runnables;
	// Flag to control the SimpleThreadpoolThread objects
	private AtomicBoolean execute;
	// Holds the "pool" of threads
	private List<TaskExecutor<Runnable>> threads;

	private ThreadPool(int threadCount) {
		// Increment pool count
		poolCount.incrementAndGet();
		this.runnables = new BlockingQueue<Runnable>(threadCount);
		this.execute = new AtomicBoolean(true);
		this.threads = new ArrayList<>();
		for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
			TaskExecutor<Runnable> thread = new TaskExecutor<Runnable>(this.execute, this.runnables);
			thread.run();
			this.threads.add(thread);
		}
	}

	public static ThreadPool getInstance() {
		return getInstance(Runtime.getRuntime().availableProcessors());
	}

	/**
	 * Gets a new threadpool instance with the number of threads specified
	 *
	 * @param threadCount
	 *            Threads to add to the pool
	 * @return new SimpleThreadpool
	 */
	public static ThreadPool getInstance(int threadCount) {
		return new ThreadPool(threadCount);
	}

	/**
	 * Adds a runnable to the queue for processing
	 *
	 * @param runnable
	 *            Runnable to be added to the pool
	 * @throws InterruptedException
	 */
	public void execute(Runnable runnable) throws InterruptedException {
		if (this.execute.get()) {
			runnables.enqueue(runnable);
		} else {
			throw new IllegalStateException("Threadpool terminating, unable to execute runnable");
		}
	}

	/**
	 * Awaits up to <b>timeout</b> ms the termination of the threads in the
	 * threadpool
	 *
	 * @param timeout
	 *            Timeout in milliseconds
	 * @throws TimeoutException
	 *             Thrown if the termination takes longer than the timeout
	 * @throws IllegalStateException
	 *             Thrown if the stop() or terminate() methods haven't been
	 *             called before awaiting
	 */
	public void awaitTermination(long timeout) throws TimeoutException {
		if (this.execute.get()) {
			throw new IllegalStateException("Threadpool not terminated before awaiting termination");
		}
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime <= timeout) {
			boolean flag = true;
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				//
			}
		}
		throw new TimeoutException("Unable to terminate threadpool within the specified timeout (" + timeout + "ms)");
	}

	/**
	 * Awaits the termination of the threads in the threadpool indefinitely
	 *
	 * @throws IllegalStateException
	 *             Thrown if the stop() or terminate() methods haven't been
	 *             called before awaiting
	 */
	public void awaitTermination() throws TimeoutException {
		if (this.execute.get()) {
			throw new IllegalStateException("Threadpool not terminated before awaiting termination");
		}
		while (true) {
			boolean flag = true;
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				//
			}
		}
	}

	/**
	 * Clears the queue of runnables and stops the threadpool. Any runnables
	 * currently executing will continue to execute.
	 */
	public void terminate() {
		runnables.clear();
		stop();
	}

	/**
	 * Stops addition of new runnables to the threadpool and terminates the pool
	 * once all runnables in the queue are executed.
	 */
	public void stop() {
		execute.set(false);
	}
}
