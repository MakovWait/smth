package by.men.game.screen;

import by.men.game.Game;
import by.mkwt.AssetHolder;
import by.mkwt.engine.GameEngine;
import by.mkwt.engine.util.Multiflexer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.inject.Inject;

public class GameScreen implements Screen {

    @Inject
    GameEngine gameEngine;
    @Inject
    AssetHolder assetHolder;
    @Inject
    ExtendViewport extendViewport;

    private Stage stage;

    @Override
    public void show() {
        //reader.buildEntityFromTiledScript("");
        stage = new Stage(new ScreenViewport());
        Multiflexer.getInstance().addProcessor(stage);

        Game.daggerMainComponent.inject(this);
    }

    @Override
    public void render(float delta) {
        gameEngine.update(delta);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        extendViewport.update(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        gameEngine.save();
        Gdx.app.exit();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
