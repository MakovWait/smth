package by.mkwt.dagger;

import box2dLight.RayHandler;
import by.mkwt.AssetHolder;
import by.mkwt.engine.Constants;
import by.mkwt.tiled.StageLoader;
import by.mkwt.tiled.serializer.TiledSerializer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MainModule {

    @Singleton @Provides
    AssetHolder provideAssetHolder() {
        return new AssetHolder();
    }

    @Singleton @Provides
    World provideWorld() {
        return new World(Constants.GRAVITY, false);
    }

    @Singleton @Provides
    Json provideJson() {
        return new Json();
    }

    @Singleton @Provides
    TiledSerializer provideTiledSerializer(Json json, World world, RayHandler rayHandler) {
        return new TiledSerializer(json, world, rayHandler);
    }

    @Singleton @Provides
    RayHandler provideRayHandler(World world) {
        RayHandler rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.5f);
        rayHandler.setShadows(true);
        rayHandler.setBlur(true);

        return rayHandler;
    }

    @Singleton @Provides
    OrthographicCamera provideOrthographicCamera() {
        return new OrthographicCamera();
    }

    @Singleton @Provides
    ExtendViewport provideExtendViewport(OrthographicCamera camera) {
        return new ExtendViewport(26, 15, camera);
    }

    @Singleton @Provides
    StageLoader provideStageLoader(AssetHolder assetHolder, TiledSerializer tiledSerializer) {
        return new StageLoader(assetHolder, tiledSerializer);
    }

}
