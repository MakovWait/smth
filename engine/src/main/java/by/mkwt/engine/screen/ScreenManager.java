package by.mkwt.engine.screen;

import com.badlogic.gdx.utils.OrderedMap;

public abstract class ScreenManager {

    OrderedMap<Class<? extends AbstractScreen>, AbstractScreen> screens = new OrderedMap<>();

    public ScreenManager() {
        initScreens();
    }

    protected abstract void initScreens();

    AbstractScreen getCurrentScreen() {
        return screens.get(screens.orderedKeys().peek());
    }

}
