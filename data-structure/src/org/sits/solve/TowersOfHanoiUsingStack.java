package org.sits.solve;

import java.util.Scanner;

import org.sits.custom.CustomStack;

/**
 * Procedure </br>
 * <tt> Hanoi(disk, source, dest, aux) </br>
 * IF disk == 1, THEN move disk from source to dest </br>
 * ELSE Hanoi(disk - 1, source, aux, dest) // Step 1 </br>
 * move disk from source to dest // Step 2 </br>
 * Hanoi(disk - 1, aux, dest, source) // Step 3 </br>
 * END IF </br>
 * END Procedure </br>
 *
 */
public class TowersOfHanoiUsingStack {
	private static CustomStack<Integer>[] tower;

	public static void towersOfHanoi(int n) {

		// create three stacks, tower[0] is scratch
		tower = new CustomStack[4];
		for (int i = 1; i <= 3; i++) {
			tower[i] = new CustomStack<>(4);
		}
		for (int d = n; d > 0; d--) {
			// initialize
			// add disk d to tower 1
			tower[1].push(new Integer(d));
		}
		// move n disks from tower 1 to 2 using 3 as
		// intermediate tower
		showTowerStates(n, 1, 2, 3);
	}

	public static void showTowerStates(int disk, int source, int dest, int aux) {

		if (disk > 0) {
			try {
				showTowerStates(disk - 1, source, aux, dest);
				// move d from top of tower x
				Integer d = tower[source].pop();
				// to top of tower y
				tower[dest].push(d);
				System.out.println("Move disk " + d + " from tower " + source + " to top of tower " + dest);
				showTowerStates(disk - 1, aux, dest, source);
			} catch (Exception ex) {
			}
		}
	}

	public static void main(String[] args) {

		try (Scanner in2 = new Scanner(System.in);) {
			System.out.println("Enter the tower size");
			int disks = in2.nextInt();
			System.out.println("Running " + disks + " disk problem:");
			towersOfHanoi(disks);
		}
	}
}
