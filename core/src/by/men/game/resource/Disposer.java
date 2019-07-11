package by.men.game.resource;

import by.men.game.dagger.Injector;

import javax.inject.Inject;

public class Disposer implements by.mkwt.engine.disposer.Disposer {

    @Inject
    AssetHolder assetHolder;

    @Override
    public void init() {
        Injector.MAIN_COMPONENT.inject(this);
    }

    @Override
    public void dispose() {
        assetHolder.dispose();
    }

}
