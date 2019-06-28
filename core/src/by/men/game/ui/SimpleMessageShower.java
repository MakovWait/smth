package by.men.game.ui;


import by.mkwt.provider.ShowMessageMediator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
public class SimpleMessageShower implements ShowMessageMediator {

    private SimpleMessageWindow window;

    public SimpleMessageShower(SimpleMessageWindow window) {
        this.window = window;
    }

    @Override
    public void showMessage(SimpleMessage message) {
        if (!window.isVisible()) {
            window.setMessage(message);
            window.setVisible(true);
            window.addAction(sequence(alpha(0), fadeIn(0.5f, Interpolation.fade)));
        }
    }

    @Override
    public void hideMessage() {
        window.reset();
        window.setVisible(false);
    }

}
