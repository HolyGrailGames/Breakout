package is.ru.tgra;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import is.ru.tgra.managers.GameManager;
import is.ru.tgra.managers.SoundManager;
import is.ru.tgra.utils.Settings;

public class Breakout extends ApplicationAdapter {

	@Override
	public void create() {
		Settings.windowWidth  = Gdx.graphics.getWidth();
		Settings.windowHeight = Gdx.graphics.getHeight();
		GameManager.create();
	}

	@Override
	public void render () {
		Gdx.graphics.setTitle("Ultimate Breakout | FPS: " + Gdx.graphics.getFramesPerSecond());
		switch (GameManager.gameState) {
		case MAIN_MENU:
			//updateMainMenu();
			//displayMainMenu();
			break;
		
		case PLAYING:
			GameManager.updateGame();
			GameManager.displayGame();
			break;
			
		case GAME_OVER:
			GameManager.updateGameOver();
			GameManager.displayGameOver();
			break;
		
		case LEVEL_TRANSITION:
			//updateLevelTransition();
			//displayLevelTransition();
			break;
		default:
			break;
		}	
	}
}