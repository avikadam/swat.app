package com.sits.java_sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// System.out.println( new StringBuffer("Hello World!").reverse() );

		String original2 = "", reverse2 = "";

		Scanner in2 = new Scanner(System.in);
		System.out.println("Enter the String2");
		original2 = in2.nextLine();
		// System.out.println(in2.nextLine());
		reverse2 = new StringBuffer(original2).reverse().toString();
		System.out.println("reverse string is =" + reverse2);

		List<A> lst = new ArrayList<>();
		lst.add(new B());
		lst.add(new C());

		lst.stream().forEach(System.out::print);

		Stack s = new Stack();
	}
}

interface A {

}

class B implements A {
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}

class C implements A {
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
