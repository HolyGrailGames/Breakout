package is.ru.tgra.graphics;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;

public class GraphicsEnvironment {
	public static int renderingProgramID;
	private static int vertexShaderID;
	private static int fragmentShaderID;
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
	private GraphicsEnvironment() {}
	
	public static void create() {
		String vertexShaderString;
		String fragmentShaderString;

		vertexShaderString = Gdx.files.internal("shaders/simple2D.vert").readString();
		fragmentShaderString =  Gdx.files.internal("shaders/simple2D.frag").readString();

		vertexShaderID = Gdx.gl.glCreateShader(GL20.GL_VERTEX_SHADER);
		fragmentShaderID = Gdx.gl.glCreateShader(GL20.GL_FRAGMENT_SHADER);
	
		Gdx.gl.glShaderSource(vertexShaderID, vertexShaderString);
		Gdx.gl.glShaderSource(fragmentShaderID, fragmentShaderString);
	
		Gdx.gl.glCompileShader(vertexShaderID);
		Gdx.gl.glCompileShader(fragmentShaderID);

		renderingProgramID = Gdx.gl.glCreateProgram();
	
		Gdx.gl.glAttachShader(renderingProgramID, vertexShaderID);
		Gdx.gl.glAttachShader(renderingProgramID, fragmentShaderID);
	
		Gdx.gl.glLinkProgram(renderingProgramID);

		Gdx.gl.glUseProgram(renderingProgramID);
		
		modelMatrixLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_modelMatrix");
		projectionMatrixLoc	= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_projectionMatrix");
		colorLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_color");
		
		positionLoc = Gdx.gl.glGetAttribLocation(renderingProgramID, "a_position");
		Gdx.gl.glEnableVertexAttribArray(positionLoc);
		
		modelMatrix = new ModelMatrix();
		
		setWindow(0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());
	}
	
	public static void clearModelMatrix()
	{
		modelMatrix.loadIdentityMatrix();
	}
	public static void setShaderMatrix() {
		modelMatrix.setShaderMatrix(modelMatrixLoc);
	}
	public static void setModelMatrixTranslation(float xTranslate, float yTranslate)
	{
		modelMatrix.addTranslation(xTranslate, yTranslate, 0.0f);
	}
	public static void setModelMatrixScale(float xScale, float yScale)
	{
		modelMatrix.addScale(xScale, yScale, 0.0f);
	}
	public static void setModelMatrixRotationX(float angle)  {
		modelMatrix.addRotationX(angle);
	}
	public static void setModelMatrixRotationY(float angle)  {
		modelMatrix.addRotationY(angle);
	}
	public static void setModelMatrixRotationZ(float angle)  {
		modelMatrix.addRotationZ(angle);
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
