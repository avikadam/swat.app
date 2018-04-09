package com.sits.java_sample;

import java.util.Scanner;

public class CodingBBD {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		// final String fileName = System.getenv("OUTPUT_PATH");
		boolean res;
		int valueToCheck = Integer.parseInt(in.nextLine().trim());
		res = isFibo(valueToCheck, 0, 1);
		System.out.println((res) ? 1 : 0);
	}

	static boolean isFibo(int valueToCheck, int previousValue, int currentValue) {

		return currentValue < valueToCheck ? false
				: valueToCheck == currentValue ? true
						: isFibo(valueToCheck, previousValue + currentValue, currentValue);
		/*
		 * return currentValue < valueToCheck ? false : valueToCheck ==
		 * currentValue ? true : isFibo(valueToCheck, previousValue +
		 * currentValue, currentValue);
		 */
	}
}
