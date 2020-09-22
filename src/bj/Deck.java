//This class represents a deck of cards


package bj;
import java.util.*;


public class Deck {
	private Stack<Card> cards;
	private int rank;
	
	//want to construct a deck with 52 cards
	public Deck() {
		this.cards = new Stack<Card>();
		//use two for loops to add in 52 cards.
		
		
	}
	
	public Stack<Card> getCards() {
		return this.cards;
	}
	
	//public void shuffle() {
		
	//}
	
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	
	public int size() {
		return this.cards.size();
	}
}
