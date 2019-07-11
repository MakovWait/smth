package by.men.game.desktop;

import by.men.game.dagger.Injector;
import by.men.game.resource.Disposer;
import by.men.game.screen.MainMenuScreen;
import by.mkwt.engine.GameContext;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;

		new LwjglApplication(new GameContext(MainMenuScreen.class, new Disposer(), new Injector()), config);
	}

}
