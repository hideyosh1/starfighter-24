
package org.blahbaka;

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

import java.io.InputStream;

public class Ship extends MovingThing {
	private int speed;
	private Image image;
	private static final String SHIP_PATH = "ship.jpg";

	public Ship() {
		this(0, 0, 50, 50, 0);
	}

	public Ship(int x, int y) {
		// add code here
		this(x, y, 50, 50, 0);
	}

	public Ship(int x, int y, int s) {
		// add code here
		this(x, y, 50, 50, s);
	}

	public Ship(int x, int y, int w, int h, int s) {
		// add code here
		setPos(x, y);
		setWidth(w);
		setHeight(h);
		speed = s;

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

	public void move(String direction) {
		// add code here
		// think about ALL your global variables and how you can use them to "move"
		// keep your parameter in mind as well
		switch (direction) {
			case "UP":
				setY(getY() - speed);
				break;
			case "LEFT":
				setX(getX() - speed);
				break;
			case "RIGHT":
				setX(getX() + speed);
				break;
			case "DOWN":
				setY(getY() + speed);
				break;
			default:
				break;
		}

	}

	public void draw(Graphics window) {
		window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}

	public String toString() {
		return super.toString() + " " + getSpeed();
	}
}
