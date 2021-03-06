import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand; 

	//Makes new player.
	public Player() {	
		hand = new ArrayList<Card>();
	}

	//Adds card to hand.
	public void addCard(Card c) {
		hand.add(c);
	}
	
	//Removes card from hand.
	public void removeCard(Card c) {
		hand.remove(c);
	}

	//Returns the hand.
	public ArrayList<Card> getHand(){
		return hand;
	}	
}
