package com.sits.java_sample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Scanner;

public class PrintNumber {

	// @Test
	public void mainOther(String[] args) {

		// ListIterator s
		try (Scanner c = new Scanner(System.in);) {
			int t = c.nextInt();
			try (Scanner c1 = new Scanner(System.in);) {
				String s = c1.nextLine();
				String[] val = s.split(" ");
				for (int i = 0; i < t; i++) {
					int n = Integer.valueOf(val[i]);
					for (int j = 1; j <= n; j++) {
						printnumber(j);
					}
				}
			}
		}
	}

	public static void printnumber(int number) {
		if (number % 3 == 0 && number % 5 == 0) {
			System.out.println("FizzBuzz");
		} else if (number % 3 == 0) {
			System.out.println("Fizz");
		} else if (number % 5 == 0) {
			System.out.println("Buzz");
		} else if (number % 7 == 0) {
			System.out.println("Buzz");
		} else {
			System.out.println(number);
		}
	}

	String replace(int number) {
		StringBuilder sb = new StringBuilder();
		if (number % 3 == 0) {
			sb.append("Fizz");
		} else if (number % 5 == 0) {
			sb.append("Buzz");
		} else if (number % 7 == 0) {
			sb.append("Buzz");
		}

		return sb.length() == 0 ? String.valueOf(number) : sb.toString();
	}

	public static void main(String[] args) {
		/*
		 * Arrays.stream(new int[] { 1, 2, 3, 4 }).map(n -> 3 * n + 1).filter(i
		 * -> i > 10).average() .ifPresent(System.out::println);
		 */

		Double d1 = new Double("2.0");
		Double d2 = new Double("2.00");

		BigDecimal b1 = new BigDecimal("2.0");
		BigDecimal b2 = new BigDecimal("2.00");

		System.out.println(d1.equals(d2) + "" + b1.equals(b2));
	}

	// @Test
	public static void maina(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");

		String[] arr = br.readLine().split(" ");

		if (Integer.parseInt(s[0]) != arr.length) {
			throw new IllegalArgumentException();
		}
		// int[] arr = { 1, 4, 5, 6, 1, 7 };
		System.out.println(getLastOccurence(arr, s[1]));
	}

	public static int getLastOccurence(String[] arr, String number) {
		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i].equals(number)) {
				return i;
			}
		}
		return -1;
	}
}

class Shape implements Serializable {
	private Square s = new Square();

	public static void main(String[] args) {
		Shape s = new Shape();

		try {
			/*
			 * FileOutputStream fo = new FileOutputStream("f.ser");
			 * ObjectOutputStream oo = new ObjectOutputStream(fo);
			 * oo.writeObject(s); oo.close();
			 */

			FileInputStream fi = new FileInputStream("f.ser");
			ObjectInputStream oi = new ObjectInputStream(fi);
			Object o = oi.readObject();
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Square {

}

class Solution {
	static int solution(int[] A) {
		int n = A.length;
		int i = n - 1;
		int result = -1;
		int k = 0, maximal = 0;
		while (i > 0) {
			if (A[i] == 1) {
				k = k + 1;
				if (k >= maximal) {
					maximal = k;
					result = i;
				}
			} else {
				k = 0;
			}
			i = i - 1;
		}
		if (A[i] == 1 && k + 1 > maximal)
			result = 0;
		return result;
	}

	public static void main(String[] args) {
		int[] a = { 0, 0, 1, 0, 1, 0, 1 };
		System.out.println(solution(a));
	}
}

class LatticePoint {
	// 1,2
}