package org.blahbaka.base;

// import java.io.File;
// import java.net.URL;
// import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

import java.io.InputStream;
import org.blahbaka.util.MovingThing;
import org.blahbaka.StarFighter;

public class Alien extends MovingThing {
	private int speed;
	private Image image;

	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	private static final int SPEED = 1;

	private static final String ALIEN_PATH = "alien.jpg";

	// private Alien() {
	// this(0, 0, WIDTH, HEIGHT, SPEED);
	// }

	public Alien(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED);
		// add code here
	}

	// private Alien(int x, int y, int s) {
	// this(x, y, WIDTH, HEIGHT, s);
	// // add code here
	// }

	public Alien(int x, int y, int w, int h, int s) {
		super(x, y, w, h);
		this.speed = s;
		// add code here
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream(ALIEN_PATH);
			image = ImageIO.read(is);
		} catch (Exception e) {
			// commit apoptosis
			//
		}
	}

	public void setSpeed(int s) {
		// add code
		this.speed = s;
	}

	public int getSpeed() {
		// add code
		return speed;
	}

	public void move(String direction) {
		// add code here
		// check that the alien is within the bounds of the screen (see
		// Starfighter.java)
		// if alien is out of bounds change speed direction
		// and move the alien down a row (40 pixels)
		// constantly change the x position of the alien by the speed

		if (getX() > StarFighter.WIDTH) {
			setX(StarFighter.WIDTH);
			setY(getY() + 40);
			speed = -speed;
		} else if (getX() < 0) {
			setX(0);
			setY(getY() + 40);
			speed = -speed;
		}
		setX(getX() + speed);

	}

	/*
	 * The draw method is done for you.
	 * This method will move the alien and update it's location on screen by
	 * constantly redrawing it.
	 */
	public void draw(Graphics window) {
		// System.out.println("drawn" + " " + getX() + " " + getY());
		window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}

	public String toString() {
		return super.toString() + getSpeed();
	}

}
