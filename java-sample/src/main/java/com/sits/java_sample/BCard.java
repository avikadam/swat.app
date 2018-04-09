package com.sits.java_sample;

import java.util.ArrayList;
import java.util.List;

public class BCard {

	public static void main(String[] args) {
		int N = 1;
		String reserved = "1A 1G 1C";
		main1(N, reserved);
	}

	public static void main1(int N, String S) {
		String[] seatsR = (S.trim().isEmpty()) ? new String[0] : S.split(" ");
		String[] col = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K" };

		List<List<List<String>>> ls = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			List<List<String>> row = new ArrayList<>();
			List<String> section1 = new ArrayList<>();
			List<String> section2 = new ArrayList<>();
			List<String> section3 = new ArrayList<>();
			for (int j = 1; j <= 10; j++) {

				if (j < 4) {
					section1.add(i + col[j - 1]);
				}
				if (j >= 4 && j < 8) {
					section2.add(i + col[j - 1]);
				}
				if (j >= 8) {
					section3.add(i + col[j - 1]);
				}
			}
			row.add(section1);
			row.add(section2);
			row.add(section3);
			ls.add(row);
		}
		int count = ls.size() * 3;

		for (List<List<String>> row : ls) {
			for (int j = 1; j < row.size(); j++) {
				int taken = 0;
				for (int i = 1; i <= seatsR.length; i++) {
					if (seatsR[i - 1].charAt(0) - '0' == j && sd(row, seatsR[i - 1], taken)) {
						count--;
					}
				}
			}
		}
		System.out.println(count);

	}

	static boolean sd(List<List<String>> row, String val, int taken) {

		if ("DG".contains(String.valueOf(val.charAt(1))) && taken < 1) {
			taken++;
			return false;
		}
		return row.get(val.charAt(0) - '1').contains(val);
	}
}
