package is.ru.tgra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.managers.GameState;
import is.ru.tgra.objects.Ball;
import is.ru.tgra.objects.Block;
import is.ru.tgra.objects.Paddle;
import is.ru.tgra.shapes.Box;
import is.ru.tgra.shapes.BoxGraphic;
import is.ru.tgra.shapes.CircleGraphic;
import is.ru.tgra.shapes.LineGraphic;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Utils;
import is.ru.tgra.utils.Vector2D;



public class Breakout extends ApplicationAdapter {
	
	private int renderingProgramID;
	private int vertexShaderID;
	private int fragmentShaderID;

	private int levelIndex;
	private int lastLevelIndex;
	private int blockCount;
	
	private boolean startedPlaying;
	
	private float deltaTime;
	
	private float shakeTimer = 0.0f;
	private Random random = new Random();
	
	private Paddle paddle;
	private Ball ball;
	private Box[] walls = new Box[3];
	private List<Block> blocks = new ArrayList<Block>();
	
	private Point2D[] bounds = new Point2D[4];
	
	private float mouseX;
	private float mouseY;

	private GameState gameState = GameState.PLAYING;

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

		GraphicsEnvironment.create(renderingProgramID);
		
		GraphicsEnvironment.setWindow(0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());

		BoxGraphic.create();
		CircleGraphic.create();
		LineGraphic.create();
		
		paddle = new Paddle(new Point2D(Settings.windowWidth/2, 32.0f), new Vector2D(90.0f, 24.0f), Settings.ORANGE_RED);
		ball = new Ball(new Point2D(Settings.windowWidth/2, 54.0f), Settings.BALL_RADIUS, Color.NAVY, Settings.BALL_SPEED);
		
		// Left wall
		walls[0] = new Box(new Point2D(Settings.WALL_THICKNESS/2, (Settings.windowHeight-Settings.WALL_THICKNESS)/2), 
						   new Vector2D(Settings.WALL_THICKNESS, Settings.windowHeight), 0.0f, Settings.LIGHT_GREEN);
		// Top wall
		walls[1] = new Box(new Point2D(Settings.windowWidth/2, Settings.windowHeight-Settings.WALL_THICKNESS/2), 
						   new Vector2D(Settings.windowWidth, Settings.WALL_THICKNESS), 0.0f, Settings.LIGHT_GREEN);
		// Right wall
		walls[2] = new Box(new Point2D(Settings.windowWidth-Settings.WALL_THICKNESS/2, (Settings.windowHeight-Settings.WALL_THICKNESS)/2), 
						   new Vector2D(Settings.WALL_THICKNESS, Settings.windowHeight), 0.0f, Settings.LIGHT_GREEN);
		
		// Bottom left
		bounds[0] = new Point2D(Settings.WALL_THICKNESS, 0);
		// Top left
		bounds[1] = new Point2D(Settings.WALL_THICKNESS, Settings.windowHeight -Settings.WALL_THICKNESS);
		// Top right
		bounds[2] = new Point2D(Settings.windowWidth-Settings.WALL_THICKNESS, Settings.windowHeight -Settings.WALL_THICKNESS);
		// Bottom right
		bounds[3] = new Point2D(Settings.windowWidth-Settings.WALL_THICKNESS, 0);
		
		
		
