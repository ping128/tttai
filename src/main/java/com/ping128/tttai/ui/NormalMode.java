package com.ping128.tttai.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ping128.tttai.game.GameState;
import com.ping128.tttai.game.Move;

public class NormalMode extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton[][] board;
	private JButton backButton;
	private Main mainMenu;
	private JLabel msg;
	private UserMessage userMsg;
	
	public NormalMode(LayoutManager layout, Main mainMenu) {
		super(layout);
		
		userMsg = new UserMessage();
		this.mainMenu = mainMenu;
		
		JLabel title = new JLabel("Normal Mode", JLabel.CENTER);
		title.setFont(new Font("Courier", Font.BOLD, 50));
		title.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		add(title, BorderLayout.NORTH);

		JPanel mainFrame = new JPanel(new BorderLayout());
		GridLayout grid = new GridLayout(GameState.BOARD_SIZE, GameState.BOARD_SIZE);
		grid.setHgap(0);
		grid.setVgap(0);
		JPanel leftFrame = new JPanel(grid);
		leftFrame.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 0));
		board = new JButton[GameState.BOARD_SIZE][GameState.BOARD_SIZE];
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int j = 0; j < GameState.BOARD_SIZE; j++) {
				board[i][j] = new JButton();
				board[i][j].setPreferredSize(new Dimension(123, 123));
				board[i][j].addActionListener(new GameEvent(new Move(i, j)));
				board[i][j].setFont(new Font("Courier", Font.BOLD, 50));
				board[i][j].setBackground(Color.YELLOW);
				board[i][j].setOpaque(true);
				leftFrame.add(board[i][j]);
			}
		}

		JPanel rightFrame = new JPanel(new BorderLayout());
		rightFrame.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 50));

		msg = new JLabel("Next Player: " + mainMenu.gameState().getNextPlayer());
		msg.setFont(new Font("Courier", Font.PLAIN, 32));

		backButton = new JButton("Back");
		backButton.addActionListener(this);

		rightFrame.add(msg, BorderLayout.NORTH);
		rightFrame.add(backButton, BorderLayout.SOUTH);

		mainFrame.add(leftFrame, BorderLayout.WEST);
		mainFrame.add(rightFrame, BorderLayout.EAST);
		add(mainFrame, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent event) {
		mainMenu.switchBack();
	}
	
	private class GameEvent implements ActionListener {
		private Move move;

		public GameEvent(Move move) {
			this.move = move;
		}

		public void actionPerformed(ActionEvent event) {
			GameState game;
			try {
				game = mainMenu.gameState();
				game.makeMove(move);
				mapState();
				if (game.isFinalState()) {
					if (game.isDraw()) {
						userMsg.draw();
					} else {
						userMsg.win(game.getWinner());
					}
					mainMenu.gameState().reset();
					mapState();
				}
			} catch (Exception e) {
				userMsg.invalidMove();
			}
		}
	}
	
	public void mapState() {
		GameState game =  mainMenu.gameState();
		msg.setText("Next Player: " + game.getNextPlayer());
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int j = 0; j < GameState.BOARD_SIZE; j++) {
				String p = game.getGrid(i, j);
				board[i][j].setText(p);
				if (p.equals(GameState.PLAYER_1)) board[i][j].setForeground(Color.GREEN);
				else if (p.equals(GameState.PLAYER_2)) board[i][j].setForeground(Color.RED);
			}
		}
	}
}
