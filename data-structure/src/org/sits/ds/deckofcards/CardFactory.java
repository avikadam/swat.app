package org.sits.ds.deckofcards;

public class CardFactory {

	public static Cards createCard(int cardNum) {
		// TODO Auto-generated method stub
		int cardSuit = cardNum / 13;
		int rankNum = cardNum % 13;

		Rank r = getRank(rankNum);
		Suit s = getSuit(cardSuit);

		// TODO: Cards c = new Cards(s, r);
		Cards c = new Cards(r.getCardValue(), s);
		return c;
	}

	private static Suit getSuit(int cardSuit) {
		switch (cardSuit) {
		case 1:
			return Suit.CLUBS;
		case 2:
			return Suit.DIAMONDS;
		case 3:
			return Suit.HEART;
		case 4:
			return Suit.SPADE;
		}
		return null;
	}

	private static Rank getRank(int rankNum) {
		switch (rankNum) {
		case 1:
			return Rank.ACE;
		case 2:
			return Rank.TWO;
		case 3:
			return Rank.THREE;
		case 4:
			return Rank.FOUR;
		case 5:
			return Rank.FIVE;
		case 6:
			return Rank.SIX;
		case 7:
			return Rank.SEVEN;
		case 8:
			return Rank.EIGHT;
		case 9:
			return Rank.NINE;
		case 10:
			return Rank.TEN;
		case 11:
			return Rank.JACK;
		case 12:
			return Rank.QUEEN;
		case 13:
			return Rank.KING;

		}

		return null;
	}

}
