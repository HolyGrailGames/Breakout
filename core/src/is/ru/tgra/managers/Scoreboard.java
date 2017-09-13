package is.ru.tgra.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.shapes.Box;
import is.ru.tgra.shapes.Text;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Vector2D;

public class Scoreboard {
	//private Text livesText;
	private Text scoreText;
	private Text levelText;
	
	private List<Box> extraPaddles;
	
	private int lives = 3;
	private int score = 0;
	private int level = 1;
	
	public Scoreboard() {
		//livesText = new Text("Lives Left: " + lives, new Point2D(10.0f, Settings.windowHeight - 10.0f), Color.BLACK, 24);
		scoreText = new Text("Score: \n" + score, new Point2D(Settings.windowWidth - 200.0f, Settings.windowHeight-50.0f), Color.BLACK, 24);
		levelText = new Text("Level: \n" + level, new Point2D(Settings.windowWidth - 200.0f, Settings.windowHeight-200.0f), Color.BLACK, 24);
		extraPaddles = new ArrayList<Box>();
		init();
	}
	
	public void draw() {
		//livesText.draw();
		scoreText.draw();
		levelText.draw();
		
		for (Box extraPaddle : extraPaddles) {
			extraPaddle.draw();
		}
	}
	
	public void addToScore(int score) {
		this.score += score;
		scoreText.setText("Score: " + "\n" + this.score);
	}
	
	public void decrementLives() {
		extraPaddles.remove(extraPaddles.size() - 1);
		this.lives--;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public void init() {
		this.lives = 3;
		this.score = 0;
		this.level = 1;
		initializeExtraPaddles();
	}
	
	private void initializeExtraPaddles() {
		float offsetX = 0.0f;
		for (int i = 0; i < lives; i++) {
			extraPaddles.add(new Box(new Point2D((Settings.windowWidth - Settings.SCOREBOARD_THICKNESS + 55.0f) + offsetX, Settings.windowHeight - 500), new Vector2D(70.0f, 18.0f), Color.RED));
			offsetX += 73.0f;
		}
	}
}
