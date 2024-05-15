
package org.blahbaka;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
// import static java.lang.Character.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;
import java.io.InputStream;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.blahbaka.base.Ammo;
import org.blahbaka.util.Direction;

public class OuterSpace extends JPanel implements KeyListener, Runnable {

	/*
	 * uncomment and comment as necessary as you add functionality to your project*
	 * 
	 */
	private boolean running;

	private Font uiFont;
	private static final String FONT_PATH = "fonts/PixelifySans-Regular.ttf";

	private AlienHorde horde;
	private Bullets shots;
	private Bullets alienShots;
	private Ship ship;
	private boolean[] keys;

	private JPanel menuPanel;
	private State state;

	public OuterSpace() {
		super(new BorderLayout());
		setBackground(Color.black);
		running = true;
		state = State.START;

		keys = new boolean[5];
		shots = new Bullets();
		alienShots = new Bullets();

		setDoubleBuffered(true);

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(FONT_PATH);
		try {
			uiFont = Font.createFont(Font.TRUETYPE_FONT, is);
			uiFont = uiFont.deriveFont(40.0f);
		} catch (Exception e) {

		}

		// instantiate what you need as you need it (from global objects above)
		ship = new Ship(400, 400);
		horde = new AlienHorde(20);

		menuPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel("StarFighter");
		JButton easy = new JButton("easy");
		JButton mid = new JButton("medium");
		JButton hard = new JButton("hard");
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = State.PLAYING;
				remove(menuPanel);
			}
		});
		mid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = State.PLAYING;
				horde.setSpeed(3);
				remove(menuPanel);
			}
		});
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = State.PLAYING;
				remove(menuPanel);
			}
		});
		menuPanel.add(title);
		menuPanel.add(easy);
		menuPanel.add(mid);
		menuPanel.add(hard);
		add(menuPanel);

		title.setVisible(true);
		easy.setVisible(true);
		mid.setVisible(true);
		hard.setVisible(true);
		menuPanel.setVisible(true);

		addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

	@Override
	public void update(Graphics window) {
		paint(window);
	}

	// the top part of the paint method is done for you
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch (state) {
			case PLAYING:
				paintPlaying(g);
				break;
			case GAMEOVER:
				break;
			case START:
				paintStart(g);
			default:
				break;

		}
	}

	private void paintStart(Graphics window) {
		revalidate();
	}

	private void paintPlaying(Graphics window) {

		// set up the double buffering to make the game animation nice and smooth
		// take a snap shot of the current screen and save it as an image
		// that is the exact same width and height as the current screen

		// create a graphics reference to the back ground image
		// we will draw all changes on the background image
		// this sets the background for the graphics window
		window.setColor(Color.BLACK);
		window.fillRect(0, 0, getWidth(), getHeight());

		// add code to move Ship, Alien, etc.-- Part 1
		if (keys[0] == true) {
			ship.move(Direction.LEFT);
		}
		if (keys[1] == true) {
			ship.move(Direction.RIGHT);
		}
		if (keys[2] == true) {
			ship.move(Direction.UP);
		}
		if (keys[3] == true) {
			ship.move(Direction.DOWN);
		}

		// add code to fire a bullet - Part 3

		if (keys[4] == true) {
			Ammo newammo = new Ammo(
					ship.getX() + ship.getWidth() / 2 - Ammo.WIDTH / 2,
					ship.getY());

			shots.add(newammo);
			keys[4] = false;
		}
		// add in collision detection to see if Bullets hit the Aliens and if Bullets

		// hit the Ship -- Part 3

		// make sure you've drawn all your stuff

		shots.moveAll(Direction.UP);
		horde.moveAll();

		horde.shootAll(alienShots);
		alienShots.moveAll(Direction.DOWN);

		horde.removeDead(shots.getList());
		ship.takeDamage(alienShots.getList());

		shots.cleanUp();
		alienShots.cleanUp();

		alienShots.drawAll(window);
		shots.drawAll(window);
		horde.drawAll(window);
		ship.draw(window);

		window.setColor(Color.white);
		window.setFont(uiFont);
		window.drawString(
				"" + ship.getLives() + " "
						+ (ship.getLives() == 1 ? "life remains"
								: "lives remain"),
				0,
				40);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = true;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = false;
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// no code needed here
		// method needs to be implemented
		// because class implements KeyListner
	}

	@Override
	public void run() {
		try {
			while (running) {
				// horrific
				Thread.sleep(5);
				repaint();

			}
		} catch (Exception e) {
			// feel free to add something here, or not
		}
	}

	private enum State {
		GAMEOVER,
		START,
		PLAYING
	}

}
