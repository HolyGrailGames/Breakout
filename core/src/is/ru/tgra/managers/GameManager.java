package is.ru.tgra.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.objects.Ball;
import is.ru.tgra.objects.Block;
import is.ru.tgra.objects.Paddle;
import is.ru.tgra.shapes.Box;
import is.ru.tgra.shapes.BoxGraphic;
import is.ru.tgra.shapes.CircleGraphic;
import is.ru.tgra.shapes.LineGraphic;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Vector2D;

public class GameManager {
	public static float deltaTime;
	
	public static float mouseX;
	public static float mouseY;
	
	public static boolean ballStuckToPaddle = true;
	public static int levelIndex;
	public static int lastLevelIndex;
	public static int blockCount;
	
	public static Paddle paddle;
	public static Ball ball;
	public static Box[] walls = new Box[3];
	public static List<Block> blocks = new ArrayList<Block>();
	public static Scoreboard scoreboard;
	public static GameOver gameOver;
	public static Point2D[] bounds = new Point2D[4];
	
	public static GameState gameState = GameState.PLAYING;
	
	public static void create() {
		GraphicsEnvironment.create();
		BoxGraphic.create();
		CircleGraphic.create();
		LineGraphic.create();
		
		paddle = new Paddle(new Point2D(Settings.windowWidth/2 - 125.0f, 32.0f), new Vector2D(90.0f, 24.0f), Settings.ORANGE_RED, Settings.PADDLE_SPEED);
		ball = new Ball(new Point2D(Settings.windowWidth/2, 54.0f), Settings.BALL_RADIUS, Color.TEAL, Settings.BALL_SPEED);
		
		// Bottom left
		bounds[0] = new Point2D(Settings.WALL_THICKNESS, 0);
		// Top left
		bounds[1] = new Point2D(Settings.WALL_THICKNESS, Settings.windowHeight -Settings.WALL_THICKNESS);
		// Top right
		bounds[2] = new Point2D(Settings.windowWidth - Settings.SCOREBOARD_THICKNESS, Settings.windowHeight -Settings.WALL_THICKNESS);
		// Bottom right
		bounds[3] = new Point2D(Settings.windowWidth- Settings.SCOREBOARD_THICKNESS, 0);

		
		blockCount = blocks.size();
		
		scoreboard = new Scoreboard();
		gameOver = new GameOver();

		// prepareNextLevel increments this when preparing each level, set to 0 to start 
		// at level 1, because real computer scientists always start counting from 0?!
		levelIndex = 0;
		// Set this variable to equal the index of the last level
		lastLevelIndex = 2;
		prepareNextLevel();
	}
	
	public static void loseLife() {
		// Don't reset blocks, just paddle and ball
		if (scoreboard.getLives() > 0) {
			scoreboard.decrementLives();
			ballStuckToPaddle = true;
			paddle.reset();
			ball.reset();
		}
		else {
			gameState = GameState.GAME_OVER;
		}
	}

	public static void prepareNextLevel() {
		ballStuckToPaddle = true;
		
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
			case 2:
				setupLevelTwo();
				break;
		}
		scoreboard.setLevelIndex(levelIndex);
		blockCount = blocks.size();	
	}
	
	private static void reset() {
		ballStuckToPaddle = true;
		paddle.reset();
		ball.reset();
		
		gameOver.reset();
	}
	
	private static void setupLevelOne() {
		walls = LevelCreator.getLevelOneWalls();
		blocks = LevelCreator.getLevelOneBlocks();
		reset();
	}
	
	private static void setupLevelTwo() {
		walls = LevelCreator.getLevelTwoWalls();
		blocks = LevelCreator.getLevelTwoBlocks();
		reset();
	}
	
	public static void processInput() {
		switch (gameState) {
			case PLAYING: {
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
				
				if (ballStuckToPaddle) {
					if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
						ballStuckToPaddle = false;
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
				break;
			}
			case GAME_OVER:
				if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
					if (gameOver.getPlayAgain()) {
						scoreboard.init();
						levelIndex = 0;
						GameManager.prepareNextLevel();
						gameState = GameState.PLAYING;
					}
				}
				break;
		case LEVEL_TRANSITION:
			break;
		case MAIN_MENU:
			break;
		default:
			break;
		}
	}
	
	public static void updateGame() {
		deltaTime = Gdx.graphics.getDeltaTime();
		
		if (blockCount == 0) {
			prepareNextLevel();
		}
		
		processInput();
		
		ball.setTimeLeftToMove(deltaTime);
		
		// Check all of the collisions.
		Collisions.checkCollisions(deltaTime);
		
		// Update the position of all of the game objects in the game.
		paddle.update(deltaTime);
		// If it's the start of the level we make the ball follow the paddle.
		if (ballStuckToPaddle) {
			ball.translate(paddle.getPosition().x - ball.getPosition().x, 0);
		}
		ball.update(deltaTime);
		for (Block block : blocks) {
			block.update(deltaTime);	
		}
		
		// Shake the screen if needed
		ScreenShaker.update(deltaTime);
	}
	

	
	public static void displayGame() {
		GraphicsEnvironment.clearScreen(Settings.LIGHT_YELLLOW);
		
		for (Box b : walls) {
			b.draw();
		}
		paddle.draw();
		ball.draw();
		
		for (Block block : blocks) {
			block.draw();	
		}
		
		scoreboard.draw();
	}
	
	public static void updateGameOver() {
		processInput();
	}
	
	public static void displayGameOver() {
		
		displayGame();
		gameOver.draw (deltaTime);
	}
}
