package org.sits.probs;

public class BinaryGap {
	static int solution(int N) {
		int maxGap = 0;
		boolean firstOne = true;
		int gap = 0;
		while (N != 0) {
			int bit = N % 2;
			if (bit == 0) {
				gap++;
			} else {
				if (firstOne) {
					firstOne = false;
				} else {
					maxGap = Math.max(maxGap, gap);
				}
				gap = 0;
			}
			N /= 2;
		}
		return maxGap;
	}

	static String findBinary(int N) {
		StringBuilder binary = new StringBuilder();
		while (N != 0) {
			binary.append(N % 2);
			N = N / 2;
		}
		return binary.toString();
	}

	public static void main(String[] args) {
		System.out.println(solution(4));// 10100
		System.out.println(findBinary(4));
		System.out.println(Integer.toBinaryString(1041));
	}
}
