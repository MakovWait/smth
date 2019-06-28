package by.mkwt.engine.component.audio;

import by.mkwt.tiled.marker.MapPropertyComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ShootSoundComponent implements Json.Serializable, Component, MapPropertyComponent {
    public String soundPath;

    public Sound sound;

    @Override
    public void write(Json json) {
        json.writeValue("sound", soundPath);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        this.soundPath = jsonData.getString("sound");
        this.sound = Gdx.audio.newSound(Gdx.files.internal(soundPath));
    }

    public Sound getSound() {
        return sound;
    }
}
