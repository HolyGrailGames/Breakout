package is.ru.tgra;

import com.badlogic.gdx.graphics.Color;

public class Box {
	private Point2D position;
	private Vector2D scale;
	private float zRotation;
	private Color color;
	
	public Box(Point2D position, Vector2D scale, float zRotation, Color color) {
		this.position = position;
		this.scale = scale;
		this.zRotation = zRotation;
		this.color = color;
	}
	
	public void draw() {
		GraphicsEnvironment.clearModelMatrix();
		GraphicsEnvironment.setColor(color);
		GraphicsEnvironment.setModelMatrixTranslation(position.x, position.y);
		GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);
		GraphicsEnvironment.setModelMatrixRotationZ(zRotation);
		BoxGraphic.drawSolidBox();
	}
	
	public void setAngle(float angle) {
		this.zRotation = angle;
	}
	
	public float getAngle() {
		return this.zRotation;
	}
}
