package com.ping128.tttai.ui;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ping128.tttai.game.GameState;

public class Main extends Applet {

	public static String GAME_NAME = "Tic Tac Toe [AI]";
	private static JFrame frame;
	private static JPanel main;
	private static GameState game;
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		
		game = new GameState();
		
		frame = new JFrame(GAME_NAME);
		frame.setResizable(false);
		frame.setSize(800, 600);
		main = new JPanel();
		main.setBackground(Color.GRAY);

		GridLayout layout = new GridLayout(0, 1);
		main.setLayout(layout);

		JLabel title = new JLabel(GAME_NAME, JLabel.CENTER);
		title.setFont(new Font("Courier", Font.BOLD, 50));

		JButton normalMode = new JButton("Two-Player Mode");
		normalMode.setFont(new Font("Courier", Font.BOLD, 32));
		normalMode.addActionListener(new NormalModeListener(this));

		JButton aiMode = new JButton("AI Mode");
		aiMode.setFont(new Font("Courier", Font.BOLD, 32));
		// aiMode.addActionListener(new MenuListener());
		main.add(title);
		main.add(normalMode);
		main.add(aiMode);

		frame.add(main);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
	}

	public GameState gameState() {
		return game;
	}
	
	private class NormalModeListener implements ActionListener {
		private Main main;

		public NormalModeListener(Main main) {
			this.main = main;
		}

		public void actionPerformed(ActionEvent event) {
			frame.getContentPane().remove(0);
			frame.getContentPane().add(new NormalMode(new BorderLayout(), main), BorderLayout.CENTER);
			frame.repaint();
			frame.validate();
		}
	}

	public void switchBack() {
		frame.getContentPane().remove(0);
		frame.add(main);
		frame.repaint();
		frame.validate();
		game.reset();
	}
}
