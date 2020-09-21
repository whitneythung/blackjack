package bj;

public class Card {
	private String cardType;
	private String suit;
	private int value;
	
	public Card(String cardType, String suit, int value) {
		this.cardType = cardType;
		this.suit = suit;
		this.value = value;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String getCardtype() {
		return cardType;
	}
	
	public int getValue() {
		return value;
	}
}