		// Start game at level 1
		levelIndex = 1;
		// Set this variable to the last level
		lastLevelIndex = 1;
		prepareNextLevel();
		blockCount = blocks.size();
	}

	private void updateGame() {
		
		
		if (blockCount == 0) {
			prepareNextLevel();
		}
		deltaTime = Gdx.graphics.getDeltaTime();
		
		processInput();
		paddle.update(deltaTime);
		if (!startedPlaying) {
			ball.translate(paddle.getPosition().x - ball.getPosition().x, 0);
		}
		
		for (int i = 0; i < bounds.length; i++) {
			int j = (i < bounds.length -1) ? i+1 : 0;
			checkCollisions(bounds[i], bounds[j], deltaTime);	
		}
		
		
		Point2D[] paddlePoints = paddle.getPoints();
		for (int i = 0; i < paddlePoints.length - 1; i++) {
			checkCollisions(paddlePoints[i], paddlePoints[i+1], deltaTime);
		}
		
		for (Block block : blocks) {
			if (!block.getExploded()) {
				Point2D[] blockPoints = block.getPoints();
				
				for (int i = 0; i < blockPoints.length; i++) {
					int j = (i < blockPoints.length - 1) ? i+1 : 0;
					boolean collision = checkCollisions(blockPoints[i], blockPoints[j], deltaTime);
					if (collision) {
						block.explode();
						blockCount--;
						shakeTimer = Settings.SHAKE_TIMER;
						break;
					}
				}	
			}
			
		}
		
		ball.update(deltaTime);
		
		
		for (Block block : blocks) {
			block.update(deltaTime);	
		}
		
		if (shakeTimer > 0) {
			shakeTimer -= deltaTime;
			
			int offsetX = random.nextInt(8) - 4;
			int offsetY = random.nextInt(8) - 4;
			
			System.out.println(offsetX + " " + offsetY);
			GraphicsEnvironment.setWindow(-offsetX, Settings.windowWidth-offsetX, -offsetY, Settings.windowHeight-offsetY);
		}
		else {
			GraphicsEnvironment.setWindow(0, Settings.windowWidth, 0, Settings.windowHeight);
		}
	}
	
	private void clearScreen(Color color) {
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void displayGame() {
		clearScreen(Settings.LIGHT_YELLLOW);
		
		for (Box b : walls) {
			b.draw();
		}
		paddle.draw();
		ball.draw();
		
		Point2D[] paddlePoints = paddle.getPoints();
		for (int i = 0; i < paddlePoints.length - 1; i++) {
			GraphicsEnvironment.clearModelMatrix();
			GraphicsEnvironment.setColor(Color.BLACK);
			
			LineGraphic.setPoints(paddlePoints[i], paddlePoints[i+1]);
			LineGraphic.drawLine();
		}
		
		for (Block block : blocks) {
			block.draw();	
		}
	}

	@Override
	public void render () {
		Gdx.graphics.setTitle("Ultimate Breakout | FPS: " + Gdx.graphics.getFramesPerSecond());
		switch (gameState) {
		case MAIN_MENU:
			//updateMainMenu();
			//displayMainMenu();
			break;
		
		case PLAYING:
			updateGame();
			displayGame();
			break;
			
		case GAME_OVER:
			//updateGameOver();
			//displayGameOver();
			break;
		
		case LEVEL_TRANSITION:
			//updateLevelTransition();
			//displayLevelTransition();
			break;
		default:
			break;
		}	
	}
	
	private void processInput() {
		
		if(Gdx.input.justTouched())
		{
			//do mouse/touch input stuff
			mouseX = Gdx.input.getX();
			mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
			
			for (Block block : blocks) {
				if (block.pointIsInside(mouseX, mouseY)) {
					block.explode();
					blockCount--;
				}	
			}
		}
		
		boolean moveLeft = false;
		boolean moveRight = false;
		
		if (!startedPlaying) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				startedPlaying = true;
				ball.setMoving(true);
			}
		}
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			moveLeft = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			moveRight = true;
		}
		paddle.setDirection(Settings.LEFT, moveLeft);
		paddle.setDirection(Settings.RIGHT, moveRight);
	}
	
	private void prepareNextLevel() {
		startedPlaying = false;
		if (levelIndex == lastLevelIndex) {
			// TODO: Here we would set state to WON GAME
			levelIndex = 1;	
		} else {
			levelIndex++;
		}
		blocks.clear();
		switch(levelIndex) {
			case 1:
				setupLevelOne();
				break;
		}
		
		blockCount = blocks.size();	
	}
	
	private void setupLevelOne() {
		float originX = ((Settings.windowWidth - (Settings.LEVEL1_COLS * (Settings.BLOCK_WIDTH + Settings.BLOCK_SPACING))) / 2) + (Settings.BLOCK_WIDTH / 2);;
		float originY = Settings.windowHeight - Settings.LEVEL1_ORIGIN_Y;
		for (int i = 0; i < Settings.LEVEL1_COLS; i++) {
			for(int j = 0; j < Settings.LEVEL1_ROWS; j++) {
				// Spawn new block, then lower the point of origin to the next row

				blocks.add(new Block(new Point2D(originX, originY), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.GOLDENROD, 5));
				originY -= Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT;

			}
			// Set origin to the next column and reset the height
			originX += Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH;
			originY = Settings.windowHeight - Settings.LEVEL1_ORIGIN_Y;
		}
	}
	
	
	private boolean checkCollisions(Point2D B, Point2D B2, float deltaTime) {
		
		Point2D[] points = ball.getPointsOnBall();
		
		Vector2D v = new Vector2D(B2.x-B.x, B2.y-B.y);
		Vector2D n = new Vector2D(v.y, -v.x);
				
		Vector2D c = ball.getVelocity();
		
		float tHit = Float.MAX_VALUE;
		
		Point2D pHit = new Point2D();
		for (int i = 0; i < points.length; i++) {
			Point2D A = points[i];
			
			float newtHit = Utils.tHit(A, B, n, c);
			if (newtHit < tHit) {
				tHit = newtHit;
				pHit = new Point2D(A.x + tHit * c.x, A.y + tHit * c.y);
			}
		}
		if (pHit.isBetween(B, B2) &&  tHit > 0 && tHit < deltaTime) {
			Vector2D a = c;
			pHit.isBetween(B, B2);
			float x = a.x - (2*(a.dot(n) / n.dot(n)) * n.x);
			float y = a.y - (2*(a.dot(n) / n.dot(n)) * n.y);
			Vector2D newDirection = new Vector2D(x,y);
			ball.setDirection(newDirection.normalize());
			/*
			System.out.println("------------------------------------------");
			System.out.println("Thit:      " + tHit);
			System.out.println("DeltaTime: " + deltaTime);
			System.out.println("phit:      " + pHit);
			System.out.println("new direction: " + newDirection.normalize());
			*/
			return true;
		}
		
		return false;
	}
	
	
	
}