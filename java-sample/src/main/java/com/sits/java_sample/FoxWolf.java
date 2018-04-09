package com.sits.java_sample;

import java.util.Scanner;

public class FoxWolf {
	static class Animal {
		private final String name;

		public Animal(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public synchronized void biteNrunAway(Animal bitter) {
			System.out.format("%s bites %s and %s run away%n", this.name, bitter.getName(), bitter.getName());
		}

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String _name1;
		String _name2;
		try {
			_name1 = in.nextLine();
			_name2 = in.nextLine();
		} catch (Exception e) {
			_name1 = null;
			_name2 = null;
		}

		final Animal wolf = new Animal(_name1);
		final Animal fox = new Animal(_name2);
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				wolf.biteNrunAway(fox);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				fox.biteNrunAway(wolf);
			}
		});
		thread1.start();
		thread2.start();
	}
}
