package by.mkwt.engine.component.interactive;

import by.mkwt.tiled.marker.MapPropertyComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class MessageComponent implements Component, MapPropertyComponent, Json.Serializable {

    public float radius;
    public String msg;

    public MessageComponent() {}

    public MessageComponent(float radius, String msg) {
        this.radius = radius;
        this.msg = msg;
    }

    @Override
    public void write(Json json) {
        json.writeValue("radius", radius);
        json.writeValue("msg", msg);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        radius = jsonData.getFloat("radius");
        msg = jsonData.getString("msg");
    }

}
