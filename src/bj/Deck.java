/*This class represents the Deck Class.
* contents of a Deck includes a stack of 52 Cards.
* User can shuffle the deck, peek at the top of the deck, 
* and draw the top card of the deck. 
* User can check the size of the deck or see if deck is empty.
* Boolean flag written to check if top of deck is an Ace.
* Written by Whitney Thung
* 09/23/2020
*/

package bj;
import java.util.*;


public class Deck {
	private Stack<Card> cards;
	
	//constructs a deck with 52 cards
	public Deck() {
		this.cards = new Stack<Card>();
		//use two for loops to add in 52 cards.
		for (Card.CardType type : Card.CardType.values()) { //This accesses the enum cardType and enum suit from Card Class
			for (Card.Suit suit : Card.Suit.values()) { //create a new card inside
				cards.push(new Card(type, suit));
			}
		}
		//shuffling 3 times to make it extra random
		shuffle();
		shuffle();
		shuffle();
		
		/* Uncomment this if you want to check to see if deck works
		System.out.println("Number of cards in deck: " + size());
		System.out.println("Cards in deck: " + cards.toString());
		//peeks at the value of the top card
		System.out.println("The value of card on top of deck is " + cards.peek().getCardValue()); */
		
	}
	
	public Stack<Card> getCards() {
		return this.cards;
	}
	
	//Prints out the card on top of the deck, and returns the int value of it
	public int draw() {
		return cards.pop().getCardValue();
	}
	
	public String peek() {
		return cards.peek().toString();
	}
	
	public boolean cardIsAce() {
		int card = cards.peek().getCardValue();
		if (card == 11) {
			return true;
		}
		return false;
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
