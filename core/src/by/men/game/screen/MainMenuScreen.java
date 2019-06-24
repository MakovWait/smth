package by.men.game.screen;

import by.men.game.Game;
import by.mkwt.AssetHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenuScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private TextButton textButton;
    private OrthographicCamera camera;

    @Inject
    AssetHolder assetHolder;

    public MainMenuScreen() {
        camera = new OrthographicCamera();
        camera.position.setZero();
        stage = new Stage(new ExtendViewport(1920, 1080, camera));
        Game.daggerMainComponent.inject(this);
    }

    @Override
    public void show() {
        System.out.println("MAIN MENU");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(assetHolder.getAsset("ui/skin/default/skin/uiskin.atlas"));
        this.skin.load(Gdx.files.internal("ui/skin/default/skin/uiskin.json"));
        this.skin.add("default-font", assetHolder.getFont24());

        this.skin.get(TextButton.TextButtonStyle.class).font = assetHolder.getFont24();
        this.skin.get(Window.WindowStyle.class).titleFont = assetHolder.getFont24();


        Window someWindow = new SomeWindow(skin);
        someWindow.setKeepWithinStage(false);
        someWindow.setMovable(true);
        someWindow.setVisible(true);
        someWindow.setResizable(true);
        someWindow.setPosition(50, 50);
        someWindow.setSize(100, 200);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = assetHolder.getFont24();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assetHolder.getFont24();

        Label label = new Label("привет как дела у меня норм а ты что скажеш?) ", labelStyle);

        someWindow.add(label);

        textButton = new TextButton("Plвввay", skin);

        textButton.setPosition(0, 0);
        textButton.setSize(100, 50);
        textButton.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));

        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                someWindow.setVisible(!someWindow.isVisible());
            }
        });

        stage.addActor(textButton);
        stage.addActor(someWindow);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
    }

    private void update(float delta) {
        stage.act(delta);
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

    }

    @Override
    public void dispose() {

    }

}
