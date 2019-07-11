package by.mkwt.engine.component.graphic;

import by.mkwt.tiled.marker.MapPropertyComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class SpriteShakeComponent implements Component, MapPropertyComponent {

    public float shakeForce = 0;


}
