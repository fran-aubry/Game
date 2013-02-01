package Geometry;

public class Point {

	protected int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Object other) {
		if(this == other) return true;
		if(!(other instanceof Point)) return false;
		Point P = (Point)other;
		return x == P.x && y == P.y;
	}
	
	public long squaredDistance(Point other) {
		return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
