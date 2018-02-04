package org.sits.solve;

import org.sits.custom.CustomStack;

public class DecimalToBinaryUsingStack {
	public static String convertDecialToBinary(int number) {

		StringBuilder binary = new StringBuilder();
		CustomStack<Integer> stack = new CustomStack<>(10);
		if (number == 0) {
			binary.append("0");
		} else {
			while (number != 0) {
				stack.push(number % 2);
				number = number / 2;
			}
		}
		while (!stack.isStackEmpty()) {
			try {
				binary.append(stack.pop());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return binary.toString();
	}

	public static void main(String a[]) {
		System.out.println("Binary value of 2 is: " + convertDecialToBinary(2));
		System.out.println("Binary value of 15 is: " + convertDecialToBinary(15));
		System.out.println("Binary value of 23 is: " + convertDecialToBinary(23));
	}
}
