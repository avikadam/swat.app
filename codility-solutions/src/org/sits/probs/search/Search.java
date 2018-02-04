package org.sits.probs.search;

import java.util.Random;

public class Search {
	private static final Random generator = new Random();
	private static final int size = 30;

	/**
	 * The binary search algorithm is more efficient than linear search, but it
	 * requires that the array be sorted. The first iteration of this algorithm
	 * tests the middle element in the array. If this matches the search key,
	 * the algorithm ends. Assuming the array is sorted in ascending order, then
	 * if the search key is less than the middle element, it cannot match any
	 * element in the second half of the array and the algorithm continues with
	 * only the first half of the array (i.e., the first element up to, but not
	 * including, the middle element). If the search key is greater than the
	 * middle element, it cannot match any element in the first half of the
	 * array and the algorithm continues with only the second half (i.e., the
	 * element after the middle element through the last element). Each
	 * iteration tests the middle value of the remaining portion of the array.
	 * If the search key does not match the element, the algorithm eliminates
	 * half of the remaining elements. The algorithm ends by either finding an
	 * element that matches the search key or reducing the sub array to zero
	 * size.
	 * 
	 * As an example consider the sorted 15-element array
	 * 
	 * 2 3 5 10 27 30 34 51 56 65 77 81 82 93 99
	 * 
	 * and a search key of 65. A program implementing the binary search
	 * algorithm would first check whether 51 is the search key (because 51 is
	 * the middle element of the array). The search key (65) is larger than 51,
	 * so 51 is ignored along with the first half of the array (all elements
	 * smaller than 51), leaving
	 * 
	 * 56 65 77 81 82 93 99
	 * 
	 * Next, the algorithm checks whether 81 (the middle element of the
	 * remainder of the array) matches the search key. The search key (65) is
	 * smaller than 81, so 81 is discarded along with the elements larger than
	 * 81. After just two tests, the algorithm has narrowed the number of values
	 * to check to only three (56, 65 and 77). It then checks 65 (which indeed
	 * matches the search key) and returns the index of the array element
	 * containing 65. This algorithm required just three comparisons to
	 * determine whether the search key matched an element of the array. Using a
	 * linear search algorithm would have required 10 comparisons.
	 * 
	 * @param searchKey
	 * @param data
	 * @return
	 */
	public static int binarySearch(int searchKey, int[] data) {
		int low = 0; // low end of the search area
		int high = data.length - 1; // high end of the search area
		int middle = (low + high + 1) / 2; // middle element
		int location = -1; // return value; -1 if not found

		do {
			// if the element is found at the middle
			if (searchKey == data[middle])
				location = middle; // location is the current middle
			// middle element is too high
			else if (searchKey < data[middle])
				high = middle - 1; // eliminate the higher half
			else // middle element is too low
				low = middle + 1; // eliminate the lower half
			middle = (low + high + 1) / 2; // recalculate the middle
		} while ((low <= high) && (location == -1));

		return location;
	}

	/**
	 * Recursion is used in this algorithm because with each pass a new array is
	 * created by cutting the old one in half. The binary search procedure is
	 * then called recursively, this time on the new array. Typically the
	 * array's size is adjusted by manipulating a beginning and ending index.
	 * The algorithm exhibits a logarithmic order of growth because it
	 * essentially divides the problem domain in half with each pass.
	 * 
	 * @param searchKey
	 * @param data
	 * @param start
	 * @param end
	 * @return
	 */
	public static int binarySearchRecursive(int searchKey, int[] data, int start, int end) {
		if (start < end) {
			int middle = (start + end + 1) / 2; // middle element

			if (searchKey == data[middle]) {
				return middle;
			} else if (searchKey < data[middle]) {
				return binarySearchRecursive(searchKey, data, start, middle);
			} else {
				return binarySearchRecursive(searchKey, data, middle, end);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] data = new int[size];

		// fill array with random ints in range 0-9
		for (int i = 0; i < size; i++)
			data[i] = generator.nextInt(10);

		System.out.println(binarySearch(4, data));

		int[] arr1 = { 2, 45, 234, 567, 876, 900, 976, 999 };
		System.out.println(binarySearchRecursive(876, arr1, 0, arr1.length - 1));
	}
}
