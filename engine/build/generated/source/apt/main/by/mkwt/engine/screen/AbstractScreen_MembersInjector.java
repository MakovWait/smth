package by.mkwt.engine.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AbstractScreen_MembersInjector implements MembersInjector<AbstractScreen> {
  private final Provider<OrthographicCamera> cameraProvider;

  private final Provider<ExtendViewport> extendViewportProvider;

  public AbstractScreen_MembersInjector(
      Provider<OrthographicCamera> cameraProvider,
      Provider<ExtendViewport> extendViewportProvider) {
    this.cameraProvider = cameraProvider;
    this.extendViewportProvider = extendViewportProvider;
  }

  public static MembersInjector<AbstractScreen> create(
      Provider<OrthographicCamera> cameraProvider,
      Provider<ExtendViewport> extendViewportProvider) {
    return new AbstractScreen_MembersInjector(cameraProvider, extendViewportProvider);
  }

  @Override
  public void injectMembers(AbstractScreen instance) {
    injectCamera(instance, cameraProvider.get());
    injectExtendViewport(instance, extendViewportProvider.get());
  }

  public static void injectCamera(AbstractScreen instance, OrthographicCamera camera) {
    instance.camera = camera;
  }

  public static void injectExtendViewport(AbstractScreen instance, ExtendViewport extendViewport) {
    instance.extendViewport = extendViewport;
  }
}
