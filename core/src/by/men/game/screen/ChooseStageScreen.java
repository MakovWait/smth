package by.men.game.screen;

import by.men.game.dagger.Injector;
import by.men.game.resource.AssetHolder;
import by.men.game.stage.StageLoader;
import by.mkwt.engine.GameContext;
import by.mkwt.engine.screen.AbstractScreen;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class ChooseStageScreen extends AbstractScreen {

    @Inject
    AssetHolder assetHolder;

    @Inject
    StageLoader stageLoader;

    private Table table;
    private TextButton backBtn;

    public ChooseStageScreen(GameContext gameContext) {
        super(gameContext);
    }

    @Override
    protected void initStage() {
        Injector.MAIN_COMPONENT.inject(this);

        stageLoader.addStage("map/club-outside.tmx");

        camera.position.setZero();
        stage = new Stage(extendViewport);
    }

    @Override
    protected void fillStage() {
        table = new Table();
        table.setFillParent(true);

        if (getPreviousScreen() != null) {
            backBtn = new TextButton("Back", assetHolder.getSkin(), "default");
            backBtn.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
            backBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameContext.setScreen(getPreviousScreen());
                }
            });
            table.add(backBtn).spaceBottom(10).width(300).row();
        }

        //Layout
        table.pack();

        stage.addActor(table);
    }

}
