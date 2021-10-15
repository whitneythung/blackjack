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
* Revised 10/14/2021
*/

package bj;
import java.util.*;

public class Blackjack {
	public static final int BLACK_JACK = 21;
	public static int gamesWon = 0;
	public static int gamesLost = 0;
	public static int gamesTied = 0;
	public static int numberOfGames = 0;
	
	public static void main(String[] args) throws InterruptedException {
		initializeGame();
}

	public static void initializeGame() throws InterruptedException {
		Scanner input = new Scanner(System.in);
		int playerCard1 = 0; int playerCard2 = 0; int dealerCard1 = 0; int dealerCard2 = 0; int playerScore = 0; int dealerHand = 0;
		String player = null; String move = null; String dealersOtherCard = null;
		boolean containsAce = false; boolean lost = false; boolean gameOver = false;

		System.out.println("Welcome to Blackjack!");

		//this while loop initializes the BlackJack game.
		while (!gameOver) {
			//prompts user's initial game
			String response = letsPlayAgain(player, input);

			if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
				gameOver = true;
			} else if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
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
				System.out.println(".\n");
				System.out.println("Here is your hand.\n");

				//Prints out the user's first card and assigns it to playerCard1
				System.out.println(deck.peek());
				playerCard1 = deck.draw();

				//Prints out the user's second card and assigns it to playerCard2
				System.out.println(deck.peek());
				playerCard2 = deck.draw();

				//Initializes the dealer's hand
				dealersOtherCard = deck.peek();
				dealerCard1 = deck.draw();
				Thread.sleep(1000);

				//In typical Blackjack games, the user is able to see the dealer's second card. That is implemented here.
				System.out.println("\nDealer also draws two cards with one card flipped open. The dealer's card flipped open is a " + deck.peek());
				dealerCard2 = deck.draw();
				dealerHand = dealerCard1 + dealerCard2;

				//Flags if you picked up an ace. This is useful if your hand busts but you had an Ace that can bring your playerScore below 21.
				containsAce = handHasAce(playerCard1, playerCard2);

				//Combines the total value of your hand as an integer.
				playerScore = add(playerCard1, playerCard2);

				System.out.println("\nYour Hand: " + playerScore + "\n");

				if (playerScore == BLACK_JACK) { //Player has a Blackjack hand and automatically ends their turn.
					System.out.println("Black Jack! Lets check the dealer's hand\n");
				} else {
					System.out.println(player + ", would you like to hit or stand?");
					move = input.nextLine();
					Thread.sleep(3000);

					//Here's a useful tip referencing De Morgan's law: Not(A or B) = Not A & Not B
					//In this case, !(hit or stand) = !hit & !stand
					//This while loop checks for valid hit/h or stand/s statements. It will go in an infinite loop until user enters a correct statement.
					while (!validStandStatement(move) && !validHitStatement(move)) {
						invalidResponse(player);
						move = input.nextLine();
					}

					//This is what happens when the player hits
					while (validHitStatement(move) && !lost) { //goes in this while loop if user hits and game isn't lost.
						if (deck.cardIsAce()) { //peeks at the top of the card before drawing to see if it is an Ace
							containsAce = true;
						}
						System.out.println("You drew a " + deck.peek() + "\n");
						playerScore += deck.draw();
						System.out.println("Total: " + playerScore + "\n");
						if (playerScore < 21) {
							//Unblock this if you want to validate the cards being removed from deck
							//System.out.println("Cards remaining in deck: " + deck.size());
							System.out.println("Would you like to hit again? Or stand? (Type 'hit/h' or 'stand/s) ");
							move = input.nextLine();
							Thread.sleep(3000);
							//Checks if user enters valid hit or stand statement
							while (!validStandStatement(move) && !validHitStatement(move)) {
								invalidResponse(player);
								move = input.nextLine();
							}
						} else if (playerScore == 21) {
							System.out.println("You hit 21, " + player + "! End of your turn.\n");
							move = "stand";
							Thread.sleep(1000);
						} else { // if score > 21, checks to see if you have an Ace in hand to change Ace value to 1
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
									invalidResponse(player);
									move = input.nextLine();
								}
							} else { //this treats ace as a 1 instead of 10
								System.out.println("Its a bust! Sorry " + player + ", you lose!\n");
								gamesLost++;
								lost = true;
							}
						}
					} // End of hit statement while loop. Will break out of this loop if player 'stands'
				} // End of player's turn

				//Dealer's turn here.
				if (!lost || playerScore == BLACK_JACK || validStandStatement(move)) {
					System.out.println("\nDealer's turn. \n");
					Thread.sleep(3000);
					System.out.println("Dealer opens the other card and reveals a " + dealersOtherCard + ". Dealer's score is " + dealerHand + "\n");
					Thread.sleep(3000);
					//dealer will keep drawing a card to try to beat the player, or dealer may bust
					while (dealerHand < playerScore) {
						System.out.println("Dealer draws a card.\n");
						Thread.sleep(1000);
						System.out.println(deck.peek());
						dealerHand += deck.draw();
						System.out.println("\nDealer's hand: " + dealerHand + "\n");
					}

					//Compares dealer and player's hand
					compareHands(dealerHand, playerScore);

				}
			} //End of loop if user responds 'yes' to play a game or another game
			else { //Invalid y/n input message
				System.out.println("Invalid Answer, type 'yes' or 'no'\n");
			}
		} //This loop exits when the game is over
		endGameMessage(numberOfGames, gamesWon, gamesLost, gamesTied);
	}

	public static String letsPlayAgain(String player, Scanner input) {
		if (numberOfGames == 0) {
			System.out.println("Would you like to play a game?");
		}
		//Different prompt if its not your first game
		else {
			System.out.println(player + ", would you like to play again?");
		}
		return input.nextLine();
	}

	public static void compareHands(int dealerHand, int playerScore) throws InterruptedException {
		if (dealerHand > playerScore && !(dealerHand > 21)) {
			System.out.println("Dealer wins! You lose!\n");
			gamesLost++;
			Thread.sleep(2000);
		} else if (dealerHand > BLACK_JACK) {
			System.out.println("Dealer bust! You win!\n");
			gamesWon++;
			Thread.sleep(2000);
		} else if (dealerHand < playerScore) {
			System.out.println("Dealer lost! You win!\n");
			gamesWon++;
			Thread.sleep(2000);
		} else if (dealerHand == playerScore) {
			System.out.println("Its a tie!\n");
			gamesTied++;
		}
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

	public static void invalidResponse(String player) {
		System.out.println("Invalid answer " + player + ", please type 'hit/h' or 'stand/s' ");
	}
	public static void endGameMessage(int numberOfGames, int gamesWon, int gamesLost, int gamesTied) throws InterruptedException {
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
}
