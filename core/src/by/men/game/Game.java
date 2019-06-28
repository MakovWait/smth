package by.men.game;

import by.men.game.screen.MainMenuScreen;
import by.men.game.ui.SimpleMessage;
import by.mkwt.dagger.DaggerMainComponent;
import by.mkwt.dagger.MainComponent;
import by.mkwt.engine.component.interactive.MessageComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import net.dermetfan.gdx.maps.tiled.TmxMapWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Game extends com.badlogic.gdx.Game {

	public static final MainComponent daggerMainComponent = DaggerMainComponent.create();

	@Override
	public void create() {
		setScreen(new MainMenuScreen());
	}

}
