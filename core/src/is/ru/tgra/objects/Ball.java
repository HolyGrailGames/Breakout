package is.ru.tgra.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.Breakout;
import is.ru.tgra.graphics.GraphicsEnvironment;
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
	
	private Point2D pHit = new Point2D();
	
	private float impactTimer = 0.0f;
	
	private Random random = new Random();
	private int[] directions = {-1, 1};
	private Point2D[] points = new Point2D[8];
	
	private Point2D startingPosition;
	private float startingSpeed;
	
	private float timeLeftToMove;
	
	public Ball(Point2D position, float radius, Color color, float speed)
	{
		super(position, new Vector2D(radius, radius), 0, color);
		this.direction = new Vector2D(-1, 1);
		this.speed = speed;
		this.radius = radius;
		this.moving = false;
		
		this.startingPosition = new Point2D(position);
		this.startingSpeed = speed;
		
		initializePoints();
	}
	
	public void reset() {
		this.direction = new Vector2D(-1, 1);
		this.position = new Point2D(this.startingPosition);
		this.speed = this.startingSpeed;
		this.moving = false;
		initializePoints();
	}

	@Override
	public void update(float deltaTime)
	{
		if (impactTimer > 0) {
			impactTimer -= deltaTime;
		}
		
		if (moving) {
			move(timeLeftToMove);
			/*if (speed < Settings.BALL_MAX_SPEED) {
				speed += Settings.BALL_ACCELERATION * deltaTime;
			}
			else if (speed > Settings.BALL_MAX_SPEED) {
				speed = Settings.BALL_MAX_SPEED;
			}*/
			
		}
	}
	
	private void move(float time) {
		float dx = direction.x * speed * time;
		float dy = direction.y * speed * time;
		translate(dx, dy);
	}

	@Override
	public void draw()
	{
		GraphicsEnvironment.clearModelMatrix();
		
		GraphicsEnvironment.setModelMatrixTranslation(position.x, position.y);
		/*
		if (impactTimer > 0) {
			GraphicsEnvironment.setColor(Color.GREEN);
			GraphicsEnvironment.setModelMatrixScale(scale.x*2f, scale.y*2f);
		}
		else {
			GraphicsEnvironment.setColor(color);
			GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);			
		}
		*/
		GraphicsEnvironment.setColor(color);
		GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);
		
		GraphicsEnvironment.setShaderMatrix();
		CircleGraphic.drawSolidCircle();
		/*
		for (Point2D point : points) {
			GraphicsEnvironment.clearModelMatrix();
			GraphicsEnvironment.setModelMatrixTranslation(point.x, point.y);
			GraphicsEnvironment.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1.0f));
			GraphicsEnvironment.setModelMatrixScale(5, 5);
			GraphicsEnvironment.setShaderMatrix();
			CircleGraphic.drawSolidCircle();
		}
		*/
	}
	
	@Override
	public void translate(float dx, float dy) {
		position.translate(dx, dy);		
		for (Point2D point : points) {
			point.translate(dx, dy);
		}
	}
	
	public void setTimeLeftToMove(float time) {
		this.timeLeftToMove = time;
	}
	
	public boolean checkCollisionWithLine(Point2D B, Point2D B2, float deltaTime) {
		
		Point2D[] points = getPointsOnBall();
		
		Vector2D v = new Vector2D(B2.x-B.x, B2.y-B.y);
		Vector2D n = new Vector2D(v.y, -v.x);
				
		Vector2D c = getVelocity();
		
		float tHit = Float.MAX_VALUE;
		
		boolean collision = false;
		
		for (int i = 0; i < points.length; i++) {
			Point2D A = points[i];
			
			float newtHit = Utils.tHit(A, B, n, c);
			if (newtHit > 0 && newtHit < deltaTime) {
				Point2D newpHit = new Point2D(A.x + newtHit * c.x, A.y + newtHit * c.y);
				if (newpHit.isBetween(B, B2)) {
					if (newtHit < tHit) {
						tHit = newtHit;
						pHit = newpHit;
						collision = true;
					}
				}
			}
		}
		
		if (collision) {
			Vector2D a = c;
			float x = a.x - (2*(a.dot(n) / n.dot(n)) * n.x);
			float y = a.y - (2*(a.dot(n) / n.dot(n)) * n.y);
			Vector2D newDirection = new Vector2D(x,y);
			setDirection(newDirection.normalize());
			move(tHit*0.999f);
			timeLeftToMove -= tHit;
			/*
			System.out.println("------------------------------------------");
			System.out.println("Thit:      " + tHit);
			System.out.println("DeltaTime: " + deltaTime);
			System.out.println("phit:      " + pHit);
			System.out.println("new direction: " + newDirection.normalize());
			*/
			return true;
		}
		
		return false;
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
	
	private void initializePoints() {
		float deg = 0.0f;
		for (int i = 0; i < points.length; i++ ) {
			points[i] = Utils.getPointOnCircle(getPosition(), getRadius(), Utils.degToRad(deg));
			deg += 45.0f;
		}
	}
}
