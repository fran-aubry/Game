package Graphics;

public class MapLayer {

	private Tile[] tileset;
	private int[][] tiles;
	private boolean isAbove;
	
	public MapLayer(Tile[] tileset, int[][] tiles, boolean isAbove) {
		this.tileset = tileset;
		this.tileset[0] = Tile.EMPTY_TILE;
		this.tiles = tiles;
		this.isAbove = isAbove;
	}
	
	public boolean isAbove() {
		return isAbove;
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
