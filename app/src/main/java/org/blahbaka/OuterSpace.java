
package org.blahbaka;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
// import static java.lang.Character.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;

import org.blahbaka.base.Ammo;
import org.blahbaka.util.Direction;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

	/*
	 * uncomment and comment as necessary as you add functionality to your project*
	 * 
	 */
	private AlienHorde horde;
	private Bullets shots;
	private Bullets alienShots;
	private Ship ship;

	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace() {
		setBackground(Color.black);

		keys = new boolean[5];
		shots = new Bullets();
		alienShots = new Bullets();

		// instantiate what you need as you need it (from global objects above)
		ship = new Ship(0, 0);
		// alienOne = new Alien(100, 50,30, 30, 2);
		// alienTwo = new Alien(150, 50, 30, 30, 2 );
		horde = new AlienHorde(20);

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

	@Override
	public void update(Graphics window) {
		paint(window);
	}

	// the top part of the paint method is done for you
	@Override
	public void paint(Graphics window) {
		// set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D) window;

		// take a snap shot of the current screen and save it as an image
		// that is the exact same width and height as the current screen
		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));

		// create a graphics reference to the back ground image
		// we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
		// this sets the background for the graphics window
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, getWidth(), getHeight());

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
		alienShots.drawAll(window);

		horde.removeDead(shots.getList());
		ship.takeDamage(alienShots.getList());

		shots.cleanUp();
		alienShots.cleanUp();

		shots.drawAll(graphToBack);
		horde.drawAll(graphToBack);
		ship.draw(graphToBack);

		twoDGraph.drawImage(back, null, 0, 0);
		back = null;
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
			while (true) {
				// TODO setup a timer
				Thread.sleep(5);
				repaint();
			}
		} catch (Exception e) {
			// feel free to add something here, or not
		}
	}

}
