package by.men.game.dagger;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class InjectorModule {

    @Singleton
    @Provides
    OrthographicCamera provideOrthographicCamera() {
        return new OrthographicCamera();
    }

    @Singleton @Provides
    ExtendViewport provideExtendViewport(OrthographicCamera camera) {
        return new ExtendViewport(26, 15, camera);
    }

    @Singleton @Provides
    PooledEngine providePooledEngine() {
        return new PooledEngine();
    }

}
