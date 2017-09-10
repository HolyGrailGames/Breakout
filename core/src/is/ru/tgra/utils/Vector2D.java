package is.ru.tgra.utils;

public class Vector2D {
	public float x;
	public float y;
	
	public Vector2D(){}
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public Vector2D normalize() {
		x = x / getLength();
		y = y / getLength();
		return this;
	}
	
	public float getLength() {
		return (float)Math.sqrt((x*x)+(y*y));
	}
	
	public float getAngle() {
		return (float)Math.atan2(y, x);
	}
	
	public float dot(Vector2D v2) {
		return x*v2.x + y*v2.y;
	}
	
	public static Vector2D up() {
		return new Vector2D(0,1);
	}
	public static Vector2D down() {
		return new Vector2D(0,-1);
	}
	public static Vector2D left() {
		return new Vector2D(-1,0);
	}
	public static Vector2D right() {
		return new Vector2D(1,0);
	}
}
