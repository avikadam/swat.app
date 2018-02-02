package org.sits.probs;

import java.util.HashMap;

/**
 * 
 * Given an array of distinct integers, find if there are two pairs (a, b) and
 * (c, d) such that a+b = c+d, and a, b, c and d are distinct elements. If there
 * are multiple answers, then print any of them.
 * 
 * Example:
 * 
 * Input: {3, 4, 7, 1, 2, 9, 8} Output: (3, 8) and (4, 7) Explanation: 3+8 = 4+7
 * 
 * Input: {3, 4, 7, 1, 12, 9}; Output: (4, 12) and (7, 9) Explanation: 4+12 =
 * 7+9
 * 
 * Input: {65, 30, 7, 90, 1, 9, 8}; Output: No pairs found Expected Time
 * Complexity: O(n2)
 * 
 * @author admin
 *
 */
public class FindPairs {
	// Class to represent a pair
	class Pair {
		int first, second;

		Pair(int f, int s) {
			first = f;
			second = s;
		}
	}

	boolean findPairs(int arr[]) {
		// Create an empty Hash to store mapping from sum to
		// pair indexes
		HashMap<Integer, Pair> map = new HashMap<Integer, Pair>();
		int n = arr.length;

		// Traverse through all possible pairs of arr[]
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				// If sum of current pair is not in hash,
				// then store it and continue to next pair
				int sum = arr[i] + arr[j];
				if (!map.containsKey(sum)) {
					map.put(sum, new Pair(i, j));

				} else {
					// Else (Sum already present in hash)

					// Find previous pair
					Pair p = map.get(sum);

					// Since array elements are distinct, we don't
					// need to check if any element is common among pairs
					System.out.println(
							"(" + arr[p.first] + ", " + arr[p.second] + ") and (" + arr[i] + ", " + arr[j] + ")");
					return true;
				}
			}
		}
		return false;
	}

	// Testing program
	public static void main(String args[]) {
		int arr[] = { 3, 4, 7, 1, 2, 9, 8 };
		FindPairs a = new FindPairs();
		a.findPairs(arr);
	}
}
