package is.ru.tgra.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import is.ru.tgra.Breakout;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Ultimate Breakout!"; // or whatever you like
		config.width = 1024;  //experiment with
		config.height = 768;  //the window size
		
		new LwjglApplication(new Breakout(), config);
	}
}
