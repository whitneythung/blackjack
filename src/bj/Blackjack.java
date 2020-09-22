package bj;
import java.util.*;


public class Blackjack {
	private int rank;
	private String dealer;	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean gameOver = false;
		int numberOfGames = 0;
		String player;
		System.out.println("Welcome to Blackjack!");
		while (!gameOver) {
		System.out.println("Would you like to play a game? (Yes/No) ");
		String response = input.nextLine();
		if (response.equalsIgnoreCase("no")) {
			gameOver = true;
		}
		else if (response.equalsIgnoreCase("yes")){
			if (numberOfGames == 0) {
			System.out.println("First things first, what is your name? ");
			player = input.nextLine();
			System.out.println("Hello " + player + ".");
			}
			numberOfGames++;
			Deck game = new Deck();
			System.out.println("Here is your hand. Would you like to hit or stand?");
			//launch blackjack game.
		}
		else {
			System.out.println("Invalid Answer, type 'yes' or 'no'");
		}
	}
		System.out.println("Game Over! Thank you for playing.");
}
	
	
	
	
}
