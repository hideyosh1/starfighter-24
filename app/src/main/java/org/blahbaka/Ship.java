
package org.blahbaka;

// import java.io.File;
// import java.net.URL;
// import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

import java.io.InputStream;
import java.util.List;

import org.blahbaka.util.MovingThing;
import org.blahbaka.base.Ammo;
import org.blahbaka.util.Direction;

public class Ship extends MovingThing {
	private int lives;
	private int speed;
	private Image image;
	private static final String SHIP_PATH = "ship.jpg";

	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;
	public static final int SPEED = 2;

	// public Ship() {
	// this(0, 0, 50, , 0);
	// }
	public int getLives() {
		return lives;
	}

	public Ship(int x, int y) {
		// add code here
		this(x, y, WIDTH, HEIGHT, SPEED);
	}

	// public Ship(int x, int y, int s) {
	// // add code here
	// this(x, y, 50, 50, s);
	// }

	public Ship(int x, int y, int w, int h, int s) {
		// add code here
		setPos(x, y);
		setWidth(w);
		setHeight(h);
		speed = s;
		lives = 3;

		try {
			// this sets ship.jpg as the image for your ship
			// for this to work ship.jpg needs to be in the same folder as this .java file

			// URL url = getClass().getResource(SHIP_PATH);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream(SHIP_PATH);
			image = ImageIO.read(is);
		} catch (Exception e) {
			// commit apoptosis
		}
	}

	public void setSpeed(int s) {
		speed = s;
		// add more code
	}

	public int getSpeed() {
		// continue coding
		return speed;
	}

	@Override
	public void move(Direction direction) {
		// add code here
		// think about ALL your global variables and how you can use them to "move"
		// keep your parameter in mind as well
		switch (direction) {
			case UP:
				setY(getY() - speed);
				break;
			case LEFT:
				setX(getX() - speed);
				break;
			case RIGHT:
				setX(getX() + speed);
				break;
			case DOWN:
				setY(getY() + speed);
				break;
			default:
				break;
		}

	}

	@Override
	public void draw(Graphics window) {
		window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}

	public void takeDamage(List<Ammo> shots) {
		for (int i = shots.size() - 1; i >= 0; i--) {
			if (collides(shots.get(i)) || shots.get(i).collides(this)) {
				System.out.println(--lives);
				shots.remove(i);
				break;
			}
		}

	}

	public String toString() {
		return super.toString() + " " + getSpeed();
	}
}
