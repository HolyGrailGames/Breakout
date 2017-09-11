package is.ru.tgra.objects;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.shapes.Box;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Vector2D;

public class Paddle extends Box {
	private float speed = 500;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	Point2D[] points = new Point2D[4];
	
	public Paddle(Point2D position, Vector2D scale, Color color) {
		super(position, scale, 0.0f, color);
		initalizePoints();
	}
	
	@Override
	public void update(float deltaTime) {
		// Early out if we are trying to move in both directions, so we don't move at all.
		if (movingLeft && movingRight) {
			return;
		}
		
		if (movingLeft) {
			position.x += speed * -1 * deltaTime;			
		}
		if (movingRight) {
			position.x += speed * deltaTime;
		}
		
		restrain();
	}
	
	public void setDirection(String direction, boolean flag) {
		if (direction == Settings.LEFT) {
			movingLeft = flag;
		}
		else if (direction == Settings.RIGHT) {
			movingRight = flag;
		}
	}
	
	private void restrain() {
		if (position.x - scale.x/2 < Settings.WALL_THICKNESS) {
			position.x = scale.x/2 + Settings.WALL_THICKNESS;
		}
		else if (position.x + scale.x/2 > Settings.windowWidth - Settings.WALL_THICKNESS) {
			position.x = Settings.windowWidth - scale.x/2 - Settings.WALL_THICKNESS;
		}
	}
	
	private void initalizePoints() {
		for (int i = 0; i < 4; i++) {
			points[i] = new Point2D();
		}
	}
	
	public Point2D[] getPoints() {
		points[0].setPosition(position.x - (scale.x/2), position.y - (scale.y/2));
		points[1].setPosition(position.x - (scale.x/2), position.y + (scale.y/2));
		points[2].setPosition(position.x + (scale.x/2), position.y + (scale.y/2));
		points[3].setPosition(position.x + (scale.x/2), position.y - (scale.y/2));
		
		/*
		System.out.println("---------------------------------------------------");
		for (Point2D point : points) {
			System.out.println(point);
		}
		*/
		
		return points;
	}
}
