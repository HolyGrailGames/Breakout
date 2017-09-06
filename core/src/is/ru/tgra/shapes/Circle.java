package is.ru.tgra.shapes;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.objects.GameObject;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Vector2D;

public class Circle extends GameObject {

	public Circle(Point2D position, Vector2D scale, float zRotation, Color color) {
		super(position, scale, zRotation, color);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		GraphicsEnvironment.clearModelMatrix();
		GraphicsEnvironment.setColor(color);
		GraphicsEnvironment.setModelMatrixTranslation(position.x, position.y);
		GraphicsEnvironment.setModelMatrixRotationZ(zRotation);
		GraphicsEnvironment.setModelMatrixScale(scale.x, scale.y);
		CircleGraphic.drawSolidCircle();
	}
	
}
