package bj;

import java.util.Map;

public class Card {
	private CardType card;
	private Suit suit;
	
	public enum CardType{
		Ace(11), King(10), Queen(10), Jack(10), Ten(10), Nine(9), Eight(8), Seven(7), Six(6), Five(5), Four(4), Three(3), Two(2);
	
		private final int value;
		//private final int valueTwo;
		private CardType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum Suit {
		Spades, Hearts, Diamonds, Clubs
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
	
	/*public int getValue(cardType type) {
		return type; 
	}*/
	
}
