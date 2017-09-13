package is.ru.tgra.utils;

import com.badlogic.gdx.graphics.Color;

public class Settings {
	
	public static float windowHeight;
	public static float windowWidth;
	
	public static final float GRAVITY = -7.5f;
	
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
	
	public static final float WALL_THICKNESS = 16.0f;
	public static final float SCOREBOARD_THICKNESS = 250.0f;
	
	public static final float PADDLE_SPEED = 700.0f;
		
	public static final float BLOCK_WIDTH = 60.0f;
	public static final float BLOCK_HEIGHT = 24.0f;
	public static final float BLOCK_SPACING = 6.0f;
	
	public static final float BALL_SPEED = 500.0f;
	public static final float BALL_RADIUS = 10.0f;
	public static final float BALL_ACCELERATION = 20.0f;
	public static final float BALL_MAX_SPEED = 1500.0f;
	
	public static final int   SHAKE_POWER = 8;
	public static final float SHAKE_TIMER = 0.2f;
	
	public static final String LEFT = "LEFT";
	public static final String RIGHT = "RIGHT";
	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	
	public static final String HORIZONTAL = "HORIZONTAL";
	public static final String VERTICAL = "VERTICAL";
	
	public static final float LEVEL1_ORIGIN_Y = 110.0f;
	public static final int LEVEL1_ROWS = 8;
	public static final int LEVEL1_COLS = 10;
	
	/**
	 * Private constructor to prevent anyone from creating an instance of this class.
	 * This is done because the Settings class is to be used as a static class.
	 */
	private Settings() {}
}
