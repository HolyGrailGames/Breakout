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

	private int positionLoc;

	private float deltaTime;
	
	private Paddle paddle;
	private Box[] walls = new Box[3];

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

		positionLoc = Gdx.gl.glGetAttribLocation(renderingProgramID, "a_position");
		Gdx.gl.glEnableVertexAttribArray(positionLoc);

		Gdx.gl.glUseProgram(renderingProgramID);

		EnvironmentGraphics.create(renderingProgramID);
		
		EnvironmentGraphics.setWindow(0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());

		BoxGraphic.create(positionLoc);
		CircleGraphic.create(positionLoc);
		
		paddle = new Paddle(new Point2D(Settings.windowWidth/2, 32.0f), new Vector2D(90.0f, 24.0f), Settings.ORANGE_RED);
		
		walls[0] = new Box(new Point2D(Settings.WALL_THICKNESS/2, Settings.windowHeight/2), 
						   new Vector2D(32.0f, Settings.windowHeight), Settings.LIGHT_GREEN);
		walls[1] = new Box(new Point2D(Settings.windowWidth/2, Settings.windowHeight-Settings.WALL_THICKNESS/2), 
						   new Vector2D(Settings.windowWidth, Settings.WALL_THICKNESS), Settings.LIGHT_GREEN);
		walls[2] = new Box(new Point2D(Settings.windowWidth-Settings.WALL_THICKNESS/2, Settings.windowHeight/2), 
						   new Vector2D(Settings.WALL_THICKNESS, Settings.windowHeight), Settings.LIGHT_GREEN);
	}

	private void update() {
		deltaTime = Gdx.graphics.getDeltaTime();
		
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