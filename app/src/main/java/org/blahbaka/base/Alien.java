package org.blahbaka.base;

// import java.io.File;
// import java.net.URL;
// import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

import java.io.InputStream;

import org.blahbaka.util.MovingThing;
import org.blahbaka.util.Direction;
import org.blahbaka.StarFighter;

public class Alien extends MovingThing {
	private int speed;
	private Image image;

	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	public static final int SPEED = 1;

	private Direction myh;

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
		speed = s;
		myh = Direction.RIGHT;
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

	@Override
	public void move(Direction direction) {
		// add code here
		// check that the alien is within the bounds of the screen (see
		// Starfighter.java)
		// if alien is out of bounds change speed direction
		// and move the alien down a row (40 pixels)
		// constantly change the x position of the alien by the speed
		switch (myh) {
			case LEFT:
				setX(getX() - speed);
				break;
			case RIGHT:
			default:
				setX(getX() + speed);
				break;

		}

		if (getX() > (StarFighter.WIDTH - WIDTH)) {
			setX(StarFighter.WIDTH - WIDTH);
			setY(getY() + 40);

			myh = Direction.LEFT;
		} else if (getX() < 0) {
			setX(0);
			setY(getY() + 40);

			myh = Direction.RIGHT;
		}

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

	public Ammo shoot() {
		return new Ammo(getX(), getY());
	}

	public String toString() {
		return super.toString() + getSpeed();
	}

}
