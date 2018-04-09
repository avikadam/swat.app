package com.sits.java_sample;

public class Java8 {
	String sound = "Default";
	Animal dog = new Animal() {
		String sound = "Bark";

		@Override
		public void sound() {
			System.out.println(this.sound);
		}
	};

	Animal sheep = () -> {
		String sound = "Baa";
		System.out.println(this.sound);
	};

	public static void main(String[] args) {
		Java8 j8 = new Java8();
		j8.dog.sound();
		j8.sheep.sound();
	}
}

interface Animal {
	void sound();
}
