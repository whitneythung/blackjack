package bj;

public class Card {
	private cardType card;
	private suit suit;
	
	enum cardType{
		Ace, King, Queen, Jack, Ten, Nine, Eight, Seven, Six, Five, Four, Three, Two, One
	}
	
	enum suit {
		Spades, Hearts, Diamonds, Clubs
	}
	
	public Card(cardType card, suit suit) {
		this.card = card;
		this.suit = suit;
	}
	
	public suit getSuit() {
		return suit;
	}
	
	public cardType getCardtype() {
		return card;
	}
	
	
}
