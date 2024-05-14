
package org.blahbaka;

// import java.awt.Color;
// import java.awt.Image;
// import java.io.File;
// import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.blahbaka.base.Ammo;
// import org.blahbaka.util.Moveable;

public class Bullets {
	List<Ammo> ammo;

	public Bullets() {
		// initalize ammo
		ammo = new ArrayList<Ammo>();
	}

	public void add(Ammo al) {
		// add al to list
		ammo.add(al);
	}

	public void drawEmAll(Graphics window) {
		for (Ammo a : ammo)
			a.draw(window);
		// draw each ammo in the list
	}

	public void moveEmAll() {
		for (Ammo a : ammo)
			a.move("");
		// move each ammon in the list
	}

	public void cleanEmUp() {
		// for every ammo in the list
		// if the ammo is out of bounds
		// remove it
		for (int i = ammo.size(); i >= 0; i--) {
			if (ammo.get(i).getY() < 0) {
				ammo.remove(i);
			}
		}
	}

	public List<Ammo> getList() {
		return ammo;
	}

	public String toString() {
		return "" + ammo;
	}
}
