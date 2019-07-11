package by.mkwt.engine;

import by.mkwt.engine.dagger.Injector;
import by.mkwt.engine.disposer.Disposer;
import by.mkwt.engine.ecs.system.render.LightingSystem;
import by.mkwt.engine.screen.AbstractScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import javax.inject.Inject;

public class GameContext extends Game {

    public static Injector injector;

    private Class<? extends AbstractScreen> startScreen;
    private Disposer disposer;

    @Inject
    PooledEngine engine;

    @Inject
    World world;

    public GameContext(Class<? extends AbstractScreen> startScreen, Disposer disposer, Injector injector) {
        this.startScreen = startScreen;
        this.disposer = disposer;
        this.injector = injector;
    }

    @Override
    public void create() {
        injector.inject(this);
        disposer.init();

        try {
            setScreen((Screen) ClassReflection.getConstructor(startScreen, this.getClass()).newInstance(this));
        } catch (ReflectionException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Could not find " + startScreen);
        }
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen instanceof AbstractScreen) {
            ((AbstractScreen) screen).setPreviousScreen(getScreen());
        }

        super.setScreen(screen);
    }

    @Override
    public void dispose() {
        //super.dispose();
        disposer.dispose();
    }

}
