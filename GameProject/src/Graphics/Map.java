package Graphics;

import java.util.LinkedList;
import java.util.Queue;

import Geometry.Index;
import Geometry.Point;

public class Map {

	private final Index[] adjacent = new Index[] {
			new Index(1, 0), 
			new Index(-1, 0), 
			new Index(0, 1), 
			new Index(0, -1),
			new Index(1, 1),
			new Index(-1, -1),
			new Index(1, -1),
			new Index(-1, 1)
	};
	
	private MapLayer[] mapLayers;
	private int height, width;
	
	public Map(MapLayer[] mapLayers) {
		this.mapLayers = mapLayers;
		this.height = mapLayers[0].getHeight();
		this.width = mapLayers[0].getWidth();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int nbOfLayers() {
		return mapLayers.length;
	}
	
	public MapLayer getLayer(int i) {
		return mapLayers[i];
	}

	public Index getCorrespondingIndex(Point P) {
		return new Index(P.getY() / 32, P.getX() / 32);
	}
	
	public Point getCorrespondingPoint(Index K) {
		return new Point(K.getJ() * 32, K.getI() * 32);
	}
	
	public LinkedList<Point> shortestPath(Point origin, Point destination) {
		Index K1 = getCorrespondingIndex(origin);
		Index K2 = getCorrespondingIndex(destination);
		LinkedList<Index> indexes = shortestPath(K1, K2);
		LinkedList<Point> points = new LinkedList<Point>();
		for(Index K : indexes) {
			points.add(getCorrespondingPoint(K));
		}
		return points;
	}
	
	public LinkedList<Index> shortestPath(Index origin, Index destination) {
		Queue<Index> Q = new LinkedList<Index>();
		Q.add(origin);
		Integer[][] distance = new Integer[height][width];
		Index[][] parent = new Index[height][width];
		distance[origin.getI()][origin.getJ()] = 0;
		while(distance[destination.getI()][destination.getJ()] == null && !Q.isEmpty()) {
			Index current = Q.poll();
			for(Index K : adjacent) {
				int i = K.getI() + current.getI();
				int j = K.getJ() + current.getJ();
				if(0 <= i && i < height && 0 <= j && j < width && distance[i][j] == null) {
					boolean isPassable = true;
					for(int l = 0; isPassable && l < mapLayers.length; l++) {
						if(mapLayers[l].getTile(i, j) != null && mapLayers[l].getTile(i, j).getPassability() == Tile.OBSTACLE) {
							isPassable = false;
						}
					}
					if(isPassable) {
						distance[i][j] = 1 + distance[current.getI()][current.getJ()];
						parent[i][j] = current;
						Q.add(new Index(i, j));
					}
					
				}
			}
		}
		return buildPath(parent, destination);
	}
	
	private LinkedList<Index> buildPath(Index[][] parent, Index destination) {
		LinkedList<Index> path = new LinkedList<Index>();
		buildPath(parent, destination, path);
		return path;
	}
	
	private void buildPath(Index[][] parent, Index current, LinkedList<Index> path) {
		path.addFirst(current);
		if(parent[current.getI()][current.getJ()] != null) {
			buildPath(parent, parent[current.getI()][current.getJ()], path);
		}
	}
	
}
