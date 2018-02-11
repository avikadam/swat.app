package org.sits.probs;

import java.util.Scanner;

public class GameOfNim {
	/**
	 * Gets the computer's move, 1 or 2. Currently this method gets a random
	 * number. Your job is to make the computer choose such that it wins every
	 * time it is possible. First, solve with recursion. After successfully
	 * completing this, run the program a few times to see if you can recognize
	 * the pattern the computer is taking. Then see if you can get the computer
	 * to choose its move without looping or recursion.
	 * 
	 * @return
	 */
	public int getRandomComputerMove() {
		return (int) (Math.random() * 2) + 1;
	}

	/**
	 * plays the game of nim, computer versus person
	 */
	public void play() {
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("Enter number of elements to start.");
			int left = sc.nextInt();
			while (left > 0) {
				int computer = getRandomComputerMove();
				System.out.println("Computer takes " + computer);
				left -= computer;
				System.out.println("Now there are " + left + " left.");
				if (left <= 0) {
					System.out.println("Computer wins!");
					return;
				}
				System.out.println("What's your move? (1 or 2)");
				int person = sc.nextInt();
				while (person != 1 && person != 2) {
					System.out.println(person + " not allowed, choose 1 or 2.");
					person = sc.nextInt();
				}
				left -= person;
				System.out.println("Now there are " + left + " left.");
				if (left <= 0) {
					System.out.println("You win!");
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		GameOfNim nim = new GameOfNim();
		nim.play();
	}
}
