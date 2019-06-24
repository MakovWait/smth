package by.mkwt.engine.component.graphic;

import box2dLight.PointLight;
import by.mkwt.tiled.marker.MapPropertyComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class PointLightComponent implements Component, MapPropertyComponent {

    public PointLight pointLight;

}
