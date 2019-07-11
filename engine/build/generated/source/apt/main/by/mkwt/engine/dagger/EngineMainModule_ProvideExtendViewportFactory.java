package by.mkwt.engine.dagger;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class EngineMainModule_ProvideExtendViewportFactory
    implements Factory<ExtendViewport> {
  private final EngineMainModule module;

  private final Provider<OrthographicCamera> cameraProvider;

  public EngineMainModule_ProvideExtendViewportFactory(
      EngineMainModule module, Provider<OrthographicCamera> cameraProvider) {
    this.module = module;
    this.cameraProvider = cameraProvider;
  }

  @Override
  public ExtendViewport get() {
    return Preconditions.checkNotNull(
        module.provideExtendViewport(cameraProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ExtendViewport> create(
      EngineMainModule module, Provider<OrthographicCamera> cameraProvider) {
    return new EngineMainModule_ProvideExtendViewportFactory(module, cameraProvider);
  }
}
