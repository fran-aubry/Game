package Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static final int PASSABLE = 0;
	public static final int OBSTACLE = 1;

	private BufferedImage groundImage, skyImage;
	private int passability, cut;

	public static Tile EMPTY_TILE = new Tile(null, PASSABLE, 0);
	
	public Tile(BufferedImage image, int passability, int cut) {
		if(image != null) {
			if(cut == 0) {
				this.skyImage = null;
				this.groundImage = image;
			} else if(cut == 32) {
				this.skyImage = image;
				this.groundImage = null;
			} else {
				this.skyImage = image.getSubimage(0, 0, 32, cut);
				this.groundImage = image.getSubimage(0, cut, 32, 32 - cut);				
			}
		} else {
			this.skyImage = null;
			this.groundImage = null;
		}
		this.cut = cut;
		this.passability = passability;
	}
	
	public int getCut() {
		return cut;
	}
	
	public BufferedImage getGroundImage() {
		return groundImage;
	}
	
	public BufferedImage getSkyImage() {
		return skyImage;
	}

	public int getPassability() {
		return passability;
	}
	
	public boolean isEmpty() {
		return groundImage == null && skyImage == null;
	}
	
}
