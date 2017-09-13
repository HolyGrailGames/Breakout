package is.ru.tgra;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.managers.GameOver;
import is.ru.tgra.managers.GameState;
import is.ru.tgra.managers.LevelCreator;
import is.ru.tgra.managers.Scoreboard;
import is.ru.tgra.managers.ScreenShaker;
import is.ru.tgra.managers.SoundManager;
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

public class Breakout extends ApplicationAdapter {
	
	private int levelIndex;
	private int lastLevelIndex;
	private int blockCount;
	
	private boolean ballStuckToPaddle = true;
	
	private float deltaTime;
		
	private Paddle paddle;
	private Ball ball;
	private Box[] walls = new Box[3];
	private List<Block> blocks = new ArrayList<Block>();
	private Scoreboard scoreboard;
	private GameOver gameOver;
	
	private Point2D[] bounds = new Point2D[4];
	
	private float mouseX;
	private float mouseY;

	private GameState gameState = GameState.PLAYING;

	@Override
	public void create() {
		Settings.windowWidth  = Gdx.graphics.getWidth();
		Settings.windowHeight = Gdx.graphics.getHeight();

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
		
		// Start game at level 1
		levelIndex = 1;
		// Set this variable to the last level
		lastLevelIndex = 1;
		
		blockCount = blocks.size();
		
		scoreboard = new Scoreboard();
		gameOver = new GameOver();

		prepareNextLevel();		
	}

	private void updateGame() {
		deltaTime = Gdx.graphics.getDeltaTime();
		
		if (blockCount == 0) {
			prepareNextLevel();
		}
		
		processInput();
		
		// Check all of the collisions.
		checkCollisions();
		
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
	

	
	private void displayGame() {
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
	
	private void updateGameOver() {
		processInput();
	}
	
	private void displayGameOver() {
		
		displayGame();
		gameOver.draw (deltaTime);
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
			updateGameOver();
			displayGameOver();
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
						
						setupLevelOne();
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
	
	private void prepareNextLevel() {
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
		}
		
		blockCount = blocks.size();	
	}
	
	private void checkCollisions() {
		for (int i = 0; i < bounds.length; i++) {
			int j = (i < bounds.length -1) ? i+1 : 0;
			if (ball.checkCollisionWithLine(bounds[i], bounds[j], deltaTime) && i == 3 && j == 0) {
				// We've hit the ground and we lose a life.
				loseLife();
				break;
			}
		}
		
		Point2D[] paddlePoints = paddle.getPoints();
		for (int i = 0; i < paddlePoints.length - 1; i++) {
			if (ball.checkCollisionWithLine(paddlePoints[i], paddlePoints[i+1], deltaTime) && i == 1) {
				// We've collided with the top of the paddle
				float ratio = paddle.getRatioOfPoint(ball.getPHit());
				
				if (ratio < 0.35 || ratio > 0.65) {
					ball.setDirection(new Vector2D(ratio*2-1, 1));
				}
				
				break;
			}
		}
		
		for (Block block : blocks) {
			if (!block.getExploded()) {
				Point2D[] blockPoints = block.getPoints();
				
				for (int i = 0; i < blockPoints.length; i++) {
					int j = (i < blockPoints.length - 1) ? i+1 : 0;
					boolean collision = ball.checkCollisionWithLine(blockPoints[i], blockPoints[j], deltaTime);
					if (collision) {
						ball.impact();
						block.explode();
						blockCount--;
						ScreenShaker.shake(Settings.SHAKE_POWER, Settings.SHAKE_TIMER);
						scoreboard.addToScore(500);
						//SoundManager.POP.play();
						break;
					}
				}	
			}
		}
	}
	
	private void setupLevelOne() {
		walls = LevelCreator.getLevelOneWalls();
		blocks = LevelCreator.getLevelOneBlocks();
		
		ballStuckToPaddle = true;
		paddle.reset();
		ball.reset();
		
		scoreboard.reset();
		gameOver.reset();
	}
	
	private void loseLife() {
		
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
}