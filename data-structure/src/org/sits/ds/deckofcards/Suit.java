package org.sits.ds.deckofcards;

public enum Suit {
	SPADE(1), HEART(2), CLUBS(3), DIAMONDS(4);
	int value;

	private Suit(int v) {
		value = v;
	}
}
