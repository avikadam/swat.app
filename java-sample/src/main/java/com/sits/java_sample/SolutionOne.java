package com.sits.java_sample;

public class SolutionOne {
	static int solution(int[] A) {
		int ans = 0;
		for (int i = 0; i < A.length; i++) {
			ans += A[i];
		}
		return ans;
	}

	/*
	 * public static void main(String[] args) { int[] A = { 1, 2, 3, 4, 5 }; //
	 * System.out.println(solution(A)); int N = 33; if (N > 1) { for (int j = 1;
	 * j <= N; j++) { System.out.println(deriveReplacement(j)); } } else {
	 * System.out.println(N); } }
	 */

	static String deriveReplacement(int number) {
		StringBuilder sb = new StringBuilder();
		if (number % 3 == 0) {
			sb.append("Fizz");
		}
		if (number % 5 == 0) {
			sb.append("Buzz");
		}
		if (number % 7 == 0) {
			sb.append("Woof");
		}

		return sb.length() == 0 ? String.valueOf(number) : sb.toString();
	}

	static int findIndex(String str) {
		int len = str.length();
		int open[] = new int[len + 1];
		int close[] = new int[len + 1];
		int index = -1;

		for (int i = 0; i < len; i++) {
			if (str.charAt(i) == '(') {
				open[i + 1] = open[i] + 1;
			} else {
				open[i + 1] = open[i];
			}
		}

		for (int i = len - 1; i >= 0; i--) {
			if (str.charAt(i) == ')') {
				close[i] = close[i + 1] + 1;
			} else {
				close[i] = close[i + 1];
			}
		}

		if (open[len] == 0 || close[0] == 0) {
			return len;
		}

		for (int i = 0; i <= len; i++) {
			if (open[i] == close[i]) {
				index = i;
			}
		}
		return index;
	}

	// Driver Method
	public static void main(String[] args) {
		String str = "((";
		System.out.println(findIndex(str));
	}
}
