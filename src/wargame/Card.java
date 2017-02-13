package wargame;

public class Card {
	private int cardValue;
	private String display;
	private Suit suit;

	public Card(int cardValue, String display, Suit suit) {
		super();
		this.cardValue = cardValue;
		this.display = display;
		this.suit = suit;
	}

	public int getCardValue() {
		return cardValue;
	}

	public String getDisplay() {
		return display;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		return "Card [cardValue=" + cardValue + ", display=" + display + ", suit=" + suit + "]";
	}

}
