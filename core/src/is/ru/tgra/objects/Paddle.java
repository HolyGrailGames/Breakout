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
	
	public Paddle(Point2D position, Vector2D scale, Color color) {
		super(position, scale, 0.0f, color);
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
}
