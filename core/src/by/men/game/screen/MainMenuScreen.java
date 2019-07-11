package by.men.game.screen;


import by.men.game.dagger.Injector;
import by.men.game.resource.AssetHolder;
import by.mkwt.engine.GameContext;
import by.mkwt.engine.screen.AbstractScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenuScreen extends AbstractScreen {

    @Inject
    AssetHolder assetHolder;

    private Table table;
    private TextButton buttonPlay, chooseGameStageButton, buttonExit;

    public MainMenuScreen(GameContext gameContext) {
        super(gameContext);
    }

    @Override
    protected void initStage() {
        Injector.MAIN_COMPONENT.inject(this);

        camera.position.setZero();
        stage = new Stage(extendViewport);
    }

    @Override
    protected void fillStage() {
        table = new Table();
        table.setFillParent(true);

        buttonPlay = new TextButton("Play", assetHolder.getSkin(), "default");
        buttonPlay.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //gameContext.setScreen(game.getPlayScreen());
            }
        });

        chooseGameStageButton = new TextButton("Choose game stage", assetHolder.getSkin(), "default");
        chooseGameStageButton.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        chooseGameStageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(game.getChooseGameStageScreen());
                gameContext.setScreen(new ChooseStageScreen(gameContext));
            }
        });

        buttonExit = new TextButton("Exit", assetHolder.getSkin(), "default");
        buttonExit.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Layout
        table.add(buttonPlay).spaceBottom(10).width(300).row();
        table.add(chooseGameStageButton).spaceBottom(10).width(300).row();
        table.add(buttonExit).spaceBottom(10).width(300).row();
        table.pack();

        stage.addActor(table);
    }

}
