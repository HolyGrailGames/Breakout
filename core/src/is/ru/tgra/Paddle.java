package is.ru.tgra;

import com.badlogic.gdx.graphics.Color;

public class Paddle {
	private Point2D position;
	private Vector2D scale;
	private Color color;
	private float speed = 500;
	
	public Paddle(Point2D position, Vector2D scale, Color color) {
		this.position = position;
		this.scale = scale;
		this.color = color;
	}
	
	public void update(float deltaTime, int direction) {
		position.x += speed * direction * deltaTime;
		
		restrain();
	}
	
	public void draw() {
		EnvironmentGraphics.setColor(color);
		EnvironmentGraphics.setModelMatrixTranslation(position.x, position.y);
		EnvironmentGraphics.setModelMatrixScale(scale.x, scale.y);
		BoxGraphic.drawSolidBox();
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
