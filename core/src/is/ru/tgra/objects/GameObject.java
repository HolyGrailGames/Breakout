package is.ru.tgra.objects;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Vector2D;

public abstract class GameObject {
	protected Point2D position;
	protected Vector2D scale;
	protected float zRotation;
	protected Color color;
	
	public GameObject(Point2D position, Vector2D scale, float zRotation, Color color ) {
		this.position = position;
		this.scale = scale;
		this.zRotation = zRotation;
		this.color = color;
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}
	
	public Point2D getPosition() {
		return this.position;
	}
	
	public void setScale(Vector2D scale) {
		this.scale = scale;
	}
	
	public Vector2D getScale() {
		return this.scale;
	}
	
	public void setRotationZ(float rotation) {
		this.zRotation = rotation;
	}
	
	public float getRotationZ() {
		return this.zRotation;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void translate(float dx, float dy) {
		position.translate(dx, dy);
	}
	
	/*
	 * METHODS THAT NEED TO BE IMPLEMENTED BY SUBCLASSES:
	 */
	public abstract void update(float deltaTime);
	public abstract void draw();
}
