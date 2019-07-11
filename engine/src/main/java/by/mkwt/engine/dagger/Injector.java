package by.mkwt.engine.dagger;

import by.mkwt.engine.GameContext;
import by.mkwt.engine.ecs.system.render.LightingSystem;

public interface Injector {

    void inject(GameContext gameContext);

    void inject(LightingSystem system);

}
