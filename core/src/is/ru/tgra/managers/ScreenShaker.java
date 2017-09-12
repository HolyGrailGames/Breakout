package is.ru.tgra.managers;

import java.util.Random;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.utils.Settings;

public class ScreenShaker {
	private static float shakeTimer = 0.0f;
	private static int shakePower = 0;
	private static Random random = new Random();
	private static boolean isShaking = false;
	
	/**
	 * Private constructor to prevent anyone from creating an instance of this class.
	 * This is done because the ScreenShaker class is to be used as a static class.
	 */
	private ScreenShaker() {}
	
	public static void update(float deltaTime) {
		if (shakeTimer > 0) {
			isShaking = true;
			shakeTimer -= deltaTime;
			
			int offsetX = random.nextInt(shakePower) - shakePower/2;
			int offsetY = random.nextInt(shakePower) - shakePower/2;
			
			GraphicsEnvironment.setWindow(-offsetX, Settings.windowWidth-offsetX, -offsetY, Settings.windowHeight-offsetY);
		}
		else if (isShaking){
			isShaking = false;
			GraphicsEnvironment.setWindow(0, Settings.windowWidth, 0, Settings.windowHeight);
		}
	}
	
	public static void shake(int power, float duration) {
		shakePower = power;
		shakeTimer = duration;
	}
}
