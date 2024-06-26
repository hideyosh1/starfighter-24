
package org.blahbaka;

// import java.awt.Color;
// import java.awt.Image;
// import java.io.File;
// import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.blahbaka.base.Alien;
import org.blahbaka.base.Ammo;

import org.blahbaka.util.Direction;

public class AlienHorde {
	private List<Alien> aliens;

	private static double shotProbability = 0.001;

	public AlienHorde(int size) {
		// initalize ArrayList
		// and fill with size amount of aliens (75 pixels apart)
		// if your row is full (out of bounds of screen)
		// move down a row (75 pixels)
		// starting point is 25, 50
		// first add aliens with speed of 0 to make sure you spacing is good
		//
		int x = 25;
		int y = 50;
		final int distance = 75;

		aliens = new ArrayList<Alien>();
		for (int i = 0; i < size; i++) {
			aliens.add(new Alien(x, y));
			x += distance;
			if (x > StarFighter.WIDTH - Alien.WIDTH) {
				x = 25;
				y += distance;
			}
		}

	}

	public void setSpeed(int speed) {
		for (Alien a : aliens)
			a.setSpeed(speed);
	}

	public void add(Alien al) {
		// add an al to the list
		aliens.add(al);
	}

	public void drawAll(Graphics window) {
		// make sure you draw all aliens in the list
		for (Alien a : aliens) {
			a.draw(window);
		}
	}

	public void moveAll() {
		// make sure you move all aliens in the list
		for (Alien a : aliens) {
			a.move(Direction.NONE);
		}
	}

	public void shootAll(Bullets bullets) {
		for (Alien a : aliens) {
			if (Math.random() < shotProbability) {
				bullets.add(a.shoot());
			}
		}
		// bullets.cleanUp();
	}

	public void removeDead(List<Ammo> shots) {
		/*
		 * Part 3
		 * for every shot in the list
		 * check if you've hit any alien in the list
		 * (do the coordinates of the shot fall between the boundarises of the alien)
		 * if they do then remove the alien and the shot
		 * make sure you break out of the loop if this happens
		 */
		for (int i = shots.size() - 1; i >= 0; i--) {
			for (int j = aliens.size() - 1; j >= 0; j--) {
				if (aliens.get(j).collides(shots.get(i)) || shots.get(i).collides(aliens.get(j))) {
					aliens.remove(j);
					shots.remove(i);
					break;
				}
			}
		}
	}

	public boolean gameOver() {
		for (Alien a : aliens) {
			if (a.getY() > StarFighter.HEIGHT) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return "" + aliens;
	}
}
