public class Card implements Comparable<Card> {
	
	private int suit;
	private int value;

	//Creates a "card."
	public Card(int s, int v) {
		suit = s;
		value = v;
	}

	//Compares card values.
	public int compareTo(Card c) {
		int measurement = 0;
		if (this.value < c.value)
			return -1;
		if (this.value > c.value)
			return 1;
		if (this.value==c.value){
			if (this.suit < c.suit){
				return -1;
			}
			if(this.suit > c.suit){
				return 1;
			}
		}
		return measurement;
	}

	//Returns card value.
	public int getValue(){
		return value;
	}
	
	//Returns card suit.
	public int getSuit(){
		return suit;
	}
	
	//Return string representation of a card.
	public String toString() {
		String[] suits = {"Clubs","Diamonds","Hearts","Spades"};
		String[] values = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
		String card = (values[value-2]+" of "+suits[suit-1]);
		return card;		
	}
}
