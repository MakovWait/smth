package by.mkwt.engine.ecs.component.graphic;

import box2dLight.PointLight;
import by.mkwt.engine.tiled.marker.MapPropertyComponent;
import com.badlogic.ashley.core.Component;

public class PointLightComponent implements Component, MapPropertyComponent {

    public PointLight pointLight;

}
