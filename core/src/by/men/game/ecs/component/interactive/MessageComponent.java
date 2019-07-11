package by.men.game.ecs.component.interactive;

import by.mkwt.engine.tiled.marker.MapPropertyComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class MessageComponent implements Component, MapPropertyComponent, Json.Serializable {

    public float radius;
    public String title;
    public Array<String> msg = new Array<>();
    public boolean isShowed = false;

    public MessageComponent() {

    }

    public MessageComponent(String title, float radius, String... msg) {
        this.title = title;
        this.radius = radius;
        this.msg.addAll(msg);
    }

    @Override
    public void write(Json json) {
        json.writeValue("radius", radius);
        json.writeValue("title", title);
//
        json.writeArrayStart("msg");
        msg.forEach(json::writeValue);
        json.writeArrayEnd();

//        json.writeObjectStart();
//        json.writeValue("msg", msg, Array.class, String.class);
//        json.writeObjectEnd();
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        radius = jsonData.getFloat("radius");
        msg.addAll(jsonData.get("msg").asStringArray());
        title = jsonData.getString("title");
    }

}
