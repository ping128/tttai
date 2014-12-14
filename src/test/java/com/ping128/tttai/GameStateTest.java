package com.ping128.tttai;

import com.ping128.tttai.game.GameState;
import com.ping128.tttai.game.Move;

public class GameStateTest {

	public static void main (String[] args) {
		GameState game = new GameState();
		try {
			game.makeMove(new Move(2, 0));
			game.makeMove(new Move(1, 0));
			game.makeMove(new Move(1, 1));
			game.makeMove(new Move(2, 2));
			System.out.println(game.getWinner());
			System.out.println(game);
			game.makeMove(new Move(0, 2));
			System.out.println(game);
			System.out.println(game.getWinner());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(game.toString());
	}

}
