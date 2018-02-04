package org.sits.probs;

public class Fibonacci {
	public static void main(String a[]) {

		int febCount = 15;
		int[] feb = new int[febCount];
		feb[0] = 0;
		feb[1] = 1;
		for (int i = 2; i < febCount; i++) {
			feb[i] = feb[i - 1] + feb[i - 2];
		}

		for (int i = 0; i < febCount; i++) {
			System.out.print(feb[i] + " ");
		}
		System.out.println("");
		StringBuilder series = new StringBuilder();
		fibonacci(series, 0, 1, 0, 15 - 2);
		System.out.println(series.toString());

	}

	public static void fibonacci(StringBuilder series, int prevalue, int crrvalue, int count, int size) {
		if (count < size) {
			int tmp = crrvalue;
			crrvalue += prevalue;
			prevalue = tmp;
			series.append(crrvalue + " ");
			fibonacci(series, prevalue, crrvalue, ++count, size);
		}
	}
}
