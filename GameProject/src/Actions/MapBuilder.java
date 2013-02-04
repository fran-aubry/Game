package Actions;

import java.util.LinkedList;

import Graphics.Map;
import Graphics.MapLayer;

public class MapBuilder {

	private int height, width;
	private LinkedList<MapLayer> layers;
	
	public MapBuilder(int height, int width) {
		this.height = height;
		this.width = width;
		layers = new LinkedList<MapLayer>();
		layers.add(new MapLayer(height, width));
	}
	
	public void addLayer() {
		layers.add(new MapLayer(height, width));
	}

	public Map getMap() {
		MapLayer[] mapLayers = new MapLayer[layers.size()];
		int i = 0;
		for(MapLayer mapLayer : layers) {
			mapLayers[i++] = mapLayer;
		}
		return new Map(mapLayers);
	}
	
	
}
