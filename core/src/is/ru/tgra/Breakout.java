package is.ru.tgra;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;


public class Breakout extends ApplicationAdapter {
	
	private int renderingProgramID;
	private int vertexShaderID;
	private int fragmentShaderID;

	private float deltaTime;
	
	private Paddle paddle;
	private Box[] walls = new Box[3];
	
	private float angle = 0.0f;

	@Override
	public void create() {
		
		Settings.windowWidth  = Gdx.graphics.getWidth();
		Settings.windowHeight = Gdx.graphics.getHeight();

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

		EnvironmentGraphics.create(renderingProgramID);
		
		EnvironmentGraphics.setWindow(0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());

		BoxGraphic.create();
		CircleGraphic.create();
		
		paddle = new Paddle(new Point2D(Settings.windowWidth/2, 32.0f), new Vector2D(90.0f, 24.0f), Settings.ORANGE_RED);
		
		// Left wall
		walls[0] = new Box(new Point2D(Settings.WALL_THICKNESS/2, Settings.windowHeight/2), 
						   new Vector2D(Settings.WALL_THICKNESS, Settings.windowHeight), 0.0f, Settings.LIGHT_GREEN);
		// Top wall
		walls[1] = new Box(new Point2D(Settings.windowWidth/2, Settings.windowHeight-Settings.WALL_THICKNESS/2), 
						   new Vector2D(Settings.windowWidth, Settings.WALL_THICKNESS), 0.0f, Settings.LIGHT_GREEN);
		// Right wall
		walls[2] = new Box(new Point2D(Settings.windowWidth-Settings.WALL_THICKNESS/2, Settings.windowHeight/2), 
						   new Vector2D(Settings.WALL_THICKNESS, Settings.windowHeight), 0.0f, Settings.LIGHT_GREEN);
	}

	private void update() {
		deltaTime = Gdx.graphics.getDeltaTime();
		
		angle += 90.0 * deltaTime;
		
		int direction = getInput();
		
		paddle.update(deltaTime, direction);
	}
	
	private void clearScreen(Color color) {
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void display() {
		clearScreen(Settings.LIGHT_YELLLOW);
				
		for (Box b : walls) {
			b.draw();
		}
		paddle.draw();
		
		EnvironmentGraphics.clearModelMatrix();
		EnvironmentGraphics.setColor(Color.MAROON);
		EnvironmentGraphics.setModelMatrixTranslation(Settings.windowWidth/2, Settings.windowHeight/2);
		//EnvironmentGraphics.setModelMatrixRotationX(angle);
		//EnvironmentGraphics.setModelMatrixRotationY(angle);
		EnvironmentGraphics.setModelMatrixRotationZ(angle);
		EnvironmentGraphics.setModelMatrixScale(10, 300);
		BoxGraphic.drawSolidBox();
		
		EnvironmentGraphics.clearModelMatrix();
		EnvironmentGraphics.setColor(Color.MAROON);
		EnvironmentGraphics.setModelMatrixTranslation(Settings.windowWidth/2, Settings.windowHeight/2);
		//EnvironmentGraphics.setModelMatrixRotationX(angle);
		//EnvironmentGraphics.setModelMatrixRotationY(angle);
		EnvironmentGraphics.setModelMatrixRotationZ(angle);
		EnvironmentGraphics.setModelMatrixScale(300, 10);
		BoxGraphic.drawSolidBox();
		
		EnvironmentGraphics.clearModelMatrix();
		EnvironmentGraphics.setColor(Color.MAROON);
		EnvironmentGraphics.setModelMatrixTranslation(Settings.windowWidth/2, Settings.windowHeight/2);
		//EnvironmentGraphics.setModelMatrixRotationX(angle);
		//EnvironmentGraphics.setModelMatrixRotationY(angle);
		EnvironmentGraphics.setModelMatrixRotationZ(angle+45);
		EnvironmentGraphics.setModelMatrixScale(300, 10);
		BoxGraphic.drawSolidBox();
		
		EnvironmentGraphics.clearModelMatrix();
		EnvironmentGraphics.setColor(Color.MAROON);
		EnvironmentGraphics.setModelMatrixTranslation(Settings.windowWidth/2, Settings.windowHeight/2);
		//EnvironmentGraphics.setModelMatrixRotationX(angle);
		//EnvironmentGraphics.setModelMatrixRotationY(angle);
		EnvironmentGraphics.setModelMatrixRotationZ(angle-45);
		EnvironmentGraphics.setModelMatrixScale(300, 10);
		BoxGraphic.drawSolidBox();
		
		EnvironmentGraphics.clearModelMatrix();
		EnvironmentGraphics.setColor(Color.MAROON);
		EnvironmentGraphics.setModelMatrixTranslation(Settings.windowWidth/2, Settings.windowHeight/2);
		EnvironmentGraphics.setModelMatrixScale(150, 150);
		CircleGraphic.drawOutlineCircle();
	}

	@Override
	public void render () {
		update();
		display();
	}
	
	private int getInput() {
		/*
		if(Gdx.input.justTouched())
		{
			//do mouse/touch input stuff
			position_x = Gdx.input.getX();
			position_y = Gdx.graphics.getHeight() - Gdx.input.getY();
		}*/
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			return -1;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			return 1;
		}
		else {
			return 0;
		}
	}
}