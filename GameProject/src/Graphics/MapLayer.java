package Graphics;

public class MapLayer {

	private Tile[] tileset;
	private int[][] tiles;
	
	public MapLayer(Tile[] tileset, int[][] tiles) {
		this.tileset = tileset;
		this.tileset[0] = Tile.EMPTY_TILE;
		this.tiles = tiles;
	}
	
	public MapLayer(int height, int width) {
		tileset = new Tile[1];
		tileset[0] = Tile.EMPTY_TILE;
		tiles = new int[height][width];
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
