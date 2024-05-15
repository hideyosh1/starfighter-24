package org.blahbaka;
//This is done

//Use this file to run the game

import javax.swing.JFrame;
import java.awt.Component;

public class StarFighter extends JFrame {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private OuterSpace theGame;

	public StarFighter() {
		super("STARFIGHTER");

		setSize(WIDTH, HEIGHT);

		theGame = new OuterSpace();
		((Component) theGame).setFocusable(true);
	}

	public void run() {
		getContentPane().add(theGame);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String args[]) {
		StarFighter run = new StarFighter();
		run.run();
	}

}
