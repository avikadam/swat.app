package com.sits.java_sample;

import java.awt.Toolkit;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class GC extends Thread {
	public static void main(String[] args) throws InterruptedException {

		Scanner in = new Scanner(System.in);
		String _name;
		try {
			_name = in.nextLine();
		} catch (Exception e) {
			_name = null;
		}
		Book novel = new Book(true, "Mobi Dick");
		novel.checkedIn();

		// drop the reference, this will be garbage collected
		new Book(true, _name);
		// force a system garbage collection
		System.gc();
		Thread.sleep(1000);
	}
}

class Book {
	boolean isCheckedOut = false;
	String name;

	public Book(boolean isCheckedOut, String name) {
		this.isCheckedOut = isCheckedOut;
		this.name = name;
	}

	void checkedIn() {
		isCheckedOut = false;
	}

	void checkedOut() {
		isCheckedOut = true;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.format("Error:checked out:%s", this.name);
		super.finalize();
	}

}

class Alert implements Runnable {
	private Vector<String> input;

	public Vector<String> getInput() {
		return input;
	}

	public void setInput(Vector<String> input) {
		this.input = input;
	}

	@Override
	public void run() {
		synchronized (input) {
			try {
				input.notify();
				Thread.sleep(3000);
				input.add("item2");
			} catch (InterruptedException e) {
			}
		}
	}
}

class ThreadDemo implements Runnable {
	int x = 0, y = 0;

	int addX() {
		x++;
		return x;
	}

	int addY() {
		y++;
		return y;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.println(Thread.currentThread().getName() + ": " + addX() + " " + addY());
	}

	public static void main(String args[]) {
		ThreadDemo obj1 = new ThreadDemo();
		ThreadDemo obj2 = new ThreadDemo();
		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		t1.start();
		t2.start();
	}
}

class MyThread extends Thread {
	MyThread() {
		System.out.print(" MyThread");
	}

	@Override
	public void run() {
		System.out.print(" bar");
	}

	public void run(String s) {
		System.out.println(" baz");
	}
}

class Thread1Demo {
	public static void main(String[] args) {
		Thread t = new MyThread() {
			@Override
			public void run() {
				System.out.println(" foo");
			}
		};
		t.start();
	}
}

class Thread2Demo extends Thread {
	final StringBuffer sb1 = new StringBuffer();
	final StringBuffer sb2 = new StringBuffer();

	public static void main(String args[]) {
		final Thread2Demo h = new Thread2Demo();

		new Thread() {
			@Override
			public void run() {
				synchronized (this) {
					h.sb1.append("Java");
					h.sb2.append("Thread");
					System.out.println(h.sb1);
					System.out.println(h.sb2);
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				synchronized (this) {
					h.sb1.append("Mutithreading");
					h.sb2.append("Example");
					System.out.println(h.sb2);
					System.out.println(h.sb1);
				}
			}
		}.start();
	}
}

class MyThread1 extends Thread {
	MyThread1() {
	}

	MyThread1(Runnable r) {
		super(r);
	}

	@Override
	public void run() {
		System.out.print("Inside Thread ");
	}
}

class RunnableDemo implements Runnable {
	@Override
	public void run() {
		System.out.print(" Inside Runnable");
	}
}

class Thread3Demo {
	public static void main(String[] args) {
		new MyThread1().start();
		new MyThread1(new RunnableDemo()).start();
		// new Thread(new MyThread1()).start();
	}
}

class Thread4Demo extends Thread {
	public static void main(String[] args) {
		Thread4Demo t = new Thread4Demo();
		t.start();
		System.out.print("one. ");
		t.start();// runtime error :: java.lang.IllegalThreadStateException
		System.out.print("two. ");
	}

	@Override
	public void run() {
		System.out.print("Thread ");
	}
}

class MultiThreadedCounter implements Runnable {

	private int n; /* how many times to increment */
	private boolean goSlow; /* whether to go fast or slow */
	private int howSlow; /* how slow to go--bigger is slower */
	private boolean useSynchronized; /*
										 * whether to use synchronized methods
										 * or not
										 */

	private int count = 0; /* we accumulate a count */

	private double sum = 0.0; /*
								 * this is just a time waster--see incrSlow
								 * below.
								 */

	/**
	 * return the count
	 * 
	 * @return count of how many times incr has been called (supposedly)
	 */

	public int getCount() {
		return count;
	}

	/**
	 * return the sum
	 * 
	 * @return sum of all terms added to sum when "wasting time"
	 */

	public double getSum() {
		return sum;
	}

	public void reset() {
		count = 0;
		sum = 0.0;
	}

	/**
	 * Increment count as quickly as Java allows.
	 */

	public void incr() {
		count++; /* Equivalent to count = count + 1; */
	}

	/**
	 * Increment count, but in between, accumulate terms from a sin function
	 * operating at 1 Hz, 44100 samples per second. This function should, over
	 * the long term, sum to approximately zero. This is really done just to
	 * "waste time" to make the window of vulnerability larger for race
	 * conditions of incrementing the count.
	 * 
	 * @param terms
	 *            number of terms to accumulate to waste time
	 */

	public void incrSlow(int terms) {
		int oldCount = 0;
		// = count;
		for (int i = 0; i < 1000; i++)
			sum += Math.sin(i * (2 * Math.PI / 44100.0));
		count = oldCount + 1;
	}

