package is.ru.tgra.utils;

public class Point2D {
	public float x;
	public float y;
	public static final float epsilon = 10.0f;
	
	public Point2D(){}
	
	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Point2D(Point2D aPoint) {
		this(aPoint.x, aPoint.y);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public float distance(Point2D p2) {
		return (float)Math.sqrt(Math.pow(p2.x - x, 2) + Math.pow(p2.y - y, 2));
	}
	
	public boolean isBetween(Point2D p1, Point2D p2) {
        if (Math.abs(p1.x - p2.x) < Math.abs(p1.y - p2.y))
        {
            if (p1.y < p2.y)
            {
                return (p1.y < y && y < p2.y);
            }

            return (p2.y < y && y < p1.y);
        }

        if (p1.x < p2.x)
        {
            return (p1.x < x && x < p2.x);
        }

        return (p2.x < x && x < p1.x);
		// epsilon is used to give us a margin of error when comparing floats.
		// TODO: Possibly we could use a percentage of the distance every time as our epsilon instead
		// of a hard-coded epsilon.
		//return Math.abs((this.distance(a) + this.distance(b)) - a.distance(b)) < epsilon;
	}
	
	public void translate(float dx, float dy) {
		x += dx;
		y += dy;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
