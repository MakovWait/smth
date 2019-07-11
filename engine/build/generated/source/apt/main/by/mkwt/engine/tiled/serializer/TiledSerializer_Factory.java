package by.mkwt.engine.tiled.serializer;

import box2dLight.RayHandler;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TiledSerializer_Factory implements Factory<TiledSerializer> {
  private final Provider<Json> jsonProvider;

  private final Provider<World> worldProvider;

  private final Provider<RayHandler> rayHandlerProvider;

  private final Provider<ObjectMap<String, String>> componentsProvider;

  public TiledSerializer_Factory(
      Provider<Json> jsonProvider,
      Provider<World> worldProvider,
      Provider<RayHandler> rayHandlerProvider,
      Provider<ObjectMap<String, String>> componentsProvider) {
    this.jsonProvider = jsonProvider;
    this.worldProvider = worldProvider;
    this.rayHandlerProvider = rayHandlerProvider;
    this.componentsProvider = componentsProvider;
  }

  @Override
  public TiledSerializer get() {
    return new TiledSerializer(
        jsonProvider.get(),
        worldProvider.get(),
        rayHandlerProvider.get(),
        componentsProvider.get());
  }

  public static Factory<TiledSerializer> create(
      Provider<Json> jsonProvider,
      Provider<World> worldProvider,
      Provider<RayHandler> rayHandlerProvider,
      Provider<ObjectMap<String, String>> componentsProvider) {
    return new TiledSerializer_Factory(
        jsonProvider, worldProvider, rayHandlerProvider, componentsProvider);
  }
}
