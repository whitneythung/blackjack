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
import java.lang.*;

public class Blackjack {
	public static final int BLACK_JACK = 21;
	
	public static void main(String[] args) throws InterruptedException {
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
			//there is a bug here where if i hit another card, it prompts me to this statement.
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
				System.out.println("Hello " + player + ". Lets start the game!\n");
				Thread.sleep(3000);
			}
			numberOfGames++;
			
			Deck deck = new Deck();

			//I've added a sleep buffer to make it seem realistic as if the dealer is actually shuffling the cards.
			System.out.println("Dealer is shuffling cards. \n");
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(1000);
			System.out.println(".\n");
			System.out.println("Here is your hand.\n");

			//Prints out the user's first card and assigns it to playerCard1
			System.out.println(deck.peek());
			playerCard1 = deck.draw();

			//Prints out the user's second card and assigns it to playerCard2
			System.out.println(deck.peek());
			playerCard2 = deck.draw();
			
			String dealersOtherCard = deck.peek();
			dealerCard1 = deck.draw();
			Thread.sleep(1000);

			//In typical Blackjack games, the user is able to see the dealer's second card. That is implemented here.
			System.out.println("\nDealer also draws two cards with one card flipped open. The dealer's card flipped open is a " + deck.peek());
			dealerCard2 = deck.draw();
			dealerHand = dealerCard1 + dealerCard2;
			
			//flags if you picked up an ace. This is useful if your hand busts but you had an Ace.
			containsAce = handHasAce(playerCard1, playerCard2);
			
			//combines the total value of your hand as an integer.
			playerScore = add(playerCard1, playerCard2);
			
			System.out.println("\nYour Hand: " + playerScore + "\n");
			
			if (playerScore == BLACK_JACK) {
				System.out.println("Black Jack! Lets check the dealer's hand\n");
			}
			if (playerScore != BLACK_JACK) {
				System.out.println(player + ", would you like to hit or stand?");
				move = input.nextLine();
				Thread.sleep(3000);

				//Tip for De Morgan's law: Not(A or B) = Not A & Not B
				//In this case, !(hit or stand) = !hit & !stand
				//This while loop checks for valid hit/h or stand/s statements. It will go in an infinite loop until user enters correct statement.
				while (!validStandStatement(move) && !validHitStatement(move)) {
					System.out.println("Invalid answer " + player + ", please type 'hit/h' or 'stand/s' ");
					move = input.nextLine();
				}

				//'hit' sometimes misses this loop and goes straight to 'would you like to play again?'
					while (validHitStatement(move) && !validStandStatement(move) && !lost) { //goes in this while loop if user hits and game isn't lost. Will not go in loop if user stands
						if (!validHitStatement(move)) { //Checks if input is a valid answer
							System.out.println("Invalid answer " + player + ", please type 'hit/h' or 'stand/s' ");
							move = input.nextLine();
						}
						else { //This is what happens when user hits
							if (deck.cardIsAce()) //peeks at the top of the card before drawing to see if it is an Ace
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
								Thread.sleep(3000);
								//Checks if user enters valid hit or stand statement
								while (!validStandStatement(move) && !validHitStatement(move)) {
									System.out.println("Invalid answer " + player + ", please type 'hit/h' or 'stand/s' ");
									move = input.nextLine();
								}
							}
							else if (playerScore == 21) {
								System.out.println("You hit 21, " + player + "! End of your turn.\n");
								move = "stand";
								Thread.sleep(1000);
							}
							else { // if score > 21, checks to see if you have an Ace in hand to change Ace value to 1
								if (containsAce) {
									playerScore -= 10;

									System.out.println("Ace changed to value 1. ");

									containsAce = false;

									System.out.println("Here is your new score: " + playerScore);
									System.out.println("Would you like to hit again? Or stand? (Type 'hit' or 'stand) ");

									move = input.nextLine();

									Thread.sleep(3000);

									//Checks if user enters valid hit or stand statement
									while (!validStandStatement(move) && !validHitStatement(move)) {
										System.out.println("Invalid answer " + player + ", please type 'hit/h' or 'stand/s' ");
										move = input.nextLine();
									}
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
			if (!lost || playerScore == BLACK_JACK || validStandStatement(move)) {
				System.out.println("\nDealer's turn. \n");
				Thread.sleep(3000);
				System.out.println("Dealer opens the other card and reveals a " + dealersOtherCard + ". Dealer's score is " + dealerHand + "\n");
				Thread.sleep(3000);
				while (dealerHand < 17) {
					System.out.println("Dealer draws a card.\n");
					Thread.sleep(1000);
					System.out.println(deck.peek());
					dealerHand += deck.draw();
					System.out.println("\nDealer's hand: " + dealerHand + "\n");
				}

				//Compares dealer and player's hand
				if (dealerHand > playerScore && !(dealerHand > 21)) {
					System.out.println("Dealer wins! You lose!\n");
					gamesLost++;
					Thread.sleep(2000);
				}
				else if (dealerHand > BLACK_JACK) {
					System.out.println("Dealer bust! You win!\n");
					gamesWon++;
					Thread.sleep(2000);
				}
				else if (dealerHand < playerScore) {
					System.out.println("Dealer lost! You win!\n");
					gamesWon++;
					Thread.sleep(2000);
				}
				else if (dealerHand == playerScore) {
					System.out.println("Its a tie!\n");
					gamesTied++;
				}
			}
		} //This is the end of the game while loop
		else {
			System.out.println("Invalid Answer, type 'yes' or 'no'");
		}
		
	} //END GAME results
		System.out.println("Game Over! Thank you for playing.\n");
		Thread.sleep(1000);
		System.out.println("Games played: " + numberOfGames);
		Thread.sleep(1000);
		System.out.println("Games won: " + gamesWon);
		Thread.sleep(1000);
		System.out.println("Games lost: " + gamesLost);
		Thread.sleep(1000);
		System.out.println("Games tied: " + gamesTied);
}
	
	public static int add(int card1, int card2) {
		return card1 + card2;
	}
	
	public static boolean validHitStatement(String str) {
		return (str.equalsIgnoreCase("hit") || str.equalsIgnoreCase("h"));
	}
	
	public static boolean validStandStatement(String str) {
		return (str.equalsIgnoreCase("stand") || str.equalsIgnoreCase("s"));
	}
	
	public static boolean handHasAce(int card1, int card2) {
		return (card1 == 11 || card2 == 11);
	}
	
}
