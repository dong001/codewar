package wargame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Game {
	private List<Card> cardList;
	private Player playerOne;
	private Player playerTwo;
	private Board desk;

	public Game(Player firstPlayer, Player secondPlayer) {
		super();
		this.playerOne = firstPlayer;
		this.playerTwo = secondPlayer;
		this.desk = new Board();
		initial();
	}

	private void initial() {
		this.cardList = new ArrayList<>();
		Map<Integer, String> map = new HashMap<>();
		map.put(11, "Jack");
		map.put(12, "Queen");
		map.put(13, "King");
		map.put(14, "Aces");
		for (int i = 2; i <= 14; i++) {
			for (Suit suit : Suit.values()) {
				Card card = new Card(i, map.getOrDefault(i, String.valueOf(i)) + " " + suit, suit);
				cardList.add(card);
			}
		}
	}

	/**
	 * 1 shuffle the cardList
	 * 2 assign each player 1/2 cards 
	 * 3 play the game: clear the previous game
	 * Stack is the board, each of two player put their cards to their board
	 * compare the value of the two cards, whoever owns the larger value card will
	 * take all the cards,and add to their win card list.
	 * 4 return the winner who got all the cards.
	 * @return Player
	 */
	public Player play() {
		Collections.shuffle(cardList);
		Queue<Card> playerOneHand = playerOne.getHand();
		Queue<Card> playerTwoHand = playerTwo.getHand();
		List<Card> playerOneCardWin = playerOne.getCardWins();
		List<Card> playerTwoCardWin = playerTwo.getCardWins();
		playerOne.clear();
		playerTwo.clear();

		for (int i = 0; i < cardList.size() / 2; i++) {
			playerOneHand.offer(cardList.get(i));
		}

		for (int i = cardList.size() / 2; i < cardList.size(); i++) {
			playerTwoHand.offer(cardList.get(i));
		}

		Stack<Card> playerOneStack = desk.getPlayerOneStack();
		Stack<Card> playerTwoStack = desk.getPlayerTwoStack();

		while ((!playerOneCardWin.isEmpty() || !playerOneHand.isEmpty())
				&& (!playerTwoCardWin.isEmpty() || !playerTwoHand.isEmpty())) {
			this.moveCards(playerOneHand, playerOneCardWin, playerTwoHand, playerTwoCardWin);

			playerOneStack.push(playerOneHand.poll());
			playerTwoStack.push(playerTwoHand.poll());
			// case1: the player1's card value is bigger than player2's
			if (playerOneStack.peek().getCardValue() > playerTwoStack.peek().getCardValue()) {
				while (!playerOneStack.isEmpty()) {
					playerOneCardWin.add(playerOneStack.pop());
				}
				while (!playerTwoStack.isEmpty()) {
					playerOneCardWin.add(playerTwoStack.pop());
				}
			// case 2:the player2's card value is bigger than player1's
			} else if (playerOneStack.peek().getCardValue() < playerTwoStack.peek().getCardValue()) {
				while (!playerTwoStack.isEmpty()) {
					playerTwoCardWin.add(playerTwoStack.pop());
				}
				while (!playerOneStack.isEmpty()) {
					playerTwoCardWin.add(playerOneStack.pop());
				}
			// case 3:the player1's card value is equals to the player1's
			} else {
				if (playerOneCardWin.isEmpty() && playerOneHand.isEmpty()) {
					return playerTwo;
				} else if (playerTwoCardWin.isEmpty() && playerTwoHand.isEmpty()) {
					return playerOne;
				}
				this.moveCards(playerOneHand, playerOneCardWin, playerTwoHand, playerTwoCardWin);
				playerOneStack.push(playerOneHand.poll());
				playerTwoStack.push(playerTwoHand.poll());
			}
		}
		if (playerOneCardWin.isEmpty() && playerOneHand.isEmpty()) {
			return playerTwo;
		} else {
			return playerOne;
		}
	}
  
	private void moveCards(Queue<Card> playerOneHand, List<Card> playerOneCardWin, Queue<Card> playerTwoHand,
			List<Card> playerTwoCardWin) {
		if (playerOneHand.isEmpty()) {
			Collections.shuffle(playerOneCardWin);
			playerOneHand.addAll(playerOneCardWin);
			playerOneCardWin.clear();
		}

		if (playerTwoHand.isEmpty()) {
			Collections.shuffle(playerTwoCardWin);
			playerTwoHand.addAll(playerTwoCardWin);
			playerTwoCardWin.clear();
		}
	}

}
