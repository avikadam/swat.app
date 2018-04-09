package com.sits.java_sample;

public class SecondLargest {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 5, 6, 3 };

		/* There should be at least two elements */
		if (arr.length < 2) {
			System.out.print(" Invalid Input ");
			return;
		}

		int first = Integer.MIN_VALUE;
		int second = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			// If current element is smaller than first then update both first
			// and second
			if (arr[i] > first) {
				second = first;
				first = arr[i];
			}
			// If arr[i] is in between first and second then update second
			else if (arr[i] > second && arr[i] != first) {
				second = arr[i];
			}
		}
	}
}
