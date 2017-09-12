package is.ru.tgra.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

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
	
	private float impactTimer = 0.0f;
	
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
		if (impactTimer > 0) {
			impactTimer -= deltaTime;
		}
		
		if (moving) {
			float dx = direction.x * speed * deltaTime;
			float dy = direction.y * speed * deltaTime;
			translate(dx, dy);
			if (speed < Settings.BALL_MAX_SPEED) {
				speed += Settings.BALL_ACCELERATION * deltaTime;
			}
			else if (speed > Settings.BALL_MAX_SPEED) {
				speed = Settings.BALL_MAX_SPEED;
			}
			
		}
	}

	@Override
	public void draw()
	{
		GraphicsEnvironment.clearModelMatrix();
		
		GraphicsEnvironment.setModelMatrixTranslation(position.x, position.y);
		if (impactTimer > 0) {
			GraphicsEnvironment.setColor(Color.BLACK);
			GraphicsEnvironment.setModelMatrixScale(scale.x*1.5f, scale.y*1.5f);
		}
		else {
			GraphicsEnvironment.setColor(color);
			GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);			
		}
		GraphicsEnvironment.setShaderMatrix();
		CircleGraphic.drawSolidCircle();
	}
	
	@Override
	public void translate(float dx, float dy) {
		position.translate(dx, dy);		
		for (Point2D point : points) {
			point.translate(dx, dy);
		}
	}
	
	public boolean checkCollisionWithLine(Point2D B, Point2D B2, float deltaTime) {
		
		Point2D[] points = getPointsOnBall();
		
		Vector2D v = new Vector2D(B2.x-B.x, B2.y-B.y);
		Vector2D n = new Vector2D(v.y, -v.x);
				
		Vector2D c = getVelocity();
		
		float tHit = Float.MAX_VALUE;
		
		Point2D pHit = new Point2D();
		for (int i = 0; i < points.length; i++) {
			Point2D A = points[i];
			
			float newtHit = Utils.tHit(A, B, n, c);
			if (newtHit < tHit) {
				tHit = newtHit;
				pHit = new Point2D(A.x + tHit * c.x, A.y + tHit * c.y);
			}
		}
		if (pHit.isBetween(B, B2) &&  tHit > 0 && tHit < deltaTime) {
			Vector2D a = c;
			pHit.isBetween(B, B2);
			float x = a.x - (2*(a.dot(n) / n.dot(n)) * n.x);
			float y = a.y - (2*(a.dot(n) / n.dot(n)) * n.y);
			Vector2D newDirection = new Vector2D(x,y);
			setDirection(newDirection.normalize());
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
		impactTimer = 0.15f;
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
