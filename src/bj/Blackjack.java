/*This class represents the Blackjack Class 
 * of a simple Blackjack game.
 * 
* Initializes the BlackJack game deck.
* Helper method validHitStatement and validStandStatement checks to see if input is valid.
* handHasAce method checks to see if you picked up an Ace. 
* game initializes a new deck of cards each time you play a game.
* 
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
		int numberOfGames = 0; int gamesWon = 0; int gamesLost = 0; int gamesTied = 0; int playerCard1 = 0;
		int playerCard2 = 0; int dealerCard1 = 0; int dealerCard2 = 0; int playerScore = 0; int dealerHand = 0;
		String player = null; String move = null;
		boolean containsAce = false; boolean lost = false; boolean gameOver = false;

		System.out.println("Welcome to Blackjack!");
		
		//this while loop initializes the BlackJack game.
		while (!gameOver) {
			if (numberOfGames == 0) { 
				System.out.println("Would you like to play a game?");
			}
			//Different prompt if its not your first game
			else { 
				System.out.println(player + ", would you like to play again?");
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
			
			Deck deck = new Deck();
			
			System.out.println("Dealer is shuffling cards. \n.\n.\n.\n.\n.\n.\n.\n ");
			System.out.println("Here is your hand.\n");
			
			System.out.println(deck.peek());
			playerCard1 = deck.draw();
			
			System.out.println(deck.peek());
			playerCard2 = deck.draw();
			
			String dealersOtherCard = deck.peek();
			dealerCard1 = deck.draw();
			
			System.out.println("\nDealer also draws two cards. The dealer's second card is " + deck.peek());
			dealerCard2 = deck.draw();
			dealerHand = dealerCard1 + dealerCard2;
			
			//flags if you picked up an ace
			containsAce = handHasAce(playerCard1, playerCard2);
			
			//combines the int value of your hand
			playerScore = add(playerCard1, playerCard2);
			
			System.out.println("\nYour Hand: " + playerScore + "\n");
			
			if (playerScore == BLACK_JACK) {
				System.out.println("Black Jack! Lets check the dealer's hand");
			}
			if (playerScore != BLACK_JACK) {
				System.out.println(player + ", would you like to hit or stand?");
				move = input.nextLine();
					while (!validStandStatement(move) && !lost) { //need to check if it is a valid statement
						if (!validHitStatement(move)) { //Checks if input is a valid answer
							System.out.println("Invalid answer " + player + ", please type 'hit/h' or 'stand/s' ");
							move = input.nextLine();
						}
						else { 
							if (deck.cardIsAce() == true) //peeks at the top of the card before drawing to see if it is an Ace
							{
								containsAce = true;
							}
							System.out.println("You drew a " + deck.peek() + "\n");
							playerScore+= deck.draw();
							System.out.println("Total: " + playerScore + "\n");
							if (playerScore < 21) {
								System.out.println("Cards remaining in deck: " + deck.size());
								System.out.println("Would you like to hit again? Or stand? (Type 'hit/h' or 'stand/s) ");
								move = input.nextLine();
							}
							else if (playerScore == 21) {
								System.out.println("You hit 21, " + player + "! End of your turn.\n");
								move = "stand";
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
									System.out.println("Its a bust! Sorry " + player + ", you lose!\n");
									gamesLost++;
									lost = true;
								}
							}
					}  
				} // end while
			} //not blackjack loop
			
			//Dealer's move here. Then compares each other's hand
			if (!lost) {
			System.out.println("\nDealer's turn. \n");

			System.out.println("Dealer opens the other card and reveals a " + dealersOtherCard + ". Dealer's score is " + dealerHand + "\n");
			
			while (dealerHand < 17) {
				System.out.println("Dealer draws a card.\n");
				System.out.println(deck.peek());
				dealerHand += deck.draw();
				System.out.println("\nDealer's hand: " + dealerHand + "\n");
			}
			
			//Compares dealer and player's hand
			if (dealerHand > playerScore && !(dealerHand > 21)) {
				System.out.println("Dealer wins! You lose!\n");
				gamesLost++;
			}
			else if (dealerHand > BLACK_JACK) {
				System.out.println("Dealer bust! You win!\n");
				gamesWon++;
			}
			else if (dealerHand < playerScore) {
				System.out.println("Dealer lost! You win!\n");
				gamesWon++;
			}
			else if (dealerHand == playerScore) {
				System.out.println("Its a tie!\n");
				gamesTied++;
			}
			
			
			}
		}
		else {
			System.out.println("Invalid Answer, type 'yes' or 'no'");
		}
		
	} //END GAME
		System.out.println("Game Over! Thank you for playing.\n");
		System.out.println("Games played: " + numberOfGames);
		System.out.println("Games won: " + gamesWon);
		System.out.println("Games lost: " + gamesLost);
}
	
	public static int add(int card1, int card2) {
		return card1 + card2;
	}
	
	public static boolean validHitStatement(String str) {
		if (str.equalsIgnoreCase("hit")){
			return true;
		}
		else if (str.equalsIgnoreCase("h")){
			return true;
		}
		return false;
	}
	
	public static boolean validStandStatement(String str) {
		if (str.equalsIgnoreCase("stand")){
			return true;
		}
		else if (str.equalsIgnoreCase("s")){
			return true;
		}
		return false;
	}
	
	public static boolean handHasAce(int card1, int card2) {
		if (card1 == 11 || card1 == 11) {
			return true;
		}
		return false;
	}
	
}
