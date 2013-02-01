package Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static final int PASSABLE = 0;
	public static final int OBSTACLE = 1;

	private BufferedImage image;
	private int passability;
	
	public Tile(BufferedImage image, int passability) {
		this.image = image;
		this.passability = passability;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public int getPassability() {
		return passability;
	}
	
}
