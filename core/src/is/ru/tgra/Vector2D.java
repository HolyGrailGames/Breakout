package is.ru.tgra;

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
}
