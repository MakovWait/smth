package by.mkwt.engine;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GameContext_MembersInjector implements MembersInjector<GameContext> {
  private final Provider<PooledEngine> engineProvider;

  private final Provider<World> worldProvider;

  public GameContext_MembersInjector(
      Provider<PooledEngine> engineProvider, Provider<World> worldProvider) {
    this.engineProvider = engineProvider;
    this.worldProvider = worldProvider;
  }

  public static MembersInjector<GameContext> create(
      Provider<PooledEngine> engineProvider, Provider<World> worldProvider) {
    return new GameContext_MembersInjector(engineProvider, worldProvider);
  }

  @Override
  public void injectMembers(GameContext instance) {
    injectEngine(instance, engineProvider.get());
    injectWorld(instance, worldProvider.get());
  }

  public static void injectEngine(GameContext instance, PooledEngine engine) {
    instance.engine = engine;
  }

  public static void injectWorld(GameContext instance, World world) {
    instance.world = world;
  }
}
