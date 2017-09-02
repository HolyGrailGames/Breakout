package is.ru.tgra;

import com.badlogic.gdx.graphics.Color;

public class Settings {
	
	public static float windowHeight;
	public static float windowWidth;
	
	public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f, 1.0f);
	public static final Color LIGHT_GRAY = new Color(0.83f, 0.83f, 0.83f, 1.0f);
	public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	public static final Color RED = new Color(1.0f, 0.0f, 0.0f, 1.0f);
	public static final Color ORANGE_RED = new Color(0.93f, 0.24f, 0.32f, 1.0f);
	public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f, 1.0f);
	public static final Color LIGHT_GREEN = new Color(0.6f, 0.94f, 0.71f, 1.0f);
	public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f, 1.0f);
	public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f, 1.0f);
	public static final Color LIGHT_YELLLOW = new Color(1.0f, 1.0f, 0.785f, 1.0f);
	
	public static final float WALL_THICKNESS = 32.0f;
	
	/**
	 * Private constructor to prevent anyone from creating an instance of this class.
	 * This is done because the Settings class is to be used as a static class.
	 */
	private Settings() {}
}
