package by.mkwt.engine.dagger;

import com.badlogic.gdx.graphics.OrthographicCamera;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class EngineMainModule_ProvideOrthographicCameraFactory
    implements Factory<OrthographicCamera> {
  private final EngineMainModule module;

  public EngineMainModule_ProvideOrthographicCameraFactory(EngineMainModule module) {
    this.module = module;
  }

  @Override
  public OrthographicCamera get() {
    return Preconditions.checkNotNull(
        module.provideOrthographicCamera(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OrthographicCamera> create(EngineMainModule module) {
    return new EngineMainModule_ProvideOrthographicCameraFactory(module);
  }
}
