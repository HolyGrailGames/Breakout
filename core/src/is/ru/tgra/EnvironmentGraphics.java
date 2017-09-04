package is.ru.tgra;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.BufferUtils;

public class EnvironmentGraphics {
	public static int modelMatrixLoc;
	public static int positionLoc;
	
	private static FloatBuffer projectionMatrix;
	private static int projectionMatrixLoc;
	private static int colorLoc;
	
	private static ModelMatrix modelMatrix;
	
	/**
	 * Private constructor to prevent anyone from creating an instance of this class.
	 * This is done because the EnvironmentGraphics class is to be used as a static class.
	 */
	private EnvironmentGraphics() {}
	
	public static void create(int renderingProgramID) {
		modelMatrixLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_modelMatrix");
		projectionMatrixLoc	= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_projectionMatrix");
		colorLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_color");
		
		positionLoc = Gdx.gl.glGetAttribLocation(renderingProgramID, "a_position");
		Gdx.gl.glEnableVertexAttribArray(positionLoc);
		
		modelMatrix = new ModelMatrix();
	}
	
	public static void clearModelMatrix()
	{
		modelMatrix.loadIdentityMatrix();
		modelMatrix.setShaderMatrix(modelMatrixLoc);
	}
	public static void setModelMatrixTranslation(float xTranslate, float yTranslate)
	{
		modelMatrix.addTranslation(xTranslate, yTranslate, 0.0f);
		modelMatrix.setShaderMatrix(modelMatrixLoc);
	}
	public static void setModelMatrixScale(float xScale, float yScale)
	{
		modelMatrix.addScale(xScale, yScale, 0.0f);
		modelMatrix.setShaderMatrix(modelMatrixLoc);
	}
	public static void setModelMatrixRotationZ(float angle)  {
		modelMatrix.addRotationZ(angle);
		modelMatrix.setShaderMatrix(modelMatrixLoc);
	}
	
	public static void setWindow(float left, float right, float bottom, float top) {
		float[] pm = new float[16];

		pm[0] = 2.0f / (right - left); pm[4] = 0.0f; pm[8] = 0.0f; pm[12] = -(right + left) / (right - left);
		pm[1] = 0.0f; pm[5] = 2.0f / (top - bottom); pm[9] = 0.0f; pm[13] = -(top + bottom) / (top - bottom);
		pm[2] = 0.0f; pm[6] = 0.0f; pm[10] = 1.0f; pm[14] = 0.0f;
		pm[3] = 0.0f; pm[7] = 0.0f; pm[11] = 0.0f; pm[15] = 1.0f;

		projectionMatrix = BufferUtils.newFloatBuffer(16);
		projectionMatrix.put(pm);
		projectionMatrix.rewind();
		Gdx.gl.glUniformMatrix4fv(projectionMatrixLoc, 1, false, projectionMatrix);
	}
	
	public static void setColor(Color color) {
		Gdx.gl.glUniform4f(colorLoc, color.r, color.g, color.b, color.a);
	}
}
