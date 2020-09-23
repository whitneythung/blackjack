//This class represents a deck of cards


package bj;
import java.util.*;


public class Deck {
	private Stack<Card> cards;
	
	//construct a deck with 52 cards
	public Deck() {
		this.cards = new Stack<Card>();
		//use two for loops to add in 52 cards.
		for (Card.CardType type : Card.CardType.values()) { //This accesses the enum cardType and enum suit from Card Class
			for (Card.Suit suit : Card.Suit.values()) { //create a new card inside
				cards.push(new Card(type, suit));
			}
		}
		System.out.println("Number of cards in deck: " + size());
		System.out.println("Cards in deck: " + cards.toString());
		shuffle();
		System.out.println("Cards in deck: " + cards.toString());
		
		//figure out how to look at the value of the top card
		System.out.println("The value of card on top of deck is " + cards.peek().getCardValue()); //cards.peek().getCardValue()
	}
	
	public Stack<Card> getCards() {
		return this.cards;
	}
	
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
	
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	
	public int size() {
		return this.cards.size();
	}
}
