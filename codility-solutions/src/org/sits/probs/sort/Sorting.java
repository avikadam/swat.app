package org.sits.probs.sort;

import java.util.stream.IntStream;

public class Sorting {

	/**
	 * Selection Sort :: The selection sort is a combination of searching and
	 * sorting. During each pass, the unsorted element with the smallest (or
	 * largest) value is moved to its proper position in the array. The number
	 * of times the sort passes through the array is one less than the number of
	 * items in the array. In the selection sort, the inner loop finds the next
	 * smallest (or largest) value and the outer loop places that value into its
	 * proper location.
	 *
	 */
	public static int[] doSelectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[j] < arr[index])
					index = j;

			int smallerNumber = arr[index];
			arr[index] = arr[i];
			arr[i] = smallerNumber;
		}
		return arr;
	}

	/**
	 * This is one of the most straightforward sorting algorithms; the core idea
	 * is to keep swapping adjacent elements of an array if they are in an
	 * incorrect order until the collection is sorted.
	 * 
	 * Small items “bubble” to the top of the list as we iterate the data
	 * structure. Hence, the technique is known as bubble sort.
	 * 
	 * As sorting is performed by swapping, we can say it performs in-place
	 * sorting.
	 * 
	 * Also, if two elements have same values, resulting data will have their
	 * order preserved – which makes it a stable sort.
	 */
	void bubbleSortJava8(Integer[] arr) {
		int n = arr.length;
		IntStream.range(0, n - 1).flatMap(i -> IntStream.range(i + 1, n - i)).forEach(j -> {
			if (arr[j - 1] > arr[j]) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}
		});
	}

	public static int[] bubbleSort(int[] x) {
		int newLowest = 0; // index of first comparison
		int newHighest = x.length - 1; // index of last comparison

		while (newLowest < newHighest) {
			int highest = newHighest;
			int lowest = newLowest;
			newLowest = x.length; // start higher than any legal index
			for (int i = lowest; i < highest; i++) {
				if (x[i] > x[i + 1]) {
					// exchange elements
					int temp = x[i];
					x[i] = x[i + 1];
					x[i + 1] = temp;
					if (i < newLowest) {
						newLowest = i - 1;
						if (newLowest < 0) {
							newLowest = 0;
						}
					} else if (i > newHighest) {
						newHighest = i + 1;
					}
					// newLowest = 0;
				}
			}
		}
		return x;
	}// end method bubbleSort4

	public static void main(String a[]) {
		int[] arr1 = { 10, 34, 2, 56, 7, 67, 88, 42 };
		int[] arr2 = bubbleSort(arr1);
		boolean flag = false;
		for (int i : arr2) {
			if (flag)
				System.out.print(", ");
			System.out.print(i);
			flag = true;
		}
	}
}
