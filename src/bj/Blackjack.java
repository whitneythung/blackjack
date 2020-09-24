/*This class represents the Blackjack Class.
 * Initializes the BlackJack game deck.
 * 
Still need to invalidate wrong inputs.

*
* Written by Whitney Thung
* 09/23/2020
*/

package bj;
import java.util.*;

public class Blackjack {
	public static final int BLACK_JACK = 21;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfGames = 0;
		String player = null;
		String move;
		int playerCard1 = 0;
		int playerCard2 = 0;
		int dealerCard1 = 0;
		int dealerCard2 = 0;
		int playerScore = 0;
		int dealerHand = 0;
		boolean containsAce = false;
		//boolean blackjack = false;
		boolean lost = false;
		boolean gameOver = false;
		
		System.out.println("Welcome to Blackjack!");
		
		while (!gameOver) {
			if (numberOfGames == 0) {
				System.out.println("Would you like to play a game? (Yes/No y/n) ");
			}
			else {
				System.out.println(player + ", would you like to play again? (Yes/No y/n)");
			}
		String response = input.nextLine();
		if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
			gameOver = true;
		}
		else if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")){
			if (numberOfGames == 0) {
			System.out.println("Welcome to BlackJack game! What is your name? ");
			player = input.nextLine();
			System.out.println("Hello " + player + ".\n");
			}
			numberOfGames++;
			lost = false;
			Deck deck = new Deck();
			System.out.println("Here is your hand.\n");
			System.out.println(deck.peek());
			playerCard1 = deck.draw();
			System.out.println(deck.peek());
			playerCard2 = deck.draw();
			String card1 = deck.peek();
			dealerCard1 = deck.draw();
			System.out.println("\nDealer also draws two cards. The dealer's second card is " + deck.peek());
			dealerCard2 = deck.draw();
			dealerHand = dealerCard1 + dealerCard2;
			
			if (playerCard1 == 11 || playerCard2 == 11) { //flags if you picked up an ace
				containsAce = true;
			}
			playerScore = playerCard1 + playerCard2;
			
			System.out.println("\nYour Hand: " + playerScore + "\n");
			if (playerScore == BLACK_JACK) {
				System.out.println("Black Jack! Lets check the dealer's hand");
			}
			if (playerScore != BLACK_JACK) {
				System.out.println(player + ", would you like to hit or stand?");
				move = input.nextLine();
				while (((move.equalsIgnoreCase("hit")) || (move.equalsIgnoreCase("h"))) && (!lost)) { //need to check if it is a valid statement
					if (move.equalsIgnoreCase("stand") || move.equalsIgnoreCase("s")) { //Checks if input is a valid answer
						System.out.println("Invalid answer " + player + ", please type 'hit/h' or 'stand/s' ");
						move = input.nextLine();
					}
					else { 
						if (deck.cardIsAce() == true) //peeks at the top of the card before drawing to see if it is an Ace
						{
							containsAce = true;
						}
					System.out.println("You drew a " + deck.peek());
					playerScore+= deck.draw();
					System.out.println("Total: " + playerScore);
					if (playerScore < 21) {
					System.out.println("Cards remaining in deck: " + deck.size());
					System.out.println("Would you like to hit again? Or stand? (Type 'hit/h' or 'stand/s) ");
					move = input.nextLine();
					}
					else { // if score > 21, checks to see if you have an Ace in hand to change Ace value to 1
						if (containsAce == true) {
							playerScore -= 10;
							System.out.println("Ace changed to value 1. ");
							containsAce = false;
							System.out.println("New score: " + playerScore);
							System.out.println("Would you like to hit again? Or stand? (Type 'hit' or 'stand) ");
							move = input.nextLine();
						}
						else { //this treats ace as a 1 instead of 10
							System.out.println("Its a bust! Sorry " + player + ", you lose! ");
							lost = true;
						}
					}
				}  
			} // end while
			} //blackjack loop
			
			//Dealer's move here. Then compares each other's hand
			if (!lost) {
			System.out.println("\nDealer turn. \n");

			System.out.println("Dealer opens the other card and reveals a " + card1 + ". Dealer score is " + dealerHand + "\n");
			while (dealerHand < 17) {
				System.out.println("Dealer draws a card.\n");
				System.out.println(deck.peek());
				dealerHand += deck.draw();
				System.out.println("\nDealer's hand: " + dealerHand + "\n");
			}
			
			if (dealerHand > playerScore && !(dealerHand > 21)) {
				System.out.println("Dealer wins! You lose!\n");
			}
			else if (dealerHand < playerScore) {
				System.out.println("Dealer lost! You win!\n");
			}
			else if (dealerHand == playerScore) {
				System.out.println("Its a tie!\n");
			}
			
			
			}
		}
		else {
			System.out.println("Invalid Answer, type 'yes' or 'no'");
		}
	//	numberOfGames++;
		
	} //END GAME
		System.out.println("Game Over! Thank you for playing.\n");
		System.out.println("Games played: " + numberOfGames);
}
	
	public static String dealCard(Deck deck) {
		//int card1 = Card.getCardType();
		if (deck.getCards().size() == 0) {
			throw new IllegalArgumentException("Deck is empty. No cards to deal.");
		}
		return "You drew a " + deck.getCards().pop();
	}
	
}
