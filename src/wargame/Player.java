package wargame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Player {
	private Queue<Card> hand = new LinkedList<>();
	private List<Card> cardWin = new ArrayList<>();

	public Queue<Card> getHand() {
		return hand;
	}

	public List<Card> getCardWins() {
		return cardWin;
	}

    public void clear(){
    	this.hand.clear();
    	this.cardWin.clear();
    }
}
