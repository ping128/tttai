package com.ping128.tttai.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
	public String name;
	List<Move> moves;

	public Player() {
		this("player");
	}

	public Player(String name) {
		this.name = name;
		moves = new ArrayList<Move>();
	}

	void addMove(Move m) {
		moves.add(m);
	}
}
