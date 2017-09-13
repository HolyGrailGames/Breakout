package is.ru.tgra.objects;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.shapes.BoxGraphic;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Vector2D;

public class Paddle extends GameObject {
	private float speed;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private Point2D[] points = new Point2D[4];
	private Point2D startingPosition;
	
	public Paddle(Point2D position, Vector2D scale, Color color, float speed) {
		super(position, scale, 0.0f, color);
		this.startingPosition = new Point2D(position);
		this.speed = speed;
		initializePoints();
	}
	
	public void reset() {
		this.position = new Point2D(this.startingPosition);
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
	
	@Override
	public void draw() {
		GraphicsEnvironment.clearModelMatrix();
		GraphicsEnvironment.setColor(color);
		GraphicsEnvironment.setModelMatrixTranslation(position.x, position.y);
		GraphicsEnvironment.setModelMatrixRotationZ(zRotation);
		GraphicsEnvironment.setModelMatrixRotationY(zRotation);
		GraphicsEnvironment.setModelMatrixRotationX(zRotation);
		GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);
		GraphicsEnvironment.setShaderMatrix();
		BoxGraphic.drawSolidBox();
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
	
	private void initializePoints() {
		for (int i = 0; i < 4; i++) {
			points[i] = new Point2D();
		}
	}
	
	public Point2D[] getPoints() {
		points[0].setPosition(position.x - (scale.x/2), position.y - (scale.y/2));
		points[1].setPosition(position.x - (scale.x/2), position.y + (scale.y/2));
		points[2].setPosition(position.x + (scale.x/2), position.y + (scale.y/2));
		points[3].setPosition(position.x + (scale.x/2), position.y - (scale.y/2));
		
		return points;
	}
	
	public float getRatioOfPoint(Point2D point) {
		float paddleLeftBound = position.x-(scale.x/2);
		float lengthOfPaddle = point.x - paddleLeftBound;
		float result = lengthOfPaddle / scale.x;
		return result;
	}
}
