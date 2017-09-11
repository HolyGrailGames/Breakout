package is.ru.tgra.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	public static final Sound POP = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.wav"));
	public static final Sound BREAKOUT = Gdx.audio.newSound(Gdx.files.internal("sounds/breakout.wav"));
	
	/**
	 * Private constructor to prevent anyone from creating an instance of this class.
	 * This is done because the Settings class is to be used as a static class.
	 */
	private SoundManager() {}
}
