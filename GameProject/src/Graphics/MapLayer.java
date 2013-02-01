package Graphics;

public class MapLayer {

	private Tile[] tileset;
	private int[][] tiles;
	
	public MapLayer(Tile[] tileset, int[][] tiles) {
		this.tileset = tileset;
		this.tiles = tiles;
	}
	
	public Tile getTile(int i, int j) {
		return tileset[tiles[i][j]];
	}
	
	public int getHeight() {
		return tiles.length;
	}
	
	public int getWidth() {
		return tiles[0].length;
	}
	
}
