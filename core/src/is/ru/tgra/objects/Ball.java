package is.ru.tgra.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.shapes.CircleGraphic;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Vector2D;

public class Ball extends GameObject
{	
	private boolean moving;
	private Vector2D direction;
	private float speed;
	private Random random = new Random();
	private int[] directions = {-1, 1};
	
	public Ball(Point2D position, Vector2D scale, Color color, float speed)
	{
		super(position, scale, 0, color);
		direction = new Vector2D(directions[random.nextInt(1)], 1);
		this.speed = speed;
		moving = false;
	}

	@Override
	public void update(float deltaTime)
	{
		if (moving) {
			translate(direction.x * speed * deltaTime, direction.y * speed * deltaTime);	
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
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
}
