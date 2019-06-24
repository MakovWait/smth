package by.mkwt.engine.component.graphic;

import by.mkwt.tiled.marker.MapPropertyComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class SpriteShakeComponent implements Component, Json.Serializable, MapPropertyComponent {

    public float shakeForce = 0;

    @Override
    public void write(Json json) {
        json.writeValue("shakeForce", shakeForce);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        shakeForce = jsonData.getFloat("shakeForce");
    }
}
