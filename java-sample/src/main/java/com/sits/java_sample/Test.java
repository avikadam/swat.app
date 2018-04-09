package com.sits.java_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) throws IOException {
		// System.out.println(gcd(10, 15));
		// System.out.println(12 % 15);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		if (s.length != 2) {
			throw new IllegalArgumentException();
		}
		List<Integer> divisors = findCommonDivisor(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		divisors.stream().forEach(System.out::println);
		System.out.println(findNoOfCommonDivisors(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
	}

	static int gcd(int a, int b) {
		while (a != 0 && b != 0) // until either one of them is 0
		{
			int c = b;
			b = a % b;
			a = c;
		}
		return a + b; // either one is 0, so return the non-zero value
	}

	static List<Integer> findCommonDivisor(int a, int b) {
		int small = a > b ? a : b;
		Math.sqrt(a);
		List<Integer> divisors = new ArrayList<>();
		for (int i = 1; i <= small / 2; i++) {
			if (a % i == 0 && b % i == 0) {
				divisors.add(i);
			}
		}
		return divisors;
	}

	static int findNoOfCommonDivisors(int a, int b) {
		int small = a > b ? a : b;
		int count = 0;
		for (int i = 1; i <= small / 2; i++) {
			if (a % i == 0 && b % i == 0) {
				count++;
			}
		}
		return count;
	}
}
