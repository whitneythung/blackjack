//This class represents a deck of cards


package bj;
import java.util.*;


public class Deck {
	private Stack<Card> cards;
	
	//construct a deck with 52 cards
	public Deck() {
		this.cards = new Stack<Card>();
		//use two for loops to add in 52 cards.
		for (Card.cardType type : Card.cardType.values()) { //This accesses the enum cardType and enum suit from Card Class
			//put something here to add a int value to each cardType
			for (Card.suit suit : Card.suit.values()) { //create a new card inside
				cards.push(new Card(type, suit));
			}
		}
		System.out.println("Number of cards in deck: " + size());
		System.out.println("Cards in deck: " + cards.toString());
		shuffle();
		System.out.println("Cards in deck: " + cards.toString());
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
