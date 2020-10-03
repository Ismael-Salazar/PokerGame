import java.util.Random;

public class Deck {
	
	//Instance Variables
	private Card[] theDeck;
	private int top;

	//Makes 52 card deck
	//14 ia used to represent an ace.
	public Deck() {
		theDeck = new Card[52];
		int count=0;
		for (int i=1; i<=4; i++){
			for (int j=2; j<=14; j++){
				theDeck[count] = new Card(i,j);
				count++;
			}	
		}
		shuffle();
	}	
	
	//Shuffles the deck
	public void shuffle() {
		Random rand = new Random();
		for (int i=0; i<=10000; i++){
			int randomIndex1 = rand.nextInt(52);
			Card temp = theDeck[randomIndex1];
			int randomIndex2 = rand.nextInt(52);
			theDeck[randomIndex1] = theDeck[randomIndex2];
			theDeck[randomIndex2] = temp;
		}
		top=0;
	}

	//Returns the top card of the deck
	public Card deal() {
		Card topCard = theDeck[top];
		top++;
		return topCard;
		}
}
