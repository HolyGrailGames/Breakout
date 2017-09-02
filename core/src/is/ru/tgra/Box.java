package is.ru.tgra;

import com.badlogic.gdx.graphics.Color;

public class Box {
	private Point2D position;
	private Vector2D scale;
	private Color color;
	
	public Box(Point2D position, Vector2D scale, Color color) {
		this.position = position;
		this.scale = scale;
		this.color = color;
	}
	
	public void draw() {
		EnvironmentGraphics.setColor(color);
		EnvironmentGraphics.setModelMatrixTranslation(position.x, position.y);
		EnvironmentGraphics.setModelMatrixScale(scale.x, scale.y);
		BoxGraphic.drawSolidBox();
	}
}
