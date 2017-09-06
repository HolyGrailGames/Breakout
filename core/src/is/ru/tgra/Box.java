package is.ru.tgra;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Box {
	protected Point2D position;
	protected Vector2D scale;
	protected float zRotation;
	protected Color color;
	private boolean moving = false;
	private Vector2D velocity;
	private Random random = new Random();
	private float speed = 50.0f;
	
	public Box(Point2D position, Vector2D scale, float zRotation, Color color) {
		this.position = position;
		this.scale = scale;
		this.zRotation = zRotation;
		this.color = color;
		
		this.velocity = new Vector2D(random.nextFloat() * 2 - 1, random.nextFloat() * 2 -1);
	}
	
	public Box(Point2D position, Vector2D scale, Color color) {
		this.position = position;
		this.scale = scale;
		this.zRotation = 0;
		this.color = color;
		
		this.velocity = new Vector2D(random.nextFloat() * 2 - 1, random.nextFloat() * 4);
	}
	
	public void update(float deltaTime) {
		if (moving) {
			velocity.y += Settings.GRAVITY * deltaTime;
			translate(this.velocity.x*deltaTime*speed, this.velocity.y*deltaTime*speed);
			if (position.y + scale.y/2 < 0) {
				position.y = scale.y/2;
				moving = false;
			}
		}
	}
	
	public void draw() {
		GraphicsEnvironment.clearModelMatrix();
		GraphicsEnvironment.setColor(color);
		GraphicsEnvironment.setModelMatrixTranslation(position.x, position.y);
		GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);
		GraphicsEnvironment.setModelMatrixRotationZ(zRotation);
		BoxGraphic.drawSolidBox();
	}
	
	public void translate(float dx, float dy) {
		position.x += dx;
		position.y += dy;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
		
	public void setAngle(float angle) {
		this.zRotation = angle;
	}
	
	public float getAngle() {
		return this.zRotation;
	}
}
