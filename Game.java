import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Game {
	
	private Player p;
	private Deck cards;
	private ArrayList<Card> currentHand;
	boolean argsUsed = false;

	//Used when a hand is given.
	public Game(String[] testHand) {
		argsUsed = true;
		cards = new Deck();
		p = new Player();
		int suit = 0;
		int value = 0;
		for (int i = 0; i <= 4; i++) {
			if (testHand[i].substring(1, testHand[i].length()).equals("1")){
				value = 14;
			} 
			else {
				value = Integer.parseInt(testHand[i].substring(1, testHand[i].length()));
			}
			if (testHand[i].substring(0, 1).equals("c")) {
				suit = 1;
			}
			if (testHand[i].substring(0, 1).equals("d")) {
				suit = 2;
			}
			if (testHand[i].substring(0, 1).equals("h")) {
				suit = 3;
			}
			if (testHand[i].substring(0, 1).equals("s")) {
				suit = 4;
			}
			p.addCard(new Card(suit, value));
			currentHand = p.getHand();
		}
	}

	//Default Constructor
	public Game() {
		cards = new Deck();
		p = new Player();
		currentHand = p.getHand();
	}

	//Plays the game.
	public void play() {
		System.out.println("Welcome to Video Poker!!");
		if (!(argsUsed)) {
			for (int i = 1; i <= 5; i++) {
				p.addCard(cards.deal());
			}
			Card currentCard = new Card(1, 2);
			Collections.sort(currentHand);
			System.out.print("Your current hand is: ");
			for (int i = 0; i <= 4; i++) {
				currentCard = currentHand.get(i);
				System.out.print(currentCard.toString() + ", ");
			}
			System.out.println(" ");
			System.out.println("Would you like to switch any cards? (yes/no)");
			Scanner input = new Scanner(System.in);
			String result = input.next();
			if (result.equals("yes")) {
				this.switchCards();
				Collections.sort(currentHand);
				System.out.print("Your current hand is: ");
				for (int i = 0; i <= 4; i++) {
					currentCard = currentHand.get(i);
					System.out.print(currentCard.toString() + ", ");
				}
				System.out.println(" ");
			}
		}
		else{
			Collections.sort(currentHand);
			System.out.print("Your current hand is: ");
			for (int i = 0; i <= 4; i++) {
				System.out.print(currentHand.get(i)+", ");
			}
			System.out.println(" ");
		}
		System.out.println(this.checkHand(currentHand));
		System.out.println("Thank you for playing :)");

	}

	//Switches Cards with deck.
	public void switchCards() {
		int kept = 0;
		Scanner input = new Scanner(System.in);
		for (int i = 1; i <= 5; i++) {
			System.out.println("Would you like to trade card " + i
					+ "? (yes/no)");
			if ((input.next()).equals("yes")) {
				currentHand.remove(kept);
				p.addCard(cards.deal());
			}

			else {
				kept++;
			}
		}
	}

	//Checks hand. 
	public String checkHand(ArrayList<Card> hand) {
		boolean highestHand = false;

		// Royal Flush
		if (isRoyalStraight(hand) && isFlush(hand)) {
			highestHand = true;
			return "You have a Royal Flush!";
		}

		// Straight Flush
		if (isStraight(hand) && isFlush(hand) || isStraightWithAce(hand) && isFlush(hand)) {
			if (!(highestHand)){
				highestHand = true;
				return "You have a Straight Flush!";
			}
		}

		// Four of a Kind
		if (hasFourOfAKind(hand) && !(highestHand)) {
			highestHand = true;
			return "You have Four of a Kind!";
		}

		// Full House
		if (isFullHouse(hand) && !(highestHand)) {
			highestHand = true;
			return "You have a Full House!";
		}

		// Flush
		if (isFlush(hand) && !(highestHand)) {
			highestHand = true;
			return "You have a Flush!";
		}

		// Straight
		if (isStraight(hand) || isStraightWithAce(hand)) {
			if(!(highestHand)){
				highestHand = true;
				return "You have a Straight!";
			}
		}

		// Three of a Kind
		if (hasThreeOfAKind(hand) && !(highestHand)) {
			highestHand = true;
			return "You have Three of a Kind!";
		}

		// Two Pairs
		if (hasTwoPairs(hand) && !(highestHand)) {
			highestHand = true;
			return "You have Two Pairs!";
		}

		// One Pair
		if (hasAPair(hand) && !(highestHand)) {
			highestHand = true;
			return "You have One Pair!";
		}

		// No Pair
		else {
			return "You have nothing in your hand :(";
		}
	}

	//Checks if its a Straight
	//Without an ace
	public boolean isStraight(ArrayList<Card> hand) {
		boolean isStraight = true;

		for (int i = 0; i <= 3; i++) {
			if (!(hand.get(i).getValue() + 1 == (hand.get(i + 1)).getValue())) {
				isStraight = false;
			}
		}
		return isStraight;
	}
	
	//Checks if it's a Straight with an Ace
	public boolean isStraightWithAce(ArrayList<Card> hand) {
		boolean isStraightWithAce = true;
		if(!(hand.get(4).getValue() == 14)){
			isStraightWithAce = false;
		}		
		for (int i = 0; i <= 2; i++) {
			if (!(hand.get(i).getValue() + 1 == (hand.get(i + 1)).getValue())) {
				isStraightWithAce = false;
			}
		}
		return isStraightWithAce;
	}

	//Checks if it's a Royal Straight
	public boolean isRoyalStraight(ArrayList<Card> hand) {
		boolean isRoyalStraight = true;
		if (!(hand.get(4).getValue() == 14)) {
			isRoyalStraight = false;
		}
		
		if (!(hand.get(0).getValue() == 10)){
			isRoyalStraight = false;
		}
		
		for (int i = 0; i <= 3; i++) {
			if (!(hand.get(i).getValue() + 1 == hand.get(i+1).getValue()) && isRoyalStraight) {
				isRoyalStraight = false;
			}
		}

		return isRoyalStraight;
	}

	//Checks if it's a flush
	public boolean isFlush(ArrayList<Card> hand) {
		boolean isFlush = true;
		for (int i = 0; i <= 3; i++) {
			if (!(hand.get(i).getSuit() == hand.get(i + 1).getSuit())) {
				isFlush = false;
			}
		}
		return isFlush;
	}

	//Checks if there's four of a kind 
	public boolean hasFourOfAKind(ArrayList<Card> hand) {
		boolean hasFourOfAKind = false;
		if (hand.get(0).getValue() == hand.get(1).getValue()) {
			if (hand.get(1).getValue() == hand.get(2).getValue()) {
				if (hand.get(2).getValue() == hand.get(3).getValue()) {
					hasFourOfAKind = true;
				}
			}
		}
		if (hand.get(1).getValue() == hand.get(2).getValue()) {
			if (hand.get(2).getValue() == hand.get(3).getValue()) {
				if (hand.get(3).getValue() == hand.get(4).getValue()) {
					hasFourOfAKind = true;
				}
			}
		}
		return hasFourOfAKind;
	}

	//Checks if its a Full House
	public boolean isFullHouse(ArrayList<Card> hand) {
		boolean isFullHouse = false;
		boolean firstTriple = false;
		boolean lastTriple = false;
		boolean case1 = false;
		boolean case2 = false;
		if (hand.get(0).getValue() == hand.get(1).getValue()) {
			if (hand.get(1).getValue() == hand.get(2).getValue()) {
				firstTriple = true;
			}
		}
		if (hand.get(3).getValue() == hand.get(4).getValue() && firstTriple) {
			case1 = true;
		}
		if (hand.get(2).getValue() == hand.get(3).getValue()) {
			if (hand.get(3).getValue() == hand.get(4).getValue()) {
				lastTriple = true;
			}
		}
		if (hand.get(0).getValue() == hand.get(1).getValue() && lastTriple) {
			case2 = true;
		}
		if (case1 || case2) {
			isFullHouse = true;
		}
		return isFullHouse;
	}

	//Checks if theres three of a kind
	public boolean hasThreeOfAKind(ArrayList<Card> hand) {
		boolean hasThreeOfAKind = false;
		if (hand.get(0).getValue() == hand.get(1).getValue()) {
			if (hand.get(1).getValue() == hand.get(2).getValue()) {
				hasThreeOfAKind = true;
			}
		}
		if (hand.get(1).getValue() == hand.get(2).getValue()) {
			if (hand.get(2).getValue() == hand.get(3).getValue()) {
				hasThreeOfAKind = true;
			}
		}
		if (hand.get(2).getValue() == hand.get(3).getValue()) {
			if (hand.get(3).getValue() == hand.get(4).getValue()) {
				hasThreeOfAKind = true;
			}
		}
		return hasThreeOfAKind;
	}

	//Checks if there's two pairs
	public boolean hasTwoPairs(ArrayList<Card> hand) {
		int numberOfPairs = 0;
		boolean hasTwoPairs = false;
		for (int i = 0; i <= 3; i++) {
			if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
				numberOfPairs++;
			}
		}
		if (numberOfPairs == 2) {
			hasTwoPairs = true;
		}
		return hasTwoPairs;
	}

	//Checks if there's a pair
	public boolean hasAPair(ArrayList<Card> hand) {
		boolean hasAPair = false;
		for (int i = 0; i <= 3; i++) {
			if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
				hasAPair = true;
			}
		}
		return hasAPair;
	}
}
