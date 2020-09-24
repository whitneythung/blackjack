/*This class represents the Card Class.
* contents of a Card includes the card type and the suit.
* User is able to print the contents of the card, or look at the card type or suit type.
* Written by Whitney Thung
* 09/23/2020
*/

package bj;

import java.util.Map;

public class Card {
	private CardType card;
	private Suit suit;
	
	public enum CardType{
		ACE(11), KING(10), QUEEN(10), JACK(10), TEN(10), NINE(9), EIGHT(8), SEVEN(7), SIX(6), FIVE(5), FOUR(4), THREE(3), TWO(2);
	
		private final int value;

		private CardType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum Suit {
		SPADES, HEARTS, DIAMONDS, CLUBS
	}
	
	public Card(CardType card, Suit suit) {
		this.card = card;
		this.suit = suit;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public CardType getCard() {
		return card;
	}
	
	//grabs a card from the deck and returns the value of the card
	public int getCardValue() {
		return card.getValue();
	}
	
	//toString method to print out the enum values correctly
	public String toString() {
		return card + " of " + suit;
	}
	
	
	
}
