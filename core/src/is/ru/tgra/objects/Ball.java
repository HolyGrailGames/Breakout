package is.ru.tgra.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.shapes.CircleGraphic;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Utils;
import is.ru.tgra.utils.Vector2D;

public class Ball extends GameObject
{	
	private boolean moving;
	private Vector2D direction;
	private float speed;
	private float radius;
	
	private Random random = new Random();
	private int[] directions = {-1, 1};
	private Point2D[] points = new Point2D[8];
	
	public Ball(Point2D position, float radius, Color color, float speed)
	{
		super(position, new Vector2D(radius, radius), 0, color);
		direction = new Vector2D(-1, 1);
		this.speed = speed;
		this.radius = radius;
		moving = false;
		
		
		initalizePoints();
	}

	@Override
	public void update(float deltaTime)
	{
		if (moving) {
			float dx = direction.x * speed * deltaTime;
			float dy = direction.y * speed * deltaTime;
			translate(dx, dy);	
		}
	}

	@Override
	public void draw()
	{
		GraphicsEnvironment.clearModelMatrix();
		GraphicsEnvironment.setColor(color);
		GraphicsEnvironment.setModelMatrixTranslation(position.x, position.y);
		GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);
		CircleGraphic.drawSolidCircle();
	}
	
	@Override
	public void translate(float dx, float dy) {
		position.translate(dx, dy);		
		for (Point2D point : points) {
			point.translate(dx, dy);
		}
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public Vector2D getDirection() {
		return this.direction;
	}
	
	public Vector2D getVelocity() {
		return new Vector2D(direction.x*speed, direction.y*speed);
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	
	public Point2D[] getPointsOnBall() {
		return this.points;
	}
	
	private void initalizePoints() {
		float deg = 0.0f;
		for (int i = 0; i < points.length; i++ ) {
			points[i] = Utils.getPointOnCircle(getPosition(), getRadius(), Utils.degToRad(deg));
			deg += 45.0f;
		}
	}
}
