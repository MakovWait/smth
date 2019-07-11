package by.men.game.dagger;

import by.men.game.resource.Disposer;
import by.men.game.screen.ChooseStageScreen;
import by.men.game.screen.MainMenuScreen;
import by.mkwt.engine.dagger.Injector;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {MainModule.class, UiMainModule.class})
public interface MainComponent {

    void inject(MainMenuScreen menuScreen);

    void inject(ChooseStageScreen menuScreen);

    void inject(Disposer disposer);

}

