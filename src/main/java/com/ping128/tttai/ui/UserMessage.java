package com.ping128.tttai.ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserMessage implements WindowListener {
	
	public void invalidMove() {
		showMsg("Invalid Move!");
	}

	public void draw() {
		showMsg("Draw!");
	}
	
	public void win(String winner) {
		showMsg(winner + " Won!");
	}
		
		
	private void showMsg(String msg) {
		JOptionPane pane = new JOptionPane(msg);
		JDialog dialog = pane.createDialog(new JFrame(), Main.GAME_NAME);
		dialog.setVisible(true);
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
