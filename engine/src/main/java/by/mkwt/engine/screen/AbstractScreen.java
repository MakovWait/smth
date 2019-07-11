package by.mkwt.engine.screen;

import by.mkwt.engine.GameContext;
import by.mkwt.engine.input.Multiflexer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import javax.inject.Inject;

public abstract class AbstractScreen implements Screen {

    protected GameContext gameContext;

    protected Stage stage;

    protected Screen previousScreen;

    @Inject
    protected OrthographicCamera camera;
    @Inject
    protected ExtendViewport extendViewport;

    public AbstractScreen(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    @Override
    public void show() {
        if (stage == null) {
            initStage();
        }

        stage.clear();
        fillStage();
        Multiflexer.getInstance().addProcessor(stage);
    }

    protected abstract void initStage();

    protected abstract void fillStage();

    @Override
    public void render(float delta) {
        clearScreen();
        update(delta);
        draw(delta);
    }

    protected void draw(float delta) {
        stage.draw();
    }

    protected void update(float delta) {
        stage.act(delta);
    }

    protected void clearScreen() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
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
        Multiflexer.getInstance().removeProcessor(stage);
    }

    @Override
    public void dispose() {
        System.out.println("Disposed");
        stage.dispose();
    }

    public void setPreviousScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }

    public Screen getPreviousScreen() {
        return previousScreen;
    }

}
