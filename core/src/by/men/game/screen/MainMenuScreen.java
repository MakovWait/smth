package by.men.game.screen;

import by.men.game.Game;
import by.men.game.ui.SimpleMessageShower;
import by.men.game.ui.SimpleMessageWindow;
import by.mkwt.AssetHolder;
import by.mkwt.engine.GameEngine;
import by.mkwt.engine.util.Multiflexer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenuScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private TextButton textButton;
    private OrthographicCamera camera;
    private SimpleMessageShower showMessageMediator;

    @Inject
    GameEngine gameEngine;
    @Inject
    AssetHolder assetHolder;
    @Inject
    ExtendViewport extendViewport;

    public MainMenuScreen() {
        Game.daggerMainComponent.inject(this);
        camera = new OrthographicCamera();
        camera.position.setZero();
        stage = new Stage(new ExtendViewport(1920, 1080, camera));
        //camera.setToOrtho(false, stage.getViewport().getScreenWidth(), stage.getViewport().getScreenHeight());
    }

    @Override
    public void show() {
        System.out.println("MAIN MENU");
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(assetHolder.getAsset("ui/skin/default/skin/uiskin.atlas"));
        this.skin.load(Gdx.files.internal("ui/skin/default/skin/uiskin.json"));
        this.skin.add("default-font", assetHolder.getFont24());

        this.skin.get(TextButton.TextButtonStyle.class).font = assetHolder.getFont24();
        this.skin.get(Window.WindowStyle.class).titleFont = assetHolder.getFont24();
        this.skin.get(Label.LabelStyle.class).font = assetHolder.getFont24();

        SimpleMessageWindow simpleMessageWindow = new SimpleMessageWindow(skin);
        simpleMessageWindow.setMovable(true);
        simpleMessageWindow.setVisible(false);
        simpleMessageWindow.setPosition(stage.getWidth() / 2 - simpleMessageWindow.getWidth(), 0);

        showMessageMediator = new SimpleMessageShower(simpleMessageWindow);

        gameEngine.setMessageMediator(showMessageMediator);

        Label label = new Label("У входа в Культурный клуб феменизма", skin);

        textButton = new TextButton("Plвввay", skin);

        textButton.setPosition(0, 0);
        textButton.setSize(100, 50);
        textButton.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));

        stage.addActor(label);

        Table table = new Table();
        table.setFillParent(true);

//        Image title = new Image(new Texture(Gdx.files.internal("map/dog.png")));
//        TextButton newGameButton = new TextButton("New Game", skin);
//        TextButton loadGameButton = new TextButton("Load Game", skin);
//        TextButton watchIntroButton = new TextButton("Watch Intro", skin);
//        TextButton creditsButton = new TextButton("Credits", skin);
//        TextButton exitButton = new TextButton("Exit", skin);
//
//
//        //Layout
//        table.add(title).spaceBottom(75).row();
//        table.add(newGameButton).spaceBottom(10).row();
//        table.add(loadGameButton).spaceBottom(10).row();
//        table.add(watchIntroButton).spaceBottom(10).row();
//        table.add(creditsButton).spaceBottom(10).row();
//        table.add(exitButton).spaceBottom(10).row();

        stage.addActor(table);

        List<String> listItems = new List<>(skin);

        listItems.setItems("Hello", "Goodbye");
        listItems.setSelectedIndex(-1);

        ScrollPane scrollPane = new ScrollPane(listItems, skin);
        scrollPane.setOverscroll(false, false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setForceScroll(true, false);
        scrollPane.setScrollBarPositions(false, true);

//        listItems.addListener(new ClickListener() {
//                                  @Override
//                                  public void clicked(InputEvent event, float x, float y) {
//                                      String choice = listItems.getSelected();
//                                      someWindow.getTitleLabel().setText(choice);
//                                      listItems.setSelectedIndex(-1);
//                                  }
//                              }
//        );
//
//        someWindow.row();
//
//        someWindow.add(scrollPane);

        stage.addActor(simpleMessageWindow);
        Multiflexer.getInstance().addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        gameEngine.update(delta);
        update(delta);

        stage.draw();
    }

    private void update(float delta) {
        stage.act(delta);
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
        assetHolder.dispose();
    }

}
