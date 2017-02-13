package wargame;

import java.util.Stack;

public class Board {
	private Stack<Card> playerOneStack = new Stack<>();
	private Stack<Card> playerTwoStack = new Stack<>();

	public Stack<Card> getPlayerOneStack() {
		return playerOneStack;
	}

	public void setPlayerOneStack(Stack<Card> playerOneStack) {
		this.playerOneStack = playerOneStack;
	}

	public Stack<Card> getPlayerTwoStack() {
		return playerTwoStack;
	}

	public void setPlayerTwoStack(Stack<Card> playerTwoStack) {
		this.playerTwoStack = playerTwoStack;
	}

}
