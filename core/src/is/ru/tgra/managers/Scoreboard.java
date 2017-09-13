package is.ru.tgra.managers;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.shapes.Text;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;

public class Scoreboard {
	//private Text livesText;
	private Text scoreText;
	private Text levelText;
	
	private int lives = 3;
	private int score = 0;
	private int level = 1;
	
	public Scoreboard() {
		//livesText = new Text("Lives Left: " + lives, new Point2D(10.0f, Settings.windowHeight - 10.0f), Color.BLACK, 24);
		scoreText = new Text("Score: " + score, new Point2D(Settings.windowWidth/2 - 50.0f, Settings.windowHeight-10.0f), Color.BLACK, 24);
		levelText = new Text("Level: " + level, new Point2D(Settings.windowWidth-180.0f, Settings.windowHeight-10.0f), Color.BLACK, 24);
	}
	
	public void draw() {
		//livesText.draw();
		scoreText.draw();
		levelText.draw();
	}
	
	public void addToScore(int score) {
		this.score += score;
		scoreText.setText("Score: " + this.score);
	}
	
	public void decrementLives() {
		this.lives--;
		//livesText.setText("Lives Left: " + this.lives);
	}
	
	public int getLives() {
		return this.lives;
	}
}
