package IO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import GameObjects.State;
import Graphics.Animation;
import Graphics.Map;
import Graphics.MapLayer;
import Graphics.Sprite;
import Graphics.Tile;


public class DataReader {

	public static Sprite readSprite(String filename) {
		Sprite sprite = null;
		try {
			Scanner reader = new Scanner(new FileReader("Sprites/" + filename));
			int numberOfStates = reader.nextInt();
			HashMap<State, Animation> spriteSheet = new HashMap<State, Animation>();
			for(int i = 0; i < numberOfStates; i++) {
				String stateName = reader.next();
				String animationFilename = reader.next();
				Animation animation = readAnimation(animationFilename);
				spriteSheet.put(State.getFromName(stateName), animation);
			}
			sprite = new Sprite(spriteSheet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}

	public static Animation readAnimation(String filename) {
		BufferedImage[] frames = null;
		int timePerFrame = 0;
		try {
			Scanner reader = new Scanner(new FileReader("Animations/" + filename));
			int numberOfFrames = reader.nextInt();
			timePerFrame = reader.nextInt();
			frames = new BufferedImage[numberOfFrames];
			for(int i = 0; i < numberOfFrames; i++) {
				String imageFilename = reader.next();
				frames[i] = ImageIO.read(new File("Images/"  +imageFilename));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Animation(frames, timePerFrame);
	}
	
	public static Map readMap(String filename) {
		Map map = null;
		try {
			Scanner reader = new Scanner(new FileReader("Maps/" + filename));
			int height = reader.nextInt();
			int width = reader.nextInt();
			int nbOfLayers = reader.nextInt();
			MapLayer[] mapLayers = new MapLayer[nbOfLayers];
			for(int i = 0; i < nbOfLayers; i++) {
				mapLayers[i] = readMapLayer(reader.next(), height, width);
			}
			map = new Map(mapLayers);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static MapLayer readMapLayer(String filename, int height, int width) {
		int[][] tiles = null;
		Tile[] tileset = null;
		try {
			Scanner reader = new Scanner(new FileReader("Maps/" + filename));
			String tilesetFilename = reader.next();
			tileset = readTileset(tilesetFilename);
			tiles = new int[height][width];
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					tiles[i][j] = reader.nextInt();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new MapLayer(tileset, tiles);
	}
	
	public static Tile[] readTileset(String filename) {
		Tile[] tileset = null;
		try {
			Scanner reader = new Scanner(new FileReader("Tilesets/" + filename));
			int size = reader.nextInt();
			tileset = new Tile[size + 1];
			tileset[0] = Tile.EMPTY_TILE;
			for(int i = 0; i < size; i++) {
				String imageFilename = reader.next();
				int passability = reader.nextInt();
				int cut = reader.nextInt();
				tileset[i + 1] = new Tile(ImageIO.read(new File("Images/" + imageFilename)), passability, cut);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tileset;
	}
	
}
