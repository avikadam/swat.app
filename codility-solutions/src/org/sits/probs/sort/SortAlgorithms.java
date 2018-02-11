package org.sits.probs.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * Eight sorting algorithm to achieve
 *
 * 1. Direct insertion sort { @link #insertionSort} 2. Hill sort { @link
 * #shellSort} 3. Simple Selection Sort { @link #selectionSort} 4. Heap ordering
 * { @link #heapSort} 5. Bubble Sort { @link #bubbleSort} 6. Quick Sort { @link
 * #quickSort} 7. Merge sorts { @link #mergingSort} 8. radix sort { @link
 * #radixSort}
 *
 * Algorithm Test Class See { @link Test} Algorithm Evaluation Analysis Class
 * See { @link Bench}
 *
 */
class SortAlgorithms {
	public static final boolean ENABLE_PRINT = false;

	public static void main(String[] args) {
		// int [] array = new int [] {3,5,3,0,8,6,1,5,8,6,2,4,9,4,7,0,1,8,9, 7,
		// 3, 1, 2, 5, 9, 7, 4, 0, 2, 6};
		int[] array = new int[] { 5, 3, 9, 1, 6, 4, 10, 2, 8, 7, 15, 3, 2 };
		// int [] array = new int [] {1, 1, 0};

		System.out.println(" Before: " + Arrays.toString(array));
		// SortAlgorithms.insertionSort(array);
		// SortAlgorithms.shellSort (array);
		// SortAlgorithms.selectionSort (array);
		// SortAlgorithms.heapSort (array);
		// SortAlgorithms.bubbleSort (array);
		// SortAlgorithms.quickSort (array, 0, array.length-1);
		// SortAlgorithms.quickSortByStack (array);
		array = SortAlgorithms.mergingSort(array);
		// SortAlgorithms.radixSort (array);
		System.out.println(" After:   " + Arrays.toString(array));
	}

	/**
	 * Unified control console output
	 */
	private static void System_out_println(String str) {
		if (ENABLE_PRINT) {
			System.out.println(str);
		}
	}

	/**
	 * Insert directly into the order
	 *
	 * 1. Starting from the first element, the element can be considered as
	 * being sorted
	 * 
	 * 2. Remove the next element to scan from back to front in the sorted
	 * sequence of elements
	 * 
	 * 3. If the element (sorted) is greater than the new element, move the
	 * element to the next position
	 * 
	 * 4. Repeat step 3 until you find that the sorted element is less than or
	 * equal to the new element's position 5. Insert a new element into the
	 * position 6. Repeat steps 2 to 5
	 * 
	 * @param arr
	 *            array to be sorted
	 */
	public static void insertionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (arr[j - 1] <= arr[j])
					break;
				int temp = arr[j]; // Exchange operation
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
				System_out_println(" Sorting:   " + Arrays.toString(arr));
			}
		}
	}

	/**
	 * Shell sort
	 *
	 * 1. Select an incremental sequence t1, t2, ..., tk, where ti> tj, tk = 1;
	 * (usually the first time take the array half a length, then halved each
	 * time until the increment is 1) 2. According to the number of incremental
	 * sequence k, the sequence of k times sorting; 3. Each trip, according to
	 * the corresponding increment ti, will be sorted into a number of
	 * sub-sequences of length m, respectively, the sub-table for direct
	 * insertion sort. Only increment factor is 1, the entire sequence as a
	 * table to deal with, the table length is the length of the entire
	 * sequence.
	 * 
	 * @param arr
	 *            array to be sorted
	 */
	public static void shellSort(int[] arr) {
		int gap = arr.length / 2;
		for (; gap > 0; gap /= 2) { // Keep narrowing gaps until 1
			System_out_println(" Gap = " + gap);
			for (int j = 0; (j + gap) < arr.length; j++) { // Use the current
															// gap for group
															// insertion
				for (int k = 0; (k + gap) < arr.length; k += gap) {
					System_out_println(" Compare: arr [ " + (k + gap) + " ] = " + arr[k + gap] + " , arr [ " + k
							+ " ] = " + arr[k]);
					if (arr[k] > arr[k + gap]) {
						int temp = arr[k + gap]; // Exchange operation
						arr[k + gap] = arr[k];
						arr[k] = temp;
						System_out_println("     Sorting:   " + Arrays.toString(arr));
					}
				}
			}
		}
	}

	/**
	 * Hill sort (Wiki official version)
	 *
	 * 1. Select an incremental sequence t1, t2, ..., tk where ti> tj, tk = 1;
	 * (note the gap value of this algorithm) 2. According to the number of
	 * incremental sequence k, the sequence of k times sorting; 3. Each trip,
	 * according to the corresponding increment ti, will be sorted into a number
	 * of sub-sequences of length m, respectively, the sub-table for direct
	 * insertion sort. Only increment factor is 1, the entire sequence as a
	 * table to deal with, the table length is the length of the entire
	 * sequence.
	 * 
	 * @param arr
	 *            array to be sorted
	 */
	public static void shell_sort(int[] arr) {
		int gap = 1, i, j, len = arr.length;
		int temp;
		while (gap < len / 3)
			gap = gap * 3 + 1; // <O (n ^ (3/2)) by Knuth, 1973>: 1, 4, 13, 40,
								// 121, ...
		for (; gap > 0; gap /= 3) {
			for (i = gap; i < len; i++) {
				temp = arr[i];
				for (j = i - gap; j >= 0 && arr[j] > temp; j -= gap)
					arr[j + gap] = arr[j];
				arr[j + gap] = temp;
			}
		}
	}

	/**
	 * Choose Sort
	 *
	 * 1. From the to-be-sorted sequence, find the element with the smallest
	 * keyword; 2. If the smallest element is not the first element to be
	 * sorted, swap it with the first element; 3. From the remaining N - 1
	 * elements, find the smallest key element, repeat, step, until the end of
	 * the sort. Only increment factor is 1, the entire sequence as a table to
	 * deal with, the table length is the length of the entire sequence.
	 * 
	 * @param arr
	 *            array to be sorted
	 */
	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) { // Select the position to
														// be sorted with the
														// smallest value
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = arr[min]; // Exchange operation
				arr[min] = arr[i];
				arr[i] = temp;
				System_out_println(" Sorting:   " + Arrays.toString(arr));
			}
		}
	}

	/**
	 * Heap sort
	 *
	 * 1. First initial sequence K [1..n] into a large top heap, then the first
	 * element K1 maximum, the heap for the initial disorder zone. 2. And then
	 * the keyword maximum record K1 (ie, the top of the heap, the first
	 * element) with the last record of the disorder zone Kn exchange, resulting
	 * in a new disorder zone K [1..n-1] and K [n], and satisfy K [1..n-1]
	 * .keysK [n] .key 3. After exchanging K1 and Kn, the top of the heap may
	 * violate the heap property, so adjust K [1..n-1] to heap, and then repeat
	 * step until it stops when there is only one element in the out-of-order
	 * area.
	 * 
	 * @param arr
	 *            array to be sorted
	 */
	public static void heapSort(int[] arr) {
		for (int i = arr.length; i > 0; i--) {
			max_heapify(arr, i);

			int temp = arr[0]; // Heap elements (first element) and Kn exchange
			arr[0] = arr[i - 1];
			arr[i - 1] = temp;
		}
	}

	private static void max_heapify(int[] arr, int limit) {
		if (arr.length <= 0 || arr.length < limit)
			return;
		int parentIdx = limit / 2;

		for (; parentIdx >= 0; parentIdx--) {
			if (parentIdx * 2 >= limit) {
				continue;
			}
			int left = parentIdx * 2; // left child node location
			int right = (left + 1) >= limit ? left : (left + 1); // right child
																	// node
																	// position,
																	// if there
																	// is no
																	// right
																	// node, the
																	// default
																	// position
																	// for the
																	// left node

			int maxChildId = arr[left] >= arr[right] ? left : right;
			if (arr[maxChildId] > arr[parentIdx]) { // exchange the maximum
													// value of the parent node
													// and the left and right
													// child nodes
				int temp = arr[parentIdx];
				arr[parentIdx] = arr[maxChildId];
				arr[maxChildId] = temp;
			}
		}
		System_out_println(" Max_Heapify: " + Arrays.toString(arr));
	}

	/**
	 * Bubble Sort
	 *
	 * Compare adjacent elements. If the first one is bigger than the second,
	 * swap them both. For each pair of adjacent elements for the same work,
	 * from the beginning of the first pair to the end of the last pair. After
	 * this step is done, the final element will be the largest number. Repeat
	 * the above steps for all the elements except the last one. Repeat the
	 * above steps for fewer and fewer elements at a time until there is no need
	 * to compare any pair of numbers.
	 * 
	 * @param arr
	 *            array to be sorted
	 */
	public static void bubbleSort(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--) { // The outer loop moves the
													// cursor
			for (int j = 0; j < i; j++) { // The inner loop iterates through the
											// cursor and after (or before) the
											// element
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					System_out_println(" Sorting: " + Arrays.toString(arr));
				}
			}
		}
	}

	/**
	 * Quick Sort (recursive)
	 *
	 * Pick an element from the sequence, called the "pivot." To rearrange the
	 * sequence, all elements smaller than the base are placed before the base,
	 * and all elements larger than the base are placed behind the base (the
	 * same number can be either side). After the partition ends, the baseline
	 * is in the middle of the series. This is called a partition operation.
	 * Recursively sorts subsequences that are smaller than the reference value
	 * element and subsequences that are larger than the reference value
	 * element.
	 * 
	 * @param arr
	 *            array to be sorted
	 * @param low
	 *            left border
	 * @param high
	 *            right border
	 */
	public static void quickSort(int[] arr, int low, int high) {
		if (arr.length <= 0)
			return;
		if (low >= high)
			return;
		int left = low;
		int right = high;

		int temp = arr[left]; // Digging 1: Save the value of the datum
		while (left < right) {
			while (left < right && arr[right] >= temp) { // Pit 2: An element
															// smaller than the
															// reference is
															// found from back
															// to front and is
															// inserted into pit
															// 1 of the
															// reference
															// position
				right--;
			}
			arr[left] = arr[right];
			while (left < right && arr[left] <= temp) { // Pit 3: Find the
														// larger element from
														// the front to the pit
														// 2 you just dug
				left++;
			}
			arr[right] = arr[left];
		}
		arr[left] = temp; // Reference value to fill pit 3, ready to recurse
							// quickly sorted row
		System_out_println(" Sorting: " + Arrays.toString(arr));
		quickSort(arr, low, left - 1);
		quickSort(arr, left + 1, high);
	}

	/**
	 * Quick sort (non-recursive)
	 *
	 * Pick an element from the sequence, called the "pivot." To rearrange the
	 * sequence, all elements smaller than the base are placed before the base,
	 * and all elements larger than the base are placed behind the base (the
	 * same number can be either side). After the partition ends, the baseline
	 * is in the middle of the series. This is called a partition operation.
	 * Divide the boundaries of the two sections after the partition (low and
	 * high) onto the stack to save and cycle, steps
	 * 
	 * @param arr
	 *            array to be sorted
	 */
	public static void quickSortByStack(int[] arr) {
		if (arr.length <= 0)
			return;
		Stack<Integer> stack = new Stack<Integer>();

		// The initial state of the left and right pointer into the stack
		stack.push(0);
		stack.push(arr.length - 1);
		while (!stack.isEmpty()) {
			int high = stack.pop(); // popout of the division
			int low = stack.pop();

			int pivotIdx = partition(arr, low, high);

			// save the intermediate variable
			if (pivotIdx > low) {
				stack.push(low);
				stack.push(pivotIdx - 1);
			}
			if (pivotIdx < high && pivotIdx >= 0) {
				stack.push(pivotIdx + 1);
				stack.push(high);
			}
		}
	}

	private static int partition(int[] arr, int low, int high) {
		if (arr.length <= 0)
			return -1;
		if (low >= high)
			return -1;
		int l = low;
		int r = high;

		int pivot = arr[l]; // digging 1: save the value of the base
		while (l < r) {
			while (l < r && arr[r] >= pivot) { // Pit 2: An element smaller than
												// the reference is found from
												// back to front and is inserted
												// into the pit 1 at the
												// reference position
				r--;
			}
			arr[l] = arr[r];
			while (l < r && arr[l] <= pivot) { // Pit 3: Find the larger element
												// from the front to the pit 2
												// you just dug
				l++;
			}
			arr[r] = arr[l];
		}
		arr[l] = pivot; // Reference value to fill the pit 3, ready to divide
						// recursive fast row
		return l;
	}

	/**
	 * Merge sort (recursive)
	 *
	 * Combine each adjacent two digits of the sequence to form floor (n / 2)
	 * sequences, each sequence contains two elements after sorting; The above
	 * sequence again merged to form floor (n / 4) sequences, each sequence
	 * contains four elements; Repeat step until all the elements are sorted.
	 * 
	 * @param arr
	 *            array to be sorted
	 */
	public static int[] mergingSort(int[] arr) {
		if (arr.length <= 1)
			return arr;

		int num = arr.length >> 1;
		int[] leftArr = Arrays.copyOfRange(arr, 0, num);
		int[] rightArr = Arrays.copyOfRange(arr, num, arr.length);
		System_out_println("split two array: " + Arrays.toString(leftArr) + " And " + Arrays.toString(rightArr));
		return mergeTwoArray(mergingSort(leftArr), mergingSort(rightArr));
	}

	private static int[] mergeTwoArray(int[] arr1, int[] arr2) {
		int i = 0, j = 0, k = 0;
		int[] result = new int[arr1.length + arr2.length]; // Apply extra space
															// to store the
															// array after the
															// merge
		while (i < arr1.length && j < arr2.length) { // Pick the smaller of the
														// two sequences into
														// the new array
			if (arr1[i] <= arr2[j]) {
				result[k++] = arr1[i++];
			} else {
				result[k++] = arr2[j++];
			}
		}
		while (i < arr1.length) { // The extra elements in sequence 1 are moved
									// into the new array
			result[k++] = arr1[i++];
		}
		while (j < arr2.length) { // The extra elements in sequence 2 are moved
									// into the new array
			result[k++] = arr2[j++];
		}
		System_out_println(" Merging: " + Arrays.toString(result));
		return result;
	}

	/**
	 * Base sort (LSD starts from low)
	 *
	 * Cardinal sorting applies to: (1) The data range is small, it is
	 * recommended to be less than 1000 (2) Each value must be greater than or
	 * equal to 0
	 *
	 * Get the largest number in the array, and obtain the median; Arr for the
	 * original array, from the lowest bit to take each bit radix array; Count
	 * radix sorts (use count ordering for small range features);
	 * 
	 * @param arr
	 *            array to be sorted
	 */
	public static void radixSort(int[] arr) {
		if (arr.length <= 1)
			return;

		// get the maximum number of array and made-digit
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		int maxDigit = 1;
		while (max / 10 > 0) {
			maxDigit++;
			max = max / 10;
		}
		System_out_println(" maxDigit: " + maxDigit);

		// apply for a bucket of space
		int[][] buckets = new int[10][arr.length];
		int base = 10;

		// From low to high, for each traversal, all the elements are assigned
		// to the bucket
		for (int i = 0; i < maxDigit; i++) {
			int[] bktLen = new int[10]; // Stores the number of storage elements
										// in each bucket

			// Assign: Assign all the elements to the bucket
			for (int j = 0; j < arr.length; j++) {
				int whichBucket = (arr[j] % base) / (base / 10);
				buckets[whichBucket][bktLen[whichBucket]] = arr[j];
				bktLen[whichBucket]++;
			}

			// Collection: one by one different data in the bucket out, to
			// prepare for the next round of high order, due to the elements
			// near the bottom of the tank top rankings, so fishing from the
			// bottom of the bucket
			int k = 0;
			for (int b = 0; b < buckets.length; b++) {
				for (int p = 0; p < bktLen[b]; p++) {
					arr[k++] = buckets[b][p];
				}
			}

			System_out_println(" Sorting: " + Arrays.toString(arr));
			base *= 10;
		}
	}
}