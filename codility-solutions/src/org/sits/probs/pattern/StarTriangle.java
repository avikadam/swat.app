package org.sits.probs.pattern;

import java.util.Scanner;

public class StarTriangle {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("Enter Piryamid Height");
			System.out.print(">>");
			int piramidHeight = sc.nextInt();
			for (int i = 1; i <= piramidHeight; i++) {
				printRow(piramidHeight, i);
			}
			for (int i = piramidHeight; i >= 1; i--) {
				printRow(piramidHeight, i);
			}
		}
	}

	private static void printRow(int piramidHeight, int i) {
		for (int j = piramidHeight - 1; j >= i; j--) {
			System.out.print(" ");
		}
		for (int k = 1; k <= (2 * i - 1); k++) {
			System.out.print("*");
		}
		System.out.println("");
	}
}
