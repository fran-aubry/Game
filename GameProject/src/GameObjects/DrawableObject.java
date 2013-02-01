package GameObjects;

import java.awt.Graphics;

import Geometry.Rectangle;

public abstract class DrawableObject extends GameObject {

	public DrawableObject(int x, int y) {
		super(x, y);
	}
	
	public DrawableObject(int x, int y, double speed) {
		super(x, y, speed);
	}

	public abstract void draw(Graphics g, int dx, int dy);
	
	public abstract Rectangle getRectangle();
	
}
