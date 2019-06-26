package by.mkwt.engine.component.graphic;

import by.mkwt.tiled.marker.MapPropertyComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class StepSoundComponent implements Component, MapPropertyComponent, Json.Serializable {
    private final static String STEP_SOUND_PATH = "step.wav";

    public Sound sound = Gdx.audio.newSound(Gdx.files.internal(STEP_SOUND_PATH));

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
