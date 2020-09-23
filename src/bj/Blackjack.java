package bj;
import java.util.*;


public class Blackjack {
	public static final int BLACK_JACK = 21;
	
	private int rank;
	private String dealer;	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean gameOver = false;
		int numberOfGames = 0;
		String player;
		String move;
		
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
			Deck deck = new Deck();
			System.out.println("Here is your hand. Would you like to hit or stand?");
			move = input.nextLine();
			while (!move.equalsIgnoreCase("stand")) {
				System.out.println(dealCard(deck));
				System.out.println("Would you like to hit again? Or stand? (Type 'hit' or 'stand) ");
				move = input.nextLine();
				System.out.println("Cards remaining in deck: " + deck.size());
			}
			System.out.println("End of your turn");
		}
		else {
			System.out.println("Invalid Answer, type 'yes' or 'no'");
		}
	}
		System.out.println("Game Over! Thank you for playing.");
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
