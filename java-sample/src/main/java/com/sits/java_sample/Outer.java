package com.sits.java_sample;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Outer {

	public void maina(String[] args) throws InterruptedException, ExecutionException {

		Object o1 = new Object();
		Object o2 = new Object();

		ExecutorService service = Executors.newFixedThreadPool(2);

		Future f1 = service.submit(() -> {
			synchronized (o1) {
				synchronized (o2) {
					System.out.print("Hello ");
				}
			}
		});

		Future f2 = service.submit(() -> {
			synchronized (o2) {
				synchronized (o1) {
					System.out.print("World");
				}
			}
		});

		f1.get();
		f2.get();

		service.shutdown();
	}

	public static void main(String[] args) throws ParseException {
		/*
		 * String[] s = { "abc" }; // System.out.println(sum); try { Outer o =
		 * new Outer(); o.p(null); } catch (Exception e) {
		 * System.out.println("Exception_"); //p(s); }
		 */

		/*
		 * Object o = new Object(); synchronized (Thread.currentThread()) {
		 * o.wait(); // o.notify(); }
		 */

		/*
		 * Map<Flower, String> m = new HashMap<>(); m.put(new Flower("Rose"),
		 * "Rose"); m.put(new Flower("Sun"), "sun");
		 * 
		 * String s = m.get(new Flower("Sun")); System.out.println(s);
		 */

		/*
		 * System.out.println("A");
		 * 
		 * synchronized (args) { System.out.println("B"); try { args.wait(); }
		 * catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } System.out.println("C");
		 */

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println(nf.parse("123.23456bcd"));
	}

	int p(String s) {
		return Integer.parseInt(s);
	}

	int p(Integer s) {
		return s;
	}

}

class Flower {
	String name;

	Flower(String name) {
		this.name = name;
	}
}

interface Ai {

}

class Bi implements Ai {
	public void doStuff() {
		System.out.println("B");
	}
}

class Ci extends Bi {
	@Override
	public void doStuff() {
		System.out.println("B");
	}
}

class Aa {

	List<Bi> s = new ArrayList<>();

	public void add(Collection<? extends Bi> col) {
		Bi g = new Ci();
		// col.add(new Ci());
	}

	/*
	 * int sum(int a, int b) { return a + b; }
	 */
}

class Aout extends Aa {
	public static void main(String[] args) {
		String[] s = { "abc" };
		// System.out.println(sum);
		try {
			p(s);
		} catch (Exception e) {
			System.out.println("Exception_");
			p(s);
		}
	}

	static int p(String[] s) {
		return Integer.parseInt(s[0]);
	}
}

class As {
	{
		System.out.println(1);
	}

	static {
		System.out.println(2);
	}

	public As() {
		System.out.println(3);
	}

	public static void main(String[] args) {
		As s = new As();
	}

	/*
	 * static int p(String[] s) { return Integer.parseInt(s[0]); }
	 */
}