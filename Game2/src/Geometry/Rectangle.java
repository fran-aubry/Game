package Geometry;

public class Rectangle {

	private int height, width;
	private Point ul, dr;

	public Rectangle(int x, int y, int height, int width) {
		this.height = height;
		this.width = width;
		ul = new Point(x, y);
		dr = new Point(x + width, y + height);
	}
	
	public Point getUL() {
		return ul;
	}
	
	public Point getDR() {
		return dr;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

	public boolean containsPoint(Point p) {
		return ul.getX() <= p.getX() && p.getX() <= dr.getX() && 
				ul.getY() <= p.getY() && p.getY() <= dr.getY();
	}

	public boolean intersects(Rectangle other) {
		return this.containsPoint(other.dr) || this.containsPoint(other.ul) ||
				other.containsPoint(dr) || other.containsPoint(ul);
	}
	
	public String toString() {
		return "[" + ul.toString() + "; " + dr.toString() + "]";
	}

}
