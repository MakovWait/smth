package by.men.game.dagger;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class UiMainModule {

    @Provides
    @Singleton
    public OrthographicCamera provideOrthographicCamera() {
        System.out.println("camera created");
        return new OrthographicCamera();
    }

    @Provides @Singleton
    public ExtendViewport provideExtendViewport(OrthographicCamera camera) {
        System.out.println("viewport created");
        return new ExtendViewport(1920, 1080, camera);
    }
}
