package is.ru.tgra.shapes;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.objects.GameObject;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Vector2D;

public class Box extends GameObject {
	private boolean moving = false;
	
	private Random random = new Random();
	private float speed = 50.0f;
	private float rotationPerSecond;
	private Vector2D velocity;
	
	public Box(Point2D position, Vector2D scale, float zRotation, Color color) {
		super(position, scale, zRotation, color);
		
		this.velocity = new Vector2D(random.nextFloat() * 2 - 1, random.nextFloat() * 2 -1);
		this.rotationPerSecond = random.nextFloat() * 360 - 180;
	}
	
	public Box(Point2D position, Vector2D scale, Color color) {
		super(position, scale, 0.0f, color);
		
		this.velocity = new Vector2D(random.nextFloat() * 2 - 1, random.nextFloat() * 4);
		this.rotationPerSecond = random.nextFloat() * 360 - 180;
	}
	
	@Override
	public void update(float deltaTime) {
		if (moving) {
			zRotation += rotationPerSecond * deltaTime;
			velocity.y += Settings.GRAVITY * deltaTime;
			translate(this.velocity.x*deltaTime*speed, this.velocity.y*deltaTime*speed);
			if (position.y + scale.y/2 < 0) {
				position.y = scale.y/2;
				moving = false;
			}
		}
	}
	
	@Override
	public void draw() {
		GraphicsEnvironment.clearModelMatrix();
		GraphicsEnvironment.setColor(color);
		GraphicsEnvironment.setModelMatrixTranslation(position.x, position.y);
		GraphicsEnvironment.setModelMatrixRotationZ(zRotation);
		GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);
		BoxGraphic.drawSolidBox();
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
}