	/**
	 * Increment count as quickly as Java allows.
	 */

	public synchronized void incrSync() {
		count++;
		/* Equivalent to count = count + 1; */ }

	/**
	 * Increment count, but in between, accumulate terms from a sin function
	 * operating at 1 Hz, 44100 samples per second. This function should, over
	 * the long term, sum to approximately zero. This is really done just to
	 * "waste time" to make the window of vulnerability larger.
	 * 
	 * @param terms
	 *            number of terms to accumulate to waste time
	 */

	public synchronized void incrSlowSync(int terms) {
		int oldCount = count;
		for (int i = 0; i < 1000; i++)
			sum += Math.sin(i * (2 * Math.PI / 44100.0));
		count = oldCount + 1;
	}

	/**
	 * Create a thread that will increment count n times.
	 * 
	 * @param n
	 *            How many times to increment
	 * @param goSlow
	 *            Whether to use the slow version
	 * @param howSlow
	 *            How many terms to accmulate
	 * @param useSynchronized
	 *            Whether to use Synchronized methods
	 */

	public MultiThreadedCounter(int n, boolean goSlow, int howSlow, boolean useSynchronized) {
		this.n = n;
		this.goSlow = goSlow;
		this.howSlow = howSlow;
		this.useSynchronized = useSynchronized;
	}

	/*
	 * run the thread and do the work of incrementing n times
	 */

	@Override
	public void run() {
		for (int i = 0; i < n; i++) {
			if (useSynchronized) {
				if (goSlow) {
					incrSlowSync(howSlow);
				} else {
					incrSync();
				}
			} else {
				if (goSlow) {
					incrSlow(howSlow);
				} else {
					incr();
				}
			}
		} // for
	} // run

	public static void main(String[] args) {
		MultiThreadedCounter mtc = new MultiThreadedCounter(1, false, 1, false);
		Thread t1 = new Thread(mtc);
		Thread t2 = new Thread(mtc);
		System.out.println("In main: About to start threads");
		t1.start();
		t2.start();
		System.out.println("In main: just started threads... waiting for t1 to finish.");

		try {
			t1.join();
			System.out.println("In main: t1 finished... waiting for t2 to finish.");
			t2.join();
			System.out.println("In main: t2 is finished");
		} catch (InterruptedException ie) {
			System.out.println(ie);
			ie.printStackTrace();
		}
		System.out.printf("count=%d sum=%f\n", mtc.getCount(), mtc.getSum());
	}
}

class AnnoyingBeep {
	Toolkit toolkit;
	Timer timer;

	public AnnoyingBeep() {
		toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, // initial delay
				1 * 1000); // subsequent rate
	}

	class RemindTask extends TimerTask {
		int numWarningBeeps = 3;

		@Override
		public void run() {
			if (numWarningBeeps > 0) {
				toolkit.beep();
				System.out.println("Beep!");
				numWarningBeeps--;
			} else {
				toolkit.beep();
				System.out.println("Time's up!");
				// timer.cancel(); //Not necessary because we call System.exit
				System.exit(0); // Stops the AWT thread (and everything else)
			}
		}
	}

	public static void main(String args[]) {
		System.out.println("About to schedule task.");
		new AnnoyingBeep();
		System.out.println("Task scheduled.");
	}
}

class SelfishRunner extends Thread {

	private int tick = 1;
	private int num;

	public SelfishRunner(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		while (tick < 400) {
			tick++;
			if ((tick % 50) == 0)
				System.out.println("Thread #" + num + ", tick = " + tick);
		}
	}
}

class RaceDemo {

	private final static int NUMRUNNERS = 2;

	public static void main(String[] args) {
		SelfishRunner[] runners = new SelfishRunner[NUMRUNNERS];

		for (int i = 0; i < NUMRUNNERS; i++) {
			runners[i] = new SelfishRunner(i);
			runners[i].setPriority(2);
		}
		for (int i = 0; i < NUMRUNNERS; i++)
			runners[i].start();
	}
}

class BadThreads {

	static String message;

	private static class CorrectorThread extends Thread {

		@Override
		public void run() {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
			// Key statement 1:
			message = "Mares do eat oats.";
		}
	}

	public static void main(String args[]) throws InterruptedException {
		// CorrectorThread t = new CorrectorThread();
		// t.join();
		(new CorrectorThread()).start();
		message = "Mares do not eat oats.";
		Thread.sleep(2000);
		// Key statement 2:
		System.out.println(message);
	}
}

class ThreadTest {
	private Vector<String> threadNames = new Vector<String>();

	public static void main(String[] args) {
		ThreadTest test = new ThreadTest();
		test.threadTest(3);
		System.out.println(test.threadNames);
	}

	private void threadTest(int numOfThreads) {
		Thread[] threads = new Thread[numOfThreads];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new ThreadTest.MyThread();
			// threads[i].start();
		}
		// for (int i = 0; i < threads.length; i++) {
		int i = 0;
		while (i < threads.length) {
			try {
				if (i == 0) {
					threads[i].start();
					threads[i].join();
					i++;
				} else if (!threads[i - 1].isAlive()) {
					threads[i].start();
					threads[i].join();
					i++;
				}
			} catch (InterruptedException ignore) {
			}
		}
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				i = i + 0;
			}
			threadNames.add(getName());
		}
	}
}