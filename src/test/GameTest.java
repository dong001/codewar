package test;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import wargame.Game;
import wargame.Player;

public class GameTest {

	@Test
	public void testGame() {
		int threshold = 10000;
		Player playerOne = new Player();
		Player playerTwo = new Player();
		Game game = new Game(playerOne, playerTwo);
		Map<Player, Integer> count = new HashMap<>();
		for (int i = 0; i < threshold; i++) {
			Player p = game.play();
			count.put(p, count.getOrDefault(p, 0) + 1);
		}
		count.values();
		assertTrue(count.values().size() == 2);
		int playerOneWinCount = count.get(playerOne);
		int playerTwoWinCount = count.get(playerTwo);
		assertTrue(playerOneWinCount + playerTwoWinCount == threshold);
		//each player has 50% chance to win
		assertTrue((double) Math.abs(playerTwoWinCount - playerOneWinCount) / threshold < 0.05);
	}

}
