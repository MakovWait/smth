package by.men.game;

import by.men.game.screen.gab.GabScreen;
import by.mkwt.dagger.DaggerMainComponent;
import by.mkwt.dagger.MainComponent;

public class Game extends com.badlogic.gdx.Game {

	public static MainComponent daggerMainComponent = DaggerMainComponent.create();

	@Override
	public void create() {
		setScreen(new GabScreen());
	}

}
