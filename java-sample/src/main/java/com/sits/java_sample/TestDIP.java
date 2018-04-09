package com.sits.java_sample;

public class TestDIP {
	static {
		System.out.println("static block");
	}

	static void m() {
		System.out.println("static method");
	}

	public static void main(String[] args) {
		System.out.println("main");
	}
}
