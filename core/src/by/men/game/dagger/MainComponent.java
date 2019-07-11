package by.mkwt.engine.dagger;

import by.men.game.Game;
import by.men.game.MenuScreen;
import by.men.game.screen.*;
import by.mkwt.engine.system.render.LightingSystem;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(GameScreen gameScreen);

    void inject(MainMenuScreen mainMenuScreen);

    void inject(MenuScreen menuScreen);

    void inject(MainScreen menuScreen);

    void inject(Game game);

    void inject(ChooseGameStageScreen chooseGameStageScreen);

    void inject(LightingSystem lightingSystem);

}

