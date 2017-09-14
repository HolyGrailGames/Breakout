package is.ru.tgra.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	public static final Sound POP = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.wav"));
	public static final Sound BREAKOUT = Gdx.audio.newSound(Gdx.files.internal("sounds/breakout.wav"));
	public static final Sound BOING = Gdx.audio.newSound(Gdx.files.internal("sounds/boing.wav"));
	public static final Sound GAME_OVER = Gdx.audio.newSound(Gdx.files.internal("sounds/game_over.wav"));
	public static final Sound LOSE_LIFE = Gdx.audio.newSound(Gdx.files.internal("sounds/lose_life.wav"));
	public static final Sound SONG = Gdx.audio.newSound(Gdx.files.internal("sounds/song.wav"));
	
	/**
	 * Private constructor to prevent anyone from creating an instance of this class.
	 * This is done because the Settings class is to be used as a static class.
	 */
	private SoundManager() {}
}
