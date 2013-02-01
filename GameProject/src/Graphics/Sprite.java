package Graphics;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import GameObjects.State;

public class Sprite {

	private HashMap<State, Animation> spriteSheet;
	
	public Sprite(HashMap<State, Animation> spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	public BufferedImage getImage(State state) {
		Animation animation = spriteSheet.get(state);
		BufferedImage image = animation.getCurrentImage();
		animation.goToNextImage();
		return image;
	}
	
}
