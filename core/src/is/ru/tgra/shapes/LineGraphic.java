package is.ru.tgra.shapes;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.utils.Point2D;

public class LineGraphic {
	private static FloatBuffer vertexBuffer;
	private static int vertexPointer;
	
	public static void create() {
		LineGraphic.vertexPointer = GraphicsEnvironment.positionLoc;
	}
	
	public static void setPoints(Point2D p1, Point2D p2) {
		float[] array = {p1.x, p1.y,
						 p2.x, p2.y};
						 
		vertexBuffer = BufferUtils.newFloatBuffer(4);
		vertexBuffer.put(array);
		vertexBuffer.rewind();
	}
	
	public static void drawLine() {
		Gdx.gl.glVertexAttribPointer(vertexPointer, 2, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glDrawArrays(GL20.GL_LINES, 0, 2);
	}
}
