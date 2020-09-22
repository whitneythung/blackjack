//This class represents a deck of cards


package bj;
import java.util.*;

public class Deck extends Card {
	private Stack<String> cards;
	private int cardsLeft;

	//want to construct a deck with 52 cards
	public Deck {
		this.cards = new Stack<String>(); 
		this.cardsLeft = 52;
	}
	
	public boolean isEmpty() {
		if (size = 0) {
			return true;
		}
		return false;
	}
	
	public int size() {
		return this.cardsLeft;
	}
}
