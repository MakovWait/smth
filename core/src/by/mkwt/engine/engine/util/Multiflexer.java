package by.mkwt.engine.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class Multiflexer {

    private static Multiflexer instance;

    private InputMultiplexer multiplexer;

    private Multiflexer() {
        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void addProcessor(InputProcessor inputProcessor) {
        multiplexer.addProcessor(inputProcessor);
    }

    public void removeProcessor(InputProcessor inputProcessor) {
        multiplexer.removeProcessor(inputProcessor);
    }

    public static Multiflexer getInstance() {
        if (instance == null) {
            instance = new Multiflexer();
        }

        return instance;
    }

}
