package by.mkwt.engine.dagger;

import by.mkwt.engine.GameContext;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerEngineMainComponent implements EngineMainComponent {
  private DaggerEngineMainComponent(Builder builder) {}

  public static Builder builder() {
    return new Builder();
  }

  public static EngineMainComponent create() {
    return new Builder().build();
  }

  @Override
  public void inject(GameContext gameContext) {}

  public static final class Builder {
    private Builder() {}

    public EngineMainComponent build() {
      return new DaggerEngineMainComponent(this);
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder engineMainModule(EngineMainModule engineMainModule) {
      Preconditions.checkNotNull(engineMainModule);
      return this;
    }
  }
}
