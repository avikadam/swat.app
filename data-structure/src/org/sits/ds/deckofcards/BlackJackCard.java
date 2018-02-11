package org.sits.ds.deckofcards;

public class BlackJackCard extends Cards {
	public BlackJackCard(int card, Suit suit) {
		super(card, suit);
	}

	@Override
	public int value() {
		int card = super.value();
		if (card == 1)
			return 11; // aces are 11
		if (card < 10)
			return card;
		return 10;
	}

	boolean isAce() {
		return super.value() == 1;
	}
}
