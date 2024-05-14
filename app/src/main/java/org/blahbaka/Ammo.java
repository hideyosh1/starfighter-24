package org.blahbaka;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing {
	private int speed;
	private static final int SPEED = 2;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;

	// public Ammo() {
	// this(10, 10, WIDTH, HEIGHT, SPEED);
	// }

	public Ammo(int x, int y) {
		// add code
		this(x, y, WIDTH, HEIGHT, SPEED);
	}

	// public Ammo(int x, int y, int s) {
	// this(x, y, WIDTH, HEIGHT, s);
	// // add code
	// }

	public Ammo(int x, int y, int w, int h, int s) {
		super(x, y, w, h);
		this.speed = s;
	}

	public void setSpeed(int s) {
		// add code
		speed = s;
	}

	public int getSpeed() {
		// add gode
		return speed;
	}

	public void draw(Graphics window) {
		// add code to draw the ammo
		// ammo will be a yellow square
		// use window.setColor(COLOR) to set the color
		// if you don't set a color, your ammo will be black and you will not see it
		// use window.fillRect(x,y,w,h); to make a rectangle/square
		window.setColor(Color.yellow);
		window.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	public void move(String direction) {
		setY(getY() - speed);
		// add code to move the ammo
		// ship ammo should only move up
	}

	public String toString() {
		return super.toString() + getSpeed();
	}
}
