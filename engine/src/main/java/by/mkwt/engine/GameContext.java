package by.mkwt;

import by.mkwt.screen.AbstractScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class GameContext extends Game {

    private Class<? extends AbstractScreen> startScreen;

    public GameContext(Class<? extends AbstractScreen> startScreen) {
        this.startScreen = startScreen;
    }

    @Override
    public void create() {
        try {
            setScreen((Screen) ClassReflection.getConstructor(startScreen, this.getClass()).newInstance(this));
        } catch (ReflectionException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Could not find " + startScreen);
        }
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    @Override
    public void dispose() {
        //super.dispose();
    }

}
