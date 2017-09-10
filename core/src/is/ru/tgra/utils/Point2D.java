package is.ru.tgra.utils;

public class Point2D {
	public float x;
	public float y;
	
	public Point2D(){}
	
	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public float distance(Point2D p2) {
		return (float)Math.sqrt(Math.pow(x-p2.x, 2) + Math.pow(y-p2.y, 2));
	}
	
	public boolean isBetween(Point2D a, Point2D b) {
		return this.distance(a) + this.distance(b) == a.distance(b);
	}
}
