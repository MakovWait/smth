package by.men.game.dagger;

import by.mkwt.engine.GameContext;
import by.mkwt.engine.ecs.system.render.LightingSystem;

public class Injector implements by.mkwt.engine.dagger.Injector {

    public static final MainComponent MAIN_COMPONENT = DaggerMainComponent.create();
    public static final InjectorComponent INJECTOR_COMPONENT = DaggerInjectorComponent.create();

    @Override
    public void inject(GameContext gameContext) {
        INJECTOR_COMPONENT.inject(gameContext);
    }

    @Override
    public void inject(LightingSystem system) {
        INJECTOR_COMPONENT.inject(system);
    }

}
