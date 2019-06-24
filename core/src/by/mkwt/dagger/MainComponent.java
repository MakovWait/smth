package by.mkwt.dagger;

import by.men.game.screen.GameScreen;
import by.men.game.screen.MainMenuScreen;
import by.mkwt.engine.system.render.LightingSystem;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(GameScreen gameScreen);

    void inject(MainMenuScreen mainMenuScreen);

    void inject(LightingSystem lightingSystem);

}

