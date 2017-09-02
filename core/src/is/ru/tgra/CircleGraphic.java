package is.ru.tgra;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;

public class CircleGraphic {
	private static FloatBuffer vertexBuffer;
	private static int vertexPointer;
	private static int verticesPerCircle = 32;
	
	public static void create(int vertexPointer) {
		CircleGraphic.vertexPointer = vertexPointer;
		
		vertexBuffer = BufferUtils.newFloatBuffer(2*verticesPerCircle);
		int index = 0;
		for (float i = 0.0f; i < 2*Math.PI; i += ((2*Math.PI)/verticesPerCircle)) {
			vertexBuffer.put(index, (float)Math.cos(i));
			vertexBuffer.put(index+1, (float)Math.sin(i));
			index+=2;
		}
		vertexBuffer.rewind();
	}
	
	public static void drawSolidCircle() {
		Gdx.gl.glVertexAttribPointer(vertexPointer, 2, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 0, verticesPerCircle);
	}

}
