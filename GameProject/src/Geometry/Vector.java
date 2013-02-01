package Geometry;

public class Vector {

	private Point origin, destination;
	private double dx, dy, length;
	
	public Vector(int x0, int y0, int x1, int y1) {
		origin = new Point(x0, y0);
		destination = new Point(x1, y1);
		dx = x1 - x0;
		dy = y1 - y0;
		length = Math.sqrt(dx * dx + dy * dy);
		dx = dx / length;
		dy = dy / length;
	}
	
	public double getLength() {
		return length;
	}
	
	public double getDx() {
		return dx;
	}
	
	public double getDy() {
		return dy;
	}
	
	public Point getOrigin() {
		return origin;
	}
	
	public Point getDestination() {
		return destination;
	}
}
