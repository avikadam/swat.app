package org.sits.ds.deckofcards;

import java.util.Collections;
import java.util.Stack;

import javax.smartcardio.Card;

public class Deck {
	private Stack<Cards> cards = new Stack<>();

	public Deck() {
	}

	public Deck(int numberOfCards) {
		for (int i = 0; i < numberOfCards; i++) {
			cards.push(CardFactory.createCard(i));
		}
	}

	private void shuffle() {
		Collections.shuffle(this.cards);
	}

	public void sort() {
		// Collections.sort(this.cards);
	}

	public void removeAllCards() {
		this.cards.removeAllElements();
	}

	public void removeCard(Card c) {
		int i = this.cards.search(c);
		this.cards.remove(i);
	}

	public Cards getCard(Card c) {
		int i = this.cards.search(c);
		return this.cards.get(i);
	}

	public Cards getTopCard() {
		return this.cards.pop();
	}

	public Cards getNthCard(int i) {
		return this.cards.get(i);
	}

	public Cards addCard(Cards c) {
		this.cards.push(c);
		return c;
	}
}
