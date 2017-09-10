package is.ru.tgra.utils;

public class Utils {
	/**
	 * Private constructor to prevent anyone from creating an instance of this class.
	 * This is done because the Utils class is to be used as a static class.
	 */
	private Utils() {}
	
	public static float Clamp(float value, float min, float max) {
		return Math.max(min, Math.min(max, value));
	}
	
	public static Point2D getPointOnLineFrom(Point2D origin, Vector2D direction, float distance) {
		Vector2D norm = direction.normalize();
		float x = origin.x + (distance * norm.x);
		float y = origin.y + (distance * norm.y);
		return new Point2D(x,y);
	}
	
	public static Point2D getPointOnCircle(Point2D origin, float radius, float angle) {
		float x = origin.x + radius * (float)Math.cos(angle);
		float y = origin.y + radius * (float)Math.sin(angle);
		return new Point2D(x,y);
	}
	
	public static float tHit(Point2D A, Point2D B, Vector2D n, Vector2D c) {
		Vector2D BminA = new Vector2D(B.x-A.x, B.y-A.y);
		return ((n.dot(BminA)) / (n.dot(c)));
	}
}
