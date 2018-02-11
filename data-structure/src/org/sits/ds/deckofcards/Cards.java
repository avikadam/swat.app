package org.sits.ds.deckofcards;

public class Cards {
	private int card;
	private Suit suit;

	public Cards(int card, Suit suit) {
		this.card = card;
		this.suit = suit;
	}

	public int value() {
		return card;
	}

	public Suit suit() {
		return suit;
	}
}
