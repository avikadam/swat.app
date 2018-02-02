package org.sits.solve;

import java.util.Arrays;
import java.util.Stack;

/**
 * The stock span problem is a financial problem where we have a series of n
 * daily price quotes for a stock and we need to calculate span of stock’s price
 * for all n days. The span Si of the stock’s price on a given day i is defined
 * as the maximum number of consecutive days just before the given day, for
 * which the price of the stock on the current day is less than or equal to its
 * price on the given day. For example, if an array of 7 days prices is given as
 * {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days
 * are {1, 1, 1, 2, 1, 4, 6}
 * 
 * @author admin
 *
 */
public class StockSpanProblem {
	// method to calculate stock span values
	// Time complexity : O(n^2)
	static void calculateSpan(int price[], int span[]) {
		// Span value of first day is always 1
		span[0] = 1;

		// Calculate span value of remaining days by linearly checking
		// previous days
		for (int i = 1; i < price.length; i++) {
			span[i] = 1; // Initialize span value

			// Traverse left while the next element on left is smaller
			// than price[i]
			for (int j = i - 1; (j >= 0) && (price[i] >= price[j]); j--)
				span[i]++;
		}
	}

	// a linear time solution for stock span problem
	// A stack based efficient method to calculate stock span values
	// Time complexity : O(n)
	static void calculateSpan(int price[], int n, int space[]) {
		// Create a stack and push index of first element to it
		Stack<Integer> st = new Stack<>();
		st.push(0);

		// Span value of first element is always 1
		space[0] = 1;

		// Calculate span values for rest of the elements
		for (int i = 1; i < n; i++) {
			// Pop elements from stack while stack is not empty and top of
			// stack is smaller than price[i]
			while (!st.empty() && price[st.peek()] <= price[i])
				st.pop();

			// If stack becomes empty, then price[i] is greater than all
			// elements
			// on left of it, i.e., price[0], price[1],..price[i-1]. Else
			// price[i]
			// is greater than elements after top of stack
			space[i] = (st.empty()) ? (i + 1) : (i - st.peek());

			// Push this element to stack
			st.push(i);
		}
	}

	// A utility function to print elements of array
	static void printArray(int arr[]) {
		System.out.print(Arrays.toString(arr));
	}

	// Driver program to test above functions
	public static void main(String[] args) {
		int price[] = { 10, 4, 5, 90, 120, 80 };
		int span[] = new int[price.length];

		// Fill the span values in array S[]
		calculateSpan(price, span);

		// print the calculated span values
		printArray(span);
	}
}
