package GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Geometry.Rectangle;
import Graphics.Sprite;


public abstract class SpritedObject extends DrawableObject {

	protected Sprite sprite;
	
	public SpritedObject(int x, int y, Sprite sprite) {
		super(x, y);
		this.sprite = sprite;
		setState(State.PERFORMING_ACTION);
	}
	
	public SpritedObject(int x, int y, double speed, Sprite sprite) {
		super(x, y, speed);
		this.sprite = sprite;
		setState(State.PERFORMING_ACTION);
	}
	
	public void draw(Graphics g, int dx, int dy) {
		g.drawImage(sprite.getImage(state), x + dx, y + dy, null);
	}
	
	public Rectangle getRectangle() {
		BufferedImage currentImage = sprite.getImage(state);
		return new Rectangle(x, y, currentImage.getHeight(), currentImage.getWidth());
	}
	
}
