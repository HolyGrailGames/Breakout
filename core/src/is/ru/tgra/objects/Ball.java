package is.ru.tgra.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.managers.Collisions;
import is.ru.tgra.shapes.BoxGraphic;
import is.ru.tgra.shapes.CircleGraphic;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Utils;
import is.ru.tgra.utils.Vector2D;

public class Ball extends GameObject
{	
	private boolean moving;
	private Vector2D direction;
	private float speed;
	private float radius;
	
	private boolean drawPoints = false;
	
	private Point2D pHit = new Point2D();
	
	private float impactTimer = 0.0f;
	
	private Random random = new Random();
	private Point2D[] points = new Point2D[8];
	
	private Point2D startingPosition;
	private float startingSpeed;
	
	private float timeLeftToMove;
	
	public Ball(Point2D position, float radius, Color color, float speed)
	{
		super(position, new Vector2D(radius, radius), 0, color);
		this.direction = new Vector2D((random.nextInt(2) * 2) - 1, 1);
		this.speed = speed;
		this.radius = radius;
		this.moving = false;
		
		this.startingPosition = new Point2D(position);
		this.startingSpeed = speed;
		
		initializePoints();
	}
	
	public void reset() {
		this.direction = new Vector2D((random.nextInt(2) * 2) - 1, 1);
		this.position = new Point2D(this.startingPosition.x, this.startingPosition.y);
		this.speed = this.startingSpeed;
		this.moving = false;
		this.impactTimer = 0.0f;
		initializePoints();
	}

	@Override
	public void update(float deltaTime)
	{
		if (impactTimer > 0) {
			impactTimer -= deltaTime;
		}
		
		if (moving) {
			timeLeftToMove = deltaTime;
			
			// Check all of the collisions.
			
			Collisions.checkCollisions(this, timeLeftToMove);
			move(timeLeftToMove);
			// Accelerate the ball gradually over time, up to a certain threshold
			if (speed < Settings.BALL_MAX_SPEED) {
				speed += Settings.BALL_ACCELERATION * deltaTime;
			}
			else if (speed > Settings.BALL_MAX_SPEED) {
				speed = Settings.BALL_MAX_SPEED;
			}
		}
	}
	
	public void move(float time) {
		float dx = direction.x * speed * time;
		float dy = direction.y * speed * time;
		translate(dx, dy);
	}
	
	public void subtractFromTimeLeftToMove(float time) {
		this.timeLeftToMove -= time;
	}

	@Override
	public void draw()
	{
		GraphicsEnvironment.clearModelMatrix();
		GraphicsEnvironment.setModelMatrixTranslation(position.x, position.y);

		if (impactTimer > 0) {
			GraphicsEnvironment.setColor(Color.GREEN);
			GraphicsEnvironment.setModelMatrixScale(scale.x*2f, scale.y*2f);
		}
		else {
			GraphicsEnvironment.setColor(color);
			GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);			
		}
		
		GraphicsEnvironment.setShaderMatrix();
		CircleGraphic.drawSolidCircle();
			
		// Draw points on ball for debug purposes.
		if (drawPoints) {
			for (Point2D point : points) {
				GraphicsEnvironment.clearModelMatrix();
				GraphicsEnvironment.setModelMatrixTranslation(point.x, point.y);
				GraphicsEnvironment.setColor(Color.RED);
				GraphicsEnvironment.setModelMatrixScale(2, 2);
				GraphicsEnvironment.setShaderMatrix();
				CircleGraphic.drawSolidCircle();
			}
		}
	}
	
	@Override
	public void translate(float dx, float dy) {
		position.translate(dx, dy);		
		for (Point2D point : points) {
			point.translate(dx, dy);
		}
	}
	
	public void toggleDrawPoints() {
		drawPoints = !drawPoints;
	}
	
	public void setTimeLeftToMove(float time) {
		this.timeLeftToMove = time;
	}
	
	public void impact() {
		impactTimer = 0.1f;
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
	
	public Point2D getPHit() {
		return this.pHit;
	}
	
	public Point2D getStartingPosition() {
		return this.startingPosition;
	}
	
	private void initializePoints() {
		float deg = 0.0f;
		for (int i = 0; i < points.length; i++ ) {
			points[i] = Utils.getPointOnCircle(getPosition(), getRadius(), Utils.degToRad(deg));
			deg += 45.0f;
		}
	}
}
