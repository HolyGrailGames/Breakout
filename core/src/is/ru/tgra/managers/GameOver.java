package is.ru.tgra.managers;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.shapes.Text;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;

public class GameOver
{

	private Text gameOverText;
	private Text playAgainText;
	
	private Color black;
	private float alphaTimer;
	private boolean playAgain;
	public GameOver() {
		black = new Color(0.0f, 0.0f, 0.0f, 0.0f);
		gameOverText = new Text("GAME OVER!" , new Point2D((Settings.windowWidth / 2.0f) - 300.0f, Settings.windowHeight / 2.0f), black, 64);
		playAgainText = new Text("Press Space to play again!" , new Point2D((Settings.windowWidth / 2.0f) - 300.0f, (Settings.windowHeight / 2.0f) - 100.0f), Color.BLACK, 24);
		alphaTimer = 0.0f;
		playAgain = false;
	}
	
	public void draw(float deltaTime) {
		alphaTimer += deltaTime;
		if (alphaTimer <= 1.0f) {
			gameOverText.setColor(black.r, black.g, black.b, alphaTimer);	
		}
		
		else {
			playAgain = true;
			playAgainText.draw();
		}
		
		gameOverText.draw();
	}
	
	public boolean getPlayAgain() {
		return this.playAgain;
	}
}


