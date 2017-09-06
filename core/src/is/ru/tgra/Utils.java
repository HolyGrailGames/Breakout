package is.ru.tgra;

public class Utils {
	/**
	 * Private constructor to prevent anyone from creating an instance of this class.
	 * This is done because the Utils class is to be used as a static class.
	 */
	private Utils() {}
	
	public static float Clamp(float value, float min, float max) {
		return Math.max(min, Math.min(max, value));
	}
}
