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
}