package by.mkwt.engine.ecs.system.render;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class LightingSystem_MembersInjector implements MembersInjector<LightingSystem> {
  private final Provider<OrthographicCamera> cameraProvider;

  private final Provider<World> worldProvider;

  private final Provider<RayHandler> rayHandlerProvider;

  public LightingSystem_MembersInjector(
      Provider<OrthographicCamera> cameraProvider,
      Provider<World> worldProvider,
      Provider<RayHandler> rayHandlerProvider) {
    this.cameraProvider = cameraProvider;
    this.worldProvider = worldProvider;
    this.rayHandlerProvider = rayHandlerProvider;
  }

  public static MembersInjector<LightingSystem> create(
      Provider<OrthographicCamera> cameraProvider,
      Provider<World> worldProvider,
      Provider<RayHandler> rayHandlerProvider) {
    return new LightingSystem_MembersInjector(cameraProvider, worldProvider, rayHandlerProvider);
  }

  @Override
  public void injectMembers(LightingSystem instance) {
    injectCamera(instance, cameraProvider.get());
    injectWorld(instance, worldProvider.get());
    injectRayHandler(instance, rayHandlerProvider.get());
  }

  public static void injectCamera(LightingSystem instance, OrthographicCamera camera) {
    instance.camera = camera;
  }

  public static void injectWorld(LightingSystem instance, World world) {
    instance.world = world;
  }

  public static void injectRayHandler(LightingSystem instance, RayHandler rayHandler) {
    instance.rayHandler = rayHandler;
  }
}
