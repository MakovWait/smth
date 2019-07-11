package by.men.game.dagger;

import box2dLight.RayHandler;
import by.men.game.resource.AssetHolder;
import by.men.game.stage.StageLoader;
import by.mkwt.engine.tiled.serializer.TiledSerializer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.PropertiesUtils;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.io.IOException;

@Module
public class MainModule {

    @Singleton @Provides
    AssetHolder provideAssetHolder() {
        System.out.println("asset holder created");
        return new AssetHolder();
    }

    @Singleton @Provides
    World provideWorld() {
        return new World(new Vector2(0, 0), false);
    }

    @Singleton @Provides
    Json provideJson() {
        return new Json();
    }

    @Singleton @Provides
    TiledSerializer provideTiledSerializer(Json json, World world, RayHandler rayHandler) {
        ObjectMap<String, String> components = new ObjectMap<>();
        try {
            PropertiesUtils.load(components, Gdx.files.internal("config/components.properties").reader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TiledSerializer(json, world, rayHandler, components);
    }

    @Singleton @Provides
    StageLoader provideStageLoader(AssetHolder assetHolder, TiledSerializer tiledSerializer) {
        return new StageLoader(assetHolder, tiledSerializer);
    }

    @Singleton @Provides
    RayHandler provideRayHandler(World world) {
        RayHandler rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.5f);
        rayHandler.setShadows(true);
        rayHandler.setBlur(true);
        return rayHandler;
    }

//    @Singleton @Provides
//    World provideWorld() {
//        return new World(Constants.GRAVITY, false);
//    }
//
//    @Singleton @Provides
//    Json provideJson() {
//        return new Json();
//    }
//
//    @Singleton @Provides
//    TiledSerializer provideTiledSerializer(Json json, World world, RayHandler rayHandler) {
//        return new TiledSerializer(json, world, rayHandler);
//    }
//
//    @Singleton @Provides
//    RayHandler provideRayHandler(World world) {
//        RayHandler rayHandler = new RayHandler(world);
//        rayHandler.setAmbientLight(0.5f);
//        rayHandler.setShadows(true);
//        rayHandler.setBlur(true);
//
//        return rayHandler;
//    }
//
//    @Singleton @Provides
//    OrthographicCamera provideOrthographicCamera() {
//        return new OrthographicCamera();
//    }
//
//    @Singleton @Provides
//    ExtendViewport provideExtendViewport(OrthographicCamera camera) {
//        return new ExtendViewport(26, 15, camera);
//    }
//
//    @Singleton @Provides
//    StageLoader provideStageLoader(AssetHolder assetHolder, TiledSerializer tiledSerializer) {
//        return new StageLoader(assetHolder, tiledSerializer);
//    }

}
