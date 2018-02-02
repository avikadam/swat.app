package org.sits.probs;

import java.util.HashSet;

public class AbsDistinct {
	public static int solution(int[] A) {
		int count = 0;
		HashSet<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < A.length; i++) {
			int number = Math.abs(A[i]);
			if (!set.contains(number)) {
				set.add(number);
				count++;
			}
		}

		return count;
	}

	static void findAbs(int[] a) {
		for (int v : a) {
			System.out.println(Math.abs(v));
		}
	}

	public static void main(String[] args) {
		int[] A = { 1, 4, 34, 2, 4, 6, 3, 6, 7, 5, 8, 28 };
		// findAbs(A);
		System.out.println(solution(A));
	}
}
