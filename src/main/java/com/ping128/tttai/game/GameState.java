package com.ping128.tttai.game;

public class GameState {

	public static final int NUM_PLAYER = 2;
	public static final int BOARD_SIZE = 3;
	public static final String PLAYER_1 = "O";
	public static final String PLAYER_2 = "X";

	private int[][] board;
	private Player[] players;
	public int numTurn;
	private int nextPlayer;

	public GameState() {
		reset();
	}

	public boolean isFinalState() {
		return numTurn == BOARD_SIZE * BOARD_SIZE || getWin() != -1;
	}

	private boolean inBoundary(Move m) {
		return m.row >= 0 && m.row < BOARD_SIZE && m.col >= 0 && m.col < BOARD_SIZE;
	}

	private boolean validMove(Move m) {
		return inBoundary(m) && board[m.row][m.col] == -1;
	}

	// returns -1 if no one wins
	private int getWin() {
		int cx[] = { 0, 1, 1, 1 }, cy[] = { 1, 1, 0, -1 };
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] != -1) {
					for (int k = 0; k < 4; k++) {
						for (int x = 1; x <= 2; x++) {
							int ii = i + cx[k] * x;
							int jj = j + cy[k] * x;
							if (!inBoundary(new Move(ii, jj)) || board[i][j] != board[ii][jj])
								break;
							if (x == 2)
								return board[i][j];
						}
					}
				}
			}
		}
		return -1;
	}

	public String getGrid(int row, int col) {
		if (board[row][col] == -1) return "";
		else return players[board[row][col]].name;
	}
	
	public boolean isDraw() {
		return getWin() == -1;
	}
	
	public String getWinner() {
		return players[getWin()].name;
	}
	
	public String getNextPlayer() {
		return players[nextPlayer].name;
	}
	

	public String getPreviousPlayer() {
		return players[(nextPlayer + NUM_PLAYER - 1) % NUM_PLAYER].name;
	}

	public void makeMove(Move m) throws Exception {
		if (!validMove(m))
			throw new Exception("Invalid Move");
		players[nextPlayer].addMove(new Move(m.row, m.col));
		board[m.row][m.col] = nextPlayer;
		nextPlayer = (nextPlayer + 1) % NUM_PLAYER;
		numTurn++;
	}

	@Override
	public String toString() {
		String ret = "";
		ret += "Turn : " + numTurn + "\n";
		ret += "Next Player: " + players[nextPlayer].name + "\n";
		ret += "+---+---+---+\n";
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == -1)
					ret += "|   ";
				else
					ret += "| " + players[board[i][j]].name + " ";
			}
			ret += "|\n+---+---+---+\n";
		}
		return ret;
	}

	public void reset() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
		players = new Player[NUM_PLAYER];
		players[0] = new Player(PLAYER_1);
		players[1] = new Player(PLAYER_2);
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = -1;
			}
		}
		numTurn = 0;
		nextPlayer = 0;
	}
}
